package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField username;
    JPasswordField password;
    JButton login, cancel;
    public Login(){
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel user = new JLabel("Username:");
        user.setBounds(40,20,100,30);
        user.setForeground(Color.WHITE);
        user.setFont(new Font("serif", Font.BOLD,20));
        add(user);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        JLabel pass = new JLabel("Password:");
        pass.setBounds(40,90,100,30);
        pass.setForeground(Color.WHITE);
        pass.setFont(new Font("serif", Font.BOLD,20));
        add(pass);

        password = new JPasswordField();
        password.setBounds(150, 90, 150, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(150,170,120,30);
        login.setBackground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(350,170,120,30);
        cancel.setBackground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(310, -14, 200,200);
        add(image);

        setBounds(500, 200, 600, 300);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == login)
            {
                String user = username.getText();
                String pass = String.valueOf(password.getPassword());
                try{
                    Conn c = new Conn();

                    String query = "select * from login where username = '" + user + "' and password = '" + pass + "'";

                    ResultSet rs =c.s.executeQuery(query);

                    if(rs.next()){
                        setVisible(false);
                        new Dashboard();
                    } else {
                       JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            } else if(ae.getSource() == cancel) {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        //new HotelManagementSystem();
        new Login();
    }
}
