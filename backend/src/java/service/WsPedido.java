/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package service;

import dao.DaoPedido;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModeloPedido;

/**
 *
 * @author abner
 */
@WebService(serviceName = "WsPedido")
public class WsPedido {
    
    DaoPedido daoPedido = new DaoPedido();
    ModeloPedido mod = new ModeloPedido();

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarPedidos")
    public List<ModeloPedido> pedidosListar() {
        List<ModeloPedido> listaPedidos;
        listaPedidos = daoPedido.pedidos();
        return listaPedidos;
    }
    
    @WebMethod(operationName = "getPedido")
    public ModeloPedido obtenerPedido(@WebParam(name = "id") int id){
        return daoPedido.infoPedido(id);
    }
    
    
    @WebMethod(operationName = "insertarPedido")
    public boolean insertarPedido(
            @WebParam(name = "idMesa") int mesa,
            @WebParam(name = "idEmpleado") int emp,
            @WebParam(name = "idCliente") int cliente,
            @WebParam(name = "total") double total,
            @WebParam(name = "observacion") String observacion,
            @WebParam(name = "estado") int estado) {

        //modeloCliente= new ModeloCliente();
        mod.setIdMesa(mesa);
        mod.setIdEmpleado(emp);
        mod.setIdCliente(cliente);
        mod.setTotal(total);
        mod.setObservaciones(observacion);
        mod.setEstado(estado);
        boolean respuesta = daoPedido.insertarPedido(mod);
        return respuesta;
    } 
    
    
    @WebMethod(operationName = "modificarPedido")
    public boolean actualizarPedido(
            @WebParam(name = "idPedido") int id,
            @WebParam(name = "idMesa") int mesa,
            @WebParam(name = "idEmpleado") int emp,
            @WebParam(name = "idCliente") int cliente,
            @WebParam(name = "total") double total,
            @WebParam(name = "observacion") String observacion,
            @WebParam(name = "estado") int estado) {

        mod.setIdPedido(id);
        mod.setIdMesa(mesa);
        mod.setIdEmpleado(emp);
        mod.setIdCliente(cliente);
        mod.setTotal(total);
        mod.setObservaciones(observacion);
        mod.setEstado(estado);

        boolean cambioExitoso = daoPedido.modificarPedido(mod);
        return cambioExitoso;
    }
    
    
    @WebMethod(operationName = "eliminarPedido")
    public boolean eliminarPedido(@WebParam(name = "id") int id) { 
        return daoPedido.eliminarPedido(id);
    } 
}
