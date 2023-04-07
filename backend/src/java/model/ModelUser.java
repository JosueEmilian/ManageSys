package model;


public class ModelUser {
    
    //principales db
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String email;
    private String password;
    private int rol;
    private boolean estado;
    
    //mostrar en table
    private String DescRol;
    private String descEstado;
    
    //relacion con db
    private String modulo;
    private String ruta;

    
    //constructor, getter and setters 
    public ModelUser() {
    }

    public ModelUser(int id, String nombre, String apellido, String usuario, String email, String password, int rol, boolean estado, String DescRol, String descEstado, String modulo, String ruta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
        this.DescRol = DescRol;
        this.descEstado = descEstado;
        this.modulo = modulo;
        this.ruta = ruta;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescRol() {
        return DescRol;
    }

    public void setDescRol(String DescRol) {
        this.DescRol = DescRol;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
  
}
