/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import JDBC_Connection.JDBCUtil;
import Model.Table_Name;
import Model.Word_Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hokta
 */
public class Table_name_DAO implements DAOInterface<Table_Name>{

    @Override
    public boolean insert(Table_Name t, String table_name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(String s, Table_Name t, String table_name) {
        try {
            java.sql.Connection connection = JDBC_Connection.JDBCUtil.getConnection();
            String SQL = "UPDATE List_of_table SET score = ? WHERE TB_name = ?";
            PreparedStatement st = connection.prepareStatement(SQL);

            // Set values in the correct order
            st.setInt(1, t.getScore());
            st.setString(2, t.getName());

            // Execute the query
            int rows = st.executeUpdate();
            System.out.println("There are " + rows + " rows changed");

            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Table_Name t, String table_name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Table_Name> selectAll(String table_name) {
        ArrayList<Table_Name> result = new ArrayList<Table_Name>();
        
        try{
            java.sql.Connection connection = JDBC_Connection.JDBCUtil.getConnection();
            String SQL = "SELECT * FROM List_of_table";
            PreparedStatement st = connection.prepareStatement(SQL);
            
            // Thực thi lệnh
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String name = rs.getString("TB_name");
                if(name.equals("Saved_Table")) continue;
                int score = rs.getInt("score");

                Table_Name tb_name = new Table_Name(name, score);
                result.add(tb_name);
            }
            JDBCUtil.closeConnection(connection);

        } catch(SQLException ex){
            System.out.println(ex);
            System.out.println("\nLỗi ở phương thức selectAll");
        }
        return result; 
    }
    
    public Table_Name selectOne(String table_name){
        Table_Name result  = null;
        try{
            java.sql.Connection connection = JDBC_Connection.JDBCUtil.getConnection();
            String SQL = "SELECT * FROM List_of_table WHERE TB_name = ?";
            PreparedStatement st = connection.prepareStatement(SQL);
            st.setString(1, table_name);
            // Thực thi lệnh
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String name = rs.getString("TB_name");
                int score = rs.getInt("score");
                result = new Table_Name(name, score);
            }
            JDBCUtil.closeConnection(connection);

        } catch(SQLException ex){
            System.out.println(ex);
            System.out.println("\nLỗi ở phương thức selectAll");
        }
        return result;
    }
    
}
