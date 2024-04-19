/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Word_Model;
import com.sun.jdi.connect.spi.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hokta
 */
public class Words_DAO implements DAOInterface<Word_Model>{

    @Override
    public boolean insert(Word_Model t, String table_name) {
        try{
            java.sql.Connection connection = JDBC_Connection.JDBCUtil.getConnection();
            String SQL = "INSERT INTO " + table_name +" (WORD, MEAN, IPA, GENRE) VALUES (? , ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(SQL);
            // Insert vao dong lenh SQL
            st.setString(1, t.getWord());
            st.setString(2, t.getMean());
            st.setString(3, t.getIpa());
            st.setString(4, t.getGenre());
            // Thực thi lệnh
            int rows = st.executeUpdate();
            System.out.println("There are " + rows +"rows changed");
            
            connection.close();
        } catch(SQLException ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(String s, Word_Model t, String table_name) {
        try {
            java.sql.Connection connection = JDBC_Connection.JDBCUtil.getConnection();
            String SQL = "UPDATE " + table_name + " SET WORD = ?, MEAN = ?, IPA = ?, GENRE = ? WHERE WORD = ?";
            PreparedStatement st = connection.prepareStatement(SQL);

            // Set values in the correct order
            st.setString(1, t.getWord());
            st.setString(2, t.getMean());
            st.setString(3, t.getIpa());
            st.setString(4, t.getGenre());
            st.setString(5, s);

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
    public boolean delete(Word_Model t, String table_name) {
        try{
            java.sql.Connection connection = JDBC_Connection.JDBCUtil.getConnection();
            String SQL = "DELETE FROM " + table_name + " WHERE WORD = ?";
            PreparedStatement st = connection.prepareStatement(SQL);
            // Insert vao dong lenh SQL
            st.setString(1, t.getWord());

            // Thực thi lệnh
            int rows = st.executeUpdate();
            System.out.println("There are " + rows +"rows changed");
            
            connection.close();
        } catch(SQLException ex){
            System.out.println(ex);
            return false;
        }
        return true;    
    }

    @Override
    public ArrayList<Word_Model> selectAll(String table_name) {
        ArrayList<Word_Model> result = new ArrayList<Word_Model>(); // Khởi tạo danh sách result
        try{
            java.sql.Connection connection = JDBC_Connection.JDBCUtil.getConnection();
            String SQL = "SELECT * FROM " + table_name;
            PreparedStatement st = connection.prepareStatement(SQL);
            
            // Thực thi lệnh
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String word = rs.getString("WORD");
                String mean = rs.getString("MEAN");
                String genre = rs.getString("GENRE");
                String ipa = rs.getString("IPA");
                Word_Model Word = new Word_Model(word, mean, ipa, genre);
                result.add(Word);
            }
            connection.close();
        } catch(SQLException ex){
            System.out.println(ex);
            System.out.println("\nLỗi ở phương thức selectAll");
        }
        return result; 
    }


}
