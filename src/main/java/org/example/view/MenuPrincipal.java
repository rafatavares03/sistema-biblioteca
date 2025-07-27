package org.example.view;

public class MenuPrincipal implements Pagina {
    @Override
    public Paginas executePage() {
        System.out.println("Você está no menu principal");
        return Paginas.SAIR;
    }
}
