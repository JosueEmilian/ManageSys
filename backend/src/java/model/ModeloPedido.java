/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author abner
 */
public class ModeloPedido {
    private int idPedido;
    private int idMesa;
    private int idEmpleado;
    private int idCliente;
    private double total;
    private String observaciones;
    private int estado;
    private String fechaCreacion;

    public ModeloPedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getTotal() {
        return total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public int getEstado() {
        return estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setEstado(int istado) {
        this.estado = istado;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
}
