package org.example;

import org.example.model.database.Database;

public class Sistema {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao sistema da biblioteca!");
        Database db = Database.getInstance();
        db.connect();
        db.disconnect();
    }
}