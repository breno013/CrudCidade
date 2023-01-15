package br.com.ada.crud.cidade.view;

import br.com.ada.crud.cidade.controller.CidadeController;
import br.com.ada.crud.cidade.model.Cidade;

import java.util.List;
import java.util.Scanner;
import static java.lang.System.exit;

public class CidadeView {

    private CidadeController controller;
    private Scanner scanner;

    public CidadeView(
            CidadeController controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar() {
        Cidade cidade = new Cidade();

        System.out.println("Informe o nome:");
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        controller.cadastrar(cidade);
    }

    public void mostrarListaCidades() {
        List<Cidade> cidades = controller.listar();
        System.out.printf("Cidade\n");
        for (int i = 0; i < cidades.size(); i++){
            System.out.print(i+1);
            mostrarListaCidades(cidades.get(i));
        }
    }

    public void mostrarListaCidades(Cidade cidade){
        System.out.printf("\t%s\n", cidade.getNome());
    }

    public void menu(){
        System.out.println("Digite o comando desejado: ");
        System.out.println("1 - Cadastrar cidade");
        System.out.println("2 - Listar cidades");
        Integer command = scanner.nextInt();
        scanner.nextLine();
        switch (command) {
            case 1:
                cadastrar();
                break;
            case 2:
                mostrarListaCidades();
                break;
            case 3:
                exit(0);
                break;
        }
        menu();
    }
}
