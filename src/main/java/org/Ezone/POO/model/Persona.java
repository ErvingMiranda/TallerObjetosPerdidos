package org.Ezone.POO.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.Ezone.POO.annotation.TextoLimpio;

public abstract class Persona extends Identity {

    @NotBlank(message = "El nombre no puede estar vacío")
    @TextoLimpio
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @TextoLimpio
    private String apellido;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Escribe un correo válido, por ejemplo usuario@correo.com")
    private String correo;

    public Persona(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    protected Persona() {}
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                "\nNombre: " + getNombreCompleto() +
                "\nCorreo: " + correo;
    }
}