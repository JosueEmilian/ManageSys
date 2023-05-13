package model;

/**
 *
 * @author josueemilian
 */
public class ModelArea {
    
    //VAR DE REGISTRO
    private int id;
    private String descripcion;
    private boolean estado;
    
    //VAR MOSTRAR DATOS FRONT
    private String strEstado;

    //

    public ModelArea() {
    }

    public ModelArea(int id, String descripcion, boolean estado, String strEstado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.strEstado = strEstado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getStrEstado() {
        return strEstado;
    }

    public void setStrEstado(String strEstado) {
        this.strEstado = strEstado;
    }

        
}
