package Hotel;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Departament extends JFrame implements ActionListener {
    JTable table;

    JButton searchButton,back;

    JTextField searchField;

    public Departament(){
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel Search = new JLabel("Search:");
        Search.setBounds(10,10,100,20);
        add(Search);

        searchField = new JTextField("Departament's Name");
        searchField.setBounds(70, 10, 150, 20);
        add(searchField);

        searchButton = new JButton("Search Departament");
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

        JLabel Dept = new JLabel("Departament");
        Dept.setBounds(0,40,100,20);
        add(Dept);

        JLabel Budget = new JLabel("Budget");
        Budget.setBounds(370,40,100,20);
        add(Budget);

        table = new JTable();
        table.setBounds(0,60,700,350);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs  = c.s.executeQuery("select * from departament order by budget asc");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }

        setLayout(null);
        setBounds(400,200,700,480);
        setResizable(false);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == searchButton){
            String deptName = searchField.getText();
            try{
                Conn c = new Conn();

                if(!deptName.isEmpty()) {
                    ResultSet rs = c.s.executeQuery("select * from departament where departament like " + "'" + deptName + "'");
                    //JOptionPane.showMessageDialog(null,"You arrived here");

                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else if(deptName.isEmpty()){
                    ResultSet rs = c.s.executeQuery("select * from departament");
                    //JOptionPane.showMessageDialog(null,"You arrived here 2");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }else{
                    JOptionPane.showMessageDialog(null,"The Departament doesn't exists or is an invalid name");
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
        new Departament();
    }
}
