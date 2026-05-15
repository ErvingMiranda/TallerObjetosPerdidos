package org.Ezone.POO.model;

import jakarta.validation.constraints.NotNull;
import org.Ezone.POO.exception.ReclamoInvalidoException;

import java.time.LocalDate;

public class Reclamo extends Identity {

    @NotNull(message = "El objeto reclamado no puede estar vacío")
    private ObjetoPerdido objeto;

    @NotNull(message = "El reclamante no puede estar vacío")
    private Reclamante reclamante;

    private LocalDate fechaReclamo;
    private boolean aprobado;

    public Reclamo() {
        this.fechaReclamo = LocalDate.now();
        this.aprobado = false;
    }

    public Reclamo(ObjetoPerdido objeto, Reclamante reclamante) {
        this.objeto = objeto;
        this.reclamante = reclamante;
        this.fechaReclamo = LocalDate.now();
        this.aprobado = false;
    }

    public ObjetoPerdido getObjeto() {
        return objeto;
    }

    public void setObjeto(ObjetoPerdido objeto) {
        this.objeto = objeto;
    }

    public Reclamante getReclamante() {
        return reclamante;
    }

    public void setReclamante(Reclamante reclamante) {
        this.reclamante = reclamante;
    }

    public LocalDate getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(LocalDate fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public boolean validarReclamo() {
        return objeto != null &&
                reclamante != null &&
                objeto.getEstado() == EstadoObjeto.DISPONIBLE &&
                reclamante.getPruebaPropiedad() != null &&
                reclamante.getPruebaPropiedad().trim().length() >= 10;
    }

    public void aprobar() {
        if (!validarReclamo()) {
            throw new ReclamoInvalidoException("El reclamo no puede aprobarse. Revise que el objeto esté disponible y que la prueba de propiedad sea válida");
        }

        aprobado = true;
        objeto.marcarComoReclamado();
        objeto.marcarComoEntregado();
    }

    public void rechazar() {
        aprobado = false;
    }

    @Override
    public String toString() {
        String nombreObjeto = objeto != null ? objeto.getNombreObjeto() : "Sin objeto";
        String descripcionObjeto = objeto != null ? objeto.getDescripcion() : "Sin descripción";
        String nombreReclamante = reclamante != null ? reclamante.getNombreCompleto() : "Sin reclamante";

        return "Objeto: " + nombreObjeto +
                "\nDescripción: " + descripcionObjeto +
                "\nReclamante: " + nombreReclamante +
                "\nFecha del reclamo: " + fechaReclamo +
                "\nAprobado: " + (aprobado ? "Sí" : "No");
    }
}