package br.com.ada.crud;

import br.com.ada.crud.controller.ArmazenamentoTipo;
import br.com.ada.crud.controller.controllerCidade.CidadeController;
import br.com.ada.crud.controller.controllerCidade.CidadeControllerFactory;
import br.com.ada.crud.controller.controllerCidade.CidadeDAOFactory;
import br.com.ada.crud.controller.controllerEstado.EstadoController;
import br.com.ada.crud.controller.controllerEstado.EstadoControllerFactory;
import br.com.ada.crud.controller.controllerEstado.EstadoDAOFactory;
import br.com.ada.crud.controller.controllerPais.PaisController;
import br.com.ada.crud.controller.controllerPais.PaisControllerFactory;
import br.com.ada.crud.controller.controllerPais.PaisDAOFactory;
import br.com.ada.crud.model.PersistenciaTipo;
import br.com.ada.crud.view.Menu;
import br.com.ada.crud.view.viewCidade.CidadeView;
import br.com.ada.crud.view.viewEstado.EstadoView;
import br.com.ada.crud.view.viewPais.PaisView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static final String ARQUIVO_PROPRIEDADES = "crud.properties";
    private  static final String CONTROLLER_TIPO = "controller.tipo";
    private static final String PERSISTENCIA_TIPO = "persistencia.tipo";

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ARQUIVO_PROPRIEDADES));

        String tipoArmazenamento = properties.getProperty(CONTROLLER_TIPO);
        ArmazenamentoTipo tipo = ArmazenamentoTipo.valueOf(tipoArmazenamento);

        String persistencia = properties.getProperty(PERSISTENCIA_TIPO);
        PersistenciaTipo persistenciaTipo = PersistenciaTipo.valueOf(persistencia);

        CidadeControllerFactory factoryCidade = new CidadeControllerFactory(new CidadeDAOFactory());
        CidadeController cidadeController = factoryCidade.criar(tipo, persistenciaTipo);

        EstadoControllerFactory factoryEstado = new EstadoControllerFactory(new EstadoDAOFactory());
        EstadoController estadoController = factoryEstado.criar(tipo,persistenciaTipo);

        PaisControllerFactory factoryPais = new PaisControllerFactory(new PaisDAOFactory());
        PaisController paisController = factoryPais.criar(tipo,persistenciaTipo);

        CidadeView cidadeView = new CidadeView(cidadeController, new Scanner(System.in));
        EstadoView estadoView = new EstadoView(estadoController, new Scanner(System.in));
        PaisView paisView = new PaisView(paisController, new Scanner(System.in));

        Menu.opcaoCadastro(cidadeView, estadoView, paisView);
    }

}