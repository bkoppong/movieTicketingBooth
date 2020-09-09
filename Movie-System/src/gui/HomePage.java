package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import api.UserAPI;
import database.DatabaseConnection;

public class HomePage {

    JLabel picLabel, title, title1, userName, password, buyTicket;
    JTextField name;
    JPasswordField pass;
    JButton login;
    static JFrame frame;


    public JPanel createContentPane() throws IOException {
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        totalGUI.setBackground(Color.WHITE);

        Image myPicture = ImageIO.read(this.getClass().getResource("/movieLogo.jpg"));
        Image scaled = myPicture.getScaledInstance(100, 70, Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(scaled));
        title = new JLabel("Cyber");
        title.setLocation(80, 20);
        title.setSize(120, 30);
        title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 18));
        title.setForeground(Color.BLUE);

        picLabel.setLocation(145, 0);
        picLabel.setSize(100, 100);

        title1 = new JLabel("Cinema ");
        title1.setLocation(245, 50);
        title1.setSize(120, 30);
        title1.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 18));
        title1.setForeground(Color.BLUE);

        userName = new JLabel("Admin Name  : ");
        userName.setLocation(80, 110);
        userName.setSize(120, 30);

        name = new JTextField();
        name.setLocation(175, 115);
        name.setSize(120, 20);

        password = new JLabel("Password       : ");
        password.setLocation(80, 150);
        password.setSize(120, 30);

        pass = new JPasswordField(30);
        pass.setLocation(175, 155);
        pass.setSize(120, 20);

        login = new JButton("Login");
        login.setLocation(210, 190);
        login.setSize(80, 20);
        login.setForeground(Color.BLACK);

        buyTicket = new JLabel("Buy movie tickets");
        buyTicket.setLocation(129, 220);
        buyTicket.setSize(120, 20);
        buyTicket.setFont(new Font("Serif", Font.BOLD, 11));
        buyTicket.setForeground(Color.RED);

        totalGUI.add(title);
        totalGUI.add(picLabel);
        totalGUI.add(title1);
        totalGUI.add(userName);
        totalGUI.add(name);
        totalGUI.add(password);
        totalGUI.add(pass);
        totalGUI.add(login);
        totalGUI.add(buyTicket);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String Pass = new String(pass.getPassword());

                UserAPI user = new UserAPI();
                boolean answer = false;

                try {

                    answer = user.checklogin(Name, Pass);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (answer == true) {
                    AdminMenu um = new AdminMenu();
                    try {
                        um.createAndShowGUI(Name);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    um.setVisible(true);
                    setVisible(false);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong User name or Password");
                    name.requestFocus();
                }
            }

            private void setVisible(boolean b) {
                // TODO Auto-generated method stub

            }
        });


        buyTicket.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                BuyTicket buy = new BuyTicket();
                try {
                    buy.createAndShowGUI();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                buy.setVisible(true);
                setVisible(false);
                frame.dispose();

            }
        });

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    static void createAndShowGUI() throws IOException {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Home Page");

        //Create and set up the content pane.
        HomePage demo = new HomePage();
        frame.setContentPane(demo.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 300);
        frame.setLocation(500, 220);
        frame.setVisible(true);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub

    }


}