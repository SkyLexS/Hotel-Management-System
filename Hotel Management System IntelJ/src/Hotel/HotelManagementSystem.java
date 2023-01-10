package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystem extends JFrame implements ActionListener {
    public HotelManagementSystem(){
        try {
            setBounds(100, 100, 1920, 1080);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setLayout(null);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
            JLabel image = new JLabel(i1);
            image.setBounds(0, 0, 1280, 720);
            add(image);

            JLabel text = new JLabel("CALLISTO SYSTEMS");
            text.setBounds(420, 100, 1000, 90);
            text.setForeground(Color.CYAN);
            text.setFont(new Font("serif", Font.ITALIC, 50));
            image.add(text);

            JButton next = new JButton("Next ");
            next.setBounds(580, 530, 150, 50);
            next.setBackground(Color.CYAN);
            next.setForeground(Color.BLACK);
            next.addActionListener(this);
            next.setFont(new Font("serif", Font.PLAIN, 18));
            image.add(next);

            setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Login();
    }

}
