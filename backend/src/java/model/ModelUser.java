package model;


public class ModelUser {
    
    //principales db
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String email;
    private String password;
    private int RegistrarRol;
    private boolean RegistrarEstado;
    
    //mostrar en table
    private String rol;
    private String estado;
    
    //relacion con db
    private String modulo;
    private String ruta;

    
    //constructor, getter and setters 
    public ModelUser() {
    }

    public ModelUser(int id, String nombre, String apellido, String usuario, String email, String password, int RegistrarRol, boolean RegistrarEstado, String rol, String estado, String modulo, String ruta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.RegistrarRol = RegistrarRol;
        this.RegistrarEstado = RegistrarEstado;
        this.rol = rol;
        this.estado = estado;
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

    public int getRegistrarRol() {
        return RegistrarRol;
    }

    public void setRegistrarRol(int RegistrarRol) {
        this.RegistrarRol = RegistrarRol;
    }

    public boolean isRegistrarEstado() {
        return RegistrarEstado;
    }

    public void setRegistrarEstado(boolean RegistrarEstado) {
        this.RegistrarEstado = RegistrarEstado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
