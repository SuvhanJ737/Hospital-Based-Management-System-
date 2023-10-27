import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class DoctorApp implements ActionListener {
JFrame frame = new JFrame("Appointments");
//Creating the Appointment form
JTextField txtAppId = new JTextField();
JTextField txtDoc = new JTextField();

JButton btnSearch = new JButton("Search");

JTextField txtSym = new JTextField();

JTextArea txtArea = new JTextArea();
JScrollPane scrollPane = new JScrollPane(txtArea);

JButton btnSendPres = new JButton("Send Pres");

String patientId = null;

DoctorApp(){
frame.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

JPanel panelMenu = new JPanel(new GridBagLayout());
panelMenu.setBackground(Color.green);
panelMenu.setPreferredSize(new Dimension(800, 50));
gbc.gridx = 0;
gbc.gridy = 0;
frame.add(panelMenu, gbc);

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

JLabel lblDoc = new JLabel("Doctor");
gbc.gridx = 0;
gbc.gridy = 1;
panel.add(lblDoc, gbc);

txtDoc.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 1;
panel.add(txtDoc, gbc);

JLabel lblSym = new JLabel("sym");
gbc.gridx = 0;
gbc.gridy = 2;
panel.add(lblSym, gbc);

txtSym.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 2;
panel.add(txtSym, gbc);

txtArea.setPreferredSize(new Dimension(400, 150));
gbc.gridx = 0;
gbc.gridy = 3;
gbc.gridwidth = 3;
panel.add(txtArea, gbc);

btnSendPres.setPreferredSize(new Dimension(150, 30));
btnSendPres.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 4;
gbc.gridwidth = 1;
panel.add(btnSendPres, gbc);

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

PreparedStatement stmt = con.prepareStatement("select patient_id, symptoms, doctor from book_appointment where appointment_id = ?");
stmt.setString(1, txtAppId.getText());
ResultSet rs = stmt.executeQuery();

while(rs.next()) {
txtDoc.setText(rs.getString("doctor"));
txtSym.setText(rs.getString("symptoms"));
patientId = rs.getString("patient_id");
}

stmt.close();
} catch (Exception e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}

if(e.getSource().equals(btnSendPres)) {
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/queston_1","root","root");

PreparedStatement stmt = con.prepareStatement("insert into prescription (appointment_id, prescription, doctor, symptoms, patient_id) values(?, ?, ?, ?, ?)");
stmt.setString(1, txtAppId.getText());
stmt.setString(2, txtArea.getText());
stmt.setString(3, txtDoc.getText());
stmt.setString(4, txtSym.getText());
stmt.setString(5, patientId);
stmt.execute();
stmt.close();

JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
} catch (Exception e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}
}
}