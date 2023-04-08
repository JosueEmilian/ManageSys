package model;

/**
 *
 * @author josueemilian
 */
public class ModelRol {
    
    private int id;
    private String nombre;
    private String descripcion;
    private boolean registrarEstado;
    private String estado;

    public ModelRol() {
    }

    public ModelRol(int id, String nombre, String descripcion, boolean registrarEstado, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.registrarEstado = registrarEstado;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRegistrarEstado() {
        return registrarEstado;
    }

    public void setRegistrarEstado(boolean registrarEstado) {
        this.registrarEstado = registrarEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
