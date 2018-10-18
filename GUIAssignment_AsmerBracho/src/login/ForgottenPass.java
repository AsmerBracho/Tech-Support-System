package login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ForgottenPass extends JPanel {

  JLabel newP = null;
  JLabel confNewP = null;
  JTextField newPassword = null;
  JTextField confNewPassword = null;
  JButton change = null; 

  public ForgottenPass() {

    this.setLayout(new GridBagLayout());
    // create constraints to work with
    GridBagConstraints grid = new GridBagConstraints();

    Border innerBorder = BorderFactory.createTitledBorder("Forgot Your Password?");
    Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    
    // create Elements
    JLabel img = new JLabel("");
    Image image = new ImageIcon(this.getClass().getResource("/imgPass.png")).getImage();
    img.setIcon(new ImageIcon(image));
    
    
    newP = new JLabel("New Password");
    newPassword = new JTextField(12);
    confNewP = new JLabel("Confirm New Password");
    confNewPassword = new JTextField(12);

    // -----------------Image-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 0;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(img, grid);   

    // -----------------User-----------------------
    JLabel userName = new JLabel("Some Dinamic User");
    JLabel user = new JLabel("User: ");
    
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 1;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(user, grid);

    // -----------------New Password-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 2;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(newP, grid);

    grid.gridx = 1;
    grid.gridy = 2;
    grid.anchor = GridBagConstraints.LINE_END;
    add(newPassword, grid);
    
    // -----------------Confirm New Password-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 3;
    grid.fill = GridBagConstraints.NONE;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    add(confNewP, grid);

    grid.gridx = 1;
    grid.gridy = 3;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(confNewPassword, grid);
    
    // -----------------Confirm New Password-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    change = new JButton("Change Password");

    grid.gridx = 1;
    grid.gridy = 4;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    add(change, grid);
     


  }

}
