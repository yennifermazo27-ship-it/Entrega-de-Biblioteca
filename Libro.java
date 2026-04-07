public class Libro {

    private String nombreUsuario;
    private int documento;
    private String titulo;
    private String autor;
    private String isbn;
    private int anio;
    private String estado;

    public Libro(String titulo, String autor, String isbn, int anio, String estado) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anio = anio;
        this.estado = estado;
    }

    public void setPrestamista(String nombreUsuario, int documento) {
        this.nombreUsuario = nombreUsuario;
        this.documento = documento;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTitulo() {
    return titulo;
}

public String getAutor() {
    return autor;
}

public int getFechaPubli() {  
    return anio;
}

    public String toString() {
        return "Usuario: " + nombreUsuario +
               " Documento: " + documento +
               " Titulo: " + titulo +
               " Autor: " + autor +
               " ISBN: " + isbn +
               " Año: " + anio +
               " Estado: " + estado;
    }
}