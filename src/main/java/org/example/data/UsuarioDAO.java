package org.example.data;

import org.example.model.Admin;
import org.example.model.Estudante;
import org.example.model.Usuario;
import org.example.controller.Sistema;

import java.sql.Connection;
import java.sql.ResultSet;
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
        try(Statement stm = connection.createStatement()) {
            stm.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Usuario read(String key) {
        Usuario user = null;
        String sql = String.format(
                """
                SELECT *
                FROM usuario AS u
                WHERE u.cpf = '%s'
                """,
                key
        );
        try(Statement stm = connection.createStatement()) {
            boolean smtSuccess = stm.execute(sql);
            if(smtSuccess) {
                ResultSet rs = stm.getResultSet();
                if(rs.next()) {
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    boolean admin = rs.getBoolean("admin");
                    if(admin) {
                        user = new Admin(cpf, nome, email);
                    } else {
                        user = new Estudante(cpf, nome, email);
                    }
                }
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return user;
    }

    public boolean update(Usuario user) {
        boolean updateSuccess = false;
        String sql = String.format(
                """
                UPDATE usuario
                SET nome = '%s', email = '%s'
                WHERE cpf = '%s';
                """,
                user.getNome(), user.getEmail(), user.getCpf()
        );
        try(Statement smt = connection.createStatement()) {
            smt.execute(sql);
            int updated = smt.getUpdateCount();
            if(updated == 1) {
                updateSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return updateSuccess;
    }

    public boolean updatePassword(Usuario user, char[] password) {
        boolean updateSuccess = false;
        String sql = String.format(
            """
            UPDATE credenciais
            SET senha = crypt('%s', gen_salt('md5'))
            WHERE user_cpf = '%s';
            """,
            String.valueOf(password), user.getCpf()
        );
        try(Statement smt = connection.createStatement()) {
            smt.execute(sql);
            int updated = smt.getUpdateCount();
            if(updated == 1) {
                updateSuccess = true;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return updateSuccess;
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
            boolean statementExecuted = stm.execute(sql);
            if(statementExecuted) {
                ResultSet rs = stm.getResultSet();
                if(rs.next()) {
                    success = rs.getBoolean("senha_match");
                }
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return success;
    }

    public List<Usuario> search(List<String> filters) {
        return List.of();
    }
}
