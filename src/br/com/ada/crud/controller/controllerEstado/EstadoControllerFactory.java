package br.com.ada.crud.controller.controllerEstado;

import br.com.ada.crud.controller.ArmazenamentoTipo;
import br.com.ada.crud.controller.controllerEstado.impl.EstadoArmazenamentoDefinitivoController;
import br.com.ada.crud.controller.controllerEstado.impl.EstadoArmazenamentoVolatilController;
import br.com.ada.crud.model.PersistenciaTipo;

public class EstadoControllerFactory {

    private EstadoDAOFactory daoFactory;

    public EstadoControllerFactory (EstadoDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public EstadoController criar (ArmazenamentoTipo tipo, PersistenciaTipo tipoPersistencia) {
        if (tipo == ArmazenamentoTipo.VOLATIL) {
            return new EstadoArmazenamentoVolatilController();
        } else if (tipo == ArmazenamentoTipo.DEFINITIVO){
            return new EstadoArmazenamentoDefinitivoController(daoFactory.create(tipoPersistencia));
        } else {
            throw new RuntimeException("Nenhuma implementação disponível!");
        }
    }
}
