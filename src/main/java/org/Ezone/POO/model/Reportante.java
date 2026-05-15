package org.Ezone.POO.model;

import org.Ezone.POO.annotation.TextoLimpio;

import jakarta.validation.constraints.NotBlank;

public class Reportante extends Persona {

    @NotBlank(message = "El área no puede estar vacía.")
    @TextoLimpio
    private String area;

    public Reportante(String nombre, String apellido, String correo, String area) {
        super(nombre, apellido, correo);
        this.area = area;
    }

    public Reportante() {}
    
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void reportarObjeto(ObjetoPerdido objeto) {
        System.out.println("Objeto reportado correctamente: " + objeto.getDescripcion());
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nÁrea: " + area;
    }
}