package br.com.ada.crud.controller.controllerCidade;

import br.com.ada.crud.controller.ArmazenamentoTipo;
import br.com.ada.crud.controller.controllerCidade.impl.CidadeArmazenamentoDefinitivoController;
import br.com.ada.crud.controller.controllerCidade.impl.CidadeArmazenamentoVolatilController;
import br.com.ada.crud.model.PersistenciaTipo;

public class CidadeControllerFactory {

    private CidadeDAOFactory daoFactory;

    public CidadeControllerFactory (CidadeDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public CidadeController criar (ArmazenamentoTipo tipo, PersistenciaTipo tipoPersistencia) {
        if (tipo == ArmazenamentoTipo.VOLATIL) {
            return new CidadeArmazenamentoVolatilController();
        } else if (tipo == ArmazenamentoTipo.DEFINITIVO){
            return new CidadeArmazenamentoDefinitivoController(daoFactory.create(tipoPersistencia));
        } else {
            throw new RuntimeException("Nenhuma implementação disponível!");
        }
    }

}
