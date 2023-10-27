import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Book implements ActionListener {
JFrame frame = new JFrame("Book Appointment");
//Creating the booking appointment frame
JTextField txtAppId = new JTextField();
JTextField txtPatId = new JTextField();
JTextField txtSym = new JTextField();
JTextField txtDoc = new JTextField();
JTextField txtDate = new JTextField();

JButton btnBook = new JButton("Book");

JButton btnReg = new JButton("Register");
JButton btnPres = new JButton("Pres");

Book(){
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

btnPres.setPreferredSize(new Dimension(150, 30));
btnPres.addActionListener(this);
gbc.gridx = 1;
gbc.gridy = 0;
panelMenu.add(btnPres, gbc);

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

JLabel lblPatId = new JLabel("Pat id");
gbc.gridx = 0;
gbc.gridy = 1;
panel.add(lblPatId, gbc);

txtPatId.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 1;
panel.add(txtPatId, gbc);

JLabel lblDoc = new JLabel("Doc");
gbc.gridx = 0;
gbc.gridy = 2;
panel.add(lblDoc, gbc);

txtDoc.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 2;
panel.add(txtDoc, gbc);

JLabel lblDate = new JLabel("Date");
gbc.gridx = 0;
gbc.gridy = 3;
panel.add(lblDate, gbc);

txtDate.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 3;
panel.add(txtDate, gbc);

JLabel lblSym = new JLabel("sym");
gbc.gridx = 0;
gbc.gridy = 4;
panel.add(lblSym, gbc);

txtSym.setPreferredSize(new Dimension(150, 30));
gbc.gridx = 1;
gbc.gridy = 4;
panel.add(txtSym, gbc);

btnBook.setPreferredSize(new Dimension(150, 30));
btnBook.addActionListener(this);
gbc.gridx = 0;
gbc.gridy = 5;
panel.add(btnBook, gbc);

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
if(e.getSource().equals(btnBook)){
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/queston_1","root","root");

PreparedStatement stmt = con.prepareStatement("insert into book_appointment values(?, ?, ?, ?, ?)");
stmt.setString(1, txtAppId.getText());
stmt.setString(2, txtPatId.getText());
stmt.setString(3, txtDoc.getText());
stmt.setString(4, txtDate.getText());
stmt.setString(5, txtSym.getText());

stmt.execute();
stmt.close();

JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
} catch (Exception e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
}

if(e.getSource().equals(btnReg)) {
frame.dispose();
new Register();
} else if(e.getSource().equals(btnPres)) {
frame.dispose();
new Prescription();
}
}
}
