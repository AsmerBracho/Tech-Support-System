package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import login.Login;

public class Admin extends JFrame {
  
  DataPanel data = null;
  Update u = null;
  RegisterPanel registerPanel = null;
  
  String cabecera = "";
  public Admin(String n) {
    super("Admin Dashboard");
    cabecera = n;
    this.setSize(1200, 890);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    
    setLayout(new BorderLayout());
    
   
    //---------------Toolbar------------------//
    JPanel toolbar = new JPanel();
    
    FlowLayout fl = new FlowLayout();
    fl.setAlignment(2);
    
    toolbar.setLayout(new BorderLayout());
    toolbar.setBackground(Color.gray);
    
    Border outerBorder = BorderFactory.createEtchedBorder();
    Border innerBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
    toolbar.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    
    JPanel right = new JPanel();
    right.setBackground(Color.gray);
    right.setLayout(fl);
    
    
    JLabel img = new JLabel("");
    Image image = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
    img.setIcon(new ImageIcon(image));
    right.add(img);
    
    
    JLabel wel = new JLabel("Welcome:   ");
    
    JLabel user = new JLabel(cabecera + "    ");
    user.setFont (user.getFont ().deriveFont (18.0f));
    user.setForeground(Color.blue);
    right.add(wel);
    right.add(user);
    
    JButton logout = new JButton("Logout");
    Image imageL = new ImageIcon(this.getClass().getResource("/logoutIcon.png")).getImage();
    logout.setIcon(new ImageIcon(imageL));
    right.add(logout);
    
    JPanel left = new JPanel();
    left.setBackground(Color.gray);
    
    JLabel users = new JLabel();
    Image image2 = new ImageIcon(this.getClass().getResource("/users.png")).getImage();
    users.setIcon(new ImageIcon(image2));
    left.add(users);
    
    JLabel title = new JLabel("Admin Dashboard");
    title.setFont (title.getFont ().deriveFont (20.0f));
    left.add(title);
    
  
    toolbar.add(left, BorderLayout.LINE_START);
    toolbar.add(right, BorderLayout.LINE_END);
    
    //----------------------botton panel Toolbar -----------------------------//
    
    JPanel botton = new JPanel(); 
    FlowLayout f2 = new FlowLayout();
    f2.setAlignment(0);
    botton.setLayout(f2);
    
    Border outerBorder0 = BorderFactory.createEtchedBorder();
    Border innerBorder0 = BorderFactory.createEmptyBorder(5, 10, 5, 10);
    botton.setBorder(BorderFactory.createCompoundBorder(outerBorder0, innerBorder0));
    
    JLabel refresh = new JLabel("");
    Image image3 = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
    refresh.setIcon(new ImageIcon(image3));
    botton.add(refresh);
    
    //----------------------ADD BIG TOOLBAR-----------------------------//
    JPanel big = new JPanel();
    big.setLayout(new BorderLayout());
    
    big.add(toolbar, BorderLayout.NORTH);
    big.add(botton, BorderLayout.SOUTH);
    
    //------------------------------------------------------------------//
    
    //--------------Register------------------//
    JPanel register = new JPanel(); 
    
    register.setLayout(new BorderLayout());
    
    Border innerBorder2 = BorderFactory.createTitledBorder("Register");
    Border outerBorder2 = BorderFactory.createEmptyBorder(10,10,10,10);
    register.setBorder(BorderFactory.createCompoundBorder(outerBorder2, innerBorder2));
    
    // resizing the panel
    Dimension d = getPreferredSize();
    d.width = 400; 
    register.setPreferredSize(d);
   
    u = new Update(this);
    registerPanel = new RegisterPanel(this);
    
    register.add(registerPanel, BorderLayout.NORTH);
    register.add(u, BorderLayout.SOUTH);
     
    //----------------------------------------//
    data = new DataPanel(this);
    
    
    this.add(big, BorderLayout.PAGE_START);
    this.add(register, BorderLayout.WEST);
    this.add(data, BorderLayout.CENTER);
    
    logout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        optionLogout();
      }
      
    });
   
   refresh.addMouseListener(new MouseListener() {

    @Override
    public void mouseClicked(MouseEvent arg0) {
      ver(); 
      new Admin(cabecera);
      
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }
     
   });
    
  }
  
  public void optionLogout() {
    Object[] options = {"Yes", "No"};
    int n = JOptionPane.showOptionDialog(
      this, "Are you sure you want to logout?"
      + "",
      "Logout",
      JOptionPane.YES_NO_OPTION,
      JOptionPane.QUESTION_MESSAGE,
      null,
      options,
      options[0]);
    
      if (n == 0) {
        this.dispose();
        new Login();
      }
  }
  
  public void ver() {
    this.dispose();
    
  }
}
