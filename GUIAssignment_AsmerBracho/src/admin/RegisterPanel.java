package admin;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import resources.DBConnect;

public class RegisterPanel extends JPanel {

  JTextField firstName = null;
  JTextField lastName = null;
  JTextField username = null;
  JTextField password = null;
  JTextField confPassword = null;
  JTextField emmail = null;
  JComboBox type = null;

  JButton register = null;
  JButton clear = null;
  Admin p = null;

  public RegisterPanel(Admin ref) {
    p = ref;
    Dimension d = getPreferredSize();
    d.height = 270;
    setPreferredSize(d);

    this.setLayout(new GridBagLayout());

    // create constraints to work with
    GridBagConstraints grid = new GridBagConstraints();

    // create the elements

    firstName = new JTextField(15);
    lastName = new JTextField(15);
    username = new JTextField(15);
    password = new JPasswordField(15);
    confPassword = new JPasswordField(15);
    emmail = new JTextField(15);
    type = new JComboBox();

    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    combo.addElement("Admin");
    combo.addElement("TechSupport");
    combo.addElement("Manager");
    type.setModel(combo);



    register = new JButton("  Register");
    Image image4 = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
    register.setIcon(new ImageIcon(image4));

    clear = new JButton("    Clear");
    Image cl = new ImageIcon(this.getClass().getResource("/cl.png")).getImage();
    clear.setIcon(new ImageIcon(cl));
    // add elements

    // -----------------First Name-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 0;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(new JLabel("First Name: "), grid);

    grid.gridx = 1;
    grid.gridy = 0;
    grid.anchor = GridBagConstraints.LINE_END;
    add(firstName, grid);

    // -----------------Last Name-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 1;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(new JLabel("Last Name: "), grid);

    grid.gridx = 1;
    grid.gridy = 1;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(lastName, grid);

    // -----------------Username-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 2;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(new JLabel("Username: "), grid);

    grid.gridx = 1;
    grid.gridy = 2;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(username, grid);

    // -----------------Password-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 3;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(new JLabel("Password: "), grid);

    grid.gridx = 1;
    grid.gridy = 3;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(password, grid);

    // -----------------Confirm Password-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 4;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(new JLabel("Confirm Password: "), grid);

    grid.gridx = 1;
    grid.gridy = 4;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(confPassword, grid);

    // -----------------Email-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 5;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(new JLabel("Email: "), grid);

    grid.gridx = 1;
    grid.gridy = 5;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(emmail, grid);

    // -----------------Type-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 6;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(new JLabel("User Type: "), grid);

    grid.gridx = 1;
    grid.gridy = 6;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(type, grid);

    // -----------------Bottoms-----------------------

    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 7;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_START;
    add(register, grid);
    // -----------------------------------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 1;
    grid.gridy = 7;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(clear, grid);



    // add listeners
    register.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        registerNewUser();

      }

    });
    clear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        clearFields();

      }

    });

  }

  public void registerNewUser() {

    try {
      Connection conn = DBConnect.getConnection();
      Statement stmt = conn.createStatement();

      if (firstName.getText().isEmpty() == false && lastName.getText().isEmpty() == false
          && username.getText().isEmpty() == false && password.getText().isEmpty() == false
          && emmail.getText().isEmpty() == false) {

        if (emmail.getText().contains("@") && emmail.getText().contains(".com")) {

          if (password.getText().equals(confPassword.getText())) {
            // register


            // hash password
            String pw = password.getText();
            String salted = "*/P4RaNGu4r!" + pw + "cUt!r!mI*/";
            String finalPassword = "";
            try {

              MessageDigest md = MessageDigest.getInstance("MD5");
              md.update(salted.getBytes());
              byte[] digest = md.digest();
              StringBuffer strib = new StringBuffer();
              for (byte b : digest) {
                strib.append(String.format("%02x", b & 0xff));
              }
              finalPassword = strib.toString();
            } catch (Exception e) {}


            stmt.execute(
                "INSERT INTO `gui_assignment`.`users`(`firstName`,`lastName`, `username`,`password`,`type`,`emmail`) "
                    + "VALUES ('" + firstName.getText() + "', '" + lastName.getText() + "', '"
                    + username.getText() + "', '" + finalPassword + "','" + type.getSelectedItem()
                    + "', '" + emmail.getText() + "');");

            JOptionPane.showMessageDialog(this, "You have been successfully Registered");
            clearFields();
            p.dispose();
            new Admin(p.cabecera);


          } else {
            JOptionPane.showMessageDialog(this, "The Passwords don't Mach");
            password.setText("");
            confPassword.setText("");
          }
        } else {
          JOptionPane.showMessageDialog(this, "Invalid Emmail Address");

        }

      } else {
        // ask for fields
        JOptionPane.showMessageDialog(this, "Please fill in all fields");

      }

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  } // end of registerNewUser

  public void clearFields() {
    firstName.setText("");
    lastName.setText("");
    username.setText("");
    password.setText("");
    confPassword.setText("");
    emmail.setText("");
    type.setSelectedIndex(0);
  }

}
