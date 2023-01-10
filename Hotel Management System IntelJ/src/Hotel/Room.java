package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class Room extends JFrame implements ActionListener {
    JTable table;

    JButton searchButton;

    JTextField searchField;

    public Room(){
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,0,600,600);
        add(image);

        JLabel Search = new JLabel("Search:");
        Search.setBounds(10,10,100,20);
        add(Search);

        searchField = new JTextField("Room Number");
        searchField.setBounds(70, 10, 150, 20);
        add(searchField);

        searchButton = new JButton("Search Room");
        searchButton.setBounds(240,10,120,20);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.BLACK);
        searchButton.addActionListener(this);
        add(searchButton);

        JLabel rNumber = new JLabel("Room Number");
        rNumber.setBounds(10,40,100,20);
        add(rNumber);

        JLabel rAvailability = new JLabel("Availability");
        rAvailability.setBounds(120,40,100,20);
        add(rAvailability);

        JLabel Status = new JLabel("Status");
        Status.setBounds(230,40,100,20);
        add(Status);

        JLabel rPrice = new JLabel("Price");
        rPrice.setBounds(330,40,100,20);
        add(rPrice);

        table = new JTable();
        table.setBounds(0,60,500,400);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs  = c.s.executeQuery("select * from room order by room_number asc");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }

        setLayout(null);
        setBounds(300,200,1050,600);
        setResizable(false);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == searchButton){
            String roomNumber = searchField.getText();
            try{
                Conn c = new Conn();

                if(!roomNumber.isEmpty()) {
                    ResultSet rs = c.s.executeQuery("select * from room where room_number like " + roomNumber);
                    //JOptionPane.showMessageDialog(null,"You arrived here");

                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else if(roomNumber.isEmpty()){
                    ResultSet rs = c.s.executeQuery("select * from room");
                    //JOptionPane.showMessageDialog(null,"You arrived here 2");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else{
                    JOptionPane.showMessageDialog(null,"Room doesn't exists or is an invalid number");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new Room();
    }

}
