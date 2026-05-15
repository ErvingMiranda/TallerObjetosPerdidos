package org.Ezone.POO.database;

import org.Ezone.POO.model.Identity;

import java.util.List;
import java.util.Optional;

public interface IDataBase<T extends Identity> {
    void guardar(T entidad);
    Optional<T> buscarPorId(String id);
    List<T> obtenerTodos();
}