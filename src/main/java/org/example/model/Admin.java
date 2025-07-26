package org.example.model;

public class Admin extends Usuario{
    public Admin(String cpf, String nome, String email) {
        this.setCpf(cpf);
        this.setNome(nome);
        this.setEmail(email);
    }
}
