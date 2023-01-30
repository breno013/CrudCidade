package br.com.ada.crud.controller.controllerPais.impl;

import br.com.ada.crud.controller.controllerPais.PaisController;
import br.com.ada.crud.controller.controllerPais.exception.PaisNaoEncontrado;
import br.com.ada.crud.model.modelPais.Pais;

import java.util.*;

public class PaisArmazenamentoVolatilController implements PaisController {

    private Map<UUID, Pais> paises = new HashMap<>();

    @Override
    public void cadastrar(Pais pais) {
        pais.setId(UUID.randomUUID());
        paises.put(pais.getId(), pais);
    }

    @Override
    public Pais ler(UUID id) {
        Pais encontrado = paises.get(id);
        if (encontrado == null) {
            throw new PaisNaoEncontrado();
        }
        return encontrado;
    }

    @Override
    public List<Pais> listar() {
        return new ArrayList<>(paises.values());
    }

    @Override
    public void atualizar(UUID id, Pais pais) {
        Pais encontrado = paises.get(id);
        if(paises.containsKey(id)) {
            paises.put(id, pais);
        } else {
            throw new PaisNaoEncontrado();
        }

    }

    @Override
    public Pais apagar(UUID id) {
        Pais apagado = paises.remove(id);
        if(apagado == null) {
            throw new PaisNaoEncontrado();
        }
        return apagado;
    }
}