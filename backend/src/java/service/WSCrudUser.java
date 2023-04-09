package service;

import dao.DaoUser;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelUser;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSCrudUser")
public class WSCrudUser {

    DaoUser daoUser = new DaoUser();
    ModelUser modelUser = new ModelUser();

    //WEBMETHOD --> LISTAR USUARIOS
    @WebMethod(operationName = "listarUsuarios")
    public List<ModelUser> listarUsuarios() {
        List<ModelUser> lstUsers;
        lstUsers = daoUser.listar();
        return lstUsers;
    }

    //WEBMETHOD --> LISTAR USUARIO INDIVIDUAL A TRAVES DE SU ID
    @WebMethod(operationName = "obtenerUsuarioPorId")
    public ModelUser obtenerUsuarioPorId(@WebParam(name = "id") int id) {
        return daoUser.list(id);
    }

    //WEBMETHOD --> BUSCAR USUARIOS
    @WebMethod(operationName = "buscarUsuario")
    public List<ModelUser> buscarUsuario(String usuario) {
        List<ModelUser> lstUsers;
        lstUsers = daoUser.Search(usuario);
        return lstUsers;
    }

    //WEBMETHOD --> REGISTRAR USUARIO
    @WebMethod(operationName = "registrarUsuario")
    public boolean registrarUsuario(
            @WebParam(name = "Nombre") String nombre,
            @WebParam(name = "Apellido") String apellido,
            @WebParam(name = "Usuario") String usuario,
            @WebParam(name = "Email") String email,
            @WebParam(name = "Password") String password,
            @WebParam(name = "id_Rol") int id_Rol,
            @WebParam(name = "id_Estado") boolean id_Estado) {

        modelUser.setNombre(nombre);
        modelUser.setApellido(apellido);
        modelUser.setUsuario(usuario);
        modelUser.setEmail(email);
        modelUser.setPassword(password);
        modelUser.setRegistrarRol(id_Rol);
        modelUser.setRegistrarEstado(id_Estado);

        daoUser.Register(modelUser);
        return true;
    }

    //WEBMETHOD --> ACTULIZAR DATOS USUARIO
    @WebMethod(operationName = "actualizarUsuario")
    public boolean actualizarUsuario(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "idRol") int idRol,
            @WebParam(name = "estado") boolean estado,
            @WebParam(name = "id") int id) {

        modelUser.setNombre(nombre);
        modelUser.setApellido(apellido);
        modelUser.setUsuario(usuario);
        modelUser.setEmail(email);
        modelUser.setPassword(password);
        modelUser.setRegistrarRol(idRol);
        modelUser.setRegistrarEstado(estado);
        modelUser.setId(id);

        boolean cambioExitoso = daoUser.Update(modelUser);
        return cambioExitoso;
    }
}
