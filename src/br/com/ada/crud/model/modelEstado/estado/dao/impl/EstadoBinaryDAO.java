package br.com.ada.crud.model.modelEstado.estado.dao.impl;

import br.com.ada.crud.model.modelCidade.cidade.dao.DAOException;
import br.com.ada.crud.model.modelEstado.estado.Estado;
import br.com.ada.crud.model.modelEstado.estado.dao.EstadoDAO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstadoBinaryDAO implements EstadoDAO {

    String diretorioRaiz = "database/binario";
    String diretorioEstados = diretorioRaiz + "/estados";

    @Override
    public void cadastrar(Estado estado) {
        Path diretorio = Paths.get(diretorioEstados);
        if (!diretorio.toFile().exists()) {
            throw new RuntimeException("Diretorio não disponível!");
        }
        File file = new File(
                diretorio.toAbsolutePath().toString(), estado.getId().toString() + ".dat");
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {
            objectOutputStream.writeObject(estado);
            objectOutputStream.flush();
        } catch (IOException ex) {
            throw new DAOException("Falha ao trabalhar os arquivos.", ex);
        }
    }

    @Override
    public List<Estado> listar() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".dat");
        List<Estado> estados = new ArrayList<>();
        File diretorio = new File(diretorioEstados);
        for (File arquivo: diretorio.listFiles(filter)) {
            try (FileInputStream fileInputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                Object object = objectInputStream.readObject();
                if (object instanceof Estado) {
                    Estado estado = (Estado) object;
                    estados.add(estado);
                }
            } catch (IOException | ClassNotFoundException ex) {
                throw new DAOException("Falha na leitura do arquivo " + arquivo.getAbsolutePath(), ex);
            }
        }
        return estados;
    }

    @Override
    public Estado buscar(UUID id) {
        File arquivo = new File(diretorioEstados, id.toString() + ".dat");
        try (FileInputStream fileInputStream = new FileInputStream(arquivo);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            Object object = objectInputStream.readObject();
            if (object instanceof Estado) {
                return (Estado) object;
            } else {
                return null;
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new DAOException("Falha na leitura do arquivo " + arquivo.getAbsolutePath(), ex);
        }
    }

    @Override
    public void atualizar(UUID id, Estado estado) {
        File file = new File(diretorioEstados, id.toString() + ".dat");

        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {
            objectOutputStream.writeObject(estado);
            objectOutputStream.flush();
        } catch (IOException ex) {
            throw new DAOException("Falha ao atualizar o estado.", ex);
        }
    }

    @Override
    public Estado apagar(UUID id) {
        Estado estado = buscar(id);
        if (estado != null) {
            File arquivo = new File(diretorioEstados, id.toString() + ".dat");
            arquivo.delete();
        }
        return estado;
    }
}