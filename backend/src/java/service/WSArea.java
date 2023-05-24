package service;

import dao.DaoArea;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelArea;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSArea")
public class WSArea {

    DaoArea daoArea = new DaoArea();
    ModelArea area = new ModelArea();

    //WEBMETHOD --> LISTAR TODAS LAS AREAS
    @WebMethod(operationName = "listarAreas")
    public List<ModelArea> listarAreas() {
        List<ModelArea> lstAreas;
        lstAreas = daoArea.listar();
        return lstAreas;
    }

    //WEBMETHOD --> LISTAR AREA INDIVIDUAL A TRAVES DE SU ID
    @WebMethod(operationName = "obtenerAreaPorID")
    public ModelArea obtenerAreaPorID(@WebParam(name = "id") int id) {
        return daoArea.list(id);
    }

    //WEBMETHOD --> REGISTRAR AREAS 
    @WebMethod(operationName = "registrarArea")
    public boolean registrarArea(
            @WebParam(name = "DESCRIPCION") String descripcion,
            @WebParam(name = "ESTADO") boolean estado) {

        area.setDescripcion(descripcion);
        area.setEstado(estado);

        daoArea.Register(area);
        return true;
    }

    //WEBMETHOD --> ACTUALIZAR AREAS
    @WebMethod(operationName = "actualizarAreas")
    public boolean actualizarAreas(
            @WebParam(name = "DESCRIPCION") String descripcion,
            @WebParam(name = "ESTADO") boolean estado,
            @WebParam(name = "ID_AREA") int id) {

        area.setDescripcion(descripcion);
        area.setEstado(estado);
        area.setId(id);

        boolean cambioExitoso = daoArea.Update(area);
        return cambioExitoso;
    }

    //WEBMETHOD --> ELIMINAR AREA POR ID
    @WebMethod(operationName = "eliminarArea")
    public boolean eliminarArea(@WebParam(name = "ID") int id) {
        area.setId(id);
        return daoArea.Delete(area);
    }

}
