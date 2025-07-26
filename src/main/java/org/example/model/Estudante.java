package org.example.model;

public class Estudante extends Usuario{
    public Estudante(String cpf, String nome, String email) {
        this.setCpf(cpf);
        this.setNome(nome);
        this.setEmail(email);
    }
}
