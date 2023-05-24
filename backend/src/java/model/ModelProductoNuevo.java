package model;

/**
 *
 * @author josueemilian
 */
public class ModelProductoNuevo {
    private int id;
    private int idtipo;
    private String descripcion;
    private double precio;
    private String fecha;
    private boolean estado;
    private String img;

    public ModelProductoNuevo(int id, int idtipo, String descripcion, double precio, String fecha, boolean estado, String img) {
        this.id = id;
        this.idtipo = idtipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.estado = estado;
        this.img = img;
    }

    public ModelProductoNuevo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}
