package service;

import dao.DaoUser;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.User;
import javax.xml.ws.Holder;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSLogin")
public class WSLogin {
    
    private DaoUser loginDao;

@WebMethod(operationName = "validarCredenciales")
public boolean validarCredenciales(@WebParam(name = "email") String email,
        @WebParam(name = "password") String password, 
        @WebParam(name = "user") Holder<User> user) {
    boolean resultado = false;
    try {
        loginDao = new DaoUser();
        resultado = loginDao.validarCredenciales(email, password, user);
        loginDao.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return resultado;
}
}
