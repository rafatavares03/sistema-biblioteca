package org.example.model.database;

import io.github.cdimascio.dotenv.Dotenv;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection  = null;

    public void connect() {
        Dotenv dotenv = Dotenv.load();
        final String jdbcURL = "jdbc:postgresql://localhost:5432/biblioteca";
        final String username = dotenv.get("USERNAME_DB");
        final String password = dotenv.get("PASSWORD_DB");
        try {
            this.connection = DriverManager.getConnection(jdbcURL, username, password);
            createDomains(this.connection);
            createTables(this.connection);
            System.out.println("Conectado com o banco de dados!");
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public void disconnect() {
        if(this.connection != null) {
            try {
                connection.close();
                System.out.println("Desconectado do banco de dados!");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    private String[] loadTables() throws IOException {
        String sql = new String(Files.readAllBytes(Paths.get("./resources/SQL/criaTabelas.sql")));
        return sql.split(";");
    }

    private String loadDomains() throws IOException {
        String sql = new String(Files.readAllBytes(Paths.get("./resources/SQL/criaDominios.sql")));
        return sql;
    }

    private void createTables(Connection connection) {
        try {
            String[] sqlCommands = loadTables();
            for(String command : sqlCommands) {
                try(Statement stm = connection.createStatement()) {
                    stm.execute(command);
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível iniciar o banco de dados!");
            System.out.println(e);
        }
    }

    private void createDomains(Connection connection) {
        try {
            String sqlCommand = loadDomains();
            try(Statement stm = connection.createStatement()) {
                stm.execute(sqlCommand);
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println("Não foi possível criar dominios.");
            System.out.println(e);
        }
    }

    public Connection getConnection(){
        return this.connection;
    }
}
