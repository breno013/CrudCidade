package br.com.ada.crud.controller.controllerEstado;

import br.com.ada.crud.model.PersistenciaTipo;
import br.com.ada.crud.model.modelEstado.estado.dao.EstadoDAO;
import br.com.ada.crud.model.modelEstado.estado.dao.impl.EstadoBinaryDAO;
import br.com.ada.crud.model.modelEstado.estado.dao.impl.EstadoXMLDao;

public class EstadoDAOFactory {

    public EstadoDAO create(PersistenciaTipo tipo) {
        EstadoDAO estadoDAO = null;
        if (tipo == PersistenciaTipo.BINARIA) {
            estadoDAO = new EstadoBinaryDAO();
        } else if (tipo == PersistenciaTipo.XML) {
            estadoDAO = new EstadoXMLDao();
        }
        return estadoDAO;
    }
}
