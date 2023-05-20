
package service;

import dao.DaoCliente;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModeloCliente;

/**
 *
 * @author abner
 */
@WebService(serviceName = "WsClienteNuevo")
public class WsClienteNuevo {

    DaoCliente daoCliente = new DaoCliente();
    ModeloCliente modeloCliente = new ModeloCliente();
    
    @WebMethod(operationName = "listarClientes")
    public List<ModeloCliente> listar() { //@WebParam(name = "name") String txt
        List<ModeloCliente> listaClientes;
        listaClientes = daoCliente.clientes();
        return listaClientes;
    }
    
    
    @WebMethod(operationName = "getUsuarioId")
    public ModeloCliente obtenerCliente(@WebParam(name = "id") int id){
        return daoCliente.infoCliente(id);
    }
    
    
    @WebMethod(operationName = "insertarCliente")
    public boolean registrarCliente(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "nit") String nit,
            @WebParam(name = "razonSocial") String razon,
            @WebParam(name = "nickname") String nickname,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "telefono") String telefono) {

        //modeloCliente= new ModeloCliente();
        modeloCliente.setNombre(nombre);
        modeloCliente.setNit(nit);
        modeloCliente.setRazonSocial(razon);
        modeloCliente.setNickname(nickname);
        modeloCliente.setDireccion(direccion);
        modeloCliente.setTelefono(telefono);
        boolean respuesta = daoCliente.insertarCliente(modeloCliente);
        return respuesta;
    } 
    
    
    @WebMethod(operationName = "actualizarUsuario")
    public boolean actualizarCliente(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "nit") String nit,
            @WebParam(name = "nickname") String nickname,
            @WebParam(name = "razonSocial") String razon,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "id") int id) {

        modeloCliente.setNombre(nombre);
        modeloCliente.setNit(nit);
        modeloCliente.setNickname(nickname);
        modeloCliente.setDireccion(direccion);
        modeloCliente.setRazonSocial(razon);
        modeloCliente.setTelefono(telefono); 
        modeloCliente.setIdCliente(id);

        boolean cambioExitoso = daoCliente.modificarCliente(modeloCliente);
        return cambioExitoso;
    }
    
}
