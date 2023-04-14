package model;

/**
 *
 * @author josueemilian
 */
public class ModelModulo {
    private int id;
    private String nombre;
    private String path;
    private int nivel;
    private Integer idModuloPadre;

    public ModelModulo() {
    }

    public ModelModulo(int id, String nombre, String path, int nivel, Integer idModuloPadre) {
        this.id = id;
        this.nombre = nombre;
        this.path = path;
        this.nivel = nivel;
        this.idModuloPadre = idModuloPadre;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Integer getIdModuloPadre() {
        return idModuloPadre;
    }

    public void setIdModuloPadre(Integer idModuloPadre) {
        this.idModuloPadre = idModuloPadre;
    }


}
