package service;

import dao.DaoProductoNuevo;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModelProductoNuevo;

/**
 *
 * @author josueemilian
 */
@WebService(serviceName = "WSProductoNuevo")
public class WSProductoNuevo {

    DaoProductoNuevo daoProductoNuevo = new DaoProductoNuevo();
    ModelProductoNuevo productoNuevo = new ModelProductoNuevo();
    
        //WEBMETHOD --> LISTAR PRODUCTOS
    @WebMethod(operationName = "listarProductosNuevo")
    public List<ModelProductoNuevo> listarProductosNuevo() {
        List<ModelProductoNuevo> lstProductosNuevos;
        lstProductosNuevos = daoProductoNuevo.listar();
        return lstProductosNuevos;
    }
    
    
        @WebMethod(operationName = "insertarProductoNuevo")
    public boolean registrarProductoNuevo(
            @WebParam(name = "idTipo") int idTipo,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "precio") double precio,
            @WebParam(name = "imagen") String imagen) {

        //modeloCliente= new ModeloCliente();
        productoNuevo.setIdtipo(idTipo);
        productoNuevo.setDescripcion(descripcion);
        productoNuevo.setPrecio(precio);
        productoNuevo.setEstado(true);
        productoNuevo.setImg(imagen);
        boolean respuesta = daoProductoNuevo.insertarProductoNuevo(productoNuevo);
        return respuesta;
    }
}
