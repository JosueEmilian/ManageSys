package service;

import dao.DaoMesa;
import java.util.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelMesa;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSMesa")
public class WSMesa {

    DaoMesa daoMesa = new DaoMesa();
    ModelMesa mesa = new ModelMesa();

    //WEBMETHOD --> LISTAR TODAS LAS MESAS
    @WebMethod(operationName = "listarMesas")
    public List<ModelMesa> listarMesas() {
        List<ModelMesa> lstMesas;
        lstMesas = daoMesa.listar();
        return lstMesas;
    }

    //LISTAR MESAS DE UNA AREA ESPECIFICA 
    @WebMethod(operationName = "listarMesasDeArea")
    public List<ModelMesa> listarMesasDeArea(@WebParam(name = "id") int id) {
        List<ModelMesa> lstMesasArea;
        lstMesasArea = daoMesa.list(id);
        return lstMesasArea;
    }

    //WEBMETHOD --> LISTAR MESA INDIVIDUAL A TRAVES DE SU ID
    @WebMethod(operationName = "obtenerMesaPorID")
    public ModelMesa obtenerMesaPorID(@WebParam(name = "id") int id) {
        return daoMesa.listIndividual(id);
    }

    //WEBMETHOD --> REGISTRAR MESAS 
    @WebMethod(operationName = "registrarMesa")
    public boolean registrarMesa(
            @WebParam(name = "ID_AREA") int idArea,
            @WebParam(name = "DESCRIPCION") String descripcion,
            @WebParam(name = "ASIENTOS") int asientos,
            @WebParam(name = "ID_ESTADO") boolean estado) {

        mesa.setIdArea(idArea);
        mesa.setDescripcion(descripcion);
        mesa.setAsientos(asientos);
        mesa.setEstado(estado);

        daoMesa.Register(mesa);
        return true;
    }

    //WEBMETHOD --> ACTUALIZAR MESA ESPECIFICA DE FORMA GENERAL
    @WebMethod(operationName = "actualizarMesasGeneral")
    public boolean actualizarMesasGeneral(
            @WebParam(name = "ID_AREA") int idArea,
            @WebParam(name = "DESCRIPCION") String descripcion,
            @WebParam(name = "ASIENTOS") int asientos,
            @WebParam(name = "ID_ESTADO") boolean estado,
            @WebParam(name = "ID_MESA") int id) {

        mesa.setIdArea(idArea);
        mesa.setDescripcion(descripcion);
        mesa.setAsientos(asientos);
        mesa.setEstado(estado);
        mesa.setId(id);

        boolean cambioExitoso = daoMesa.UpdateIndividual(mesa);
        return cambioExitoso;
    }

    //WEBMETHOD --> ACTUALIZAR MESA --> DISPONIBLE/NO-DISPONIBLE
    @WebMethod(operationName = "actualizarMesa")
    public boolean actualizarMesa(
            @WebParam(name = "ID_MESA") int idMesa) {

        mesa.setId(idMesa);

        boolean cambioExitoso = daoMesa.Update(mesa);
        return cambioExitoso;
    }

    //WEBMETHOD --> ELIMINAR MESA POR ID
    @WebMethod(operationName = "eliminarMesa")
    public boolean eliminarMesa(@WebParam(name = "ID") int id) {
        mesa.setId(id);
        return daoMesa.Delete(mesa);
    }

}
