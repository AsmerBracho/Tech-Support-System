package login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import admin.Admin;
import manager.Manager;
import techSupport.TechSupport;

public class Login extends JFrame {

  JTextField username = null;
  JTextField password = null;
  JButton loginButton = null;

  String cabecera = "";
  
  public Login() {
    
    super("Login");

    this.setSize(400, 250);
    this.setVisible(true);
    this.setResizable(false);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    JPanel big = new JPanel();
    big.setLayout(new BorderLayout());
    Border innerBorder = BorderFactory.createTitledBorder("Login");
    Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    big.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    JPanel r = new JPanel();
    GridLayout gr = new GridLayout(6, 1);
    r.setLayout(gr);

    username = new JTextField(20);
    password = new JPasswordField(20);
    loginButton = new JButton("login");

    // add elements
    r.add(new JLabel("Username: "));
    r.add(username);
    r.add(new JLabel("Password: "));
    r.add(password);
    r.add(new JLabel("")); // blank space
    r.add(loginButton);


    // login button action
    view.loginButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        loginWithDatabase();

      }

    });

    JLabel img = new JLabel("");
    Image image = new ImageIcon(this.getClass().getResource("/imglock.png")).getImage();
    img.setIcon(new ImageIcon(image));


    // adding the login window
    big.add(img, BorderLayout.CENTER);

    // add the form on the right

    big.add(r, BorderLayout.LINE_END);

    this.add(big);


  }

  public void loginWithDatabase() {

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception e) {}

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn =
          DriverManager.getConnection("jdbc:mysql://185.61.152.17/localhost:3306/flowbmcy_asmer?user=flowbmcy_ab&password=29febrero");
      stmt = conn.createStatement();

      String un = username.getText();
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
      
      rs = stmt.executeQuery(
          "SELECT * FROM users WHERE username = '" + un + "' and binary password = '" + finalPassword + "';");
      

      // loop over results
      if (rs.next() && un.isEmpty() == false && pw.isEmpty() == false) {
        cabecera = rs.getString("firstName") + " " + rs.getString("lastName");
        
        String type = rs.getString("type");
        
        //create the different panels
        if("Admin".equals(type)) {
          new Admin(cabecera);
        } else if ("TechSupport".equals(type)) {
          new TechSupport(cabecera); 
        } else if ("Manager".equals(type)) {
          new Manager(cabecera);
        }
            
        username.setText("");
        password.setText("");
        this.dispose();
        
        
      } else {
        JOptionPane.showMessageDialog(this, "The Username or Password is incorret");
      }

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  } // end of login data


}
