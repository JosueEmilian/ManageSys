/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author abner
 */
public class ModeloProducto {
    private int idCombo;
    private int idTipoCombo;
    private String descripcion;
    private float precio;
    private String fechaCreacion;
    private int estado;
    private String imagen;

    public ModeloProducto() {
    }

    public int getIdCombo() {
        return idCombo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdTipoCombo() {
        return idTipoCombo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public void setIdTipoCombo(int idTipoCombo) {
        this.idTipoCombo = idTipoCombo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
