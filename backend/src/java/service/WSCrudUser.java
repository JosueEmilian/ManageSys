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
    

   @WebMethod(operationName = "listarUsuarios")
    public List<ModelUser> listarUsuarios() {
        List<ModelUser> lstUsers;
        lstUsers = daoUser.listar();
        return lstUsers;
    }
}
