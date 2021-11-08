package Libreria.demo.controladores;

import Libreria.demo.entidades.Autor;
import Libreria.demo.entidades.Editorial;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.repositorios.AutorRepositorio;
import Libreria.demo.repositorios.EditorialRepositorio;
import Libreria.demo.servicios.LibroServicio;
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
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    public LibroServicio libroservicio;
    
    @Autowired
    public AutorRepositorio autorrespositorio;
    
    @Autowired
    public EditorialRepositorio editorialrepositorio;
    
    
@GetMapping("/")
public String index(){
return "index.html";
}

    @GetMapping("/crearlibro")
public String crearlibro(ModelMap modelo){

    List<Editorial> editoriales = editorialrepositorio.findAll();
    modelo.put("editoriales", editoriales);
    
    List<Autor> autores = autorrespositorio.findAll();
    modelo.put("autores", autores);
     
    return "crearlibro.html";

}

@GetMapping("/elimarlibro")
public String elimarlibro(){
return "elimarlibro.html";
}

@GetMapping("/editarlibro")
public String editarlibro(){
return "editarlibro.html";
}

    @PostMapping("/crearlibroguardar")
public String crearlibroguardar(ModelMap modelo, @RequestParam double isbnLibro, @RequestParam String tituloLibro, @RequestParam Integer anioLibro, @RequestParam Integer ejemplaresLibro, @RequestParam Integer ejemplaresLibroPres, @RequestParam Integer ejemplaresLibroRes, @RequestParam String editorialLibro, @RequestParam String autorLibro){

        try {
            libroservicio.registrarLibro(isbnLibro, tituloLibro, anioLibro, ejemplaresLibro, ejemplaresLibroRes, ejemplaresLibroRes, Boolean.FALSE, editorialLibro, autorLibro);
        
            System.out.println("");
        } catch (ErrorServicio ex) {
            
            modelo.put("error", ex.getMessage());
            
            
            List<Editorial> editoriales = editorialrepositorio.findAll();
    modelo.put("editoriales", editoriales);
    
    List<Autor> autores = autorrespositorio.findAll();
    modelo.put("autores", autores);
            return "crearlibro.html";
        }
    
        
    return "crearlibro.html";
}


}
