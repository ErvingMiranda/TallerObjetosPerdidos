package org.Ezone.POO.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.Ezone.POO.annotation.TextoLimpio;
import org.Ezone.POO.exception.ObjetoNoDisponibleException;

import java.time.LocalDate;

public class ObjetoPerdido extends Identity {
    @NotBlank(message = "El nombre del objeto no puede estar vacío")
    @Size(min = 3, max = 60, message = "El nombre del objeto debe tener entre 3 y 60 caracteres")
    private String nombreObjeto;

    @Size(min = 15, message = "Escribe una descripción adecuada, aunque sea breve")
    private String descripcion;

    @TextoLimpio
    private String lugarEncontrado;

    private LocalDate fechaEncontrado;
    private EstadoObjeto estado;
    private Reportante reportante;

    public ObjetoPerdido(String nombreObjeto, String descripcion, String lugarEncontrado, LocalDate fechaEncontrado, EstadoObjeto estado, Reportante reportante) {
        this.nombreObjeto = nombreObjeto;
        this.descripcion = descripcion;
        this.lugarEncontrado = lugarEncontrado;
        this.fechaEncontrado = fechaEncontrado;
        this.estado = estado;
        this.reportante = reportante;
    }

    public ObjetoPerdido() {
        this.estado = EstadoObjeto.DISPONIBLE;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugarEncontrado() {
        return lugarEncontrado;
    }

    public void setLugarEncontrado(String lugarEncontrado) {
        this.lugarEncontrado = lugarEncontrado;
    }

    public LocalDate getFechaEncontrado() {
        return fechaEncontrado;
    }

    public void setFechaEncontrado(LocalDate fechaEncontrado) {
        this.fechaEncontrado = fechaEncontrado;
    }

    public EstadoObjeto getEstado() {
        return estado;
    }

    public Reportante getReportante() {
        return reportante;
    }

    public void setReportante(Reportante reportante) {
        this.reportante = reportante;
    }

    public void marcarComoReclamado() {
        if (estado != EstadoObjeto.DISPONIBLE) {
            throw new ObjetoNoDisponibleException("El objeto no está disponible para reclamo.");
        }
        estado = EstadoObjeto.RECLAMADO;
    }

    public void marcarComoEntregado() {
        if (estado == EstadoObjeto.ENTREGADO) {
            throw new ObjetoNoDisponibleException("El objeto ya fue entregado.");
        }
        estado = EstadoObjeto.ENTREGADO;
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                "\nDescripción: " + descripcion +
                "\nLugar encontrado: " + lugarEncontrado +
                "\nFecha: " + fechaEncontrado +
                "\nEstado: " + estado +
                "\nReportado por: " + reportante.getNombreCompleto();
    }
}