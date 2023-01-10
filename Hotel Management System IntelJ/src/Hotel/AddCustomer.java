package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;

public class AddCustomer extends JFrame implements ActionListener {
    JTextField number,name,country,deposit;
    JRadioButton rbMale,rbFemale;
    JComboBox cbId;

    Choice croom;

    JLabel timeCheck;

    JButton add,back;
    public AddCustomer(){
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel titlu = new JLabel("New Customer");
        titlu.setBounds(100,20,300,30);
        titlu.setFont(new Font("Raleway", Font.PLAIN,20));
        add(titlu);

        JLabel lbId = new JLabel("Method of Identification");
        lbId.setBounds(35,80,250,20);
        lbId.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbId);

        String options[] = {"Passport", "Driving License", "ID card","Birth certificare(children)"};
        cbId = new JComboBox(options);
        cbId.setBounds(300,80,150,25);
        cbId.setBackground(Color.WHITE);
        add(cbId);

        JLabel lbNumber = new JLabel("Number");
        lbNumber.setBounds(35,120,250,20);
        lbNumber.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbNumber);

        number = new JTextField();
        number.setBounds(300,120,150,25);
        add(number);

        JLabel lbName = new JLabel("Name");
        lbName.setBounds(35,160,250,20);
        lbName.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbName);

        name = new JTextField();
        name.setBounds(300,160,150,25);
        add(name);

        JLabel lbGender = new JLabel("Gender");
        lbGender.setBounds(35,200,250,20);
        lbGender.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbGender);

        rbMale = new JRadioButton("Male");
        rbMale.setBackground(Color.WHITE);
        rbMale.setBounds(300,200,60,25);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBackground(Color.WHITE);
        rbFemale.setBounds(360,200,80,25);
        add(rbFemale);

        JLabel lbCountry = new JLabel("Country");
        lbCountry.setBounds(35,240,250,20);
        lbCountry.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbCountry);

        country = new JTextField();
        country.setBounds(300,240,150,25);
        add(country);

        JLabel lbcRoom = new JLabel("Allocated Room");
        lbcRoom.setBounds(35,280,250,20);
        lbcRoom.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbcRoom);

        croom = new Choice();
        try{
            Conn conn = new Conn();
            String query = "select * from room where availability = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("room_number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        croom.setBounds(300,280,150,25);
        add(croom);

        JLabel time = new JLabel("Checkin time");
        time.setBounds(35,320,150,20);
        time.setFont(new Font("Raleway", Font.PLAIN,20));
        add(time);

        Date date = new Date();

        timeCheck = new JLabel("" + date);
        timeCheck.setBounds(300,320,150,25);
        timeCheck.setFont(new Font("Raleway", Font.PLAIN,18));
        add(timeCheck);

        JLabel lbDeposit = new JLabel("Deposit");
        lbDeposit.setBounds(35,360,250,20);
        lbDeposit.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbDeposit);

        deposit = new JTextField();
        deposit.setBounds(300,360,150,25);
        add(deposit);

        add = new JButton("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(50,410,120,30);
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(200,410,120,30);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450,50,300,400);
        add(image);



        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setBounds(350,200,800,550);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            String id = (String) cbId.getSelectedItem();
            String numar = number.getText();
            String nume = name.getText();
            String gender = null;

            if(rbMale.isSelected()){
                gender = "Male";
            }else{
                gender = "Female";
            }

            String tara = country.getText();
            String room = croom.getSelectedItem();
            String time = timeCheck.getText();
            String depozit = deposit.getText();
            try{
                String query = "insert into customer values('"+id+"', '"+numar+"', '"+nume+"','"+gender+"', '"+tara+"', '"+room+"', '"+time+"', '"+depozit+"')";
                String query2 = "update room set availability = 'Occupied' where room_number = '"+room+"'";

                Conn conn = new Conn();

                conn.s.executeUpdate(query);
                conn.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,"New Customer Added Succesfully");
                setVisible(false);
                new Reception();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }
    public static void main(String[] args){
        new AddCustomer();
    }
}
