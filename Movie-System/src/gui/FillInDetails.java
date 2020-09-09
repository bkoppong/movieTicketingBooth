package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import api.UserAPI;
import database.DatabaseConnection;

public class FillInDetails extends JFrame {

    JPanel panel = new JPanel();


    public static void main(String[] args) {
        FillInDetails frameTabel = new FillInDetails("", "", "", null, (Integer) null);
    }


    public FillInDetails(String title, String date, String time, ArrayList<Integer> list, int counter) {

        super("Fill In Details");
        setSize(350, 250);
        setLocation(500, 280);
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);
        JLabel fullName = new JLabel("Name    ");
        fullName.setBounds(25, 40, 100, 15);
        panel.add(fullName);

        JTextField name = new JTextField();
        name.setBounds(125, 35, 150, 20);
        panel.add(name);

        JLabel ICNumber = new JLabel("IC Number     ");
        ICNumber.setBounds(25, 80, 100, 15);
        panel.add(ICNumber);

        JTextField ic = new JTextField();
        ic.setBounds(125, 75, 150, 20);
        panel.add(ic);

        JLabel AcNo = new JLabel("Telephone No.   ");
        AcNo.setBounds(25, 120, 100, 15);
        panel.add(AcNo);

        JTextField no = new JTextField();
        no.setBounds(125, 115, 150, 20);
        panel.add(no);

        JButton button = new JButton("Confirm");
        button.setBounds(185, 160, 80, 25);
        panel.add(button);

        JButton back = new JButton("Back");
        back.setBounds(55, 160, 80, 25);
        panel.add(back);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                String message = "Are you sure you want to proceed? ";
                int answer = JOptionPane.showConfirmDialog(frame, message);
                if (answer == JOptionPane.YES_OPTION) {
                    double totalPrice = 0;
                    try {
                        totalPrice = checkPrice(title, date, counter);
                        System.out.println(totalPrice);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    String Name = name.getText();
                    String NC = ic.getText();
                    String acNum = no.getText();
                    String number = null;
                    try {
                        number = findNo(date, title, time);
                        //System.out.println(number);
                    } catch (Exception e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }

                    UserAPI user = new UserAPI();
                    try {
                        user.insert(Name, NC, title, date, time, list, totalPrice, number);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    ShowReceipt show = null;
                    try {
                        show = new ShowReceipt(title, date, time, list, totalPrice, number);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    show.setVisible(true);
                    setVisible(false);
                    frame.dispose();

                } else if (answer == JOptionPane.NO_OPTION) {
                }
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectSeat secondform = null;
                try {
                    secondform = new SelectSeat("", "", "");
                    secondform.createAndShowGui(title, date, time);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                secondform.setVisible(true);
                setVisible(false);
                dispose();
            }
        });


    }

    public String findNo(String date, String title, String time) throws Exception {
        // TODO Auto-generated method stub
        String number = null;
        String sql = "Select hall from movie where date=? AND title= ? AND time = ? ";
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, date);
        ps.setString(2, title);
        ps.setString(3, time);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            number = rs.getString("hall");
        }
        // System.out.println(pass);
        ps.close();
        rs.close();
        conn.close();
        return number;
    }


    public double checkPrice(String title, String date, int counter) throws Exception {
        double total = 0;
        double Price = 0;
        String sql = "SELECT price from movie WHERE title= ? AND date = ?";
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, date);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Price = rs.getDouble("price");
        }
        total = Price * counter;
        ps.close();
        rs.close();
        conn.close();
        return total;

    }


}
