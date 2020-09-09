package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class AdminMenu {

    JLabel label, picLabel, picBack, choose;
    JButton addMovie, viewMovie;
    static JFrame frame;

    public JPanel createContentPane(String name) throws IOException {
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        totalGUI.setBackground(Color.lightGray);

		/*label = new JLabel("Welcome"+"  "+name);
		label.setLocation(120,20);
		label.setSize(120, 30);
		label.setFont(new Font("Serif",Font.BOLD,18));
	    label.setForeground(Color.BLUE);*/

        choose = new JLabel("Please select one ");
        choose.setLocation(120, 30);
        choose.setSize(130, 30);
        choose.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 16));
        choose.setForeground(Color.BLUE);

        Image myPicture = ImageIO.read(this.getClass().getResource("/Movie-Clapper-icon.png"));
        Image scaled = myPicture.getScaledInstance(120, 105, Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(scaled));
        picLabel.setLocation(255, 170);
        picLabel.setSize(100, 100);

        BufferedImage myPicture1 = ImageIO.read(this.getClass().getResource("/back.jpg"));
        Image scaled1 = myPicture1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        picBack = new JLabel(new ImageIcon(scaled1));
        picBack.setLocation(5, 170);
        picBack.setSize(100, 100);

        addMovie = new JButton("Add Movie");
        addMovie.setLocation(110, 90);
        addMovie.setSize(150, 35);


        viewMovie = new JButton("View Movie ");
        viewMovie.setLocation(110, 150);
        viewMovie.setSize(150, 35);

        //totalGUI.add(label);
        totalGUI.add(choose);
        totalGUI.add(addMovie);
        totalGUI.add(viewMovie);
        totalGUI.add(picLabel);
        totalGUI.add(picBack);

        addMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddMovie add = new AddMovie();
                try {
                    add.createAndShowGUI();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                add.setVisible(true);
                setVisible(false);
                frame.dispose();
            }
        });

        viewMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ViewMovie view = null;
                try {
                    view = new ViewMovie();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //view.createAndShowGUI();
                view.setVisible(true);
                setVisible(false);
                frame.dispose();
            }
        });

        picBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
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

        return totalGUI;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI("");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }

    static void createAndShowGUI(String name) throws IOException {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Selection");

        //Create and set up the content pane.
        AdminMenu demo = new AdminMenu();
        frame.setContentPane(demo.createContentPane(name));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 300);
        frame.setLocation(500, 220);
        frame.setVisible(true);
    }


    public void setVisible(boolean b) {
        // TODO Auto-generated method stub

    }
}
