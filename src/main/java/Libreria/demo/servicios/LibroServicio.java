/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.demo.servicios;

import Libreria.demo.entidades.Autor;
import Libreria.demo.entidades.Editorial;
import Libreria.demo.entidades.Libro;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.repositorios.LibroRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mariano_mdz
 */
@Service
public class LibroServicio {

    private LibroRepositorio libroRepositorio;

    @Transactional
    public void registrarLibro(Double isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Editorial editorial, Autor autor) throws ErrorServicio {

        if (isbn == null || isbn.isNaN()) {

            throw new ErrorServicio("ISBN del Libro no puede estar Vacío");
        }
        if (titulo == null || titulo.isEmpty()) {

            throw new ErrorServicio("Título Libro no puede estar Vacío");
        }
        if (anio == null) {

            throw new ErrorServicio("Año no puede estar Vacío");
        }
        if (ejemplares == null) {

            throw new ErrorServicio("Ejemplares no puede estar Vacío");
        }
        if (ejemplaresPrestados == null) {

            throw new ErrorServicio("Ejemplares Prestados no puede estar Vacío");
        }
        if (ejemplaresRestantes == null) {

            throw new ErrorServicio("Ejemplares restantes no puede estar Vacío");
        }
        if (editorial == null) {

            throw new ErrorServicio("Editorial no puede estar Vacío");
        }
        if (autor == null) {

            throw new ErrorServicio("Autor no puede estar Vacío");
        }

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);
        libro.setAlta(true);
        libro.setEditorial(editorial);
        libro.setAutor(autor);

        libroRepositorio.save(libro);
        
        
    }
    
    @Transactional
    public void editarLibro(String id, Double isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Editorial editorial, Autor autor)throws ErrorServicio{
    Optional<Libro> respuesta = libroRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            if(libro.getAutor().getId().equals(id)){
                libro.setAutor(autor);
                libro.setEjemplares(ejemplares);
                libro.setEjemplaresPrestados(ejemplaresPrestados);
                libro.setEjemplaresRestantes(ejemplaresRestantes);
                libro.setIsbn(isbn);
                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libroRepositorio.save(libro);
            }else{
                throw new ErrorServicio("No tiene permisos suficientes para realizar la operación");
            }
            }else{
                throw new ErrorServicio("No existe un libro con el identificador indicado");
            }
                    
        }
    
    @Transactional
    public void eliminarLibro(String id) throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            if(libro.getId().equals(id)){
                libro.setAlta(false);
                libroRepositorio.save(libro);
                
            }
        } else {
            throw new ErrorServicio("No existe un Libro con el identificador indicado");
            
        }
    }
    
}
