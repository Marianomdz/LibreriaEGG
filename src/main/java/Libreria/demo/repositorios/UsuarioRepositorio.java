package Libreria.demo.repositorios;


import Libreria.demo.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



/*
 * @author Mariano_mdz
 */

@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, String> {
    
    @Query("Select  c from Usuario c where c.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
    
     @Query("Select  c from Usuario c where c.alta = 1")
    public List<Usuario> buscarPorAlta();
 
}
