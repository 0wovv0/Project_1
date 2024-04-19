package DAO;

import java.util.ArrayList;

public interface DAOInterface<T> {
    public boolean insert(T t, String table_name);
    
    public boolean update(String s, T t, String table_name);
    
    public boolean delete(T t, String table_name);
    
    public ArrayList<T> selectAll(String table_name);
        
}
