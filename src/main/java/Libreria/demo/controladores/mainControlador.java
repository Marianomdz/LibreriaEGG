package Libreria.demo.controladores;

import Libreria.demo.entidades.Usuario;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.servicios.AutorServicio;
import Libreria.demo.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @author Mariano_mdz 
 */

@Controller
@RequestMapping("/")
public class mainControlador {
  
 @Autowired
    private UsuarioServicio usuarioservicio;    
    
 
@GetMapping("/index")
public String index(){
return "index.html";
}       

@GetMapping("/")
public String login(){
return "login.html";
}       

@PostMapping("/loguear")
public String loguear(ModelMap modelo, @RequestParam String emailUsuario, @RequestParam String passwordUsuario){
    
        try {
            usuarioservicio.loguearUsuario(emailUsuario, passwordUsuario);
        } catch (ErrorServicio ex) {
           modelo.put("error", ex.getMessage());
           modelo.put("emailUsuario", emailUsuario);
           return "login.html";
        }
    
return "index.html";
}

@GetMapping("usuario/crearusuario")
public String crearusuario(){
return "crearusuario.html";
}       

@PostMapping("usuario/guardar")
public String guardar(ModelMap modelo, @RequestParam String nombreUsuario, @RequestParam String emailUsuario, @RequestParam String passwordUsuario2, @RequestParam String passwordUsuario){
    
        try {
            usuarioservicio.registrarUsuario(nombreUsuario, emailUsuario, passwordUsuario, passwordUsuario2);
        } catch (ErrorServicio ex) {
           modelo.put("error", ex.getMessage());
           modelo.put("nombreAutor", nombreUsuario);
           return "crearusuario.html";
        }
    
return "login.html";
}

@GetMapping("usuario/listarusuario")
public String listarusuario(ModelMap model){
    
    List<Usuario> usuario = usuarioservicio.ListarUsuarios();
    model.put("Usuarios", usuario);
    
return "listarusuario.html";


}
}