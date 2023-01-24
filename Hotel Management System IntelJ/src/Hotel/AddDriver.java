package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDriver extends JFrame implements ActionListener {
    JTextField dname,dage,cBrand;
    JButton addDriver,cancel;
    JComboBox dgendercombo,availabilityCombo;
    public AddDriver(){
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Add Driver");
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        heading.setBounds(150,20,200,20);
        add(heading);

        JLabel dnamelb = new JLabel("Driver's Name:");
        dnamelb.setFont(new Font("Tahoma",Font.PLAIN,16));
        dnamelb.setBounds(60,80,120,30);
        add(dnamelb);

        dname = new JTextField();
        dname.setBounds(200, 80, 150, 30);
        add(dname);

        JLabel dagelb = new JLabel("Driver's Age:");
        dagelb.setFont(new Font("Tahoma",Font.PLAIN,16));
        dagelb.setBounds(60,130,120,30);
        add(dagelb);

        dage = new JTextField();
        dage.setBounds(200, 130, 150, 30);
        add(dage);

        JLabel dgenderlb = new JLabel("Gender:");
        dgenderlb.setFont(new Font("Tahoma",Font.PLAIN,16));
        dgenderlb.setBounds(60,180,120,30);
        add(dgenderlb);

        String dgender[] = {"Male", "Female"};
        dgendercombo = new JComboBox(dgender);
        dgendercombo.setBounds(200,180,150,30);
        dgendercombo.setBackground(Color.WHITE);
        add(dgendercombo);

        JLabel carBrand = new JLabel("Car's Brand:");
        carBrand.setFont(new Font("Tahoma",Font.PLAIN,16));
        carBrand.setBounds(60,230,120,30);
        add(carBrand);

        cBrand = new JTextField();
        cBrand.setBounds(200, 230, 150, 30);
        add(cBrand);

        JLabel availability = new JLabel("Status Car:");
        availability.setFont(new Font("Tahoma",Font.PLAIN,16));
        availability.setBounds(60,280,120,30);
        add(availability);

        String available[] = {"Available", "On Route", "Coming back","Not Available"};
        availabilityCombo = new JComboBox(available);
        availabilityCombo.setBounds(200,280,150,30);
        availabilityCombo.setBackground(Color.WHITE);
        add(availabilityCombo);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,30, 500,300);
        add(image);

        addDriver = new JButton("Add Driver");
        addDriver.setForeground(Color.WHITE);
        addDriver.setBackground(Color.BLACK);
        addDriver.addActionListener(this);
        addDriver.setBounds(60,350,130,30);
        add(addDriver);

        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        cancel.setBounds(220,350,130,30);
        add(cancel);

        setBounds(350,200,940,540);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == addDriver){
            String driverName = dname.getText();
            String available = (String) availabilityCombo.getSelectedItem();
            String driverGender = (String) dgendercombo.getSelectedItem();
            String driverAge = dage.getText();
            String carBrand =cBrand.getText();
            String regex4Name = "[a-zA-Z]+";
            Pattern pattern4Name = Pattern.compile(regex4Name);
            Pattern pattern4Car = Pattern.compile(regex4Name);
            Matcher matcher4Car = pattern4Car.matcher(carBrand);
            Matcher matcher4Name = pattern4Name.matcher(driverName);

            if(driverName.equals("") || !matcher4Name.matches()){
                JOptionPane.showMessageDialog(null,"Driver's name is null or an invalid name!");
                return;
            }

            if(driverAge.equals("") || Integer.parseInt(driverAge) > 60 || Integer.parseInt(driverAge) < 18){
                JOptionPane.showMessageDialog(null,"The Driver's age is null or the driver is too old!");
                return;
            }
            if(carBrand.equals("") || !matcher4Car.matches()){
                JOptionPane.showMessageDialog(null, "The car's name should not be null and should be a valid car name!");
                return;
            }


            try{
                Conn conn = new Conn();
                String query = "insert into driver values('"+driverName+"', '"+driverAge+"', '"+available+"', '"+driverGender+"', '"+carBrand+"')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"New Driver Added Successfully");

                setVisible(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new AddDriver();
    }
}
