package br.com.ada.crud.model.modelCidade.cidade.dao.impl;

import br.com.ada.crud.model.modelCidade.Cidade;
import br.com.ada.crud.model.modelCidade.cidade.dao.CidadeDAO;
import br.com.ada.crud.model.modelCidade.cidade.dao.DAOException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CidadeBinaryDAO implements CidadeDAO {

    String diretorioRaiz = "database/binario";
    String diretorioCidades = diretorioRaiz + "/cidades";

    @Override
    public void cadastrar(Cidade cidade) {
        Path diretorio = Paths.get(diretorioCidades);
        if (!diretorio.toFile().exists()) {
            throw new RuntimeException("Diretorio não disponível!");
        }
        File file = new File(
                diretorio.toAbsolutePath().toString(), cidade.getId().toString() + ".dat");
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(cidade);
            objectOutputStream.flush();
        } catch (IOException ex) {
            throw new DAOException("Falha ao trabalhar os arquivos.", ex);
        }
    }

    @Override
    public List<Cidade> listar() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".dat");
        List<Cidade> cidades = new ArrayList<>();
        File diretorio = new File(diretorioCidades);
        for (File arquivo: diretorio.listFiles(filter)) {
            try (FileInputStream fileInputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                Object object = objectInputStream.readObject();
                if (object instanceof Cidade) {
                    Cidade cidade = (Cidade) object;
                    cidades.add(cidade);
                }
            } catch (IOException | ClassNotFoundException ex) {
                throw new DAOException("Falha na leitura do arquivo " + arquivo.getAbsolutePath(), ex);
            }
        }
        return cidades;
    }

    @Override
    public Cidade buscar(UUID id) {
        File arquivo = new File(diretorioCidades, id.toString() + ".dat");
        try (FileInputStream fileInputStream = new FileInputStream(arquivo);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            Object object = objectInputStream.readObject();
            if (object instanceof Cidade) {
                return (Cidade) object;
            } else {
                return null;
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new DAOException("Falha na leitura do arquivo " + arquivo.getAbsolutePath(), ex);
        }
    }

    @Override
    public void atualizar(UUID id, Cidade cidade) {
        File file = new File(diretorioCidades, id.toString() + ".dat");
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(cidade);
            objectOutputStream.flush();
        } catch (IOException ex) {
            throw new DAOException("Falha ao atualizar a cidade.", ex);
        }
    }

    @Override
    public Cidade apagar(UUID id) {
        Cidade cidade = buscar(id);
        if (cidade != null) {
            File arquivo = new File(diretorioCidades, id.toString() + ".dat");
            arquivo.delete();
        }
        return cidade;
    }
}
