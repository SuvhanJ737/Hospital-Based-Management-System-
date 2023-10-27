import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class PatientRecords implements ActionListener {
JFrame frame = new JFrame("Patient Records");
//Creating the patient record form
JButton btnReggister = new JButton("Register");
JButton btnBook = new JButton("Book");
JButton btnPres = new JButton("pres");

JTextField txtPatId = new JTextField();
JButton btnSearch = new JButton("Search");

JTextArea txtArea = new JTextArea();
JScrollPane scrollPane = new JScrollPane(txtArea);

PatientRecords(){
frame.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

JPanel panelMenu = new JPanel(new GridBagLayout());
panelMenu.setBackground(Color.green);
panelMenu.setPreferredSize(new Dimension(800, 50));

btnReggister.setPreferredSize(new Dimension(150, 30));
btnReggister.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 0;
panelMenu.add(btnReggister, gbc);

btnBook.setPreferredSize(new Dimension(150, 30));
btnBook.addActionListener(this);
gbc.gridx = 1;
gbc.gridy = 0;
panelMenu.add(btnBook, gbc);

btnPres.setPreferredSize(new Dimension(150, 30));
btnPres.addActionListener(this);
gbc.gridx = 2;
gbc.gridy = 0;
panelMenu.add(btnPres, gbc);


gbc.gridx = 0;
gbc.gridy = 0;
frame.add(panelMenu, gbc);

JPanel panel = new JPanel(new GridBagLayout());
panel.setBackground(Color.red);
panel.setPreferredSize(new Dimension(800, 500));
gbc.gridx = 0;
gbc.gridy = 1;

JLabel lblPatId = new JLabel("Pat id");
gbc.gridx = 0;
gbc.gridy = 0;
panel.add(lblPatId, gbc);

txtPatId.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 0;
panel.add(txtPatId, gbc);

btnSearch.setPreferredSize(new Dimension(150, 30));
btnSearch.addActionListener(this);
gbc.gridx = 2;
gbc.gridy = 0;
panel.add(btnSearch, gbc);

txtArea.setPreferredSize(new Dimension(400, 150));
gbc.gridx = 0;
gbc.gridy = 1;
gbc.gridwidth = 3;
panel.add(txtArea, gbc);

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
txtArea.setText("Symptoms\tDoctor\tPrescription");
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/queston_1","root","root");
	
PreparedStatement stmt = con.prepareStatement("select * from prescription where patient_id = ?");
stmt.setString(1, txtPatId.getText());
ResultSet rs = stmt.executeQuery();

while(rs.next()) {
txtArea.append("\n" + rs.getString("symptoms") + "\t" + rs.getString("doctor") + "\t" + rs.getString("prescription"));
}

} catch (Exception e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}

if(e.getSource().equals(btnBook)) {
frame.dispose();
new Book();
} else if(e.getSource().equals(btnPres)) {
frame.dispose();
new Prescription();
} else if(e.getSource().equals(btnReggister)) {
frame.dispose();
new Register();
}
}
}
