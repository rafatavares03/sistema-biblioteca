package org.example.controller;

import org.example.data.UsuarioDAO;
import org.example.model.Usuario;

public class GerenciadorDeUsuario {
    private static UsuarioDAO userDAO = new UsuarioDAO();

    public static boolean criaUsuario(Usuario user, char[] password) {
        return userDAO.create(user, password);
    }
}
