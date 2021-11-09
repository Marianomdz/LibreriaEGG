package Libreria.demo.repositorios;

import Libreria.demo.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 * @author Mariano_mdz 
 */

@Repository
public interface EditorialRepositorio extends JpaRepository <Editorial, String> {
    
    @Query("Select c from Editorial c where c.nombre = :nombre")
    public Editorial buscarPorNombre(@Param("nombre") String nombre);

    @Query("Select  c from Editorial c where c.alta = 1")
    public List<Editorial> buscarPorAlta();
}
