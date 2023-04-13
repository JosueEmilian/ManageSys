package service;

import dao.DaoModulo;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelModulo;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSCrudModulo")
public class WSCrudModulo {

    DaoModulo daoModulo = new DaoModulo();
    ModelModulo modulo = new ModelModulo();

    //WEBMETHOD --> LISTAR TODOS LOS MODULOS
    @WebMethod(operationName = "listarTodosLosModulos")
    public List<ModelModulo> listarModulos() {
        List<ModelModulo> lstModulos;
        lstModulos = daoModulo.listar();
        return lstModulos;
    }

    //WEBMETHOD --> LISTAR MODULO INDIVIDUAL A TRAVES DE SU ID
    @WebMethod(operationName = "obtenerModuloPorID")
    public ModelModulo obtenerModuloPorId(@WebParam(name = "id") int id) {
        return daoModulo.list(id);
    }

    //WEBMETHOD --> BUSCAR MODULO POR NOMBRE O PATH
    @WebMethod(operationName = "buscarModulo")
    public List<ModelModulo> buscarModulo(String modulo) {
        List<ModelModulo> lstModulos;
        lstModulos = daoModulo.Search(modulo);
        return lstModulos;
    }

    //WEBMETHOD --> REGISTRAR NUEVOS MODULOS
    @WebMethod(operationName = "registrarModulo")
    public boolean registarModulo(
            @WebParam(name = "NOMBRE") String nombre,
            @WebParam(name = "PATH") String path,
            @WebParam(name = "NIVEL") int nivel,
            @WebParam(name = "ID_MODULO_PADRE") Integer idModuloPadre) {

        modulo.setNombre(nombre);
        modulo.setPath(path);
        modulo.setNivel(nivel);
        modulo.setIdModuloPadre(idModuloPadre);

        daoModulo.Register(modulo);
        return true;
    }

    //WEBMETHOD --> ACTUALIZAR MODULOS
    @WebMethod(operationName = "actualizarModulo")
    public boolean actualizarModulo(
            @WebParam(name = "NOMBRE") String nombre,
            @WebParam(name = "PATH") String path,
            @WebParam(name = "NIVEL") int nivel,
            @WebParam(name = "ID_MODULO_PADRE") Integer idModuloPadre,
            @WebParam(name = "ID_MODULO") int id) {

        modulo.setNombre(nombre);
        modulo.setPath(path);
        modulo.setNivel(nivel);
        modulo.setIdModuloPadre(idModuloPadre);
        modulo.setId(id);

        boolean cambioExitoso = daoModulo.Update(modulo);
        return cambioExitoso;
    }

    //WEBMETHOD --> ELIMINAR MODULO POR ID
    @WebMethod(operationName = "EliminarModulo")
    public boolean eliminarModulo(@WebParam(name = "ID") int id) {
        modulo.setId(id);
        return daoModulo.Delete(modulo);
    }
}
