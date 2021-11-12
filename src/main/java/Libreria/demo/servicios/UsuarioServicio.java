package Libreria.demo.servicios;

import Libreria.demo.entidades.Usuario;
import Libreria.demo.errores.ErrorServicio;
import Libreria.demo.repositorios.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * @author Mariano_mdz
 */
@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrarUsuario(String nombre, String email, String password, String password2) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {

            throw new ErrorServicio("Nombre Autor no puede estar Vacío");
        }

        if (email == null || email.isEmpty()) {

            throw new ErrorServicio("Email no puede estar Vacío");
        }

        if (password == null || password.isEmpty()) {

            throw new ErrorServicio("Password no puede estar Vacío");
        }

        if (password2 == null || password2.isEmpty() || !password.equals(password2)) {

            throw new ErrorServicio("Repetir Password no puede estar Vacío y deben ser iguales");
        }
        if (usuarioRepositorio.buscarPorEmail(email) != null) {
            throw new ErrorServicio("El nombre de usuario ya existe");
        }

        //Optional<Autor> respuesta = autorRepositorio.findById(id);
        //if(respuesta.isPresent()){
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);

        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        //usuario.setPassword(password);
        usuario.setAlta(true);

        usuarioRepositorio.save(usuario);

    }

    @Transactional
    public boolean loguearUsuario(String email, String password) throws ErrorServicio {
        
        Usuario usuarioBuscado = usuarioRepositorio.buscarPorEmail(email);
        

        if (usuarioBuscado != null) {

            //if (usuarioBuscado.getPassword().equals(encriptada)) {
            if (new BCryptPasswordEncoder().matches(password,usuarioBuscado.getPassword())) {
                return true;
            } else {
                throw new ErrorServicio("Nombre de Usuario o Password Incorrecta!!");
            }

        } else {
            throw new ErrorServicio("El usuario no existe!!");
        }
        //return true;

    }

    public List<Usuario> ListarUsuarios() {
        return usuarioRepositorio.findAll();
        // return autorRepositorio.buscarPorAlta();

    }

//    @Transactional
//    public void editarAutor(String id, String nombre, Boolean alta)throws ErrorServicio{
//          
//        Optional<Autor> respuesta = autorRepositorio.findById(id);
//        
//        if(respuesta.isPresent()){
//            Autor autor = respuesta.get();
//            autor.setNombre(nombre);
//            autorRepositorio.save(autor);
//                               
//        } else {
//     
//            throw new ErrorServicio("No se ecuentra el autor con el identificador indicado");
//        }
//                
//    }     
//    
//    //@Transactional(readOnly = true)    
//    public List <Autor> ListarAutores(){
////    return autorRepositorio.findAll();
//    return autorRepositorio.buscarPorAlta();
//    
//     }
//    
//    public void ListarAutoresporAlta(){
//    autorRepositorio.buscarPorAlta();
//     }
//    
//    //@Transactional(readOnly = true)
//    public void ListarAutoresporNombre(String nombre){
//    autorRepositorio.buscarPorNombre(nombre);
//     }
//    
//
//    public Optional<Autor> BuscarAutoresPorId(String id){
//    return autorRepositorio.findById(id);
//     }
//
//    
//    public void BajaAutor(String id, String nombre, Boolean alta)throws ErrorServicio{
//    String id_libro;
//        Optional<Autor> respuesta = autorRepositorio.findById(id);
//        List<Libro> respuestaLibro = libroRepositorio.buscarPorAutor(id);
//        if(respuesta.isPresent()){
//            Autor autor = respuesta.get();
//            autor.setAlta(false);
//            autorRepositorio.save(autor);
//            for (Libro libro : respuestaLibro) {
//                libro.setAlta(false);
//                id_libro = libro.getId();
//                libroServicio.eliminarLibro(id_libro);
//            }
//            
//            
//        } else {
//            throw new ErrorServicio("No se ecnuentra el autor con el identificador indicado");
//        }
//        
//        
//    }     
}
