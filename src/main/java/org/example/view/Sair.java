package org.example.view;

import org.example.controller.Sistema;

public class Sair implements Pagina{
    @Override
    public Paginas executePage() {
        Sistema.signOut();
        return Paginas.LOGIN;
    }
}
