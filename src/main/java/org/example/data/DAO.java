package org.example.data;

import java.util.List;

public interface DAO {
    boolean create(Object dataObject);
    Object read(String key);
    boolean update(Object dataObject);
    boolean delete(String key);
    List<Object> search(List<String> filters);
}
