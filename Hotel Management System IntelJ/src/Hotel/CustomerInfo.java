package Hotel;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerInfo extends JFrame implements ActionListener {
    JTable table;

    JButton searchButton,back;

    JTextField searchField;

    public CustomerInfo(){
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel Search = new JLabel("Search:");
        Search.setBounds(10,10,100,20);
        add(Search);

        searchField = new JTextField("Customer name");
        searchField.setBounds(70, 10, 150, 20);
        add(searchField);

        searchButton = new JButton("Search Customer");
        searchButton.setBounds(240,10,120,20);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.BLACK);
        searchButton.addActionListener(this);
        add(searchButton);

        back = new JButton("Exit");
        back.setBounds(380,10,80,20);
        back.setForeground(Color.white);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        JLabel CustID = new JLabel("Document");
        CustID.setBounds(0,40,100,20);
        add(CustID);

        JLabel CustPhone = new JLabel("Phone Number");
        CustPhone.setBounds(120,40,100,20);
        add(CustPhone);

        JLabel CustName = new JLabel("Name");
        CustName.setBounds(240,40,100,20);
        add(CustName);

        JLabel CustGender = new JLabel("Gender");
        CustGender.setBounds(355,40,100,20);
        add(CustGender);

        JLabel CustCountry = new JLabel("Country");
        CustCountry.setBounds(475,40,100,20);
        add(CustCountry);

        JLabel CustRoom = new JLabel("Room");
        CustRoom.setBounds(595,40,100,20);
        add(CustRoom);

        JLabel CustCheckin = new JLabel("Check-in time");
        CustCheckin.setBounds(710,40,100,20);
        add(CustCheckin);

        JLabel CustDeposit = new JLabel("Deposit");
        CustDeposit.setBounds(830,40,100,20);
        add(CustDeposit);

        table = new JTable();
        table.setBounds(0,60,950,400);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs  = c.s.executeQuery("select * from customer order by name asc");
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
            String custName = searchField.getText();
            try{
                Conn c = new Conn();

                if(!custName.isEmpty()) {
                    ResultSet rs = c.s.executeQuery("select * from customer where name like "+ "'" + custName + "'");
                    //JOptionPane.showMessageDialog(null,"You arrived here");

                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else if(custName.isEmpty()){
                    ResultSet rs = c.s.executeQuery("select * from customer");
                    //JOptionPane.showMessageDialog(null,"You arrived here 2");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else{
                    JOptionPane.showMessageDialog(null,"Employee doesn't exists or you typed a wrong name");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==back){
            dispose();
            new Reception();
        }
    }
}
