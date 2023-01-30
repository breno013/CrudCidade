package br.com.ada.crud.view.viewPais;

import br.com.ada.crud.controller.controllerPais.PaisController;
import br.com.ada.crud.controller.controllerPais.exception.PaisNaoEncontrado;
import br.com.ada.crud.model.modelPais.Pais;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PaisView {

    private PaisController controllerPais;

    private Scanner scanner;

    public PaisView (PaisController controllerPais, Scanner scanner) {
        this.controllerPais = controllerPais;
        this.scanner = scanner;
    }

    public void cadastrar() {
        Pais pais = new Pais();

        System.out.print("Informe o nome do país que deseja cadastrar: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        pais.setNome(nome);

        System.out.printf("Informe a sigla do país %s: ", nome);
        String sigla = scanner.nextLine();
        pais.setSigla(sigla);
        System.out.println();

        controllerPais.cadastrar(pais);
    }

    public void listar(UUID id) {
        Pais pais = controllerPais.ler(id);
        exibirPais(pais);
    }

    public void atualizar() {
        try {
            listarTodos();
            System.out.print("Informe o número do país que deseja atualizar: ");
            String numeroPais = scanner.nextLine();

            //Verificar se possui letra
            for (int i = 0; i < numeroPais.length(); i++){
                if (Character.isLetter(numeroPais.charAt(i))==true){
                    System.out.println("Opção inválida! Tente novamente");
                    atualizar();
                    break;
                } else {
                    int numeroPaisInt = Integer.parseInt(numeroPais);
                    Pais pais = controllerPais.listar().get(numeroPaisInt - 1);
                    atualizar(pais);
                }
            }
        } catch (Exception e) {
            System.out.print("Opção inválida! Tente novamente.");
            atualizar();
        }
    }

    public void atualizar(Pais pais) {
        exibirPais(pais);

        System.out.print("Informe o novo nome do país: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        pais.setNome(nome);

        System.out.printf("Informe a sigla do país %s ", nome);
        String sigla = scanner.nextLine();
        pais.setSigla(sigla);
        System.out.println();

        try {
            controllerPais.atualizar(pais.getId(), pais);
        } catch (PaisNaoEncontrado ex) {
            System.out.println("País informado não existe na base. Tente novamente.");
        }
    }

    public void apagar() {
        try {
            listarTodos();
            System.out.println("Informe o número do país que deseja apagar: ");
            String numeroPais = scanner.nextLine();

            for (int i = 0; i < numeroPais.length(); i++){
                if (Character.isLetter(numeroPais.charAt(i))==true){
                    System.out.println("Opção inválida! Tente novamente");
                    apagar();
                    break;
                } else {
                    int numeroPaisInt = Integer.parseInt(numeroPais);
                    Pais pais = controllerPais.listar().get(numeroPaisInt - 1);
                    apagar(pais.getId());
                }
            }
        } catch (Exception e) {
            System.out.print("Opção inválida! Tente novamente.");
            apagar();
        }
    }

    public void apagar(UUID id) {
        try {
            Pais pais = controllerPais.apagar(id);
            System.out.println("Informações abaixo foram apagadas");
            exibirPais(pais);
        } catch (PaisNaoEncontrado ex) {
            System.out.println("País não foi apagado pois não foi localizada. Tente novamente.");
        }
    }

    public void listarTodos() {
        List<Pais> cidades = controllerPais.listar();
        for (int i = 0; i < cidades.size(); i++) {
            System.out.print((i + 1) + " - ");
            exibirPais(cidades.get(i));
        }
    }

    public void exibirPais(Pais pais) {
        System.out.println("País --> Nome: " + pais.getNome() + " / Sigla: " + pais.getSigla());
        System.out.println();
    }

    public void exibirOpcoesPais() {
        System.out.println("Informe o número da operação que deseja realizar:");
        System.out.println("1 - Cadastrar País");
        System.out.println("2 - Listar Países");
        System.out.println("3 - Atualizar País");
        System.out.println("4 - Apagar País");
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
        exibirOpcoesPais();
    }
}