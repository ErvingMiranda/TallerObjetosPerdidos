package org.Ezone.POO.service;

import org.Ezone.POO.database.IDataBase;
import org.Ezone.POO.exception.EntidadNoEncontradaException;
import org.Ezone.POO.model.ObjetoPerdido;
import org.Ezone.POO.validator.ValidadorJakarta;

import java.util.List;

public class ServiceObjetoPerdido implements ICrudService<ObjetoPerdido> {
    private final IDataBase<ObjetoPerdido> dataBase;

    public ServiceObjetoPerdido(IDataBase<ObjetoPerdido> dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void registrar(ObjetoPerdido objeto) {
        ValidadorJakarta.validar(objeto);
        dataBase.guardar(objeto);
    }

    @Override
    public ObjetoPerdido buscarPorId(String id) {
        return dataBase.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("No encontré un objeto con ese identificador."));
    }

    @Override
    public List<ObjetoPerdido> listar() {
        return dataBase.obtenerTodos();
    }

    public List<ObjetoPerdido> buscarPorNombreObjeto(String nombreObjeto) {
        return dataBase.obtenerTodos()
                .stream()
                .filter(objeto -> objeto.getNombreObjeto().equalsIgnoreCase(nombreObjeto.trim()))
                .toList();
    }
}