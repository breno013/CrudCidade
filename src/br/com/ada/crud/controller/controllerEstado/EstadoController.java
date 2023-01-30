package br.com.ada.crud.controller.controllerEstado;

import br.com.ada.crud.model.modelEstado.estado.Estado;

import java.util.List;
import java.util.UUID;

public interface EstadoController {

    void cadastrar (Estado estado);

    Estado ler (UUID id);

    List<Estado> listar();

    void atualizar (UUID id, Estado estado);

    Estado apagar (UUID id);

}