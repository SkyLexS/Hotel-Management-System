package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateCheck extends JFrame implements ActionListener {
    Choice custIDChoice;
    JTextField fieldName,fieldcheckin,fieldamountPaid,fieldpendingAmount,fieldcRoom;
    JButton check,update,back;

    public UpdateCheck(){
        setVisible(true);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel updStatus = new JLabel("Update status");
        updStatus.setFont(new Font("Tahoma",Font.PLAIN,20));
        updStatus.setBounds(90,20,200,30);
        updStatus.setForeground(Color.BLUE);
        add(updStatus);

        custIDChoice = new Choice();
        custIDChoice.setBounds(200,80,150,25);
        add(custIDChoice);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                custIDChoice.add(rs.getString("IDnum"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel custID = new JLabel("Customer ID");
        custID.setBounds(30,80,100,20);
        add(custID);

        JLabel custName = new JLabel("Name");
        custName.setBounds(30,120,100,20);
        add(custName);

        fieldName = new JTextField();
        fieldName.setBounds(200,120,150,25);
        add(fieldName);

        JLabel checkin = new JLabel("Checkin time");
        checkin.setBounds(30,160,100,20);
        add(checkin);

        fieldcheckin = new JTextField();
        fieldcheckin.setBounds(200,160,150,25);
        add(fieldcheckin);

        JLabel amountPaid = new JLabel("Amount Paid");
        amountPaid.setBounds(30,200,100,20);
        add(amountPaid);

        fieldamountPaid = new JTextField();
        fieldamountPaid.setBounds(200,200,150,25);
        add(fieldamountPaid);

        JLabel pendingAmount = new JLabel("Pending amount");
        pendingAmount.setBounds(30,240,100,20);
        add(pendingAmount);

        fieldpendingAmount = new JTextField();
        fieldpendingAmount.setBounds(200,240,150,25);
        add(fieldpendingAmount);

        JLabel roomNB = new JLabel("Room Number");
        roomNB.setBounds(30,280,100,20);
        add(roomNB);

        fieldcRoom = new JTextField();
        fieldcRoom.setBounds(200,280,150,25);
        add(fieldcRoom);

        check = new JButton("Check");
        check.setBounds(30,320,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(150,320,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(270,320,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400,50,500,300);
        add(image);

        setLayout(null);
        setBounds(300,200,980,500);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String id = custIDChoice.getSelectedItem();
            String query = "select * from customer where IDnum='" + id +"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    fieldcRoom.setText(rs.getString("room"));
                    fieldName.setText(rs.getString("name"));
                    fieldcheckin.setText(rs.getString("checkin_time"));
                    fieldamountPaid.setText(rs.getString("deposit"));
                }
                ResultSet ts = c.s.executeQuery("select * from room where room_number='" +fieldcRoom.getText()+ "'" );
                while(ts.next()){
                    String price  = ts.getString("price");
                    int amountPaid = Integer.parseInt(price) - Integer.parseInt(fieldamountPaid.getText());
                    if(amountPaid > 0) {
                        fieldpendingAmount.setText("" + amountPaid);
                    }else{
                        fieldpendingAmount.setText("0");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == update){
            String idnum = custIDChoice.getSelectedItem();
            String room = fieldcRoom.getText();
            String name = fieldName.getText();
            String checkin = fieldcheckin.getText();
            String deposit = fieldamountPaid.getText();
            String regex4Name = "[A-Za-z]+\s[A-Za-z]+";
            String regex4Deposit = "[0-9]{3}$";
            Pattern pattern4Name = Pattern.compile(regex4Name);
            Pattern pattern4Deposit = Pattern.compile(regex4Deposit);
            Matcher matcher4Name = pattern4Name.matcher(name);
            Matcher matcher4Deposit = pattern4Deposit.matcher(deposit);

            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from room where room_number='" +fieldcRoom.getText()+ "'");
                while(rs.next()){
                    String price = rs.getString("price");
                    int amountPaid = Integer.parseInt(price) - Integer.parseInt(fieldamountPaid.getText());
                    if(amountPaid < 0){
                        JOptionPane.showMessageDialog(null,"Too much money inserted");
                        return;
                    }


                }
            }catch(Exception e){
                e.printStackTrace();
            }


            if(name.equals("") || name.length()>20 || !matcher4Name.matches()){
                JOptionPane.showMessageDialog(null,"Name is empty or your name is more than 20 characters");
                return;
            }

            if(deposit.equals("") || deposit.length() > 5 || !matcher4Deposit.matches()){
                JOptionPane.showMessageDialog(null,"Deposit is empty or it is an invalid number");
                return;
            }

//            if(finalAmount < 0){
//                JOptionPane.showMessageDialog(null, "Too much money inserted");
//                return;
//            }

            try{
                Conn c = new Conn();
                c.s.executeUpdate("update customer set name='" +name+"', checkin_time='" +checkin+ "', deposit='" +deposit+ "'");
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");

                new Reception();
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }

        }else if(ae.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args){
        new UpdateCheck();
    }
}
