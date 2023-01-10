package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame implements ActionListener {
    JButton newCustomer,Rooms,

            Departament,allEmployees,custInfo,manInfo,

            checkout,update,roomStatus,pickupService,searchRooms,logout;

    public Reception() {
        setVisible(true);
        setResizable(false);

        newCustomer = new JButton("New Customer Form");
        newCustomer.setBounds(10,30,200,30);
        newCustomer.setBackground(Color.BLACK);
        newCustomer.setForeground(Color.WHITE);
        newCustomer.addActionListener(this);
        add(newCustomer);

        Rooms = new JButton("Rooms");
        Rooms.setBounds(10,70,200,30);
        Rooms.setBackground(Color.BLACK);
        Rooms.setForeground(Color.WHITE);
        Rooms.addActionListener(this);
        add(Rooms);

        Departament = new JButton("Departaments");
        Departament.setBounds(10,110,200,30);
        Departament.setForeground(Color.WHITE);
        Departament.setBackground(Color.BLACK);
        add(Departament);

        allEmployees = new JButton("Employees");
        allEmployees.setBounds(10,150,200,30);
        allEmployees.setBackground(Color.BLACK);
        allEmployees.setForeground(Color.WHITE);
        add(allEmployees);

        custInfo = new JButton("Customer Info");
        custInfo.setBounds(10,190,200,30);
        custInfo.setBackground(Color.BLACK);
        custInfo.setForeground(Color.WHITE);
        add(custInfo);

        manInfo = new JButton("Manager Info");
        manInfo.setBounds(10,230,200,30);
        manInfo.setBackground(Color.BLUE);
        manInfo.setForeground(Color.WHITE);
        add(manInfo);

        checkout = new JButton("Checkout");
        checkout.setBounds(10,270,200,30);
        checkout.setBackground(Color.GREEN);
        checkout.setForeground(Color.WHITE);
        add(checkout);

        update = new JButton("Status Update");
        update.setBounds(10,310,200,30);
        update.setBackground(Color.RED);
        update.setForeground(Color.WHITE);
        add(update);

        roomStatus = new JButton("Room Status");
        roomStatus.setBounds(10,350,200,30);
        roomStatus.setBackground(Color.BLACK);
        roomStatus.setForeground(Color.WHITE);
        add(roomStatus);

        pickupService = new JButton("Pickup Status");
        pickupService.setBounds(10,390,200,30);
        pickupService.setBackground(Color.BLACK);
        pickupService.setForeground(Color.WHITE);
        add(pickupService);

        searchRooms = new JButton("Search Rooms");
        searchRooms.setBounds(10,430,200,30);
        searchRooms.setBackground(Color.BLACK);
        searchRooms.setForeground(Color.WHITE);
        add(searchRooms);

        logout = new JButton("Logout");
        logout.setBounds(10,470,200,30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        add(logout);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250,30,500,470);
        add(image);

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(350, 200, 800, 570);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == newCustomer){
            setVisible(false);
            new AddCustomer();
        }else if(ae.getSource() == Rooms){
            setVisible(false);
            new Room();
        }
    }

    public static void main(String[] args) {
        new Reception();
    }
}