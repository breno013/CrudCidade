package br.com.ada.crud.cidade.controller;

import br.com.ada.crud.cidade.model.Cidade;

import java.util.List;
import java.util.UUID;

public interface CidadeController {

    void cadastrar(Cidade cidade);

    Cidade ler(UUID id);

    List<Cidade> listar();
}
