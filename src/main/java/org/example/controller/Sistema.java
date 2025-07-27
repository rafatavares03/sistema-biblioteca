package org.example.controller;

import org.example.data.Database;
import org.example.model.Usuario;
import org.example.view.*;

import java.sql.Connection;

public class Sistema {
    Pagina[] paginas = new Pagina[Paginas.values().length];
    public static Usuario usuario = null;
    private static final Database db = Database.getInstance();

    public Sistema() {
        paginas[Paginas.LOGIN.ordinal()] = new Login();
        paginas[Paginas.REGISTRAR.ordinal()] = new Registrar();
        paginas[Paginas.ENTRAR.ordinal()] = new Entrar();
        paginas[Paginas.MENU_PRINCIPAL.ordinal()] = new MenuPrincipal();
        paginas[Paginas.SAIR.ordinal()] = null;
    }
    public void start() {
        db.connect();
        System.out.println("Bem vindo ao sistema da biblioteca!");
        int paginaAtual = Paginas.LOGIN.ordinal();
        while(paginas[paginaAtual] != null) {
            paginaAtual = paginas[paginaAtual].executePage().ordinal();
        }
        db.disconnect();
    }
    public static Connection getDBConnection() {
        return db.getConnection();
   }
}