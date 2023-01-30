package br.com.ada.crud.controller.controllerEstado.impl;

import br.com.ada.crud.controller.controllerEstado.EstadoController;

import br.com.ada.crud.controller.controllerEstado.exception.EstadoNaoEncontrado;
import br.com.ada.crud.model.modelEstado.estado.Estado;

import java.util.*;

public class EstadoArmazenamentoVolatilController implements EstadoController {

    private Map<UUID, Estado> estados = new HashMap<>();
    @Override
    public void cadastrar(Estado estado) {
        estado.setId(UUID.randomUUID());
        estados.put(estado.getId(), estado);
    }

    @Override
    public Estado ler(UUID id) {
        Estado encontrado = estados.get(id);
        if (encontrado == null) {
            throw new EstadoNaoEncontrado();
        }
        return encontrado;
    }

    @Override
    public List<Estado> listar() {
        return new ArrayList<>(estados.values());
    }

    @Override
    public void atualizar(UUID id, Estado estado) {
        Estado encontrado = estados.get(id);
        if (estados.containsKey(id)) {
            estados.put(id, estado);
        } else {
            throw new EstadoNaoEncontrado();
        }
    }

    @Override
    public Estado apagar(UUID id) {
        Estado apagado = estados.remove(id);
        if (apagado == null) {
            throw new EstadoNaoEncontrado();
        }
        return apagado;
    }
}
