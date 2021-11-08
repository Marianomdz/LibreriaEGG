package Libreria.demo.servicios;

import Libreria.demo.entidades.Autor;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.repositorios.AutorRepositorio;
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
            throw new ErrorServicio("No se ecnuentra el autor con el identificador indicado");
        }
                
    }     
    
    //@Transactional(readOnly = true)    
    public void ListarAutores(){
    autorRepositorio.findAll();
     }
    
    //@Transactional(readOnly = true)
    public void ListarAutoresporNombre(String nombre){
    autorRepositorio.buscarPorNombre(nombre);
     }
    
    
    public void BajaAutor(String id, String nombre, Boolean alta)throws ErrorServicio{
    
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setAlta(false);
            autorRepositorio.save(autor);
                               
        } else {
            throw new ErrorServicio("No se ecnuentra el autor con el identificador indicado");
        }
        
        
    }     
}
