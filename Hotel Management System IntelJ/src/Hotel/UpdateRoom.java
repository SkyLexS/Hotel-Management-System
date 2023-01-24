package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateRoom extends JFrame implements ActionListener {
    JTextField fieldAvailability,fieldClean,fieldPrice,fieldBed;
    Choice RoomNumber;
    JButton check,update,back;

    public UpdateRoom(){
        setVisible(true);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel updStatus = new JLabel("Update status");
        updStatus.setFont(new Font("Tahoma",Font.PLAIN,20));
        updStatus.setBounds(90,20,200,30);
        updStatus.setForeground(Color.BLUE);
        add(updStatus);

        RoomNumber = new Choice();
        RoomNumber.setBounds(200,80,150,25);
        add(RoomNumber);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            while(rs.next()){
                RoomNumber.add(rs.getString("room_number"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel custID = new JLabel("Room Number");
        custID.setBounds(30,80,100,20);
        add(custID);

        JLabel availability = new JLabel("Availability");
        availability.setBounds(30,120,100,20);
        add(availability);

        fieldAvailability = new JTextField();
        fieldAvailability.setBounds(200,120,150,25);
        add(fieldAvailability);

        JLabel cleaningStatus = new JLabel("Cleaning Status");
        cleaningStatus.setBounds(30,160,100,20);
        add(cleaningStatus);

        fieldClean = new JTextField();
        fieldClean.setBounds(200,160,150,25);
        add(fieldClean);

        JLabel price = new JLabel("Price");
        price.setBounds(30,200,100,20);
        add(price);

        fieldPrice = new JTextField();
        fieldPrice.setBounds(200,200,150,25);
        add(fieldPrice);

        JLabel bedType = new JLabel("Bed Type");
        bedType.setBounds(30,240,100,20);
        add(bedType);

        fieldBed = new JTextField();
        fieldBed.setBounds(200,240,150,25);
        add(fieldBed);

        check = new JButton("Check");
        check.setBounds(30,280,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(150,280,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(270,280,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,500,300);
        add(image);

        setLayout(null);
        setBounds(300,200,980,500);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String roomNumberSelectedItem = RoomNumber.getSelectedItem();
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from room where room_number='" +roomNumberSelectedItem+ "'");
                while(rs.next()){
                    fieldAvailability.setText(rs.getString("availability"));
                    fieldClean.setText(rs.getString("cleaning_status"));
                    fieldPrice.setText(rs.getString("price"));
                    fieldBed.setText(rs.getString("bed_type"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == update){
            String roomNumberSelectedItem = RoomNumber.getSelectedItem();
            String availability = fieldAvailability.getText();
            String cleaning = fieldClean.getText();
            String price = fieldPrice.getText();
            String bed = fieldBed.getText();
            String regex4Price = "[0-9]{3}$";
            Pattern pattern4Price = Pattern.compile(regex4Price);
            Matcher matcher4Price = pattern4Price.matcher(price);

            if(!availability.equals("Available") && !availability.equals("Occupied")){
                JOptionPane.showMessageDialog(null,"Invalid option");
                return;
            }

            if(!cleaning.equals("Clean") && !cleaning.equals("Dirty")){
                JOptionPane.showMessageDialog(null,"Invalid Option");
                return;
            }
            if(Integer.parseInt(roomNumberSelectedItem) < 200 || !matcher4Price.matches()){
                if(Integer.parseInt(price) != 300){
                    JOptionPane.showMessageDialog(null,"Invalid price");
                }
            }else if(Integer.parseInt(roomNumberSelectedItem) >= 200 || !matcher4Price.matches()){
                if(Integer.parseInt(price) < 400){
                    JOptionPane.showMessageDialog(null,"Invalid price");
                }
            }
            if(!bed.equals("Single Bed") && !bed.equals("Double Bed") && !bed.equals("King Bed")){
                JOptionPane.showMessageDialog(null,"Invalid option");
                return;
            }

            try{
                Conn c = new Conn();
                c.s.executeUpdate("update room set availability='" +availability+"', cleaning_status='" +cleaning+ "', price='" +price+ "', bed_type='" +bed+ "' where room_number='"+roomNumberSelectedItem+"'");
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
        new UpdateRoom();

    }
}
