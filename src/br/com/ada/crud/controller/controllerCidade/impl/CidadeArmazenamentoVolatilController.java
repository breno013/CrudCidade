package br.com.ada.crud.controller.controllerCidade.impl;

import br.com.ada.crud.controller.controllerCidade.CidadeController;
import br.com.ada.crud.controller.controllerCidade.exception.CidadeNaoEncontrada;
import br.com.ada.crud.model.modelCidade.Cidade;

import java.util.*;
public class CidadeArmazenamentoVolatilController implements CidadeController {

    private Map<UUID, Cidade> cidades = new HashMap<>();
    @Override
    public void cadastrar(Cidade cidade) {
        cidade.setId(UUID.randomUUID());
        cidades.put(cidade.getId(), cidade);
    }

    @Override
    public Cidade ler(UUID id) {
        Cidade encontrada = cidades.get(id);
        if (encontrada == null) {
            throw new CidadeNaoEncontrada();
        }
        return encontrada;
    }

    @Override
    public List<Cidade> listar() {
        return new ArrayList<>(cidades.values());
    }

    @Override
    public void atualizar(UUID id, Cidade cidade) {
        if (cidades.containsKey(id)) {
            cidades.put(id, cidade);
        } else {
            throw new CidadeNaoEncontrada();
        }

    }

    @Override
    public Cidade apagar(UUID id) {
        Cidade apagada = cidades.remove(id);
        if (apagada == null) {
            throw new CidadeNaoEncontrada();
        }
        return apagada;
    }

}
