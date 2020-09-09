package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import api.MovieAPI;
import bean.Movie;

public class ViewMovie extends JFrame {

    public ViewMovie() throws Exception {
        initComponents();
        populateJTable();
    }

    // create a methode to populate data Into JTable from Mysql  database And Displaying Picture

    public void populateJTable() throws Exception {
        MyQuery mq = new MyQuery();
        ArrayList<Movie> list = mq.BindTable();
        String[] columnName = {"Id", "Title", "Date", "Time", "Price", "Hall", "Image"};
        Object[][] rows = new Object[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            rows[i][0] = list.get(i).getID();
            rows[i][1] = list.get(i).getTitle();
            rows[i][2] = list.get(i).getDate();
            rows[i][3] = list.get(i).getTime();
            rows[i][4] = list.get(i).getPrice();
            rows[i][5] = list.get(i).getHall();
            if (list.get(i).getImage() != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getImage()).getImage()
                        .getScaledInstance(150, 120, Image.SCALE_SMOOTH));
                rows[i][6] = image;
            } else {
                rows[i][6] = null;
            }
        }

        TheModel model = new TheModel(rows, columnName);
        jTable1.setModel(model);
        jTable1.setRowHeight(120);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(150);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new JTable();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JButton("Back");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminMenu secondform = new AdminMenu();
                try {
                    secondform.createAndShowGUI("");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                secondform.setVisible(true);
                setVisible(false);
                dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(261, Short.MAX_VALUE).addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addContainerGap().addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>


    // show image from jtable to jlabel
    private void jTable1MouseClicked(MouseEvent evt) {
        Point p = evt.getPoint();
        int col = jTable1.columnAtPoint(p);
        int row = jTable1.rowAtPoint(p);
        int i = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2 && (col == 0)) {
            String id = (String) jTable1.getValueAt(row, col);
            MovieAPI movie = new MovieAPI();
            try {
                movie.getData(id);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            dispose();
        } else {
            ImageIcon image1 = (ImageIcon) jTable1.getValueAt(i, 6);
            Image image2 = image1.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight()
                    , Image.SCALE_SMOOTH);
            ImageIcon image3 = new ImageIcon(image2);
            jLabel1.setIcon(image3);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewMovie().setVisible(true);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private JTable jTable1;
    private javax.swing.JButton back;

    // End of variables declaration
}

