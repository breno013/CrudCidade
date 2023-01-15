package br.com.ada.crud.cidade;

import br.com.ada.crud.cidade.controller.impl.CidadeArmazenamentoVolatilController;
import br.com.ada.crud.cidade.view.CidadeView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CidadeView cidadeView = new CidadeView(new CidadeArmazenamentoVolatilController(), new Scanner(System.in));
        cidadeView.menu();
    }
}