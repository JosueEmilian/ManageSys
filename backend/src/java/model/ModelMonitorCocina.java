package model;

/**
 *
 * @author josueemilian
 */
public class ModelMonitorCocina {
    private int idPedido;
    private String observacion;
    private String descripcion;
    private int cantidad;
    private String mesa;
    private String area;
    private String hora;
    private String imagen;

    public ModelMonitorCocina() {
    }

    public ModelMonitorCocina(int idPedido, String observacion, String descripcion, int cantidad, String mesa, String area, String hora, String imagen) {
        this.idPedido = idPedido;
        this.observacion = observacion;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.mesa = mesa;
        this.area = area;
        this.hora = hora;
        this.imagen = imagen;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
