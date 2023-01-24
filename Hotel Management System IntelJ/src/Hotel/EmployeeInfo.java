package Hotel;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EmployeeInfo extends JFrame implements ActionListener {
    JTable table;

    JButton searchButton,back;

    JTextField searchField;

    public EmployeeInfo(){
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel Search = new JLabel("Search:");
        Search.setBounds(10,10,100,20);
        add(Search);

        searchField = new JTextField("Employee name");
        searchField.setBounds(70, 10, 150, 20);
        add(searchField);

        searchButton = new JButton("Search Employee");
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

        JLabel EmpName = new JLabel("Name");
        EmpName.setBounds(10,40,100,20);
        add(EmpName);

        JLabel EmpAge = new JLabel("Age");
        EmpAge.setBounds(160,40,100,20);
        add(EmpAge);

        JLabel EmpGender = new JLabel("Gender");
        EmpGender.setBounds(300,40,100,20);
        add(EmpGender);

        JLabel EmpJob = new JLabel("Job");
        EmpJob.setBounds(440,40,100,20);
        add(EmpJob);

        JLabel EmpSalary = new JLabel("Salary");
        EmpSalary.setBounds(580,40,100,20);
        add(EmpSalary);

        JLabel EmpPhone = new JLabel("Phone Number");
        EmpPhone.setBounds(720,40,100,20);
        add(EmpPhone);

        JLabel EmpMail = new JLabel("Mail");
        EmpMail.setBounds(860,40,100,20);
        add(EmpMail);

        table = new JTable();
        table.setBounds(0,60,990,400);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs  = c.s.executeQuery("select * from employee order by name asc");
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
                    ResultSet rs = c.s.executeQuery("select * from employee where name like "+ "'" + empName + "'");
                    //JOptionPane.showMessageDialog(null,"You arrived here");

                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else if(empName.isEmpty()){
                    ResultSet rs = c.s.executeQuery("select * from employee");
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
    public static void main(String[] args){
        new EmployeeInfo();
    }
}
