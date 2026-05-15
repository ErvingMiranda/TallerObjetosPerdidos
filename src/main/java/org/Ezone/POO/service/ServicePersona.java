package org.Ezone.POO.service;

import org.Ezone.POO.database.IDataBase;
import org.Ezone.POO.exception.EntidadNoEncontradaException;
import org.Ezone.POO.model.Persona;
import org.Ezone.POO.validator.ValidadorJakarta;

import java.util.List;

public class ServicePersona implements ICrudService<Persona> {
    private final IDataBase<Persona> dataBase;

    public ServicePersona(IDataBase<Persona> dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void registrar(Persona persona) {
        ValidadorJakarta.validar(persona);
        dataBase.guardar(persona);
    }

    @Override
    public Persona buscarPorId(String id) {
        return dataBase.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("No encontré una persona con ese identificador."));
    }

    @Override
    public List<Persona> listar() {
        return dataBase.obtenerTodos();
    }

    public List<Persona> buscarPorNombre(String nombre) {
        return dataBase.obtenerTodos()
                .stream()
                .filter(persona -> persona.getNombre().equalsIgnoreCase(nombre.trim()))
                .toList();
    }

    public List<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
        return dataBase.obtenerTodos()
                .stream()
                .filter(persona ->
                        persona.getNombre().equalsIgnoreCase(nombre.trim()) &&
                                persona.getApellido().equalsIgnoreCase(apellido.trim()))
                .toList();
    }
}