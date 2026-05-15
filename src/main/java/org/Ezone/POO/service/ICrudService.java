package org.Ezone.POO.service;

import java.util.List;

public interface ICrudService<T> {
    void registrar(T entidad);
    T buscarPorId(String id);
    List<T> listar();
}