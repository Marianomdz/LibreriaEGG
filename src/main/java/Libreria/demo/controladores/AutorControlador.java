package Libreria.demo.controladores;

import Libreria.demo.entidades.Autor;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.servicios.AutorServicio;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorservicio;
    
@GetMapping("/crearautor")
public String crearautor(){
return "crearautor.html";
}    

@PostMapping("/guardar")
public String guardar(ModelMap modelo, @RequestParam String nombreAutor){
    
        try {
            autorservicio.registrarAutor(nombreAutor, Boolean.FALSE);
        } catch (ErrorServicio ex) {
           modelo.put("error", ex.getMessage());
           modelo.put("nombreAutor", nombreAutor);
           return "crearautor.html";
        }
    
return "crearautor.html";
}

@GetMapping("/listarautor")
public String listarautor(ModelMap model){
    
    List<Autor> autores = autorservicio.ListarAutores();
    model.put("Autores", autores);
    
return "listarautor.html";
}    


@GetMapping("/editarautor")
public String editarautor(ModelMap model, @RequestParam String id) {
    
        Optional<Autor> autor_id = autorservicio.BuscarAutoresPorId(id);
        //model.addAttribute("autor",autor_id);
        model.put("id", autor_id.get().getId());
        model.put("nombre", autor_id.get().getNombre());
       
            
return "editarautor.html";
}  


@PostMapping("/editarautorguardar")
public String editarautorguardar(ModelMap modelo, @RequestParam String id, @RequestParam String nombreAutor){

    List<Autor> autores = autorservicio.ListarAutores();
    modelo.put("Autores", autores);  
    
        try {
            autorservicio.editarAutor(id, nombreAutor, Boolean.FALSE);
        } catch (ErrorServicio ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombreAutor);
            return "editarautor.html";
        }
    
       
return "listarautor.html";
}
}