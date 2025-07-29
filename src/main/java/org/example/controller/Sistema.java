package org.example.controller;

import org.example.data.Database;
import org.example.model.Usuario;
import org.example.view.*;

import java.sql.Connection;

public class Sistema {
    Pagina[] paginas = new Pagina[Paginas.values().length];
    private static Usuario user = null;
    private static final Database db = Database.getInstance();

    public Sistema() {
        paginas[Paginas.LOGIN.ordinal()] = new Login();
        paginas[Paginas.REGISTRAR.ordinal()] = new Registrar();
        paginas[Paginas.ENTRAR.ordinal()] = new Entrar();
        paginas[Paginas.SAIR.ordinal()] = new Sair();
        paginas[Paginas.MENU_PRINCIPAL.ordinal()] = new MenuPrincipal();
        paginas[Paginas.PERFIL.ordinal()] = new Perfil();
        paginas[Paginas.CADASTRAR_ADMIN.ordinal()] = new CadastrarAdmin();
        paginas[Paginas.ENCERRAR.ordinal()] = null;
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
   public static Usuario getUser() {
        return user;
   }

   public static void signIn(Usuario usuario) {
        user = usuario;
   }

   public static void signOut() {
        user = null;
   }
}