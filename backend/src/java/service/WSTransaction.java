package service;

import dao.DaoTransaction;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelTransaction;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSTransaction")
public class WSTransaction {

    DaoTransaction daoTransaction = new DaoTransaction();
    ModelTransaction transaction = new ModelTransaction();

    @WebMethod(operationName = "registerTransaction")
    public boolean registerTransaction(
            @WebParam(name = "nombreCliente") String nombreCliente,
            @WebParam(name = "nitCliente") String nitCliente,
            @WebParam(name = "razonSocialCliente") String razonSocialCliente,
            @WebParam(name = "nicknameCliente") String nicknameCliente,
            @WebParam(name = "direccionCliente") String direccionCliente,
            @WebParam(name = "telefonoCliente") String telefonoCliente,
            @WebParam(name = "idMesa") int idMesa,
            @WebParam(name = "idEmpleado") int idEmpleado,
            @WebParam(name = "observacionPedido") String observacionPedido,
            @WebParam(name = "idCombo") int idCombo,
            @WebParam(name = "cantidadDetalle") int cantidadDetalle,
            @WebParam(name = "precioDetalle") double precioDetalle,
            @WebParam(name = "totalLineaDetalle") double totalLineaDetalle,
            @WebParam(name = "observacionDetalle") String observacionDetalle) {

        transaction.setNombreCliente(nombreCliente);
        transaction.setNitCliente(nitCliente);
        transaction.setRazonSocialCliente(razonSocialCliente);
        transaction.setNicknameCliente(nicknameCliente);
        transaction.setDireccionCliente(direccionCliente);
        transaction.setTelefonoCliente(telefonoCliente);
        transaction.setIdMesa(idMesa);
        transaction.setIdEmpleado(idEmpleado);
        transaction.setObservacionPedido(observacionPedido);
        transaction.setIdCombo(idCombo);
        transaction.setCantidadDetalle(cantidadDetalle);
        transaction.setPrecioDetalle(precioDetalle);
        transaction.setTotalLineaDetalle(totalLineaDetalle);
        transaction.setObservacionDetalle(observacionDetalle);
        transaction.setEstadoDetalle(true);

        return daoTransaction.Register(transaction);
    }
}
