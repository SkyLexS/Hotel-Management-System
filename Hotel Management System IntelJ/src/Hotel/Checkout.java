package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Checkout extends JFrame implements ActionListener {
    Choice custID;
    JTextField fieldRoom,lbcheckin,lbcheckout;
    JButton checkout,back,check;
    //JLabel lbcheckin,lbcheckout;
    public Checkout(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Checkout");
        text.setBounds(100,20,100,30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(text);

        JLabel id = new JLabel("Customer ID");
        id.setBounds(30, 73, 100, 30);
        add(id);

        custID = new Choice();
        custID.setBounds(150,80,150,25);
        add(custID);



        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel tick = new JLabel(i3);
        tick.setBounds(310,80,20,20);
        add(tick);

        JLabel roomNumber = new JLabel("Room Number");
        roomNumber.setBounds(30, 130, 100, 30);
        add(roomNumber);

        fieldRoom = new JTextField();
        fieldRoom.setBounds(150,130,150,25);
        add(fieldRoom);

        JLabel checkinTime = new JLabel("Checkin Time");
        checkinTime.setBounds(30, 180, 100, 30);
        add(checkinTime);

        lbcheckin = new JTextField();
        lbcheckin.setBounds(150,180,150,25);
        add(lbcheckin);


        JLabel checkoutTime = new JLabel("Checkout Time");
        checkoutTime.setBounds(30, 230, 100, 30);
        add(checkoutTime);

        Date date = new Date();
        lbcheckout = new JTextField(""+date);
        lbcheckout.setBounds(150,230,150,25);
        add(lbcheckout);

//        JLabel checkoutTime = new JLabel("Checkout Time");
//        checkoutTime.setBounds(30, 230, 100, 30);
//        add(checkoutTime);
//
//        lbcheckout = new JLabel();
//        lbcheckout.setBounds(150,230,150,25);
//        add(lbcheckout);

        checkout = new JButton("Checkout");
        checkout.setBounds(30,280,120,30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(this);
        add(checkout);

        check = new JButton("Check");
        check.setBounds(170,320,120,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        back = new JButton("Exit");
        back.setBounds(170,280,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400,250,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(350,50,400,250);
        add(image);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                custID.add(rs.getString("IDnum"));
                fieldRoom.setText(rs.getString("room"));
                lbcheckin.setText(rs.getString("checkin_time"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        setBounds(300,200,800,400);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String id = custID.getSelectedItem();
            String query = "select * from customer where IDnum='" + id +"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    fieldRoom.setText(rs.getString("room"));
                    lbcheckin.setText(rs.getString("checkin_time"));
                }

            }catch(Exception e){
                e.printStackTrace();
            }


        }else if(ae.getSource() == checkout){
            String query1 = "delete from customer where IDnum='"+custID.getSelectedItem()+"'";
            String query2 = "update room set availability='Available' where room_number='"+fieldRoom.getText()+"'";
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Checkout done");

            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == back){
            setVisible(false);
            new Reception();
        }

    }
    public static void main(String[] args){
        new Checkout();
    }
}
