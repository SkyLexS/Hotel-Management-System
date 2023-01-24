package Hotel;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateDriver extends JFrame implements ActionListener {
    JButton submit,back,searchButton;
    JTextField searchField;
    JCheckBox availableCars;
    JTable table;

    public UpdateDriver(){
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,0,600,600);
        add(image);

        JLabel Search = new JLabel("Search:");
        Search.setBounds(10,10,100,20);
        add(Search);

        availableCars = new JCheckBox("Show available cars");
        availableCars.setBounds(20,35,160,25);
        availableCars.setBackground(Color.WHITE);
        add(availableCars);

        searchField = new JTextField("");
        searchField.setBounds(70, 10, 150, 20);
        add(searchField);

        searchButton = new JButton("Search Cars");
        searchButton.setBounds(240,10,120,20);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.BLACK);
        searchButton.addActionListener(this);
        add(searchButton);

        submit = new JButton("Submit");
        submit.setBounds(240,35,120,20);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Exit");
        back.setBounds(380,10,80,20);
        back.setForeground(Color.white);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        JLabel rNumber = new JLabel("Nume");
        rNumber.setBounds(10,60,100,20);
        add(rNumber);

        JLabel rAvailability = new JLabel("Availability");
        rAvailability.setBounds(120,60,100,20);
        add(rAvailability);

        JLabel Status = new JLabel("Gender");
        Status.setBounds(230,60,100,20);
        add(Status);

        JLabel rPrice = new JLabel("Brand");
        rPrice.setBounds(330,60,100,20);
        add(rPrice);

        table = new JTable();
        table.setBounds(0,80,500,400);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs  = c.s.executeQuery("select * from driver order by name asc");
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
            String driverName = searchField.getText();
            try{
                Conn c = new Conn();

                if(!driverName.isEmpty() ) {
                    ResultSet rs = c.s.executeQuery("select * from driver where name='" +driverName+ "'");

                    //JOptionPane.showMessageDialog(null,"You arrived here");

                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else if(driverName.isEmpty()){
                    ResultSet rs = c.s.executeQuery("select * from driver");
                    //JOptionPane.showMessageDialog(null,"You arrived here 2");
                    table.setModel(DbUtils.resultSetToTableModel(rs));}

                else{
                    JOptionPane.showMessageDialog(null,"Driver doesn't exists or is an invalid name");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == submit){
            try{
                Conn c = new Conn();
                if(availableCars.isSelected()) {
                    ResultSet rs = c.s.executeQuery("select * from driver where availability='Available'");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else if(!availableCars.isSelected()){
                    ResultSet rs2 = c.s.executeQuery("select * from driver");
                    table.setModel(DbUtils.resultSetToTableModel(rs2));
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }else if(ae.getSource()==back){
            dispose();
            new Reception();
        }
    }
    public static void main(String[] args){
        new UpdateDriver();
    }
}
