import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Register implements ActionListener {
JFrame frame = new JFrame("Register");
//Creating the register form
JTextField txtId = new JTextField();
JTextField txtFullName = new JTextField();
JTextField txtContact = new JTextField();
JTextField txtAge = new JTextField();

JButton btnRegister = new JButton("Register");

JButton btnBook = new JButton("Book Appointment");

JButton btnPrescription = new JButton("Prescription");

JButton btnPatientRecords = new JButton("Patient records");

Register(){
frame.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

JPanel panelMenu = new JPanel(new GridBagLayout());
panelMenu.setBackground(Color.green);
panelMenu.setPreferredSize(new Dimension(800, 50));
gbc.gridx = 0;
gbc.gridy = 0;
frame.add(panelMenu, gbc);

btnBook.setPreferredSize(new Dimension(150, 30));
btnBook.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 0;
panelMenu.add(btnBook, gbc);

btnPrescription.setPreferredSize(new Dimension(150, 30));
btnPrescription.addActionListener(this);
gbc.gridx = 1;
gbc.gridy = 0;
panelMenu.add(btnPrescription, gbc);

btnPatientRecords.setPreferredSize(new Dimension(150, 30));
btnPatientRecords.addActionListener(this);
gbc.gridx = 2;
gbc.gridy = 0;
panelMenu.add(btnPatientRecords, gbc);

JPanel panel = new JPanel(new GridBagLayout());
panel.setBackground(Color.red);
panel.setPreferredSize(new Dimension(800, 500));

JLabel lblId = new JLabel("Patient Id");
gbc.gridx = 0;
gbc.gridy = 0;
panel.add(lblId, gbc);

txtId.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 0;
panel.add(txtId, gbc);

JLabel lblFullName = new JLabel("Full Name");
gbc.gridx = 0;
gbc.gridy = 1;
panel.add(lblFullName, gbc);

txtFullName.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 1;
panel.add(txtFullName, gbc);

JLabel lblContact = new JLabel("Contact Number");
gbc.gridx = 0;
gbc.gridy = 2;
panel.add(lblContact, gbc);

txtContact.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 2;
panel.add(txtContact, gbc);

JLabel lblAge = new JLabel("Age");
gbc.gridx = 0;
gbc.gridy = 3;
panel.add(lblAge, gbc);

txtAge.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 3;
panel.add(txtAge, gbc);

btnRegister.setPreferredSize(new Dimension(150, 30));
btnRegister.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 4;
panel.add(btnRegister, gbc);

gbc.gridx = 0;
gbc.gridy = 1;
frame.add(panel, gbc);

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(new Dimension(1080, 720));
frame.setVisible(true);
}
//Connection to the database
@Override
public void actionPerformed(ActionEvent e) {
if(e.getSource().equals(btnRegister)) {
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/queston_1","root","root");

PreparedStatement stmt = con.prepareStatement("insert into patient values (?, ?, ?, ?)");
stmt.setString(1, txtId.getText());
stmt.setString(2, txtFullName.getText());
stmt.setString(3, txtContact.getText());
stmt.setString(4, txtAge.getText());

stmt.execute();
stmt.close();

txtId.setText(null);
txtFullName.setText(null);
txtContact.setText(null);
txtAge.setText(null);

JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
} catch (Exception e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}

if(e.getSource().equals(btnBook)) {
frame.dispose();
new Book();
} else if(e.getSource().equals(btnPrescription)) {
frame.dispose();
new Prescription();
} else if(e.getSource().equals(btnPatientRecords)) {
frame.dispose();
new PatientRecords();
}
}
}
