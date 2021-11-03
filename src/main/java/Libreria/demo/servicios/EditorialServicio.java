/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.demo.servicios;

import Libreria.demo.entidades.Editorial;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.repositorios.EditorialRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mariano_mdz
 */

@Service
public class EditorialServicio {
     private EditorialRepositorio editorialRepositorio;
    
     @Transactional
    public void registrarEditorial(String nombre, Boolean alta)throws ErrorServicio{
    
    if(nombre==null || nombre.isEmpty()){
            
            throw new ErrorServicio("Nombre Editorial no puede estar Vacío");
        }
       
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        editorialRepositorio.save(editorial);
}
    
    @Transactional
    public void editarEditorial(String id, String nombre, Boolean alta) throws ErrorServicio{
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
                               
        } else {
            throw new ErrorServicio("No se ecnuentra la Editorial con el identificador indicado");
        }
        
    }
    
    
    public void ListarEditoriales(){
    editorialRepositorio.findAll();
     }
}