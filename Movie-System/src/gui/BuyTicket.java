package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import api.UserAPI;
import database.DatabaseConnection;

public class BuyTicket {

    static JFrame frame;
    JLabel title, lblMainDate, selectMovie, dateOfShow, picture, labelDateOfShowTime, time, price;
    JComboBox Select, Date, Time, Price;
    JTextArea Description;
    JButton next, cancel;
    String getMovie, getTime, getDate;


    public JPanel createContentPane() throws IOException {

        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        totalGUI.setBackground(Color.lightGray);

        title = new JLabel("CYBER CINEMA");
        title.setLocation(170, 15);
        title.setSize(230, 30);
        title.setFont(new java.awt.Font("Times New Roman", 0, 20));
        title.setForeground(Color.BLACK);

        lblMainDate = new JLabel();
        lblMainDate.setLocation(172, 45);
        lblMainDate.setSize(330, 15);
        lblMainDate.setFont(new java.awt.Font("Tahoma", 0, 12));
        lblMainDate.setForeground(Color.RED);

        DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM dd, yyyy");
        Date date = new Date();
        lblMainDate.setText(dateFormat.format(date));

        selectMovie = new JLabel("Select Movie");
        selectMovie.setLocation(15, 85);
        selectMovie.setSize(200, 30);
        selectMovie.setFont(new java.awt.Font("Tahoma", 0, 12));

        Date = new JComboBox();
        Date.setLocation(115, 140);
        Date.setSize(175, 20);

        Time = new JComboBox();
        Time.setLocation(115, 190);
        Time.setSize(175, 20);

        Price = new JComboBox();
        Price.setLocation(115, 240);
        Price.setSize(175, 20);

        Description = new JTextArea();
        Description.setLocation(15, 300);
        Description.setSize(420, 120);
        Description.setWrapStyleWord(true);
        Description.setBorder(BorderFactory.createTitledBorder("Description"));

        picture = new JLabel();
        picture.setLocation(337, 47);
        picture.setSize(165, 230);
        Border border = BorderFactory.createLineBorder(Color.BLACK); // add border
        picture.setBorder(border);

        Select = new JComboBox();
        Select.setLocation(115, 90);
        Select.setSize(175, 20);
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String sql = "Select title FROM movie";
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("title");
                DefaultComboBoxModel model = (DefaultComboBoxModel) Select.getModel();
                if (model.getIndexOf(name) == -1) {
                    Select.addItem(name);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                getMovie = (String) Select.getSelectedItem();
                displayDate(getMovie);
                displayImage(getMovie);
                displayDescription(getMovie);
            }
        });

        Date.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                getDate = (String) Date.getSelectedItem();
                displayTime(getDate, getMovie);
            }
        });

        Time.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                getTime = (String) Time.getSelectedItem();
                displayPrice(getDate, getMovie, getTime);
            }
        });

        getMovie = (String) Select.getSelectedItem();
        displayDate(getMovie);
        displayImage(getMovie);
        displayDescription(getMovie);

        dateOfShow = new JLabel("Date of Show");
        dateOfShow.setLocation(15, 135);
        dateOfShow.setSize(200, 30);
        dateOfShow.setFont(new java.awt.Font("Tahoma", 0, 12));

        time = new JLabel("Time of Show  ");
        time.setLocation(15, 185);
        time.setSize(200, 30);
        time.setFont(new java.awt.Font("Tahoma", 0, 12));

        price = new JLabel("Movie Price  ");
        price.setLocation(15, 235);
        price.setSize(200, 30);
        price.setFont(new java.awt.Font("Tahoma", 0, 12));

        next = new JButton("Next");
        next.setLocation(200, 439);
        next.setSize(95, 25);

        cancel = new JButton("Cancel");
        cancel.setLocation(315, 439);
        cancel.setSize(95, 25);

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectSeat back = null;
                try {
                    back = new SelectSeat("", "", "");
                } catch (Exception e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                try {
                    back.createAndShowGui(getMovie, getTime, getDate);
                    //System.out.println(getMovie+getTime+getDate);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                back.setVisible(true);
                setVisible(false);
                frame.dispose();
            }
        });


        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomePage back = new HomePage();
                try {
                    back.createAndShowGUI();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                back.setVisible(true);
                setVisible(false);
                frame.dispose();
            }

        });

        totalGUI.add(title);
        totalGUI.add(lblMainDate);
        totalGUI.add(selectMovie);
        totalGUI.add(Select);
        totalGUI.add(dateOfShow);
        totalGUI.add(Date);
        totalGUI.add(picture);
        totalGUI.add(time);
        totalGUI.add(Time);
        totalGUI.add(price);
        totalGUI.add(Price);
        totalGUI.add(Description);
        totalGUI.add(next);
        totalGUI.add(cancel);
        return totalGUI;
    }


    static void createAndShowGUI() throws IOException {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Movie Ticket Sales");
        //Create and set up the content pane.
        BuyTicket demo = new BuyTicket();
        frame.setContentPane(demo.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(535, 520);
        frame.setLocation(480, 120);
        frame.setVisible(true);
    }


    public void setVisible(boolean b) {
        // TODO Auto-generated method stub

    }

    private void displayDate(Object selected) {
        // TODO Auto-generated method stub
        try {
            Date.removeAllItems();
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String sql = "Select date FROM movie WHERE title = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setObject(1, selected);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String date1 = rs.getString("date");
                DefaultComboBoxModel model = (DefaultComboBoxModel) Date.getModel();
                if (model.getIndexOf(date1) == -1) {
                    Date.addItem(date1);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        getDate = (String) Date.getSelectedItem();
        displayTime(getDate, getMovie);

    }


    private void displayImage(Object selected) {
        // TODO Auto-generated method stub
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String sql = "Select image FROM movie WHERE title = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setObject(1, selected);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] img = rs.getBytes("image");

                //Resize The ImageIcon
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myImg = im.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                picture.setIcon(newImage);
            } else {
                JOptionPane.showMessageDialog(null, "No Data");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayTime(String getDate, String getMovie) {
        // TODO Auto-generated method stub
        try {
            Time.removeAllItems();
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String sql = "Select time FROM movie WHERE title = ? and date = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setObject(1, getMovie);
            ps.setObject(2, getDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String time = rs.getString("time");
                DefaultComboBoxModel model = (DefaultComboBoxModel) Time.getModel();
                if (model.getIndexOf(time) == -1) {
                    Time.addItem(time);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        getTime = (String) Time.getSelectedItem();
        displayPrice(getDate, getMovie, getTime);
    }

    private void displayPrice(String getDate, String getMovie, String getTime) {
        // TODO Auto-generated method stub
        try {
            Price.removeAllItems();
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String sql = "Select price FROM movie WHERE title = ? And date= ? And time = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setObject(1, getMovie);
            ps.setObject(2, getDate);
            ps.setObject(3, getTime);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String price = rs.getString("price");
                DefaultComboBoxModel model = (DefaultComboBoxModel) Price.getModel();
                if (model.getIndexOf(price) == -1) {
                    Price.addItem(price);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayDescription(Object selected) {
        // TODO Auto-generated method stub
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String sql = "Select description FROM movie WHERE title = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setObject(1, selected);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                Description.setText(description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
