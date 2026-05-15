package org.Ezone.POO.ui;

import org.Ezone.POO.database.DataBase;
import org.Ezone.POO.model.ObjetoPerdido;
import org.Ezone.POO.model.Persona;
import org.Ezone.POO.model.Reclamante;
import org.Ezone.POO.model.Reclamo;
import org.Ezone.POO.model.Reportante;
import org.Ezone.POO.service.ServiceObjetoPerdido;
import org.Ezone.POO.service.ServicePersona;
import org.Ezone.POO.service.ServiceReclamo;
import org.Ezone.POO.validator.ValidadorJakarta;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final ServicePersona servicePersona;
    private final ServiceObjetoPerdido serviceObjetoPerdido;
    private final ServiceReclamo serviceReclamo;

    public Menu() {
        scanner = new Scanner(System.in);
        servicePersona = new ServicePersona(new DataBase<>());
        serviceObjetoPerdido = new ServiceObjetoPerdido(new DataBase<>());
        serviceReclamo = new ServiceReclamo(new DataBase<>());
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> menuPersonas();
                    case 2 -> menuObjetos();
                    case 3 -> menuReclamos();
                    case 0 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("Sistema de custodia de objetos perdidos");
        System.out.println("1. Administrar personas");
        System.out.println("2. Administrar objetos perdidos");
        System.out.println("3. Administrar reclamos");
        System.out.println("0. Salir");
    }

    private void menuPersonas() {
        int opcion;

        do {
            System.out.println();
            System.out.println("Administrar personas");
            System.out.println("1. Registrar reportante");
            System.out.println("2. Registrar reclamante");
            System.out.println("3. Listar personas");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> registrarReportante();
                    case 2 -> registrarReclamante();
                    case 3 -> listarPersonas();
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private void menuObjetos() {
        int opcion;

        do {
            System.out.println();
            System.out.println("Administrar objetos perdidos");
            System.out.println("1. Registrar objeto perdido");
            System.out.println("2. Listar objetos perdidos");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> registrarObjetoPerdido();
                    case 2 -> listarObjetos();
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private void menuReclamos() {
        int opcion;

        do {
            System.out.println();
            System.out.println("Administrar reclamos");
            System.out.println("1. Registrar reclamo");
            System.out.println("2. Aprobar reclamo");
            System.out.println("3. Rechazar reclamo");
            System.out.println("4. Listar reclamos");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> registrarReclamo();
                    case 2 -> aprobarReclamo();
                    case 3 -> rechazarReclamo();
                    case 4 -> listarReclamos();
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private void registrarReportante() {
        System.out.println();
        System.out.println("Registrar reportante");

        Reportante reportante = new Reportante();

        leerTextoValidado("Nombre: ", reportante, "nombre");
        leerTextoValidado("Apellido: ", reportante, "apellido");
        leerTextoValidado("Correo: ", reportante, "correo");
        leerTextoValidado("Área: ", reportante, "area");

        servicePersona.registrar(reportante);

        System.out.println("Reportante registrado correctamente.");
    }

    private void registrarReclamante() {
        System.out.println();
        System.out.println("Registrar reclamante");

        Reclamante reclamante = new Reclamante();

        leerTextoValidado("Nombre: ", reclamante, "nombre");
        leerTextoValidado("Apellido: ", reclamante, "apellido");
        leerTextoValidado("Correo: ", reclamante, "correo");
        leerTextoValidado("Prueba de propiedad: ", reclamante, "pruebaPropiedad");

        servicePersona.registrar(reclamante);

        System.out.println("Reclamante registrado correctamente.");
    }

    private void registrarObjetoPerdido() {
        System.out.println();
        System.out.println("Registrar objeto perdido");

        Persona persona = seleccionarPersonaPorNombre("Nombre del reportante: ");

        if (!(persona instanceof Reportante reportante)) {
            System.out.println("La persona seleccionada no está registrada como reportante.");
            return;
        }

        ObjetoPerdido objeto = new ObjetoPerdido();
        objeto.setReportante(reportante);

        leerTextoValidado("Nombre del objeto: ", objeto, "nombreObjeto");
        leerTextoValidado("Descripción del objeto: ", objeto, "descripcion");
        leerTextoValidado("Lugar encontrado: ", objeto, "lugarEncontrado");

        LocalDate fechaEncontrado = leerFecha("Fecha encontrado (YYYY-MM-DD): ");
        objeto.setFechaEncontrado(fechaEncontrado);

        serviceObjetoPerdido.registrar(objeto);

        reportante.reportarObjeto(objeto);

        System.out.println("Objeto perdido registrado correctamente.");
    }

    private void registrarReclamo() {
        System.out.println();
        System.out.println("Registrar reclamo");

        ObjetoPerdido objeto = seleccionarObjetoPorNombre();
        Persona persona = seleccionarPersonaPorNombre("Nombre del reclamante: ");

        if (!(persona instanceof Reclamante reclamante)) {
            System.out.println("La persona seleccionada no está registrada como reclamante.");
            return;
        }

        Reclamo reclamo = new Reclamo(objeto, reclamante);
        serviceReclamo.registrar(reclamo);

        reclamante.solicitarReclamo();

        System.out.println("Reclamo registrado correctamente.");
    }

    private void aprobarReclamo() {
        System.out.println();
        System.out.println("Aprobar reclamo");

        Reclamo reclamo = seleccionarReclamo();
        serviceReclamo.aprobarReclamo(reclamo);

        System.out.println("Reclamo aprobado correctamente.");
    }

    private void rechazarReclamo() {
        System.out.println();
        System.out.println("Rechazar reclamo");

        Reclamo reclamo = seleccionarReclamo();
        serviceReclamo.rechazarReclamo(reclamo);

        System.out.println("Reclamo rechazado correctamente.");
    }

    private void listarPersonas() {
        System.out.println();
        System.out.println("Personas registradas");

        List<Persona> personas = servicePersona.listar();

        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }

        for (Persona persona : personas) {
            System.out.println(persona);
            System.out.println();
        }
    }

    private void listarObjetos() {
        System.out.println();
        System.out.println("Objetos perdidos registrados");

        List<ObjetoPerdido> objetos = serviceObjetoPerdido.listar();

        if (objetos.isEmpty()) {
            System.out.println("No hay objetos perdidos registrados.");
            return;
        }

        for (ObjetoPerdido objeto : objetos) {
            System.out.println(objeto);
            System.out.println();
        }
    }

    private void listarReclamos() {
        System.out.println();
        System.out.println("Reclamos registrados");

        List<Reclamo> reclamos = serviceReclamo.listar();

        if (reclamos.isEmpty()) {
            System.out.println("No hay reclamos registrados.");
            return;
        }

        for (Reclamo reclamo : reclamos) {
            System.out.println(reclamo);
            System.out.println();
        }
    }

    private Persona seleccionarPersonaPorNombre(String mensaje) {
        String nombre = leerTexto(mensaje);
        List<Persona> personas = servicePersona.buscarPorNombre(nombre);

        if (personas.isEmpty()) {
            throw new RuntimeException("No encontré personas registradas con ese nombre.");
        }

        if (personas.size() == 1) {
            Persona persona = personas.get(0);
            System.out.println("Persona encontrada: " + persona.getNombreCompleto());
            return persona;
        }

        System.out.println("Encontré varias personas con ese nombre:");

        for (Persona persona : personas) {
            System.out.println("- " + persona.getNombreCompleto());
        }

        String apellido = leerTexto("Apellido de la persona: ");
        List<Persona> coincidencias = servicePersona.buscarPorNombreYApellido(nombre, apellido);

        if (coincidencias.isEmpty()) {
            throw new RuntimeException("No encontré una persona con ese nombre y apellido.");
        }

        if (coincidencias.size() == 1) {
            Persona persona = coincidencias.get(0);
            System.out.println("Persona seleccionada: " + persona.getNombreCompleto());
            return persona;
        }

        System.out.println("Hay más de una persona con el mismo nombre completo:");

        for (int i = 0; i < coincidencias.size(); i++) {
            Persona persona = coincidencias.get(i);
            System.out.println((i + 1) + ". " + persona.getNombreCompleto());
        }

        int opcion = leerEntero("Seleccione una opción: ");

        if (opcion < 1 || opcion > coincidencias.size()) {
            throw new RuntimeException("La opción seleccionada no es válida.");
        }

        return coincidencias.get(opcion - 1);
    }

    private ObjetoPerdido seleccionarObjetoPorNombre() {
        String nombreObjeto = leerTexto("Nombre del objeto: ");
        List<ObjetoPerdido> objetos = serviceObjetoPerdido.buscarPorNombreObjeto(nombreObjeto);

        if (objetos.isEmpty()) {
            throw new RuntimeException("No encontré objetos registrados con ese nombre.");
        }

        System.out.println("Se le mostrarán los objetos registrados con ese nombre.");
        System.out.println("Revise la información y seleccione cuál corresponde.");
        System.out.println("Si ninguno coincide, seleccione 0.");

        for (int i = 0; i < objetos.size(); i++) {
            ObjetoPerdido objeto = objetos.get(i);

            System.out.println();
            System.out.println((i + 1) + ". " + objeto.getNombreObjeto());
            System.out.println("Descripción: " + objeto.getDescripcion());
            System.out.println("Lugar encontrado: " + objeto.getLugarEncontrado());
            System.out.println("Fecha encontrado: " + objeto.getFechaEncontrado());
            System.out.println("Estado: " + objeto.getEstado());
        }

        int opcion = leerEntero("Seleccione una opción: ");

        if (opcion == 0) {
            throw new RuntimeException("No se seleccionó ningún objeto.");
        }

        if (opcion < 1 || opcion > objetos.size()) {
            throw new RuntimeException("La opción seleccionada no es válida.");
        }

        return objetos.get(opcion - 1);
    }

    private Reclamo seleccionarReclamo() {
        List<Reclamo> reclamos = serviceReclamo.listar();

        if (reclamos.isEmpty()) {
            throw new RuntimeException("No hay reclamos registrados.");
        }

        System.out.println("Seleccione el reclamo:");

        for (int i = 0; i < reclamos.size(); i++) {
            Reclamo reclamo = reclamos.get(i);

            System.out.println();
            System.out.println((i + 1) + ". Reclamo de " + reclamo.getReclamante().getNombreCompleto());
            System.out.println("Objeto: " + reclamo.getObjeto().getNombreObjeto());
            System.out.println("Descripción: " + reclamo.getObjeto().getDescripcion());
            System.out.println("Fecha reclamo: " + reclamo.getFechaReclamo());
            System.out.println("Aprobado: " + (reclamo.isAprobado() ? "Sí" : "No"));
        }

        int opcion = leerEntero("Seleccione una opción: ");

        if (opcion < 1 || opcion > reclamos.size()) {
            throw new RuntimeException("La opción seleccionada no es válida.");
        }

        return reclamos.get(opcion - 1);
    }

    private String leerTextoValidado(String mensaje, Object objeto, String propiedad) {
        while (true) {
            try {
                System.out.print(mensaje);
                String valor = scanner.nextLine();

                asignarValorTemporal(objeto, propiedad, valor);
                ValidadorJakarta.validarPropiedad(objeto, propiedad);

                return valor;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void asignarValorTemporal(Object objeto, String propiedad, String valor) {
        switch (propiedad) {
            case "nombre" -> {
                if (objeto instanceof Persona persona) {
                    persona.setNombre(valor);
                }
            }
            case "apellido" -> {
                if (objeto instanceof Persona persona) {
                    persona.setApellido(valor);
                }
            }
            case "correo" -> {
                if (objeto instanceof Persona persona) {
                    persona.setCorreo(valor);
                }
            }
            case "area" -> {
                if (objeto instanceof Reportante reportante) {
                    reportante.setArea(valor);
                }
            }
            case "pruebaPropiedad" -> {
                if (objeto instanceof Reclamante reclamante) {
                    reclamante.setPruebaPropiedad(valor);
                }
            }
            case "nombreObjeto" -> {
                if (objeto instanceof ObjetoPerdido objetoPerdido) {
                    objetoPerdido.setNombreObjeto(valor);
                }
            }
            case "descripcion" -> {
                if (objeto instanceof ObjetoPerdido objetoPerdido) {
                    objetoPerdido.setDescripcion(valor);
                }
            }
            case "lugarEncontrado" -> {
                if (objeto instanceof ObjetoPerdido objetoPerdido) {
                    objetoPerdido.setLugarEncontrado(valor);
                }
            }
            default -> throw new IllegalArgumentException("Propiedad no reconocida.");
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use el formato YYYY-MM-DD.");
            }
        }
    }
}