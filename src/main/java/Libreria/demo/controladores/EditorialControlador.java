package Libreria.demo.controladores;

import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.servicios.EditorialServicio;
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
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    public EditorialServicio editorialservicio;
    
@GetMapping("/creareditorial")
public String creareditorial(){
return "creareditorial.html";
}

@PostMapping("/guardar")
public String guardar(ModelMap modelo, @RequestParam String nombreEditorial){
        try {
            editorialservicio.registrarEditorial(nombreEditorial, Boolean.TRUE);
        } catch (ErrorServicio ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("nombreEditorial", nombreEditorial);
            return "creareditorial.html";
        }
    
return "creareditorial.html";
}

}
