package br.com.ada.crud.controller.controllerCidade;

import br.com.ada.crud.model.modelCidade.cidade.dao.CidadeDAO;
import br.com.ada.crud.model.PersistenciaTipo;
import br.com.ada.crud.model.modelCidade.cidade.dao.impl.CidadeBinaryDAO;
import br.com.ada.crud.model.modelCidade.cidade.dao.impl.CidadeXMLDao;

public class CidadeDAOFactory {

    public CidadeDAO create(PersistenciaTipo tipo) {
        CidadeDAO cidadeDAO = null;
        if (tipo == PersistenciaTipo.BINARIA) {
            cidadeDAO = new CidadeBinaryDAO();
        } else if (tipo == PersistenciaTipo.XML) {
            cidadeDAO = new CidadeXMLDao();
        }
        return cidadeDAO;
    }
}
