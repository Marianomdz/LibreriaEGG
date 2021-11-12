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
import Libreria.demo.repositorios.AutorRepositorio;
import Libreria.demo.repositorios.EditorialRepositorio;
import Libreria.demo.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mariano_mdz
 */
@Service
public class LibroServicio {

    @Autowired
    public LibroRepositorio libroRepositorio;
    
    @Autowired
    public AutorRepositorio autorrepositorio;
    
    @Autowired
    public EditorialRepositorio editorialrepositorio;

    @Transactional
    public void registrarLibro(double isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, String editorialLibro, String autorLibro) throws ErrorServicio {

        
        Editorial editorial1 = editorialrepositorio.getOne(editorialLibro);
        Autor autor1 = autorrepositorio.getOne(autorLibro);
        
        if (editorial1==null){
            throw new ErrorServicio("No se encontro la editorial");
        }

        if (autor1==null){
            throw new ErrorServicio("No se encontro la autor1");
        }        
//        if (isbn == null || isbn.isNaN()) {
//
//            throw new ErrorServicio("ISBN del Libro no puede estar Vacío");
//        }
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
//        if (editorial == null) {
//
//            throw new ErrorServicio("Editorial no puede estar Vacío");
//        }
//        if (autor == null) {
//
//            throw new ErrorServicio("Autor no puede estar Vacío");
//        }

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);
        libro.setAlta(true);
        libro.setEditorial(editorial1);
        libro.setAutor(autor1);

        libroRepositorio.save(libro);

    }

    @Transactional
    public void editarLibro(String id, Double isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Editorial editorial_id, Autor autor_id) throws ErrorServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            if (libro.getAutor().getId().equals(id)) {
                libro.setAutor(autor_id);
                libro.setEjemplares(ejemplares);
                libro.setEjemplaresPrestados(ejemplaresPrestados);
                libro.setEjemplaresRestantes(ejemplaresRestantes);
                libro.setIsbn(isbn);
                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libroRepositorio.save(libro);
            } else {
                throw new ErrorServicio("No tiene permisos suficientes para realizar la operación");
            }
        } else {
            throw new ErrorServicio("No existe un libro con el identificador indicado");
        }

    }

    @Transactional
    public void eliminarLibro(String id) throws ErrorServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            if (libro.getId().equals(id)) {
                libro.setAlta(false);
                libroRepositorio.save(libro);

            }
        } else {
            throw new ErrorServicio("No existe un Libro con el identificador indicado");

        }
    }

    
    @Transactional
    public void recuperarLibro(String id) throws ErrorServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            if (libro.getId().equals(id)) {
                libro.setAlta(true);
                libroRepositorio.save(libro);

            }
        } else {
            throw new ErrorServicio("No existe un Libro con el identificador indicado");

        }
    }
    
    
    public List<Libro> ListarLibros() {
        return libroRepositorio.findAll();
    }
    
    public List<Libro> ListarLibrosAlta() {
        return libroRepositorio.ListarLibrosAlta();
    }

    public void ListarLibrosporTitulo(String titulo) {
        libroRepositorio.buscarPorTitulo(titulo);
    }
    
     public Optional<Libro> BuscarLibrosPorId(String id){
    return libroRepositorio.findById(id);
     }
}
