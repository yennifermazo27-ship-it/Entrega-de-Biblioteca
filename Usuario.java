public class Usuario {

    private String nombre;
    private int documento;
    private String tipoUsuario; 
    private String estado;     
    private String password;

    public Usuario(String nombre, int documento, String tipoUsuario, String estado, String password) {
        this.nombre = nombre;
        this.documento = documento;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDocumento() {
        return documento;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }

    public boolean esAdmin() {
        return tipoUsuario.equalsIgnoreCase("ADMIN");
    }

    public boolean esBibliotecario() {
        return tipoUsuario.equalsIgnoreCase("BIBLIOTECARIO");
    }

    public boolean esLector() {
        return tipoUsuario.equalsIgnoreCase("LECTOR");
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", documento=" + documento +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}