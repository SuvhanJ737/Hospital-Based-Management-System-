import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class PatientAppointment implements ActionListener {
JFrame frame = new JFrame("View Appointments");
//Creating patient appointment form
JTextField txtPatientId = new JTextField();

JButton btnSearch = new JButton("Search");

JTextArea txtArea = new JTextArea();
JScrollPane scrollPane = new JScrollPane(txtArea);

PatientAppointment(){
frame.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

JPanel panel = new JPanel(new GridBagLayout());
panel.setBackground(Color.red);
panel.setPreferredSize(new Dimension(800, 500));

JLabel lblPatId = new JLabel("Pat Id");
gbc.gridx = 0;
gbc.gridy = 0;
panel.add(lblPatId, gbc);

txtPatientId.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 0;
panel.add(txtPatientId, gbc);

btnSearch.setPreferredSize(new Dimension(150, 30));
btnSearch.addActionListener(this);
gbc.gridx = 2;
gbc.gridy = 0;
panel.add(btnSearch, gbc);

gbc.gridx = 0;
gbc.gridy = 1;
gbc.gridwidth = 3;
txtArea.setPreferredSize(new Dimension(600, 400));
panel.add(txtArea, gbc);

frame.add(panel);

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(new Dimension(1080, 720));
frame.setVisible(true);
}
//Connection to the database
@Override
public void actionPerformed(ActionEvent e) {
if(e.getSource().equals(btnSearch)) {
txtArea.setText("doctor\tdate\n");
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/queston_1","root","root");

PreparedStatement stmt = con.prepareStatement("select * from book_appointment where patient_id = ?");
stmt.setString(1, txtPatientId.getText());
ResultSet rs = stmt.executeQuery();

while(rs.next()) {
txtArea.append(rs.getString("doctor") + "\t" + rs.getString("date") + "\n");
}

} catch (Exception e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}
}
}