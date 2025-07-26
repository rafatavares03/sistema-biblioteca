package org.example.controller;

import org.example.data.UsuarioDAO;
import org.example.model.Usuario;

public class GerenciadorDeUsuario {
    public static Usuario usuario = null;
    private static UsuarioDAO userDAO = new UsuarioDAO();

    public static boolean criaUsuario(Usuario user, char[] senha) {
        userDAO.create(user);
        return true;
    }
}
