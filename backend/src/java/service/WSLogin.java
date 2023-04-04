package service;

import dao.DaoUser;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSLogin")
public class WSLogin {

    
    private DaoUser loginDao;

    @WebMethod(operationName = "validarCredenciales")
    public boolean validarCredenciales(@WebParam(name = "email") String email,
            @WebParam(name = "password") String password) {
        boolean resultado = false;
        try {
            loginDao = new DaoUser();
            resultado = loginDao.validarCredenciales(email, password);
            loginDao.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
}