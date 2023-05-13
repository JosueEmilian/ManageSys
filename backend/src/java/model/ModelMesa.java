package model;

/**
 *
 * @author josueemilian
 */
public class ModelMesa {
    //VAR REGISTRO
    private int id;
    private int idArea;
    private String descripcion;
    private int asientos;
    private boolean estado;
    
    //VAR MOSTRAR DATOS FRONT
    private String descArea;
    private String strEstadoMesa;

    public ModelMesa() {
    }

    public ModelMesa(int id, int idArea, String descripcion, int asientos, boolean estado, String descArea, String strEstadoMesa) {
        this.id = id;
        this.idArea = idArea;
        this.descripcion = descripcion;
        this.asientos = asientos;
        this.estado = estado;
        this.descArea = descArea;
        this.strEstadoMesa = strEstadoMesa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescArea() {
        return descArea;
    }

    public void setDescArea(String descArea) {
        this.descArea = descArea;
    }

    public String getStrEstadoMesa() {
        return strEstadoMesa;
    }

    public void setStrEstadoMesa(String strEstadoMesa) {
        this.strEstadoMesa = strEstadoMesa;
    }


}
