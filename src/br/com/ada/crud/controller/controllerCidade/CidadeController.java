package br.com.ada.crud.controller.controllerCidade;
import br.com.ada.crud.model.modelCidade.Cidade;

import java.util.List;
import java.util.UUID;

public interface CidadeController {

    void cadastrar (Cidade cidade);

    Cidade ler (UUID id);

    List<Cidade> listar();

    void atualizar (UUID id, Cidade cidade);

    Cidade apagar (UUID id);

}
