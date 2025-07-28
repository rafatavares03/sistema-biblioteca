package org.example.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login implements Pagina{
    public Paginas executePage() {
        Paginas proximaPagina = Paginas.ENCERRAR;
        System.out.print(
                """     
                Entre ou crie uma conta:
                1 - ENTRAR
                2 - REGISTRAR
                3 - ENCERRAR
                """
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
