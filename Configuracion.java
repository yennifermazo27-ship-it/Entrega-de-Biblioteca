public class Configuracion {

    public static boolean campoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean validarTexto(String texto) {
        return !campoVacio(texto) && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }

    public static boolean validarDocumento(int documento) {
        return documento > 0;
    }

    public static boolean validarTipoUsuario(String tipo) {
        return tipo.equalsIgnoreCase("ADMIN") ||
               tipo.equalsIgnoreCase("BIBLIOTECARIO") ||
               tipo.equalsIgnoreCase("LECTOR");
    }

    public static boolean validarEstadoUsuario(String estado) {
        return estado.equalsIgnoreCase("Activo") ||
               estado.equalsIgnoreCase("Inactivo");
    }

    public static boolean validarPassword(String password) {
        return !campoVacio(password) && password.length() >= 4;
    }

    public static boolean validarUsuario(Usuario u) {

        if (u == null) return false;

        if (campoVacio(u.getNombre())) {
            System.out.println(" Nombre vacío");
            return false;
        }

        if (campoVacio(u.getTipoUsuario())) {
            System.out.println(" Tipo de usuario vacío");
            return false;
        }

        if (campoVacio(u.getEstado())) {
            System.out.println(" Estado vacío");
            return false;
        }

        if (!validarTexto(u.getNombre())) return false;
        if (!validarDocumento(u.getDocumento())) return false;
        if (!validarTipoUsuario(u.getTipoUsuario())) return false;
        if (!validarEstadoUsuario(u.getEstado())) return false;

        if (u.getTipoUsuario().equalsIgnoreCase("ADMIN")) {
            if (campoVacio(u.getPassword())) {
                System.out.println(" Password vacío");
                return false;
            }
            if (!validarPassword(u.getPassword())) return false;
        }

        return true;
    }

    public static boolean validarLibro(Libro l) {

        if (l == null) return false;

        if (campoVacio(l.getTitulo())) {
            System.out.println(" Título vacío");
            return false;
        }

        if (campoVacio(l.getAutor())) {
            System.out.println(" Autor vacío");
            return false;
        }

        if (campoVacio(l.getIsbn())) {
            System.out.println(" ISBN vacío");
            return false;
        }

        if (!validarTexto(l.getTitulo())) return false;
        if (!validarTexto(l.getAutor())) return false;

        if (!validarAnio(l.getFechaPubli())) {
            System.out.println(" Año de publicación inválido");
            return false;
        }

        return true;
    }

    public static boolean validarAnio(int anio) {
        int anioActual = 2026;
        return anio > 0 && anio <= anioActual;
    }
}