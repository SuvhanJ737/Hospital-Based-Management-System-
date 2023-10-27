import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Prescription implements ActionListener {
JFrame frame = new JFrame("Prescriptions");
//Creating the prescription form
JTextField txtAppId = new JTextField();

JTextArea txtAreaPres = new JTextArea();
JScrollPane scrollPres = new JScrollPane(txtAreaPres);

JButton btnSearch = new JButton("seach");

JButton btnBook = new JButton("Book");
JButton btnReg = new JButton("Register");
JButton btnPatientRecords = new JButton("Patient records");

Prescription(){
frame.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

JPanel panelMenu = new JPanel(new GridBagLayout());
panelMenu.setBackground(Color.green);
panelMenu.setPreferredSize(new Dimension(800, 50));
gbc.gridx = 0;
gbc.gridy = 0;
frame.add(panelMenu, gbc);

btnReg.setPreferredSize(new Dimension(150, 30));
btnReg.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 0;
panelMenu.add(btnReg, gbc);

btnBook.setPreferredSize(new Dimension(150, 30));
btnBook.addActionListener(this);
gbc.gridx = 1;
gbc.gridy = 0;
panelMenu.add(btnBook, gbc);

btnPatientRecords.setPreferredSize(new Dimension(150, 30));
btnPatientRecords.addActionListener(this);
gbc.gridx = 2;
gbc.gridy = 0;
panelMenu.add(btnPatientRecords, gbc);

JPanel panel = new JPanel(new GridBagLayout());
panel.setBackground(Color.red);
panel.setPreferredSize(new Dimension(800, 500));

JLabel lblAppId = new JLabel("App id");
gbc.gridx = 0;
gbc.gridy = 0;
panel.add(lblAppId, gbc);

txtAppId.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 0;
panel.add(txtAppId, gbc);

btnSearch.setPreferredSize(new Dimension(150, 30));
btnSearch.addActionListener(this);
gbc.gridx = 2;
gbc.gridy = 0;
panel.add(btnSearch, gbc);

JLabel lblPres = new JLabel("pres");
gbc.gridx = 0;
gbc.gridy = 1;
panel.add(lblPres, gbc);

txtAreaPres.setPreferredSize(new Dimension(300, 150));
gbc.gridx = 1;
gbc.gridy = 1;
panel.add(txtAreaPres, gbc);

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
if(e.getSource().equals(btnSearch)) {
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/queston_1","root","root");

PreparedStatement stmt = con.prepareStatement("select prescription from prescription where appointment_id = ?");
stmt.setString(1, txtAppId.getText());
ResultSet rs = stmt.executeQuery();

while(rs.next()) {
txtAreaPres.setText(rs.getString("prescription"));
}

stmt.close();
} catch (Exception e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}

if(e.getSource().equals(btnBook)) {
frame.dispose();
new Book();
} else if(e.getSource().equals(btnReg)) {
frame.dispose();
new Register();
} else if(e.getSource().equals(btnPatientRecords)) {
frame.dispose();
new PatientRecords();
}
}
}
