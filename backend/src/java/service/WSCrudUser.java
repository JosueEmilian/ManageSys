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
            @WebParam(name="id_Rol") int id_Rol, 
            @WebParam(name="id_Estado") boolean id_Estado) {
        
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
}
