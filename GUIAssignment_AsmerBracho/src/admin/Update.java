package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import resources.DBConnect;

public class Update extends JPanel {

  private JTextField uFirstName = null;
  private JTextField uLastName = null;
  private JTextField uUsername = null;
  private JTextField uPassword = null;
  private JTextField uEmmail = null;
  private JComboBox uType = null;
  String id = null;
  int theId;
  Admin p = null;

  public JTextField getuFirstName() {
    return uFirstName;
  }

  public void setuFirstName(JTextField uFirstName) {
    this.uFirstName = uFirstName;
  }


  public JTextField getuLastName() {
    return uLastName;
  }

  public void setuLastName(JTextField uLastName) {
    this.uLastName = uLastName;
  }

  public JTextField getuUsername() {
    return uUsername;
  }

  public void setuUsername(JTextField uUsername) {
    this.uUsername = uUsername;
  }

  public JTextField getuPassword() {
    return uPassword;
  }

  public void setuPassword(JTextField uPassword) {
    this.uPassword = uPassword;
  }

  public JTextField getuEmmail() {
    return uEmmail;
  }

  public void setuEmmail(JTextField uEmmail) {
    this.uEmmail = uEmmail;
  }

  public JComboBox getuType() {
    return uType;
  }

  public void setuType(JComboBox uType) {
    this.uType = uType;
  }

  JLabel img = null;
  JButton update = null;
  JButton seeUpdate = null;
  JButton delete = null;

  JLabel fn;
  JLabel ln;
  JLabel un;
  JLabel pa;
  JLabel ty;
  JLabel em;

  public Update(Admin ref) {
    p = ref;
    Border innerBorder = BorderFactory.createTitledBorder("Update");
    Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    // set layout for this class
    this.setLayout(new BorderLayout());

    // create panels
    JPanel b = new JPanel();
    JPanel t = new JPanel();
    JPanel bt = new JPanel();

    // create elements
    uFirstName = new JTextField(15);
    uLastName = new JTextField(15);
    uUsername = new JTextField(15);
    uPassword = new JTextField(15);
    uEmmail = new JTextField(15);
    uType = new JComboBox();
    id = "id";
    theId = -1;

    fn = new JLabel("Name: ");
    ln = new JLabel("Surname: ");
    un = new JLabel("Username: ");
    pa = new JLabel("Password: ");
    ty = new JLabel("UserType: ");
    em = new JLabel("Emmail: ");

    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    combo.addElement("Admin");
    combo.addElement("TechSupport");
    combo.addElement("Manager");
    uType.setModel(combo);

    seeUpdate = new JButton("Update User");
    update = new JButton("     Update");
    Image upd = new ImageIcon(this.getClass().getResource("/update.png")).getImage();
    update.setIcon(new ImageIcon(upd));

    delete = new JButton("     Delete");
    Image del = new ImageIcon(this.getClass().getResource("/delete.png")).getImage();
    delete.setIcon(new ImageIcon(del));

    img = new JLabel("");
    Image image = new ImageIcon(this.getClass().getResource("/up.png")).getImage();
    img.setIcon(new ImageIcon(image));

    // set panel positions
    // ----------------------- TOP Panel------------------
    t.setLayout(new BorderLayout());

    t.add(img, BorderLayout.WEST);
    t.add(seeUpdate, BorderLayout.BEFORE_FIRST_LINE);

    // ----------------------- BOTTOM Panel --------------
    GridLayout gr = new GridLayout(7, 2);
    gr.setVgap(3);
    b.setLayout(gr);

    b.add(fn);
    b.add(uFirstName);
    b.add(ln);
    b.add(uLastName);
    b.add(un);
    b.add(uUsername);
    b.add(pa);
    b.add(uPassword);
    b.add(em);
    b.add(uEmmail);
    b.add(ty);
    b.add(uType);

    GridLayout gr2 = new GridLayout(2, 1);
    gr2.setVgap(3);
    bt.setLayout(gr2);

    bt.add(update);
    bt.add(delete);

    this.add(t, BorderLayout.NORTH);
    this.add(b, BorderLayout.CENTER);
    this.add(bt, BorderLayout.AFTER_LAST_LINE);

    seeUpdate(false);

    // add listeners
    seeUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        seeUpdate(true);
        seeUpdate.setBackground(Color.black);
        seeUpdate.setForeground(Color.white);
      }
    });

    update.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateUser();

      }

    });
  }

  public void seeUpdate(boolean t) {
    img.setEnabled(t);
    uFirstName.setVisible(t);
    uLastName.setVisible(t);
    uUsername.setVisible(t);
    uPassword.setVisible(t);
    uEmmail.setVisible(t);
    uType.setVisible(t);
    fn.setEnabled(t);
    ln.setEnabled(t);
    un.setEnabled(t);
    pa.setEnabled(t);
    em.setEnabled(t);
    ty.setEnabled(t);
    update.setVisible(t);
    delete.setVisible(t);

  }

  public void clearFields() {
    uFirstName.setText("");
    uLastName.setText("");
    uUsername.setText("");
    uPassword.setText("");
    uEmmail.setText("");
    uType.setSelectedIndex(0);
  }

  public void updateUser() {
    try {
      Connection conn = DBConnect.getConnection();
      Statement st = conn.createStatement();

      if (uFirstName.getText().isEmpty() == false && uLastName.getText().isEmpty() == false
          && uUsername.getText().isEmpty() == false && uPassword.getText().isEmpty() == false
          && uEmmail.getText().isEmpty() == false) {

        if (uEmmail.getText().contains("@") && uEmmail.getText().contains(".com")) {


          // hash password
          String pw = uPassword.getText();
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
          // update

          st.execute("UPDATE `gui_assignment`.`users` SET firstName = '" + uFirstName.getText()
              + "', lastName = '" + uLastName.getText() + "', username = '" + uUsername.getText()
              + "', password = '" + finalPassword + "', type = '" + uType.getSelectedItem()
              + "', emmail = '" + uEmmail.getText() + "'  WHERE id = '" + theId + "';");

          JOptionPane.showMessageDialog(this, "User successfully Updated");
          clearFields();
          p.dispose();
          new Admin(p.cabecera);

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
  }
}
