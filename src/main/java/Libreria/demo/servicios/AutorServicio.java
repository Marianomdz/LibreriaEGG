package Libreria.demo.servicios;

import Libreria.demo.entidades.Autor;
import Libreria.demo.entidades.Libro;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.repositorios.AutorRepositorio;
import Libreria.demo.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author Mariano_mdz
 */

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    public LibroRepositorio libroRepositorio;
    
    @Autowired
    public LibroServicio libroServicio;
    
    @Transactional
    public void registrarAutor(String nombre, Boolean alta)throws ErrorServicio{
    
        if(nombre==null || nombre.isEmpty()){
            
            throw new ErrorServicio("Nombre Autor no puede estar Vacío");
        }
        //Optional<Autor> respuesta = autorRepositorio.findById(id);
       
        //if(respuesta.isPresent()){
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
        
        autorRepositorio.save(autor);
        
        //} else {
        //    throw new ErrorServicio("No se ecnuentra el autor con el identificador indicado");
       // }
    }
    
    @Transactional
    public void editarAutor(String id, String nombre, Boolean alta)throws ErrorServicio{
          
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
                               
        } else {
     
            throw new ErrorServicio("No se ecuentra el autor con el identificador indicado");
        }
                
    }     
    
    //@Transactional(readOnly = true)    
    public List <Autor> ListarAutores(){
//    return autorRepositorio.findAll();
    return autorRepositorio.buscarPorAlta();
    
     }
    
    public void ListarAutoresporAlta(){
    autorRepositorio.buscarPorAlta();
     }
    
    //@Transactional(readOnly = true)
    public void ListarAutoresporNombre(String nombre){
    autorRepositorio.buscarPorNombre(nombre);
     }
    

    public Optional<Autor> BuscarAutoresPorId(String id){
    return autorRepositorio.findById(id);
     }

    
    public void BajaAutor(String id, String nombre, Boolean alta)throws ErrorServicio{
    String id_libro;
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        List<Libro> respuestaLibro = libroRepositorio.buscarPorAutor(id);
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setAlta(false);
            autorRepositorio.save(autor);
            for (Libro libro : respuestaLibro) {
                libro.setAlta(false);
                id_libro = libro.getId();
                libroServicio.eliminarLibro(id_libro);
            }
            
            
        } else {
            throw new ErrorServicio("No se ecnuentra el autor con el identificador indicado");
        }
        
        
    }     
}
