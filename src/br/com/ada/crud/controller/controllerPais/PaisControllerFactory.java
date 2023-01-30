package br.com.ada.crud.controller.controllerPais;

import br.com.ada.crud.controller.ArmazenamentoTipo;
import br.com.ada.crud.controller.controllerPais.impl.PaisArmazenamentoDefinitivoController;
import br.com.ada.crud.controller.controllerPais.impl.PaisArmazenamentoVolatilController;
import br.com.ada.crud.model.PersistenciaTipo;

public class PaisControllerFactory {

    private PaisDAOFactory daoFactory;

    public PaisControllerFactory (PaisDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public PaisController criar (ArmazenamentoTipo tipo, PersistenciaTipo tipoPersistencia) {
        if (tipo == ArmazenamentoTipo.VOLATIL) {
            return new PaisArmazenamentoVolatilController();
        } else if (tipo == ArmazenamentoTipo.DEFINITIVO){
            return new PaisArmazenamentoDefinitivoController(daoFactory.create(tipoPersistencia));
        } else {
            throw new RuntimeException("Nenhuma implementação disponível!");
        }
    }
}