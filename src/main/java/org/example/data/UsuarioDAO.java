package org.example.data;

import java.util.List;

public class UsuarioDAO implements DAO {
    public boolean create (Object dataObject){
        System.out.println("create");
        return true;
    }

    public Object read(String key) {
        System.out.println("read");
        return new Object();
    }

    public boolean update(Object dataObject) {
        System.out.println("update");
        return true;
    }

    public boolean delete (String key) {
        return false;
    }

    public List<Object> search(List<String> filters) {
        return List.of();
    }
}
