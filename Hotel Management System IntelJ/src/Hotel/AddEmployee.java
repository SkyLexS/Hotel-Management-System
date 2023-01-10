package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEmployee extends JFrame implements ActionListener {
    JTextField fieldName,fieldAge,fieldSalary,fieldPhone,fieldEmail;
    JRadioButton rbMale,rbFemale;
    JButton submit;
    JComboBox cbJob;
    public AddEmployee(){
        setBounds(350,200,850,540);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        JLabel lbname = new JLabel("Name:");
        lbname.setBounds(60,30,120,30);
        lbname.setForeground(Color.BLACK);
        lbname.setFont(new Font("Tahoma", Font.PLAIN,17));
        add(lbname);

        fieldName = new JTextField();
        fieldName.setBounds(200, 30, 150, 30);
        add(fieldName);

        JLabel lbage = new JLabel("Age:");
        lbage.setBounds(60,80,100,30);
        lbage.setForeground(Color.BLACK);
        lbage.setFont(new Font("Tahoma", Font.PLAIN,17));
        add(lbage);

        fieldAge = new JTextField();
        fieldAge.setBounds(200, 80, 150, 30);
        add(fieldAge);

        JLabel lbGender = new JLabel("Gender:");
        lbGender.setBounds(60,130,100,30);
        lbGender.setForeground(Color.BLACK);
        lbGender.setFont(new Font("Tahoma", Font.PLAIN,17));
        add(lbGender);

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(200,130,70,30);
        rbMale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbMale.setBackground(Color.white);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(280,130,70,30);
        rbFemale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbFemale.setBackground(Color.WHITE);
        add(rbFemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        JLabel lbjob = new JLabel("Job:");
        lbjob.setBounds(60,180,100,30);
        lbjob.setFont(new Font("Tahoma", Font.PLAIN,17));
        add(lbjob);

        String jobs[] = { "Front Desk Clerks", "Porters", "Housekeeping", "Kitchen Staff", "Room Service", "Chefs", "Waiter/Waitress", "Manager", "Acountant"};
        cbJob = new JComboBox(jobs);
        cbJob.setBounds(200,180,150,30);
        cbJob.setBackground(Color.WHITE);
        add(cbJob);

        JLabel lbSalary = new JLabel("Salary:");
        lbSalary.setBounds(60,230,100,30);
        lbSalary.setForeground(Color.BLACK);
        lbSalary.setFont(new Font("Tahoma", Font.PLAIN,17));
        add(lbSalary);

        fieldSalary = new JTextField();
        fieldSalary.setBounds(200, 230, 150, 30);
        add(fieldSalary);

        JLabel lbPhone = new JLabel("Phone:");
        lbPhone.setBounds(60,280,100,30);
        lbPhone.setForeground(Color.BLACK);
        lbPhone.setFont(new Font("Tahoma", Font.PLAIN,17));
        add(lbPhone);

        fieldPhone = new JTextField();
        fieldPhone.setBounds(200, 280, 150, 30);
        add(fieldPhone);

        JLabel lbEmail = new JLabel("Email:");
        lbEmail.setBounds(60,330,100,30);
        lbEmail.setForeground(Color.BLACK);
        lbEmail.setFont(new Font("Tahoma", Font.PLAIN,17));
        add(lbEmail);

        fieldEmail = new JTextField();
        fieldEmail.setBounds(200, 330, 150, 30);
        add(fieldEmail);

        submit = new JButton("Submit");
        submit.setBounds(200,430,150,30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450,450,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 60, 450, 380);
        add(image);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        String name = fieldName.getText();
        String age = fieldAge.getText();
        String salary = fieldSalary.getText();
        String phone = fieldPhone.getText();
        String email = fieldEmail.getText();
        String gender = null;
        String regex4Email = "^(.+)@(.+)$";
        String regex4Phone = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
        Pattern pattern4Email = Pattern.compile(regex4Email);
        Pattern pattern4Phone = Pattern.compile(regex4Phone);
        Matcher matcher4Email = pattern4Email.matcher(email);
        Matcher matcher4Phone = pattern4Phone.matcher(phone);

        if(name.equals("") || name.length()>20){
            JOptionPane.showMessageDialog(null,"Name is empty or your name is more than 20 characters");
            return;
        }
        if(age.equals("") || Integer.parseInt(age) > 100){
            JOptionPane.showMessageDialog(null,"Age is empty or is an invalid number");
            return;
        }

        if(salary.equals("") || salary.length() > 5){
            JOptionPane.showMessageDialog(null,"Salary is empty or it is an invalid number");
            return;
        }

        if(phone.equals("") || !matcher4Phone.matches()){
            JOptionPane.showMessageDialog(null,"Phone is empty or is an invalid phone number");
            return;
        }

        if(email.equals("") || !matcher4Email.matches()){
            JOptionPane.showMessageDialog(null,"Email is empty or is invalid adress");
            return;
        }

        if(rbMale.isSelected()) {
            gender = "Male";

        } else if(rbFemale.isSelected()){
            gender = "Female";
        }


        String job = (String) cbJob.getSelectedItem();
        try{
            Conn c = new Conn();
            String query = "insert into employee values('"+name+"', '"+age+"', '"+gender+"', '"+job+"', '"+salary+"', '"+phone+"', '"+email+"')";

            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null,"Employee added successfully!");

            setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new AddEmployee();
    }
}
