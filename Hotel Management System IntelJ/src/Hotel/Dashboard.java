package Hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    JMenuItem addemployee,addrooms,addDriver,reception;
    public Dashboard(){
        setBounds(0,0,1550,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550,1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,1000);
        add(image);

        JLabel text = new JLabel("The Callisto Organization Welcomes You");
        text.setBounds(400,80,1000,50);
        text.setFont(new Font("serif",Font.ITALIC,40 ));
        image.add(text);

        JMenuBar mb = new JMenuBar();
        mb.setBounds(0,0,1550,30);
        image.add(mb);

        JMenu hotel = new JMenu("Hotel Management");
        mb.add(hotel);

        reception = new JMenuItem("Reception");
        reception.addActionListener(this);
        hotel.add(reception);

        JMenu admin = new JMenu("Admin Menu");
        admin.setForeground(Color.RED);
        mb.add(admin);

        addemployee = new JMenuItem("Add Employee");
        addemployee.addActionListener(this);
        admin.add(addemployee);

        addrooms = new JMenuItem("Add Rooms");
        addrooms.addActionListener(this);
        admin.add(addrooms);

        addDriver = new JMenuItem("Add Driver");
        addDriver.addActionListener(this);
        admin.add(addDriver);



        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == addemployee){
            new AddEmployee();

        }else if(ae.getSource() == addrooms){
            new AddRooms();
        }else if(ae.getSource() == addDriver){
            new AddDriver();
        }else if(ae.getSource() == reception){
            new Reception();
        }
    }
    public static void main(String[] args){
        new Dashboard();
    }
}
