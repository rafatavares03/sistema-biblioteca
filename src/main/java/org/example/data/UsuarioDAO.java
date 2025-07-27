package org.example.data;

import org.example.model.Admin;
import org.example.model.Estudante;
import org.example.model.Usuario;
import org.example.controller.Sistema;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsuarioDAO{
    private Connection connection = Sistema.getDBConnection();

    public boolean create (Usuario user, char[] password){
        String sql = String.format(
                """
                BEGIN;
                    INSERT INTO usuario(cpf, nome, email, admin) VALUES ('%s', '%s', '%s', %s);
                    INSERT INTO credenciais(user_cpf, senha) VALUES ('%s', crypt('%s', gen_salt('md5')));
                COMMIT;
                """,
                user.getCpf(), user.getNome(), user.getEmail(), user instanceof Admin, user.getCpf(), String.valueOf(password)
        );
        System.out.println(sql);
        try(Statement stm = connection.createStatement()) {
            stm.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Usuario read(String key) {
        System.out.println("read");
        return new Estudante("123","456", "789");
    }

    public boolean update(Usuario user) {
        System.out.println("update");
        return true;
    }

    public boolean updatePassword(Usuario user, char[] password) {
        return true;
    }

    public boolean delete (String key) {
        return false;
    }

    public boolean authenticate(String key, char[] password) {
        boolean success = false;
        String sql = String.format(
                """
                SELECT (senha = crypt('%s', senha)) AS senha_match
                FROM credenciais AS CRED
                WHERE CRED.user_cpf = '%s'
                """,
                String.valueOf(password), key
        );
        try(Statement stm = connection.createStatement()) {
            success = stm.execute(sql);
        } catch(SQLException e) {
            System.out.println(e);
        }
        return success;
    }

    public List<Usuario> search(List<String> filters) {
        return List.of();
    }
}
