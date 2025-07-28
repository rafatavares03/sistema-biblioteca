package org.example.controller;

import org.example.data.UsuarioDAO;
import org.example.model.Usuario;

public class GerenciadorDeUsuario {
    private static Usuario user = null;
    private static UsuarioDAO userDAO = new UsuarioDAO();

    public static boolean criaUsuario(Usuario user, char[] password) {
        return userDAO.create(user, password);
    }

    public static boolean autenticaUsuario(String key, char[] password) {
        return userDAO.authenticate(key, password);
    }

    public static Usuario buscaUsuario(String key) {
        return userDAO.read(key);
    }

    public static void setUser(Usuario usuario) {
        user = usuario;
    }

    public static Usuario getUser() {
        return user;
    }
}
