package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import database.DatabaseConnection;

public class SelectSeat extends JPanel {

    int a;
    Object source;
    int counter = 0;

    static JFrame frame = new JFrame("Select Seat");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGui("", "", "");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    static void createAndShowGui(String movie, String time, String date) throws Exception {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SelectSeat(movie, time, date));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public SelectSeat(String movie, String time, String date) throws Exception {
        JPanel topPanel = new JPanel(new GridLayout(1, 10)); // row,col
        RectDraw rect = new RectDraw();
        rect.setPreferredSize(new Dimension(20, 25));
        topPanel.add(rect);

        JToggleButton[] ButtonList = new JToggleButton[30];

        JPanel ButtonPanel = new JPanel(new GridLayout(5, 15, 30, 25)); // row,col,hgap,vgap
        for (int i = 0; i < 30; i++) {
            a = i + 1;
            ButtonList[i] = new JToggleButton("" + a);
            ButtonPanel.add(ButtonList[i]);

        }

        int no = findNo(movie, time, date);
        List<String> seats = checkSeat(movie, no);

        for (String s : seats) // remove [] brackets (22,23)
        {

            String[] selected = s.split(","); // remove the comma
            for (String t : selected) {
                for (int j = 0; j < 30; j++) {
                    if (ButtonList[j].getText().equals(t))  // if seats label with 22 and 23
                    {
                        ButtonList[j].setEnabled(false); // non-clickable
                        UIManager.put("ToggleButton.select", Color.GREEN);
                        SwingUtilities.updateComponentTreeUI(ButtonList[j]);
                    }
                }
            }

        }

        JPanel bottomPanel = new JPanel(new GridLayout(1, 5, 5, 10));
        JButton cancel = new JButton("Back");
        JButton confirm = new JButton("Next");
        bottomPanel.add(cancel);
        bottomPanel.add(confirm);

        setLayout(new BorderLayout(10, 25));
        add(topPanel, BorderLayout.PAGE_START);
        add(ButtonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 10));
        ButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); //top,left,bottom,right


        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < 30; i++) {
                    if (ButtonList[i].isSelected()) {
                        list.add(i + 1);
                        counter++;
                    }
                }
                if (list.size() == 0) {
                    JFrame parent = new JFrame();
                    JOptionPane.showMessageDialog(parent, "You haven't select a seat");
                } else {
                    FillInDetails details = new FillInDetails(movie, date, time, list, counter);
                    details.setVisible(true);
                    setVisible(false);
                    frame.dispose();
                    //System.out.println(counter);
                }

            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuyTicket secondform = new BuyTicket();
                try {
                    secondform.createAndShowGUI();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                secondform.setVisible(true);
                setVisible(false);
                frame.dispose();
            }
        });
    }

    public List<String> checkSeat(String movie, int no) throws Exception // get all the name
    {
        String sql = "SELECT seat from user_payment WHERE hall_number= ? AND title = ?";
        List<String> seat = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, no);
            ps.setString(2, movie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                seat.add(rs.getString("seat"));
            }
        }
        return seat;

    }

    private int findNo(String movie, String time, String date) throws Exception {
        // TODO Auto-generated method stub
        int number = 0;
        String sql = "Select hall from movie where date=? AND title= ? AND time = ? ";
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, date);
        ps.setString(2, movie);
        ps.setString(3, time);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            number = rs.getInt("hall");
        }
        ps.close();
        rs.close();
        conn.close();
        return number;
    }


    private static class RectDraw extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //g.drawRect(0,50,0,50);//x,y,width,height
            g.setColor(Color.GRAY);
            g.fillRect(20, 5, 370, 25); //x,y,width,height
            g.setColor(Color.BLUE);
            g.drawString("Movie Sceen", 150, 20);
        }


    }

}
