package org.example.view;

import org.example.controller.GerenciadorDeUsuario;
import org.example.model.Admin;

import java.util.Scanner;

public class CadastrarAdmin implements Pagina{
    public Paginas executePage() {
        System.out.println("Preencha as seguintes informações.");
        System.out.printf("CPF: ");
        Scanner scanner = new Scanner(System.in);
        String cpf = scanner.nextLine();
        if(cpf.length() != 11) {
            System.out.println("CPF inválido, tamanho diferente de 11 dígitos.");
            //return false;
        }
        System.out.printf("Nome: ");
        String nome = scanner.nextLine();
        if(nome.isBlank()) {
            System.out.println("O campo nome precisa ser preenchido.");
            //return false;
        }
        System.out.printf("Email: ");
        String email = scanner.nextLine();
        /*
        if(email.isBlank()) {
            email = null;
        }
        */
        System.out.printf("Senha: ");
        char[] senha = scanner.nextLine().toCharArray();
        if(senha.length < 8 || senha.length > 20) {
            System.out.println("A senha deve conter entre 8 a 20 caracteres.");
            return Paginas.LOGIN;
        }
        Admin novoAdmin = new Admin(cpf, nome, email);
        boolean sucesso = GerenciadorDeUsuario.criaUsuario(novoAdmin, senha);
        if(sucesso) {
            System.out.println("Administrador cadastrado com sucesso!");
        } else {
            System.out.println("Infelizmente não foi possível cadastrar o administrador.");
        }
        return Paginas.MENU_PRINCIPAL;
    }
}
