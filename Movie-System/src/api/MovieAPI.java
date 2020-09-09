package api;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import bean.Movie;
import database.DatabaseConnection;
import gui.EditMovie;

public class MovieAPI {

    public void insert(String title, String date, String time, String price, String no, String description,
                       InputStream is) throws Exception {
        // TODO Auto-generated method stub
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String sql = "Insert into movie(title,date,time,price,hall,description,image)VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, price);
            ps.setString(5, no);
            ps.setString(6, description);
            ps.setBlob(7, is);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Movie Inserted Successfully");
            connect.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void getData(String id) throws Exception {

        String movie = null, date = null, time = null, price = null, description = null, hall = null;
        byte[] img = null;
        // TODO Auto-generated method stub
        String sql = "Select * FROM movie WHERE id= ? ";
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            movie = rs.getString("title");
            date = rs.getString("date");
            time = rs.getString("time");
            price = rs.getString("price");
            description = rs.getString("description");
            hall = rs.getString("hall");
            img = rs.getBytes("image");
        }
        EditMovie edit = new EditMovie();
        edit.createAndShowGUI(movie, date, time, price, description, hall, img, id);
        edit.setVisible(true);
        ps.close();
        rs.close();
        conn.close();
    }

    public void edit(String title, String date, String time, String price, String no, String description, String id) throws Exception {
        // TODO Auto-generated method stub
        DatabaseConnection db = new DatabaseConnection();
        Connection connect = db.getConnection();
        String sql = "UPDATE movie SET title = ?," + "date = ?," + "time = ?," + "price= ?," + "hall=?," + "description =? WHERE id = ? ";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, date);
        ps.setString(3, time);
        ps.setString(4, price);
        ps.setString(5, no);
        ps.setString(6, description);
        ps.setString(7, id);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Movie Details Updated");
        connect.close();
        ps.close();

    }

}
