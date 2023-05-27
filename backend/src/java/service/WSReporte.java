package service;

import dao.DaoReportes;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import model.ModelReportes;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSReporte")
public class WSReporte {
    
    DaoReportes daoReportes = new DaoReportes();
    ModelReportes reportes = new ModelReportes();
    
        //WEBMETHOD --> Historial de pedidos
    @WebMethod(operationName = "historialPedidos")
    public List<ModelReportes> historialPedidos() {
        List<ModelReportes> lstHistorialPedido;
        lstHistorialPedido = daoReportes.historialPedidos();
        return lstHistorialPedido;
    }
}
