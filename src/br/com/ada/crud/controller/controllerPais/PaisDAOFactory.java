package br.com.ada.crud.controller.controllerPais;

import br.com.ada.crud.model.PersistenciaTipo;
import br.com.ada.crud.model.modelPais.pais.dao.PaisDAO;
import br.com.ada.crud.model.modelPais.pais.dao.impl.PaisBinaryDAO;
import br.com.ada.crud.model.modelPais.pais.dao.impl.PaisXMLDao;

public class PaisDAOFactory {

    public PaisDAO create (PersistenciaTipo tipo) {
        PaisDAO paisDAO = null;
        if (tipo == PersistenciaTipo.BINARIA) {
            paisDAO = new PaisBinaryDAO();
        } else if (tipo == PersistenciaTipo.XML) {
            paisDAO = new PaisXMLDao();
        }
        return paisDAO;
    }
}
