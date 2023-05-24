/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package service;

import dao.DaoProducto;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.ModeloProducto;

/**
 *
 * @author abner
 */
@WebService(serviceName = "WsProducto")
public class WsProducto {

    DaoProducto dao=new DaoProducto();
    ModeloProducto model = new ModeloProducto();
    
    @WebMethod(operationName = "getProductos")
    public List<ModeloProducto> obtenerProductos() {
        List<ModeloProducto> productos;
        productos = dao.productos();
        return productos;
    }
    
    @WebMethod(operationName = "getInfoProducto")
    public ModeloProducto obtenerProducto(@WebParam(name = "id") int id){
        return dao.infoProducto(id);
    }
    
    @WebMethod(operationName = "insertarProducto")
    public boolean insertarProducto(
            @WebParam(name = "idTipo") int tipo,
            @WebParam(name = "descripcion") String desc,
            @WebParam(name = "precio") float precio, 
            @WebParam(name = "estado") int estado,
            @WebParam(name = "imagen") String imagen) {

        //modeloCliente= new ModeloCliente();
        model.setIdTipoCombo(tipo);
        model.setDescripcion(desc);
        model.setPrecio(precio); 
        model.setEstado(estado);
        model.setImagen(imagen);
        
        boolean respuesta = dao.insertarProducto(model);
        return respuesta;
    } 
    
    
    @WebMethod(operationName = "modificarProducto")
    public boolean modificarProducto(
            @WebParam(name = "idCombo") int id,
            @WebParam(name = "idTipo") int tipo,
            @WebParam(name = "descripcion") String desc,
            @WebParam(name = "precio") float precio,
            @WebParam(name = "estado") int estado,
            @WebParam(name = "imagen") String img ) {

        model.setIdCombo(id);
        model.setIdTipoCombo(tipo);
        model.setDescripcion(desc);
        model.setPrecio(precio);
        model.setEstado(estado);
        model.setImagen(img);

        boolean cambioExitoso = dao.modififcarProducto(model);
        return cambioExitoso;
    }
    
    @WebMethod(operationName = "eliminarProducto")
    public boolean eliminarProd(@WebParam(name = "id") int id) { 
        return dao.eliminarProducto(id);
    } 
    
}
