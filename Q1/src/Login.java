import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login implements ActionListener {
JFrame frame = new JFrame("Login Page");
//Creating login page
JTextField txtUsername = new JTextField();
JPasswordField txtPassword = new JPasswordField();

JRadioButton rbtnDoctor = new JRadioButton("Doctor");
JRadioButton rbtnNurse = new JRadioButton("Nurse");
JRadioButton rbtnPatient = new JRadioButton("Patient");

JButton btnLogin = new JButton("Login");

String sql = null;
String user = null;

Login(){
frame.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

JPanel panel = new JPanel(new GridBagLayout());
panel.setBackground(Color.red);
panel.setPreferredSize(new Dimension(800, 500));;

JLabel lblUsername  = new JLabel("Username");
gbc.gridx = 0;
gbc.gridy = 0;
panel.add(lblUsername, gbc);

txtUsername.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 0;
panel.add(txtUsername, gbc);

JLabel lblPassword  = new JLabel("Password");
gbc.gridx = 0;
gbc.gridy = 1;
panel.add(lblPassword, gbc);

txtPassword.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 1;
panel.add(txtPassword, gbc);

ButtonGroup group = new ButtonGroup();
group.add(rbtnDoctor);
group.add(rbtnNurse);
group.add(rbtnPatient);

rbtnDoctor.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 2;
gbc.gridwidth = 2;
panel.add(rbtnDoctor, gbc);

rbtnNurse.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 3;
panel.add(rbtnNurse, gbc);

rbtnPatient.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 4;
panel.add(rbtnPatient, gbc);

btnLogin.setPreferredSize(new Dimension(150, 30));
btnLogin.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 5;
panel.add(btnLogin, gbc);

frame.add(panel);

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(new Dimension(1080, 720));
frame.setVisible(true);
}

public static void main(String[] args) {
new Login();
}

@Override
public void actionPerformed(ActionEvent e) {
if (e.getSource().equals(rbtnDoctor)) {
sql = "select username, password from doctor_login where binary username = ? and binary password = ?";
user = "doctor";

frame.dispose();
new DoctorApp();
} else if (e.getSource().equals(rbtnNurse)) {
sql = "select username, password from nurse_login where binary username = ? and binary password = ?";
user = "nurse";

frame.dispose();
new Register();
} else if (e.getSource().equals(rbtnPatient)) {
sql = "select username, password from patient_login where binary username = ? and binary password = ?";
user = "patient";

frame.dispose();
new PatientAppointment();
}
//Connection to the database
if(e.getSource().equals(btnLogin)) {
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/queston_1","root","root");

PreparedStatement stmt = con.prepareStatement(sql);
stmt.setString(1, txtUsername.getText());
stmt.setString(2, String.valueOf(txtPassword.getPassword()));

ResultSet rs = stmt.executeQuery();

if(rs.next()) {
if(user == "doctor") {
frame.dispose();
new DoctorApp();
} else if(user == "nurse") {
frame.dispose();
new Register();
} else if(user == "patient") {
frame.dispose();
new PatientAppointment();
}
}

stmt.close();

} catch (Exception e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}

}

}
