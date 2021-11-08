package Libreria.demo.controladores;

import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.servicios.AutorServicio;
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

}
