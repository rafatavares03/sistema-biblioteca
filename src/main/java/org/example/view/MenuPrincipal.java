package org.example.view;

import org.example.controller.Sistema;
import org.example.model.Admin;

import java.util.Scanner;

public class MenuPrincipal implements Pagina {
    @Override
    public Paginas executePage() {
        Paginas proximaPagina;
        if(Sistema.user instanceof Admin) {
            proximaPagina = adminPage();
        } else {
            proximaPagina = regularUserPage();
        }

        return proximaPagina;
    }

    private Paginas adminPage() {
        Paginas proximaPagina = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print(
              """
              MENU PRINCIPAL
              1 - MEU PERFIL
              2 - VER LOCAÇÕES SOLICITADAS
              3 - VER REGISTRO DE LOCAÇÕES
              4 - ALUGAR LIVRO
              5 - ADICIONAR LIVRO
              6 - EDITAR LIVRO
              7 - REMOVER LIVRO
              8 - CADASTRAR ADMINISTRADOR
              9 - SAIR
             """
        );
        int selected;
        do {
            try{
                selected = scanner.nextInt();
                scanner.nextLine();
            } catch(Exception e) {
                selected = 0;
                scanner.nextLine();
            }
            proximaPagina = switch (selected) {
                case 1 -> Paginas.PERFIL;
                case 2 -> Paginas.LOCACOES_SOLICITADAS;
                case 3 -> Paginas.LOCACOES_REGISTRO;
                case 4 -> Paginas.ALUGAR_LIVRO;
                case 5 -> Paginas.ADICIONAR_LIVRO;
                case 6 -> Paginas.EDITAR_LIVRO;
                case 7 -> Paginas.REMOVER_LIVRO;
                case 8 -> Paginas.CADASTRAR_ADMIN;
                case 9 -> Paginas.SAIR;
                default -> null;
            };
            if(proximaPagina == null) {
                System.out.println("A opção escolhida é inválida.");
            }
        } while(proximaPagina == null);
        return proximaPagina;
    }

    private Paginas regularUserPage() {
        Paginas proximaPagina = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print(
              """
              MENU PRINCIPAL
              1 - MEU PERFIL
              2 - ALUGAR LIVRO
              3 - SAIR
             """
        );
        int selected;
        do {
            try{
                selected = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                selected = 0;
                scanner.nextLine();
            }
            proximaPagina = switch (selected) {
                case 1 -> Paginas.PERFIL;
                case 2 -> Paginas.ALUGAR_LIVRO;
                case 3 -> Paginas.SAIR;
                default -> null;
            };
            if(proximaPagina == null) {
                System.out.println("A opção escolhida é inválida.");
            }
        } while(proximaPagina == null);
        return proximaPagina;
    }
}
