package model;

/**
 *
 * @author josueemilian
 */
public class ModelPermiso {
    
    //var de ingreso
    private int id;
    private int idModulo;
    private int idRol;
    
    //var de visualizacion
    private String nombreModulo;
    private String nombreRol;
    private String pathModulo;

    public ModelPermiso() {
    }

    public ModelPermiso(int id, int idModulo, int idRol, String nombreModulo, String nombreRol, String pathModulo) {
        this.id = id;
        this.idModulo = idModulo;
        this.idRol = idRol;
        this.nombreModulo = nombreModulo;
        this.nombreRol = nombreRol;
        this.pathModulo = pathModulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getPathModulo() {
        return pathModulo;
    }

    public void setPathModulo(String pathModulo) {
        this.pathModulo = pathModulo;
    }


}
