package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import api.MovieAPI;
import api.UserAPI;
import bean.Movie;

public class EditMovie extends JTextField {

    static JFrame frame;
    private JLabel label, title, dateOfShow, timeOfShow, insertData, price, hallNumber, description;
    private JButton cancel, edit;
    JTextField movieTitle, moviePrice, movieTime, movieHall, movieDate;
    JTextArea movieDescription;
    String s;

    public JPanel createContentPane(String _title, String _date, String _time, String _price, String _description, String _hall, byte[] img, String id) throws IOException {

        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        totalGUI.setBackground(Color.lightGray);

        insertData = new JLabel("EDIT MOVIE DETAILS");
        insertData.setLocation(20, 15);
        insertData.setSize(230, 30);
        insertData.setFont(new Font("Times New Roman", 0, 20));
        insertData.setForeground(Color.magenta);

        label = new JLabel();
        label.setLocation(315, 40);
        label.setSize(165, 230);
        Border border = BorderFactory.createLineBorder(Color.BLACK); // add border
        label.setBorder(border);
        ImageIcon image = new ImageIcon(img);
        Image im = image.getImage();
        Image myImg = im.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(myImg);
        label.setIcon(newImage);

        title = new JLabel("Movie Title         : ");
        title.setLocation(15, 65);
        title.setSize(120, 25);
        title.setFont(new Font("Tahoma", 0, 12));

        movieTitle = new JTextField();
        movieTitle.setLocation(130, 70);
        movieTitle.setSize(170, 20);
        movieTitle.setText(_title);


        dateOfShow = new JLabel("Date of Show     : ");
        dateOfShow.setLocation(15, 105);
        dateOfShow.setSize(120, 25);
        dateOfShow.setFont(new Font("Tahoma", 0, 12));

        //DateFormat format = new SimpleDateFormat("yyyy-MMMM--dd");
        //JFormattedTextField dateTextField = new JFormattedTextField(format);
        //dateTextField.setLocation(130,110);
        //dateTextField.setSize(170, 20);

        movieDate = new JTextField();
        movieDate.setLocation(130, 110);
        movieDate.setSize(170, 20);
        movieDate.setText(_date);


        timeOfShow = new JLabel("Time of Show     :");
        timeOfShow.setLocation(15, 145);
        timeOfShow.setSize(120, 25);
        timeOfShow.setFont(new Font("Tahoma", 0, 12));

        movieTime = new JTextField();
        movieTime.setLocation(130, 150);
        movieTime.setSize(170, 20);
        movieTime.setText(_time);

        price = new JLabel("Movie Price (RM)  :  ");
        price.setLocation(15, 185);
        price.setSize(120, 25);
        price.setFont(new Font("Tahoma", 0, 12));

        moviePrice = new JTextField();
        moviePrice.setLocation(130, 190);
        moviePrice.setSize(170, 20);
        moviePrice.setText(_price);

        hallNumber = new JLabel("Hall Number        : ");
        hallNumber.setLocation(15, 225);
        hallNumber.setSize(120, 25);
        hallNumber.setFont(new Font("Tahoma", 0, 12));

        movieHall = new JTextField();
        movieHall.setLocation(130, 230);
        movieHall.setSize(170, 20);
        movieHall.setText(_hall);

        movieDescription = new JTextArea();
        movieDescription.setLocation(15, 300);
        movieDescription.setSize(420, 110);
        movieDescription.setText(_description);
        movieDescription.setWrapStyleWord(true);
        movieDescription.setBorder(BorderFactory.createTitledBorder("Description"));

        totalGUI.add(new JScrollPane(movieDescription));


        cancel = new JButton("Cancel");
        cancel.setLocation(235, 435);
        cancel.setSize(85, 25);

        edit = new JButton("Edit");
        edit.setLocation(335, 435);
        edit.setSize(85, 25);

        totalGUI.add(insertData);
        totalGUI.add(label);
        totalGUI.add(title);
        totalGUI.add(movieTitle);
        totalGUI.add(dateOfShow);
        totalGUI.add(movieDate);
        totalGUI.add(timeOfShow);
        totalGUI.add(movieTime);
        totalGUI.add(price);
        totalGUI.add(moviePrice);
        totalGUI.add(hallNumber);
        totalGUI.add(movieHall);
        totalGUI.add(movieDescription);
        totalGUI.add(cancel);
        totalGUI.add(edit);

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = movieTitle.getText();
                String date = movieDate.getText();
                String time = movieTime.getText();
                String price = moviePrice.getText();
                String no = movieHall.getText();
                String description = movieDescription.getText();
                MovieAPI api = new MovieAPI();
                try {
                    api.edit(title, date, time, price, no, description, id);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                AdminMenu back = new AdminMenu();
                try {
                    back.createAndShowGUI("");
                } catch (IOException e1) {
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
                AdminMenu back = new AdminMenu();
                try {
                    back.createAndShowGUI("");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                back.setVisible(true);
                setVisible(false);
                frame.dispose();
            }
        });
        return totalGUI;
    }


    public void createAndShowGUI(String title, String date, String time, String price, String description, String hall, byte[] img, String id) throws IOException {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("New Movie");
        //Create and set up the content pane.
        frame.setContentPane(createContentPane(title, date, time, price, description, hall, img, id));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(515, 520);
        frame.setLocation(480, 120);
        frame.setVisible(true);
    }

    //Methode To Resize The ImageIcon
    public ImageIcon ResizeImage(String imgPath) {
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    public static void main(String[] args) throws IOException {
        EditMovie am = new EditMovie();
        am.createAndShowGUI("", "", "", "", "", "", null, "");
    }
}
