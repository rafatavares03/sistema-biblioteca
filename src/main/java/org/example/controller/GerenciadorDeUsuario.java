package org.example.controller;

import org.example.data.UsuarioDAO;
import org.example.model.Admin;
import org.example.model.Estudante;
import org.example.model.Usuario;

public class GerenciadorDeUsuario {
    private static final UsuarioDAO userDAO = new UsuarioDAO();

    public static boolean criaUsuario(Usuario user, char[] password) {
        return userDAO.create(user, password);
    }

    public static boolean autenticaUsuario(String key, char[] password) {
        return userDAO.authenticate(key, password);
    }

    public static Usuario buscaUsuario(String key) {
        return userDAO.read(key);
    }

    public static boolean alteraUsuario(String attribute, String newValue) {
        Usuario modified;
        if(Sistema.user instanceof Admin) {
            modified = new Admin(Sistema.user.getCpf(), Sistema.user.getNome(), Sistema.user.getEmail());
        } else {
            modified = new Estudante(Sistema.user.getCpf(), Sistema.user.getNome(), Sistema.user.getEmail());
        }
        switch (attribute) {
            case "nome":
                modified.setNome(newValue);
                break;
            case "email":
                modified.setEmail(newValue);
                break;
        }
        boolean updateSuccess = userDAO.update(modified);
        if(updateSuccess) {
            Sistema.user = modified;
        }
        return updateSuccess;
    }
}
