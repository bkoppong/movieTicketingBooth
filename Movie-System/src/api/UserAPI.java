package api;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JPasswordField;

import database.DatabaseConnection;

public class UserAPI {

    public boolean checklogin(String name, String Pass) throws Exception {
        boolean check = false;

        String sql = "SELECT * FROM admin WHERE name =? and password= ?";
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, Pass);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        ps.close();
        rs.close();
        conn.close();
        return check;
    }

    public void insert(String Name, String NC, String title, String date, String time, ArrayList<Integer> list, double totalPrice, String number) throws Exception {
        System.out.println(list);
        DatabaseConnection db = new DatabaseConnection();
        Connection connect = db.getConnection();
        String sql = "Insert into user_payment(name,ic,title,date,time,seat,price,hall_number)VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setString(1, Name);
        ps.setString(2, NC);
        ps.setString(3, title);
        ps.setString(4, date);
        ps.setString(5, time);
        String seatList = "";
        for (Integer seat : list) {
            if (seatList.equals("")) {
                seatList += seat;
            } else {
                seatList += "," + seat;
            }
        }
        ps.setString(6, seatList);
        ps.setDouble(7, totalPrice);
        ps.setString(8, number);
        ps.executeBatch();
        ps.executeUpdate();
        connect.close();
        ps.close();

    }


}
