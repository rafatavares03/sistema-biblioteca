package org.example.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login implements Pagina{
    public Paginas executePage() {
        Paginas proximaPagina = Paginas.SAIR;
        System.out.println(
                "Entre ou crie uma conta:\n" +
                "1 - Entrar\n" +
                "2 - Registrar\n" +
                "3 - Sair"
        );
        Scanner scanner = new Scanner(System.in);
        int selected;
        try {
            selected = scanner.nextInt();
        } catch (InputMismatchException e) {
            selected = 0;
        }
        switch(selected){
            case 1:
                proximaPagina = Paginas.ENTRAR;
                break;
            case 2:
                proximaPagina = Paginas.REGISTRAR;
                break;
            case 3:
                break;
            default:
                System.out.println("A opção escolhida é inválida.");
                break;
        }
        return proximaPagina;
    }
}
