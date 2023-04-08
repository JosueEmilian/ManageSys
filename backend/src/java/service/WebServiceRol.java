package service;

import dao.DaoRol;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import model.ModelRol;

@WebService(serviceName = "WebServiceRol")
public class WebServiceRol {

    DaoRol rol = new DaoRol();

    //WEBMETHOD --> LISTAR USUARIOS
    @WebMethod(operationName = "listarRoles")
    public List<ModelRol> listarUsuarios() {
        List<ModelRol> lstUsers;
        lstUsers = rol.listar();
        return lstUsers;
    }
}
