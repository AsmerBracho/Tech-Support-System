package login;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Forgot extends JFrame {
  ForgottenPass f = null; 
  
  public Forgot() {
    super("Rescue Password");
    
    this.setSize(400, 280);
    this.setVisible(true);
    this.setResizable(false);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
   
    //adding the login window
    f = new ForgottenPass();
    this.add(f, BorderLayout.NORTH); 
  }
  
}

