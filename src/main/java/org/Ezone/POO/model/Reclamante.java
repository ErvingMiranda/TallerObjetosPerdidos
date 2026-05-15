package org.Ezone.POO.model;

import jakarta.validation.constraints.NotBlank;

public class Reclamante extends Persona {

    @NotBlank(message = "La prueba de propiedad no puede estar vacía.")
    private String pruebaPropiedad;

    public Reclamante(String nombre, String apellido, String correo, String pruebaPropiedad) {
        super(nombre, apellido, correo);
        this.pruebaPropiedad = pruebaPropiedad;
    }

    public Reclamante() {}

    public String getPruebaPropiedad() {
        return pruebaPropiedad;
    }

    public void setPruebaPropiedad(String pruebaPropiedad) {
        this.pruebaPropiedad = pruebaPropiedad;
    }

    public void solicitarReclamo() {
        System.out.println("Solicitud de reclamo registrada para: " + getNombre());
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nPrueba de propiedad: " + pruebaPropiedad;
    }
}