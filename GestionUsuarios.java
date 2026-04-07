import java.util.ArrayList;

public class GestionUsuarios {

    private ArrayList<Usuario> listaUsuarios;

    public GestionUsuarios() {
        listaUsuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario u) {
        listaUsuarios.add(u);
    }

    public Usuario buscarUsuario(int documento) {
        for (Usuario u : listaUsuarios) {
            if (u.getDocumento() == documento) {
                return u;
            }
        }
        return null;
    }

    public boolean eliminarUsuario(int documento) {
        for (Usuario u : listaUsuarios) {
            if (u.getDocumento() == documento) {
                listaUsuarios.remove(u);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Usuario> listarUsuarios() {
        return listaUsuarios;
    }
}