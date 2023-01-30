package br.com.ada.crud.view.viewEstado;

import br.com.ada.crud.controller.controllerEstado.EstadoController;
import br.com.ada.crud.controller.controllerEstado.exception.EstadoNaoEncontrado;
import br.com.ada.crud.model.modelEstado.estado.Estado;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class EstadoView {
    private EstadoController controllerEstado;
    private Scanner scanner;

    public EstadoView(EstadoController controllerEstado, Scanner scanner) {
        this.controllerEstado = controllerEstado;
        this.scanner = scanner;
    }

    public void cadastrar() {
        Estado estado = new Estado();

        System.out.print("Informe o nome do estado que deseja cadastrar: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        estado.setNome(nome);

        System.out.printf("Informe a sigla do estado %s: ", nome);
        String sigla = scanner.nextLine();
        estado.setSigla(sigla);

        System.out.printf("Informe o nome do país a qual %s pertence: ", nome);
        String pais = scanner.nextLine();
        estado.setPais(pais);
        System.out.println();

        controllerEstado.cadastrar(estado);
    }

    public void listar(UUID id) {
        Estado estado = controllerEstado.ler(id);
        exibirEstado(estado);
    }

    public void atualizar() {

        try {

            listarTodos();
            System.out.print("Informe o número do estado que deseja atualizar: ");
            String numeroEstado = scanner.nextLine();

            //Verificar se possui letra
            for (int i = 0; i < numeroEstado.length(); i++){
                if (Character.isLetter(numeroEstado.charAt(i))==true){

                    System.out.println("Opção inválida! Tente novamente");
                    atualizar();
                    break;

                } else {
                    int numeroEstadoInt = Integer.parseInt(numeroEstado);
                    Estado estado = controllerEstado.listar().get(numeroEstadoInt - 1);
                    atualizar(estado);
                }
            }

        } catch (Exception e) {
            System.out.print("Opção inválida! Tente novamente.");
            atualizar();
        }
    }

    public void atualizar(Estado estado) {
        exibirEstado(estado);

        System.out.print("Informe o novo nome do estado: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        estado.setNome(nome);

        System.out.printf("Informe a sigla do estado %s: ", nome);
        String sigla = scanner.nextLine();
        estado.setSigla(sigla);

        System.out.printf("Informe o nome do país a qual %s pertence: ", nome);
        String pais = scanner.nextLine();
        estado.setPais(pais);
        System.out.println();

        try {
            controllerEstado.atualizar(estado.getId(), estado);
        } catch (EstadoNaoEncontrado ex) {
            System.out.println("Estado informado não existe na base. Tente novamente.");
        }

    }

    public void apagar() {
        try {
            listarTodos();
            System.out.println("Informe o número do estado que deseja apagar: ");
            String numeroEstado = scanner.nextLine();

            for (int i = 0; i < numeroEstado.length(); i++){
                if (Character.isLetter(numeroEstado.charAt(i))==true){
                    System.out.println("Opção inválida! Tente novamente");
                    apagar();
                    break;
                } else {
                    int numeroEstadoInt = Integer.parseInt(numeroEstado);
                    Estado estado = controllerEstado.listar().get(numeroEstadoInt - 1);
                    apagar(estado.getId());
                }
            }
        } catch (Exception e){
            System.out.print("Opção inválida! Tente novamente.");
            apagar();
        }
    }

    public void apagar(UUID id) {
        try {
            Estado estado = controllerEstado.apagar(id);
            System.out.println("Informações abaixo foram apagadas");
            exibirEstado(estado);
        } catch (EstadoNaoEncontrado ex) {
            System.out.println("Estado não foi apagado pois não foi localizada. Tente novamente.");
        }
    }

    public void listarTodos() {
        List<Estado> cidades = controllerEstado.listar();
        for (int i = 0; i < cidades.size(); i++) {
            System.out.print((i + 1) + " - ");
            exibirEstado(cidades.get(i));
        }
    }

    public void exibirEstado(Estado estado) {
        System.out.println("Estado --> Nome: " + estado.getNome() + " / Sigla: " + estado.getSigla() + " / País: " + estado.getPais());
        System.out.println();
    }

    public void exibirOpcoesEstado() {
        System.out.println("Informe o número da operação que deseja realizar:");
        System.out.println("1 - Cadastrar Estado");
        System.out.println("2 - Listar Estados");
        System.out.println("3 - Atualizar Estado");
        System.out.println("4 - Apagar Estado");
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
        exibirOpcoesEstado();
    }

}