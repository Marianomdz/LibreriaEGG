package Libreria.demo.repositorios;

import Libreria.demo.entidades.Editorial;
import Libreria.demo.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 * @author Mariano_mdz 
 */

@Repository
public interface LibroRepositorio extends JpaRepository <Libro, String>{

    @Query("Select c from Libro c where c.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo") String titulo);
}
