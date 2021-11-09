package Libreria.demo.repositorios;

import Libreria.demo.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 * @author Mariano_mdz
 */

@Repository
public interface AutorRepositorio extends JpaRepository <Autor, String> {
    
    @Query("Select  c from Autor c where c.nombre = :nombre")
    public Autor buscarPorNombre(@Param("nombre") String nombre);
    
    
     @Query("Select  c from Autor c where c.alta = 1")
    public List<Autor> buscarPorAlta();
 
}
