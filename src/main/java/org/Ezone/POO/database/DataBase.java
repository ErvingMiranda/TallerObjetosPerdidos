package org.Ezone.POO.database;

import org.Ezone.POO.model.Identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataBase<T extends Identity> implements IDataBase<T> {
    private final List<T> registros = new ArrayList<>();

    @Override
    public void guardar(T entidad) {
        registros.add(entidad);
    }

    @Override
    public Optional<T> buscarPorId(String id) {
        return registros.stream()
                .filter(entidad -> entidad.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<T> obtenerTodos() {
        return registros;
    }
}