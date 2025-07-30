package org.example.view;

import org.example.controller.GerenciadorDeUsuario;
import org.example.controller.Sistema;
import org.example.model.Admin;
import org.example.model.Estudante;

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
        do {
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
                case 5:
                    proximaPagina = Paginas.MENU_PRINCIPAL;
                default:
                    System.out.println("A opção escolhida é inválida.");
            }
        } while(proximaPagina == null);
        return proximaPagina;
    }

    public void verDados() {
        System.out.println("Nome: " + Sistema.user.getNome());
        System.out.println("CPF: " + Sistema.user.getCpf());
        System.out.println("Email: " + Sistema.user.getEmail());
    }

    public boolean alterarDados() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual informação você deseja alterar?");
        System.out.print(
                """
                1 - NOME
                2 - EMAIL
                3 - SENHA
                4 - CANCELAR
                """
        );
        int selected;
        try{
            selected = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            selected = 0;
            scanner.nextLine();
        }
        boolean success = false;
        switch (selected){
            case 1:
                System.out.println("Digite o nome: ");
                String nome = scanner.nextLine();
                success = GerenciadorDeUsuario.alteraUsuario("nome", nome);
        }
        return success;
    }
}
