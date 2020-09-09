package gui;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.Movie;
import database.DatabaseConnection;

public class MyQuery {

    public ArrayList<Movie> BindTable() throws Exception {

        ArrayList<Movie> list = new ArrayList<Movie>();
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT `id`,`title`, `date`, `time`, `price`, `hall`,`image` FROM `movie`");

            Movie p;
            while (rs.next()) {
                p = new Movie(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("price"),
                        rs.getString("hall"),
                        //rs.getString("description"),
                        rs.getBytes("image")
                );
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

