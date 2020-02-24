package pe.edu.continental.adaug.Entidades;

public class Usuario {
    int id;
    String nombre, apellidos, usuario, pass;

    public Usuario() {

    }

    public Usuario(String nombre, String apellidos, String usuario, String pass) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.pass = pass;
    }

    //validacion de cambops
    public boolean isNull(){
        if(nombre.equals("")&&apellidos.equals("")&&usuario.equals("")&&pass.equals("")){
            return false;
        }else {
            return true;
        }
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", usuario='" + usuario + '\'' +
                ", pass='" + pass + '\'' +
                '}';
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
