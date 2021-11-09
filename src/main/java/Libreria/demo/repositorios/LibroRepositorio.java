package Libreria.demo.repositorios;

import Libreria.demo.entidades.Editorial;
import Libreria.demo.entidades.Libro;
import java.util.List;
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
    
    
    @Query("Select c from Libro c where c.autor.id = :autor_id")
    List<Libro> buscarPorAutor(@Param("autor_id") String autor_id);
    
    @Query("Select c from Libro c where c.editorial.id = :editorial_id")
    List<Libro> buscarPorEditorial(@Param("editorial_id") String editorial_id);
    
}
