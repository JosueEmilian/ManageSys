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

}
