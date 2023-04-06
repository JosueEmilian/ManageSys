package model;


public class User {
    
    private int id;
    private String usuario;
    private String email;
    private String password;
    private String rol;
    private String modulo;
    private String ruta;

    public User() {
    }

    public User(int id, String usuario, String email, String password, String rol, String modulo, String ruta) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.modulo = modulo;
        this.ruta = ruta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
