/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author abner
 */
public class ModeloCliente {

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getNickname() {
        return nickname;
    }

    
    
    public ModeloCliente() {
    }
    


    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    private int idCliente;
    private String nombre;
    private String nit;
    private String razonSocial;
    private String nickname;
    private String direccion;
    private String telefono;
    
}
