package org.Ezone.POO.service;

import org.Ezone.POO.database.IDataBase;
import org.Ezone.POO.exception.EntidadNoEncontradaException;
import org.Ezone.POO.model.Reclamo;
import org.Ezone.POO.validator.ValidadorJakarta;

import java.util.List;

public class ServiceReclamo implements ICrudService<Reclamo> {
    private final IDataBase<Reclamo> dataBase;

    public ServiceReclamo(IDataBase<Reclamo> dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void registrar(Reclamo reclamo) {
        ValidadorJakarta.validar(reclamo);
        dataBase.guardar(reclamo);
    }

    @Override
    public Reclamo buscarPorId(String id) {
        return dataBase.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("No encontré un reclamo con ese identificador."));
    }

    @Override
    public List<Reclamo> listar() {
        return dataBase.obtenerTodos();
    }

    public void aprobarReclamo(Reclamo reclamo) {
        reclamo.aprobar();
    }

    public void rechazarReclamo(Reclamo reclamo) {
        reclamo.rechazar();
    }
}