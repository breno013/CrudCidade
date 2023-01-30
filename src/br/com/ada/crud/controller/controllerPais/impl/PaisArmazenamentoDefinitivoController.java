package br.com.ada.crud.controller.controllerPais.impl;

import br.com.ada.crud.controller.controllerPais.PaisController;
import br.com.ada.crud.model.modelPais.Pais;
import br.com.ada.crud.model.modelPais.pais.dao.PaisDAO;

import java.util.List;
import java.util.UUID;

public class PaisArmazenamentoDefinitivoController implements PaisController {

    private PaisDAO paisDAO;

    public PaisArmazenamentoDefinitivoController(PaisDAO paisDAO) {
        this.paisDAO = paisDAO;
    }
    @Override
    public void cadastrar(Pais pais) {
        pais.setId(UUID.randomUUID());
        paisDAO.cadastrar(pais);
    }

    @Override
    public Pais ler(UUID id) {
        return paisDAO.buscar(id);
    }

    @Override
    public List<Pais> listar() {
        return paisDAO.listar();
    }

    @Override
    public void atualizar(UUID id, Pais pais) {
        paisDAO.atualizar(id, pais);
    }

    @Override
    public Pais apagar(UUID id) {
        return paisDAO.apagar(id);
    }
}
