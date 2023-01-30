package br.com.ada.crud.model.modelPais.pais.dao.impl;

import br.com.ada.crud.model.modelPais.Pais;
import br.com.ada.crud.model.modelPais.pais.dao.PaisDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisXMLDao implements PaisDAO {

    String diretorioRaiz = "database/xml";
    String diretorioPaises = diretorioRaiz + "/paises";

    @Override
    public void cadastrar(Pais pais) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document document =  builder.newDocument();
            Element elementPais = document.createElement("pais");
            document.appendChild(elementPais);

            Element elementId = document.createElement("id");
            elementId.setTextContent(pais.getId().toString());
            elementPais.appendChild(elementId);

            Element elementNome = document.createElement("nome");
            elementNome.setTextContent(pais.getNome());
            elementPais.appendChild(elementNome);

            Element elementSigla = document.createElement("sigla");
            elementSigla.setTextContent(pais.getSigla());
            elementPais.appendChild(elementSigla);

            File arquivo = new File(diretorioPaises, pais.getId().toString() + ".xml");
            try (FileOutputStream output = new FileOutputStream(arquivo)) {
                TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(output);

                transformer.transform(source, result);
            } catch (IOException | TransformerException e) {
                throw  new RuntimeException(e);
            }
        } catch (ParserConfigurationException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public List<Pais> listar() {
        FilenameFilter filter = (dir, nome) -> nome.endsWith(".xml");
        File diretorio = new File(diretorioPaises);
        List<Pais> paises = new ArrayList<>();
        for (File arquivo : diretorio.listFiles(filter)) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                Document document = builder.parse(arquivo);

                Element elementPais = document.getDocumentElement();
                Node elementId = elementPais.getElementsByTagName("id").item(0);
                Node elementNome = elementPais.getElementsByTagName("nome").item(0);
                Node elementSigla = elementPais.getElementsByTagName("sigla").item(0);

                Pais pais =new Pais();
                pais.setId(UUID.fromString(elementId.getTextContent()));
                pais.setNome(elementNome.getTextContent());
                pais.setSigla(elementSigla.getTextContent());

                paises.add(pais);

            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return paises;
    }

    @Override
    public Pais buscar(UUID id) {
        File arquivo = new File(diretorioPaises, id.toString() + ".xml");

        try {
            DocumentBuilderFactory factory =  DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(arquivo);
            Element elementPais = document.getDocumentElement();
            Node elementId = elementPais.getElementsByTagName("id").item(0);
            Node elementNome = elementPais.getElementsByTagName("nome").item(0);
            Node elementSigla = elementPais.getElementsByTagName("sigla").item(0);

            Pais pais = new Pais();
            pais.setId(UUID.fromString(elementId.getTextContent()));
            pais.setNome(elementNome.getTextContent());
            pais.setSigla(elementSigla.getTextContent());
            return pais;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(UUID id, Pais pais) {
        File arquivo = new File(diretorioPaises, id.toString() + ".xml");
        arquivo.delete();

        pais.setId(id);

        cadastrar(pais);
    }

    @Override
    public Pais apagar(UUID id) {
        Pais pais = buscar(id);
        if (pais != null) {
            File arquivo = new File(diretorioPaises, id.toString() + ".xml");
            arquivo.delete();
        }
        return pais;
    }
}