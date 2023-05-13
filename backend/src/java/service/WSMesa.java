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

    @WebMethod(operationName = "listarMesasDeArea")
    public List<ModelMesa> listarMesasDeArea(@WebParam(name = "id") int id) {
        List<ModelMesa> lstMesasArea;
        lstMesasArea = daoMesa.list(id);
        return lstMesasArea;
    }

    //WEBMETHOD --> ACTUALIZAR MESA --> DISPONIBLE/NO-DISPONIBLE
    @WebMethod(operationName = "actualizarMesa")
    public boolean actualizarMesa(
            @WebParam(name = "ID_MESA") int idMesa) {

        mesa.setId(idMesa);

        boolean cambioExitoso = daoMesa.Update(mesa);
        return cambioExitoso;
    }

}
