import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionPrestamos gestionPrestamos = new GestionPrestamos();

        System.out.println("=================================");
        System.out.println(" BIBLIOTECA MUNICIPAL ");
        System.out.println(" Versión 1.0 ");
        System.out.println("=================================");

        int opcion = 0;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Login");
            System.out.println("3. Salir");

            System.out.print("Opción: ");
            String entrada = sc.nextLine();

            if (entrada.isEmpty()) {
                System.out.println("No puede estar vacío");
                continue;
            }

            if (!(entrada.equals("1") || entrada.equals("2") || entrada.equals("3"))) {
                System.out.println("Opción inválida");
                continue;
            }

            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1:
                    registrarUsuario(sc, gestionUsuarios);
                    break;
                case 2:
                    login(sc, gestionUsuarios, gestionPrestamos);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
            }

        } while (opcion != 3);

        sc.close();
    }

    public static void registrarUsuario(Scanner sc, GestionUsuarios gu) {

        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
        } while (Configuracion.campoVacio(nombre));

        String docStr;
        int documento;
        do {
            System.out.print("Documento: ");
            docStr = sc.nextLine();
        } while (Configuracion.campoVacio(docStr));

        documento = Integer.parseInt(docStr);

        String tipo = seleccionarRol(sc);
        String estado = seleccionarEstado(sc);

        String password = "";
        if (tipo.equalsIgnoreCase("ADMIN")) {
            do {
                System.out.print("Password: ");
                password = sc.nextLine();
            } while (Configuracion.campoVacio(password));
        }

        Usuario u = new Usuario(nombre, documento, tipo, estado, password);

        if (Configuracion.validarUsuario(u)) {
            gu.agregarUsuario(u);
            System.out.println("Usuario registrado");
        } else {
            System.out.println("Error en los datos");
        }
    }

    public static void login(Scanner sc, GestionUsuarios gu, GestionPrestamos gp) {

        System.out.print("Documento: ");
        int doc = Integer.parseInt(sc.nextLine());

        Usuario u = gu.buscarUsuario(doc);

        if (u == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        if (u.esAdmin()) {
            System.out.print("Password: ");
            String pass = sc.nextLine();

            if (!u.getPassword().equals(pass)) {
                System.out.println("Password incorrecto");
                return;
            }

            menuAdmin(sc, gu, gp);

        } else if (u.esBibliotecario()) {
            menuBibliotecario(sc, gp);
        } else if (u.esLector()) {
            menuLector(gp);
        }
    }

    public static String seleccionarRol(Scanner sc) {

        String opcion;

        do {
            System.out.println("\nSeleccione tipo de usuario:");
            System.out.println("1. ADMIN");
            System.out.println("2. BIBLIOTECARIO");
            System.out.println("3. LECTOR");
            System.out.print("Opción: ");

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3"));

        if (opcion.equals("1")) return "ADMIN";
        if (opcion.equals("2")) return "BIBLIOTECARIO";
        return "LECTOR";
    }

    public static String seleccionarEstado(Scanner sc) {

        String opcion;

        do {
            System.out.println("\nSeleccione estado:");
            System.out.println("1. Activo");
            System.out.println("2. Inactivo");
            System.out.print("Opción: ");

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2"));

        if (opcion.equals("1")) return "Activo";
        return "Inactivo";
    }

    public static void menuAdmin(Scanner sc, GestionUsuarios gu, GestionPrestamos gp) {

        String opcion;

        do {
            System.out.println("\n--- MENÚ ADMIN ---");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Eliminar Usuario");
            System.out.println("3. Registrar Préstamo");
            System.out.println("4. Devolver Libro");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarUsuario(sc, gu);
                    break;
                case "2":
                    System.out.print("Documento: ");
                    int doc = Integer.parseInt(sc.nextLine());

                    if (gu.eliminarUsuario(doc)) {
                        System.out.println("Usuario eliminado");
                    } else {
                        System.out.println("No encontrado");
                    }
                    break;
                case "3":
                    registrarPrestamo(sc, gp);
                    break;
                case "4":
                    devolverLibro(sc, gp);
                    break;
            }

        } while (!opcion.equals("5"));
    }

    public static void menuBibliotecario(Scanner sc, GestionPrestamos gp) {

        String opcion;

        do {
            System.out.println("\n--- MENÚ BIBLIOTECARIO ---");
            System.out.println("1. Registrar Préstamo");
            System.out.println("2. Devolver Libro");
            System.out.println("3. Ver préstamos");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarPrestamo(sc, gp);
                    break;
                case "2":
                    devolverLibro(sc, gp);
                    break;
                case "3":
                    if (gp.listarPrestamos().isEmpty()) {
                        System.out.println("No hay préstamos registrados");
                    } else {
                        for (Libro l : gp.listarPrestamos()) {
                            System.out.println(l);
                        }
                    }
                    break;
            }

        } while (!opcion.equals("4"));
    }

    public static void menuLector(GestionPrestamos gp) {

        if (gp.listarPrestamos().isEmpty()) {
            System.out.println("No hay préstamos registrados");
        } else {
            for (Libro l : gp.listarPrestamos()) {
                System.out.println(l);
            }
        }
    }

    public static void registrarPrestamo(Scanner sc, GestionPrestamos gp) {

        String nombreUsuario;
        do {
            System.out.print("Nombre del usuario: ");
            nombreUsuario = sc.nextLine();
        } while (Configuracion.campoVacio(nombreUsuario));

        String docStr;
        int documento;
        do {
            System.out.print("Documento del usuario: ");
            docStr = sc.nextLine();
        } while (Configuracion.campoVacio(docStr));

        documento = Integer.parseInt(docStr);

        String titulo;
        do {
            System.out.print("Título: ");
            titulo = sc.nextLine();
        } while (Configuracion.campoVacio(titulo));

        String autor;
        do {
            System.out.print("Autor: ");
            autor = sc.nextLine();
        } while (Configuracion.campoVacio(autor));

        String isbn;
        do {
            System.out.print("ISBN: ");
            isbn = sc.nextLine();
        } while (Configuracion.campoVacio(isbn));

        String anioStr;
        int anio;

        do {
            System.out.print("Año: ");
            anioStr = sc.nextLine();
        } while (Configuracion.campoVacio(anioStr));

        anio = Integer.parseInt(anioStr);

        Libro libro = new Libro(titulo, autor, isbn, anio, "Disponible");
        libro.setPrestamista(nombreUsuario, documento);

        if (Configuracion.validarLibro(libro)) {
            gp.registrarPrestamo(libro);
            System.out.println("Préstamo realizado a: " + nombreUsuario);
            System.out.println("Documento: " + documento);
        }
    }

    public static void devolverLibro(Scanner sc, GestionPrestamos gp) {

        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        if (gp.devolverLibro(isbn)) {
            System.out.println("Libro devuelto");
        } else {
            System.out.println("No encontrado");
        }
    }
}