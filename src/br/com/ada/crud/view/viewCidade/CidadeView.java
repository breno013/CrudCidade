package br.com.ada.crud.view.viewCidade;

import br.com.ada.crud.controller.controllerCidade.CidadeController;
import br.com.ada.crud.controller.controllerCidade.exception.CidadeNaoEncontrada;
import br.com.ada.crud.model.modelCidade.Cidade;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CidadeView {

    private CidadeController controllerCidade;
    private Scanner scanner;

    public CidadeView(CidadeController controllerCidade, Scanner scanner) {
        this.controllerCidade = controllerCidade;
        this.scanner = scanner;
    }

    public void cadastrar() {
        Cidade cidade = new Cidade();

        System.out.print("Informe o nome da cidade que deseja cadastrar: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        System.out.printf("Informe o nome do estado a qual %s pertence: ", nome);
        String estado = scanner.nextLine();
        cidade.setEstado(estado);
        System.out.println();

        controllerCidade.cadastrar(cidade);
    }

    public void listar(UUID id) {
        Cidade cidade = controllerCidade.ler(id);
        exibirCidade(cidade);
    }

    public void atualizar() {
        try {
            listarTodos();
            System.out.print("Informe o número da cidade que deseja atualizar: ");
            String numeroCidade = scanner.nextLine();

            //Verificar se possui letras
            for (int i = 0; i < numeroCidade.length(); i++) {
                if (Character.isLetter(numeroCidade.charAt(i))==true)
                {
                    System.out.println("Opção inválida! Tente novamente");
                    atualizar();
                    break;

                } else {
                    int numeroCidadeInt = Integer.parseInt(numeroCidade);
                    Cidade cidade = controllerCidade.listar().get(numeroCidadeInt - 1);
                    atualizar(cidade);
                }

            }

        } catch (Exception e) {
            System.out.print("Opção inválida! Tente novamente.");
            atualizar();
        }

    }

    public void atualizar(Cidade cidade) {
        exibirCidade(cidade);

        System.out.print("Informe o novo nome da cidade: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        System.out.printf("Informe o nome do estado a qual %s pertence: ", nome);
        String estado = scanner.nextLine();
        cidade.setEstado(estado);
        System.out.println();

        try {
            controllerCidade.atualizar(cidade.getId(), cidade);
        } catch (CidadeNaoEncontrada ex) {
            System.out.println("Cidade informada não existe na base. Tente novamente.");
        }

    }

    public void apagar() {
        try {
            listarTodos();
            System.out.println("Informe o número da cidade que deseja apagar: ");
            String numeroCidade = scanner.nextLine();

            for (int i = 0; i < numeroCidade.length(); i++){
                if (Character.isLetter(numeroCidade.charAt(i))==true){
                    System.out.println("Opção inválida! Tente novamente");
                    apagar();
                    break;
                } else {
                    int numeroCidadeInt = Integer.parseInt(numeroCidade);
                    Cidade cidade = controllerCidade.listar().get(numeroCidadeInt -1);
                    apagar(cidade.getId());
                }
            }

        } catch (Exception e){
            System.out.print("Opção inválida! Tente novamente.");
            apagar();
        }
    }

    public void apagar(UUID id) {
        try {
            Cidade cidade = controllerCidade.apagar(id);
            System.out.println("Informações abaixo foram apagadas");
            exibirCidade(cidade);
        } catch (CidadeNaoEncontrada ex) {
            System.out.println("Cidade não foi apagada pois não foi localizada. Tente novamente.");
        }
    }

    public void listarTodos() {
        List<Cidade> cidades = controllerCidade.listar();
        for (int i = 0; i < cidades.size(); i++) {
            System.out.print((i + 1) + " - ");
            exibirCidade(cidades.get(i));
        }
    }

    public void exibirCidade(Cidade cidade) {
        System.out.println("Cidade --> Nome: " + cidade.getNome() + " / Estado: " + cidade.getEstado());
        System.out.println();
    }

    public void exibirOpcoesCidade() {
        System.out.println("Informe o número da operação que deseja realizar:");
        System.out.println("1 - Cadastrar Cidade");
        System.out.println("2 - Listar Cidades");
        System.out.println("3 - Atualizar Cidade");
        System.out.println("4 - Apagar Cidade");
        System.out.println("0 - Sair");
        System.out.println();
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                cadastrar();
                break;
            case "2":
                listarTodos();
                break;
            case "3":
                atualizar();
                break;
            case "4":
                apagar();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
        exibirOpcoesCidade();
    }

}
