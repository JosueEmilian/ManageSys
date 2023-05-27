package model;

/**
 *
 * @author josueemilian
 */
public class ModelDetallePedido {
    private int id;
    private int idPedido;
    private int idCombo;
    private int cantidad;
    private double precio;
    private double total;
    private String observacion;
    private int estado;
    private String fecha;

    public ModelDetallePedido() {
    }

    public ModelDetallePedido(int id, int idPedido, int idCombo, int cantidad, double precio, double total, String observacion, int estado, String fecha) {
        this.id = id;
        this.idPedido = idPedido;
        this.idCombo = idCombo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.observacion = observacion;
        this.estado = estado;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
