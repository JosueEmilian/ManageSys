package service;

import dao.DaoRol;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelRol;

@WebService(serviceName = "WebServiceRol")
public class WebServiceRol {

    DaoRol daoRol = new DaoRol();
    ModelRol rol = new ModelRol();

    //WEBMETHOD --> LISTAR TODOS LOS ROLES
    @WebMethod(operationName = "listarTodosLosRoles")
    public List<ModelRol> listarRoles() {
        List<ModelRol> lstRoles;
        lstRoles = daoRol.listar();
        return lstRoles;
    }

    //WEBMETHOD --> LISTAR ROL INDIVIDUAL A TRAVES DE SU ID
    @WebMethod(operationName = "obtenerRolPorID")
    public ModelRol obtenerRolPorId(@WebParam(name = "id") int id) {
        return daoRol.list(id);
    }

    //WEBMETHOD --> BUSCAR ROL POR NOMBRE
    @WebMethod(operationName = "buscarRol")
    public List<ModelRol> buscarRol(String rol) {
        List<ModelRol> lstRoles;
        lstRoles = daoRol.Search(rol);
        return lstRoles;
    }

    //WEBMETHOD --> REGISTRAR ROL
    @WebMethod(operationName = "registrarRol")
    public boolean registrarRol(
            @WebParam(name = "Nombre") String nombre,
            @WebParam(name = "Descripcion") String descripcion,
            @WebParam(name = "Estado") boolean estado) {

        rol.setNombre(nombre);
        rol.setDescripcion(descripcion);
        rol.setRegistrarEstado(estado);

        daoRol.Register(rol);
        return true;
    }

    //WEBMETHOD --> ACTUALIZAR ROL
    @WebMethod(operationName = "actualizarRol")
    public boolean actualizarRol(
            @WebParam(name = "NOMBRE") String nombre,
            @WebParam(name = "DESCRIPCION") String descripcion,
            @WebParam(name = "ESTADO") boolean estado,
            @WebParam(name = "ID_ROL") int id) {

        rol.setNombre(nombre);
        rol.setDescripcion(descripcion);
        rol.setRegistrarEstado(estado);
        rol.setId(id);

        boolean cambioExitoso = daoRol.Update(rol);
        return cambioExitoso;
    }
    
    //WEBMETHOD --> ELIMINAR ROL POR ID
    @WebMethod(operationName = "EliminarRol")
    public boolean eliminarRol(@WebParam(name = "ID") int id) {
        rol.setId(id);
        return daoRol.Delete(rol);
    }

}
