package org.example.data;

import org.example.model.Usuario;

import java.util.List;

public interface UserDAO {
    boolean create(Usuario user, char[] password);
    Usuario read(String cpf);
    boolean update(Usuario user);
    boolean updatePassword(Usuario user, char[] password);
    boolean delete(String cpf);
    List<Usuario> search(List<String> filters);
}
