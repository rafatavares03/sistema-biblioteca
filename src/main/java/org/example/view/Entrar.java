package org.example.view;

import org.example.controller.GerenciadorDeUsuario;
import org.example.model.Admin;

import java.util.Scanner;

public class Entrar implements Pagina{
    public Paginas executePage() {
        Paginas proximaPagina = Paginas.LOGIN;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu cpf: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        char[] senha = scanner.nextLine().toCharArray();
        boolean sucesso = GerenciadorDeUsuario.autenticaUsuario(cpf, senha);
        if(sucesso) {
            GerenciadorDeUsuario.setUser(GerenciadorDeUsuario.buscaUsuario(cpf));
            System.out.println(GerenciadorDeUsuario.getUser() instanceof Admin);
            System.out.println("Login realizado com sucesso.");
            proximaPagina = Paginas.MENU_PRINCIPAL;
        } else {
            System.out.println("Falha no login, tente novamente mais tarde.");
        }
        return proximaPagina;
    }
}
