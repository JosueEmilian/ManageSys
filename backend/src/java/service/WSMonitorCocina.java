package service;

import dao.DaoMonitorCocina;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelMonitorCocina;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSMonitorCocina")
public class WSMonitorCocina {

    DaoMonitorCocina daoMonitorCocina = new DaoMonitorCocina();
    ModelMonitorCocina monitorCocina = new ModelMonitorCocina();

    //WEBMETHOD --> LISTAR PEDIDOS PENDIENTES
    @WebMethod(operationName = "listarPedidosPendientes")
    public List<ModelMonitorCocina> listarPedidosPendientes() {
        List<ModelMonitorCocina> lstPedidosPendientes;
        lstPedidosPendientes = daoMonitorCocina.listar();
        return lstPedidosPendientes;
    }

    //WEBMETHOD --> ACTUALIZAR ESTADO DEL PEDIDO A DESPACHADO
    @WebMethod(operationName = "actualizarEstadoPedido")
    public boolean actualizarEstadoPedido(
            @WebParam(name = "ID_PEDIDO") int id) {
        monitorCocina.setIdPedido(id);
        boolean cambioExitoso = daoMonitorCocina.Update(monitorCocina);
        return cambioExitoso;
    }

}
