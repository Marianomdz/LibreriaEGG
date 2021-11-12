package Libreria.demo.controladores;

import Libreria.demo.entidades.Autor;
import Libreria.demo.entidades.Editorial;
import Libreria.demo.entidades.Libro;
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

//@GetMapping("/editarlibro")
//public String editarlibro(){
//return "editarlibro.html";
//}

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

@GetMapping("/listarlibro")
public String listarlibro(ModelMap model){
    
    List<Libro> libros = libroservicio.ListarLibros();
    model.put("Libros", libros);
    
return "listarlibro.html";
}    

@GetMapping("/editarlibro")
public String editarlibro(ModelMap modelo, @RequestParam String id) {
    
        Optional<Libro> libro_id = libroservicio.BuscarLibrosPorId(id);
        
        List<Editorial> editoriales = editorialrepositorio.findAll();
        modelo.put("editoriales", editoriales);
    
        List<Autor> autores = autorrespositorio.findAll();
        modelo.put("autores", autores);
        
        //model.addAttribute("autor",autor_id);
        modelo.put("id", libro_id.get().getId());
        modelo.put("titulo", libro_id.get().getTitulo());
        modelo.put("alta", libro_id.get().isAlta());
        modelo.put("anio", libro_id.get().getAnio());
        modelo.put("ejemplares", libro_id.get().getEjemplares());
        modelo.put("ejemplaresPrestados", libro_id.get().getEjemplaresPrestados());
        modelo.put("ejemplaresRestantes", libro_id.get().getEjemplaresRestantes());
        modelo.put("isbn", libro_id.get().getIsbn());
        modelo.put("autor_id", libro_id.get().getAutor().getId());
        modelo.put("autor_id_nombre", libro_id.get().getAutor().getNombre());
        modelo.put("editorial_id", libro_id.get().getEditorial().getNombre());
        
        
            
return "editarlibro.html";
}  


@PostMapping("/editarlibroguardar")
public String editarlibroguardar(ModelMap modelo, @RequestParam String id, @RequestParam double isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes, @RequestParam boolean alta, @RequestParam Editorial editorial, @RequestParam Autor autor  ){

    List<Libro> libros = libroservicio.ListarLibros();
    modelo.put("Libros", libros);  
    
        try {
            
        libroservicio.editarLibro(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, alta, editorial, autor);
        } catch (ErrorServicio ex) {
            //Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("titulo", titulo);
            return "editarlibro.html";
        }
    
       
return "listarlibro.html";
}

//
//@GetMapping("/eliminarlibro")
//public String eliminarlibro(ModelMap modelo, @RequestParam String id, @RequestParam double isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes, @RequestParam boolean alta, @RequestParam Editorial editorial, @RequestParam Autor autor  ){
//    
//   
//    
//        Optional<Libro> libro_id = libroservicio.BuscarLibrosPorId(id);
//        //model.addAttribute("autor",autor_id);
//        modelo.put("id", libro_id.get().getId());
//        modelo.put("nombre", libro_id.get().getTitulo());
//        
//     
//     
//            
//return "eliminarlibro.html";
//}   


@GetMapping("/eliminarlibroguardar")
//public String eliminarlibroguardar(ModelMap modelo, @RequestParam String id, @RequestParam double isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes, @RequestParam boolean alta, @RequestParam Editorial editorial, @RequestParam Autor autor  ){
public String eliminarlibroguardar(ModelMap modelo, @RequestParam String id){
    List<Libro> libros = libroservicio.ListarLibros();
    modelo.put("Libros", libros);  
    
        try {

            libroservicio.eliminarLibro(id);
    
        } catch (ErrorServicio ex) {
            //Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("id", id);
            return "eliminarlibro.html";
        }
    
       
return "listarlibro.html";
}


@GetMapping("/recuperarlibroguardar")

public String recuperarlibroguardar(ModelMap modelo, @RequestParam String id){
    List<Libro> libros = libroservicio.ListarLibros();
    modelo.put("Libros", libros);  
    
        try {

            libroservicio.recuperarLibro(id);
    
        } catch (ErrorServicio ex) {
            //Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("id", id);
            return "eliminarlibro.html";
        }
    
       
return "listarlibro.html";
}

}

