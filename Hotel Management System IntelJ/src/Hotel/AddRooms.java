package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRooms extends JFrame implements ActionListener {
    JTextField fieldRoomNo,fieldRoomPrice;
    JButton addRoom,cancel;
    JComboBox statusRoomCombo,cleaningStatusCombo,BedSizeCombo;
    public AddRooms(){
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Add Rooms");
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        heading.setBounds(150,20,200,20);
        add(heading);

        JLabel lbRoomNo = new JLabel("Room Number:");
        lbRoomNo.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbRoomNo.setBounds(60,80,120,30);
        add(lbRoomNo);

        fieldRoomNo = new JTextField();
        fieldRoomNo.setBounds(200, 80, 150, 30);
        add(fieldRoomNo);

        JLabel lbAvailable = new JLabel("Available:");
        lbAvailable.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbAvailable.setBounds(60,130,120,30);
        add(lbAvailable);

        String statusRoom[] = {"Available", "Occupied"};
        statusRoomCombo = new JComboBox(statusRoom);
        statusRoomCombo.setBounds(200,130,150,30);
        statusRoomCombo.setBackground(Color.WHITE);
        add(statusRoomCombo);

        JLabel lbClean = new JLabel("Cleaning Status:");
        lbClean.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbClean.setBounds(60,180,120,30);
        add(lbClean);

        String cleaningStatus[] = {"Clean", "Dirty"};
        cleaningStatusCombo = new JComboBox(cleaningStatus);
        cleaningStatusCombo.setBounds(200,180,150,30);
        cleaningStatusCombo.setBackground(Color.WHITE);
        add(cleaningStatusCombo);

        JLabel lbRoomPrice = new JLabel("Room Price:");
        lbRoomPrice.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbRoomPrice.setBounds(60,230,120,30);
        add(lbRoomPrice);

        fieldRoomPrice = new JTextField();
        fieldRoomPrice.setBounds(200, 230, 150, 30);
        add(fieldRoomPrice);

        JLabel lbBedSize = new JLabel("Bed Size:");
        lbBedSize.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbBedSize.setBounds(60,280,120,30);
        add(lbBedSize);

        String BedSize[] = {"Single Bed", "Double Bed", "King Bed"};
        BedSizeCombo = new JComboBox(BedSize);
        BedSizeCombo.setBounds(200,280,150,30);
        BedSizeCombo.setBackground(Color.WHITE);
        add(BedSizeCombo);

        addRoom = new JButton("Add Room");
        addRoom.setForeground(Color.WHITE);
        addRoom.setBackground(Color.BLACK);
        addRoom.addActionListener(this);
        addRoom.setBounds(60,350,130,30);
        add(addRoom);

        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        cancel.setBounds(220,350,130,30);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400,30, 500,300);
        add(image);

        setBounds(350,200,940,540);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == addRoom){
            String roomNumber = fieldRoomNo.getText();
            String available = (String) statusRoomCombo.getSelectedItem();
            String statusClean = (String) cleaningStatusCombo.getSelectedItem();
            String roomPrice = fieldRoomPrice.getText();
            String bedSize = (String) BedSizeCombo.getSelectedItem();

            if(roomNumber.equals("") || roomNumber.length() > 3 || Integer.parseInt(roomNumber) > 600){
                JOptionPane.showMessageDialog(null,"Room number is empty or an invalid number");
                return;
            }

            if(roomPrice.equals("") || roomPrice.length() > 4 || Integer.parseInt(roomPrice) < 300){
                JOptionPane.showMessageDialog(null,"Room does not have a price or the price is an invalid number");
                return;
            }

            try{
                Conn conn = new Conn();
                String query = "insert into room values('"+roomNumber+"', '"+available+"', '"+statusClean+"', '"+roomPrice+"', '"+bedSize+"')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"New Room Added Successfully");

                dispose();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new AddRooms();
    }
}
