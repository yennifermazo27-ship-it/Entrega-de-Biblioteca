import java.util.ArrayList;

public class GestionPrestamos {

    private ArrayList<Libro> listaPrestamos;

    public GestionPrestamos() {
        listaPrestamos = new ArrayList<>();
    }

    public void registrarPrestamo(Libro libro) {
        if (libro.getEstado().equalsIgnoreCase("Disponible")) {
            libro.setEstado("No disponible");
            listaPrestamos.add(libro);
        } else {
            System.out.println("El libro no está disponible");
        }
    }

    public Libro buscarPrestamo(String isbn) {
        for (Libro l : listaPrestamos) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    public boolean devolverLibro(String isbn) {
        for (Libro l : listaPrestamos) {
            if (l.getIsbn().equals(isbn)) {
                l.setEstado("Disponible");
                listaPrestamos.remove(l);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Libro> listarPrestamos() {
        return listaPrestamos;
    }
}