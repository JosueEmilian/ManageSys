package service;

import dao.DaoPermiso;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelPermiso;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSCrudPermiso")
public class WSCrudPermiso {

    DaoPermiso daoPermiso = new DaoPermiso();
    ModelPermiso permiso = new ModelPermiso();

    //WEBMETHOD --> LISTAR TODOS LOS PERMISOS
    @WebMethod(operationName = "listarTodosLosPermisos")
    public List<ModelPermiso> listarPermisos() {
        List<ModelPermiso> lstPermisos;
        lstPermisos = daoPermiso.listar();
        return lstPermisos;
    }

    //WEBMETHOD --> LISTAR PERMISO INDIVIDUAL A TRAVES DE SU ID
    @WebMethod(operationName = "obtenerPermisoPorID")
    public ModelPermiso obtenerPermisoPorID(@WebParam(name = "id") int id) {
        return daoPermiso.list(id);
    }

    //WEBMETHOD --> BUSCAR PERMISO POR NOMBRE MODULO O NOMBRE ROL
    @WebMethod(operationName = "buscarPermiso")
    public List<ModelPermiso> buscarPermiso(String permiso) {
        List<ModelPermiso> lstPermiso;
        lstPermiso = daoPermiso.Search(permiso);
        return lstPermiso;
    }

    //WEBMETHOD --> REGISTRAR NUEVOS PERMISOS
    @WebMethod(operationName = "registrarPermiso")
    public boolean registrarPermiso(
            @WebParam(name = "ID_MODULO") int idModulo,
            @WebParam(name = "ID_ROL") int idRol) {

        permiso.setIdModulo(idModulo);
        permiso.setIdRol(idRol);

        daoPermiso.Register(permiso);
        return true;
    }

    //WEBMETHOD --> ACTUALIZAR PERMISOS
    @WebMethod(operationName = "actualizarPermisos")
    public boolean actualizarPermiso(
            @WebParam(name = "ID_MODULO") int idModulo,
            @WebParam(name = "ID_ROL") int idRol,
            @WebParam(name = "ID_PERMISO") int id) {

        permiso.setIdModulo(idModulo);
        permiso.setIdRol(idRol);
        permiso.setId(id);

        boolean cambioExitoso = daoPermiso.Update(permiso);
        return cambioExitoso;
    }

    //WEBMETHOD --> ELIMINAR PERMISO POR ID
    @WebMethod(operationName = "eliminarPermiso")
    public boolean eliminarPermiso(@WebParam(name = "ID") int id) {
        permiso.setId(id);
        return daoPermiso.Delete(permiso);
    }

}
