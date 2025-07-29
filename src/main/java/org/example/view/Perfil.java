package org.example.view;

import org.example.controller.Sistema;

import java.util.Scanner;

public class Perfil implements Pagina{
    @Override
    public Paginas executePage() {
        Paginas proximaPagina = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print(
                """
                PERFIL
                1 - VER MEUS DADOS
                2 - ALTERAR DADOS
                3 - VER LIVROS ALUGADOS
                4 - DELETAR CONTA
                5 - VOLTAR
                """
        );
        int selected;
        while(proximaPagina == null) {
            try {
                selected = scanner.nextInt();
                scanner.nextLine();
            } catch(Exception e) {
                selected = 0;
                scanner.nextLine();
            }
            switch (selected) {
                case 1:
                    verDados();
                    break;
                default:
                    System.out.println("A opção escolhida é inválida.");
            }
        }
        return proximaPagina;
    }

    public void verDados() {
        System.out.println("Nome: " + Sistema.getUser().getNome());
        System.out.println("CPF: " + Sistema.getUser().getCpf());
        System.out.println("Email: " + Sistema.getUser().getEmail());
    }
}
