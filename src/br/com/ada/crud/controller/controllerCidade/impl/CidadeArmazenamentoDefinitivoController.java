package br.com.ada.crud.controller.controllerCidade.impl;

import br.com.ada.crud.controller.controllerCidade.CidadeController;
import br.com.ada.crud.model.modelCidade.Cidade;
import br.com.ada.crud.model.modelCidade.cidade.dao.CidadeDAO;

import java.util.List;
import java.util.UUID;

public class CidadeArmazenamentoDefinitivoController implements CidadeController{

    private CidadeDAO cidadeDAO;

    public CidadeArmazenamentoDefinitivoController(CidadeDAO cidadeDAO) {
        this.cidadeDAO = cidadeDAO;
    }
    @Override
    public void cadastrar(Cidade cidade) {
        cidade.setId(UUID.randomUUID());
        cidadeDAO.cadastrar(cidade);
    }

    @Override
    public Cidade ler(UUID id) {
        return cidadeDAO.buscar(id);
    }

    @Override
    public List<Cidade> listar() {
        return cidadeDAO.listar();
    }

    @Override
    public void atualizar(UUID id, Cidade cidade) {
        cidadeDAO.atualizar(id, cidade);
    }

    @Override
    public Cidade apagar(UUID id) {
        return cidadeDAO.apagar(id);
    }

}
