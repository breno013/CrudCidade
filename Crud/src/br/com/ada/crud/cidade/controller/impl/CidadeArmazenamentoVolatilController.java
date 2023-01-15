package br.com.ada.crud.cidade.controller.impl;

import br.com.ada.crud.cidade.controller.CidadeController;
import br.com.ada.crud.cidade.model.Cidade;

import java.util.*;

public class CidadeArmazenamentoVolatilController implements CidadeController{

    private final Map<UUID, Cidade> cidades = new HashMap<>();

    @Override
    public void cadastrar(Cidade cidade) {
        cidade.setId(UUID.randomUUID());
        cidades.put(cidade.getId(), cidade);
    }

    @Override
    public Cidade ler(UUID id) {
        Cidade encontrada = cidades.get(id);
        return encontrada;
    }

    @Override
    public List<Cidade> listar() {
        return new ArrayList<>(cidades.values());
    }

}
