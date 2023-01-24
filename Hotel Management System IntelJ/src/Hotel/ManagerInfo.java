package Hotel;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManagerInfo extends JFrame implements ActionListener {
    JTable table;

    JButton searchButton,back;

    JTextField searchField;

    public ManagerInfo(){
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel Search = new JLabel("Search:");
        Search.setBounds(10,10,100,20);
        add(Search);

        searchField = new JTextField("Manager name");
        searchField.setBounds(70, 10, 150, 20);
        add(searchField);

        searchButton = new JButton("Search Manager");
        searchButton.setBounds(240,10,120,20);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.BLACK);
        searchButton.addActionListener(this);
        add(searchButton);

        back = new JButton("Exit");
        back.setBounds(380,10,80,20);
        back.setForeground(Color.white);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        JLabel ManName = new JLabel("Name");
        ManName.setBounds(10,40,100,20);
        add(ManName);

        JLabel ManAge = new JLabel("Age");
        ManAge.setBounds(160,40,100,20);
        add(ManAge);

        JLabel ManGender = new JLabel("Gender");
        ManGender.setBounds(300,40,100,20);
        add(ManGender);

        JLabel ManJob = new JLabel("Job");
        ManJob.setBounds(440,40,100,20);
        add(ManJob);

        JLabel ManSalary = new JLabel("Salary");
        ManSalary.setBounds(580,40,100,20);
        add(ManSalary);

        JLabel ManPhone = new JLabel("Phone Number");
        ManPhone.setBounds(720,40,100,20);
        add(ManPhone);

        JLabel ManMail = new JLabel("Mail");
        ManMail.setBounds(860,40,100,20);
        add(ManMail);

        table = new JTable();
        table.setBounds(0,60,990,400);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs  = c.s.executeQuery("select * from employee where job='Manager' order by name asc");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }

        setLayout(null);
        setBounds(300,200,1000,600);
        setResizable(false);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == searchButton){
            String empName = searchField.getText();
            try{
                Conn c = new Conn();

                if(!empName.isEmpty()) {
                    ResultSet rs = c.s.executeQuery("select * from employee where name like "+ "'" + empName + "' AND job='Manager'");
                    //JOptionPane.showMessageDialog(null,"You arrived here");

                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else if(empName.isEmpty()){
                    ResultSet rs = c.s.executeQuery("select * from employee where job='Manager'");
                    //JOptionPane.showMessageDialog(null,"You arrived here 2");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else{
                    JOptionPane.showMessageDialog(null,"Employee doesn't exists or you typed a wrong name");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==back){
            dispose();
            new Reception();
        }
    }
}
