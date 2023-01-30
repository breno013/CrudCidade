package br.com.ada.crud.model.modelPais.pais.dao.impl;

import br.com.ada.crud.model.modelCidade.cidade.dao.DAOException;
import br.com.ada.crud.model.modelPais.Pais;
import br.com.ada.crud.model.modelPais.pais.dao.PaisDAO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisBinaryDAO implements PaisDAO {

    String diretorioRaiz = "database/binario";
    String diretorioPaises = diretorioRaiz + "/paises";

    @Override
    public void cadastrar(Pais pais) {
        Path diretorio = Paths.get(diretorioPaises);
        if (!diretorio.toFile().exists()) {
            throw new RuntimeException("Diretorio não disponível!");
        }
        File file = new File(
                diretorio.toAbsolutePath().toString(), pais.getId().toString() + ".dat");
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(pais);
            objectOutputStream.flush();
        } catch (IOException ex) {
            throw new DAOException("Falha ao trabalhar os arquivos.", ex);
        }
    }

    @Override
    public List<Pais> listar() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".dat");
        List<Pais> paises = new ArrayList<>();
        File diretorio = new File(diretorioPaises);
        for (File arquivo: diretorio.listFiles(filter)) {
            try (FileInputStream fileInputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                Object object = objectInputStream.readObject();
                if (object instanceof Pais) {
                    Pais pais = (Pais) object;
                    paises.add(pais);
                }
            } catch (IOException | ClassNotFoundException ex) {
                throw new DAOException("Falha na leitura do arquivo " + arquivo.getAbsolutePath(), ex);
            }
        }
        return paises;
    }

    @Override
    public Pais buscar(UUID id) {
        File arquivo = new File(diretorioPaises, id.toString() + ".dat");
        try (FileInputStream fileInputStream = new FileInputStream(arquivo);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            Object object = objectInputStream.readObject();
            if (object instanceof Pais) {
                return (Pais) object;
            } else {
                return null;
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new DAOException("Falha na leitura do arquivo " + arquivo.getAbsolutePath(), ex);
        }
    }

    @Override
    public void atualizar(UUID id, Pais pais) {
        File file = new File(diretorioPaises, id.toString() + ".dat");
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(pais);
            objectOutputStream.flush();
        } catch (IOException ex) {
            throw new DAOException("Falha ao atualizar país.", ex);
        }
    }

    @Override
    public Pais apagar(UUID id) {
        Pais pais = buscar(id);
        if (pais != null) {
            File arquivo = new File(diretorioPaises, id.toString() + ".dat");
            arquivo.delete();
        }
        return pais;
    }
}