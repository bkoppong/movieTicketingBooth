package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DatabaseConnection;

public class ShowReceipt extends JFrame {

    JPanel panel = new JPanel();


    public static void main(String[] args) throws Exception {
        ShowReceipt frameTabel = new ShowReceipt("", "", "", null, (Double) null, "");
    }

    public ShowReceipt(String title, String date, String time, ArrayList<Integer> list, double total, String no) throws Exception {
        // TODO Auto-generated constructor stub

        super("Receipt");
        setSize(400, 370);
        setLocation(500, 260);
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        JLabel HallNumber = new JLabel("Hall Number    :");
        HallNumber.setBounds(25, 20, 100, 15);
        panel.add(HallNumber);

        JLabel number = new JLabel(no);
        number.setBounds(125, 15, 150, 20);
        panel.add(number);

        JLabel MovieTitle = new JLabel("Movie Title       :");
        MovieTitle.setBounds(25, 60, 100, 15);
        panel.add(MovieTitle);

        JLabel Title = new JLabel(title + "");
        Title.setBounds(125, 55, 150, 20);
        panel.add(Title);

        JLabel MovieDate = new JLabel("Date                   :");
        MovieDate.setBounds(25, 100, 100, 15);
        panel.add(MovieDate);

        JLabel Date = new JLabel(date);
        Date.setBounds(125, 95, 150, 20);
        panel.add(Date);

        JLabel MovieTime = new JLabel("Time                  :");
        MovieTime.setBounds(25, 140, 100, 15);
        panel.add(MovieTime);

        JLabel Time = new JLabel(time);
        Time.setBounds(125, 135, 150, 20);
        panel.add(Time);

        JLabel Seat = new JLabel("Seat(s)              :");
        Seat.setBounds(25, 180, 100, 15);
        panel.add(Seat);

        String res = convertToString(list);

        JLabel selectedSeat = new JLabel(res);
        selectedSeat.setBounds(125, 175, 150, 20);
        panel.add(selectedSeat);

        JLabel totalPrice = new JLabel("Total                  :");
        totalPrice.setBounds(25, 230, 100, 15);
        panel.add(totalPrice);

        JLabel price = new JLabel("RM " + total + "");
        price.setBounds(125, 225, 150, 20);
        panel.add(price);

        JButton button = new JButton("Close");
        button.setBounds(150, 275, 160, 25);
        panel.add(button);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        button.addActionListener(new ActionListener() {
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
            }

        });

    }


    static String convertToString(ArrayList<Integer> numbers) {
        StringBuilder builder = new StringBuilder();
        // Append all Integers in StringBuilder to the StringBuilder.
        for (int number : numbers) {
            builder.append(number);
            builder.append(",");
        }
        // Remove last delimiter with setLength.
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }


}
