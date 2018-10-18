package manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import resources.DBConnect;

public class Owner extends JFrame {
  
  int count = 0;
  
  int ct = 50;
  
  public Owner() {
    super("Owner");
    setSize(350,300);
    setVisible(true);
    setResizable(false);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    
    JPanel toolbar = new JPanel();
      toolbar.setBackground(Color.GRAY);
  
      FlowLayout fl = new FlowLayout();
      fl.setAlignment(0);
      toolbar.setLayout(fl);
  
      JLabel img = new JLabel("");
      Image image = new ImageIcon(this.getClass().getResource("/owner.png")).getImage();
      img.setIcon(new ImageIcon(image));
  
      JLabel title = new JLabel("Tickets Costs");
      title.setFont(title.getFont().deriveFont(20.0f));
  
      toolbar.add(img);
      toolbar.add(title);
    
    JPanel center = new JPanel(); 
      center.setLayout(new BorderLayout());
      
      JPanel left = new JPanel(); 
      left.setLayout(new GridLayout(2,2));
      
        Border innerBorder = BorderFactory.createTitledBorder("Opened Tickets");
        Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        left.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        JLabel verde = new JLabel();
        Image v = new ImageIcon(this.getClass().getResource("/rojo.png")).getImage();
        verde.setIcon(new ImageIcon(v));
        
        JLabel opened = new JLabel("  "+ Integer.toString(countTicket(1)));
        opened.setFont(opened.getFont().deriveFont(20.0f));
        
        JLabel cost = new JLabel();
        Image c = new ImageIcon(this.getClass().getResource("/cost.png")).getImage();
        cost.setIcon(new ImageIcon(c));
        
        JLabel money = new JLabel(Integer.toString(ct*countTicket(1)));
        money.setFont(money.getFont().deriveFont(20.0f));
        
        left.add(verde);
        left.add(opened);
        left.add(cost);
        left.add(money);
        
      JPanel right = new JPanel(); 
      right.setLayout(new GridLayout(2,2));
        
          Border innerBorder2 = BorderFactory.createTitledBorder("Closed Tickets");
          Border outerBorder2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
          right.setBorder(BorderFactory.createCompoundBorder(outerBorder2, innerBorder2));
          
          JLabel rojo = new JLabel();
          Image r = new ImageIcon(this.getClass().getResource("/verde.png")).getImage();
          rojo.setIcon(new ImageIcon(r));
          
          JLabel closed = new JLabel("  "+ Integer.toString(countTicket(0)));
          closed.setFont(closed.getFont().deriveFont(20.0f));
          
          JLabel cost2 = new JLabel();
          Image c2 = new ImageIcon(this.getClass().getResource("/cost.png")).getImage();
          cost2.setIcon(new ImageIcon(c2));
          
          JLabel money2 = new JLabel(Integer.toString(ct*countTicket(0)));
          money2.setFont(money2.getFont().deriveFont(20.0f));
          
          right.add(rojo);
          right.add(closed);
          right.add(cost2);
          right.add(money2);
          
      center.add(left, BorderLayout.WEST);
      center.add(right, BorderLayout.EAST);
      
    this.add(toolbar, BorderLayout.NORTH);
    this.add(center, BorderLayout.CENTER);
    JButton close = new JButton("Close"); 
    this.add(close, BorderLayout.AFTER_LAST_LINE);
    
    close.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        ver(); 
      }
      
    });
  }
  public void ver() {
    this.setVisible(false);
  }

  public int countTicket(int t) {
    String status = "";
    if (t == 0) {
      status = "Closed";
    }else { 
      status = "Opened";
    }
     
    try {
      Connection conn = DBConnect.getConnection();
      Statement stmt = conn.createStatement();

      stmt = conn.createStatement();
      ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM ticket WHERE status = '" + status + "'");
      r.next();
      count = r.getInt("rowcount");
      r.close();
    
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    
    return count; 
    
  }
}
