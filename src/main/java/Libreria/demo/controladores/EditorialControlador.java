package Libreria.demo.controladores;

import Libreria.demo.entidades.Editorial;
import Libreria.demo.entidades.Libro;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.repositorios.LibroRepositorio;
import Libreria.demo.servicios.EditorialServicio;
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
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    public EditorialServicio editorialservicio;
    
    @Autowired
    public LibroRepositorio librorepositorio;
    
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

@GetMapping("/listareditorial")
public String listareditorial(ModelMap model){
    
    List<Editorial> editoriales = editorialservicio.ListarEditoriales();
    model.put("Editoriales", editoriales);
    
return "listareditorial.html";
}    

@GetMapping("/editareditorial")
public String editareditorial(ModelMap model, @RequestParam String id) {
    
        Optional<Editorial> editorial_id = editorialservicio.BuscarEditorialPorId(id);
        //model.addAttribute("autor",autor_id);
        model.put("id", editorial_id.get().getId());
        model.put("nombre", editorial_id.get().getNombre());
       
            
return "editareditorial.html";
}


@PostMapping("/editareditorialguardar")
public String editareditorialguardar(ModelMap modelo, @RequestParam String id, @RequestParam String nombreEditorial){

    List<Editorial> editoriales = editorialservicio.ListarEditoriales();
    modelo.put("Editoriales", editoriales);  
    
        try {
            editorialservicio.editarEditorial(id, nombreEditorial, Boolean.FALSE);
    
        } catch (ErrorServicio ex) {
            //Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombreEditorial);
            return "editareditorial.html";
        }
    
       
return "listareditorial.html";
}


@GetMapping("/eliminareditorial")
public String eliminareditorial(ModelMap modelo, @RequestParam String id) {
    
   
    
        Optional<Editorial> editorial_id = editorialservicio.BuscarEditorialPorId(id);
        
        modelo.put("id", editorial_id.get().getId());
        modelo.put("nombre", editorial_id.get().getNombre());
        
     List<Libro> libros = librorepositorio.buscarPorEditorial(id);   
     modelo.put("libros", libros);
     
            
return "eliminareditorial.html";
}   
}
