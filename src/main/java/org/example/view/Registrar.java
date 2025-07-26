package org.example.view;

import org.example.controller.GerenciadorDeUsuario;
import org.example.model.Estudante;

import java.util.Arrays;
import java.util.Scanner;

public class Registrar implements Pagina{
    @Override
    public Paginas executePage() {
        System.out.printf("Digite o cpf: ");
        Scanner scanner = new Scanner(System.in);
        String cpf = scanner.nextLine();
        if(cpf.length() != 11) {
            System.out.println("CPF inválido, tamanho diferente de 11 dígitos.");
            //return false;
        }
        System.out.printf("Digite seu nome: ");
        String nome = scanner.nextLine();
        if(nome.isBlank()) {
            System.out.println("O campo nome precisa ser preenchido.");
            //return false;
        }
        System.out.printf("Digite seu email: ");
        String email = scanner.nextLine();
        /*
        if(email.isBlank()) {
            email = null;
        }
        */
        System.out.printf("Digite sua senha: ");
        char[] senha = scanner.nextLine().toCharArray();
        if(senha.length < 8 || senha.length > 20) {
            System.out.println("A senha deve conter entre 8 a 20 caracteres.");
            //return false;
        }
        Estudante novoEstudante = new Estudante(cpf, nome, email);
        boolean sucesso = GerenciadorDeUsuario.criaUsuario(novoEstudante, senha);
        if(sucesso) {
            System.out.println("Usuario cadastrado com sucesso!");
        } else {
            System.out.println("Infelizmente não foi possível cadastrar o usuário.");
        }
        //return true;
        return Paginas.LOGIN;
    }
}
