package manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javax.swing.border.Border;

import login.Login;
import resources.DBConnect;

public class Toolbar extends JPanel {
    
    JButton logout = null;
    JButton filter = null; 
    String cabecera =""; 
    Manager m = null; 
    JComboBox filterByTech = null;
    JButton cost = null; 
    
    public Toolbar(Manager m) {
      this.m = m;
      
      this.setLayout(new BorderLayout());
      
      cabecera = m.getCabecera();
      //-------------Top Panel-------------------------//
      JPanel top = new JPanel();
      top.setBackground(Color.gray);
      top.setLayout(new BorderLayout());
      
        //-------------left panel-------------------------//
        JPanel left = new JPanel(); 
        left.setLayout(new FlowLayout());
        left.setBackground(Color.GRAY);
        
        JLabel img = new JLabel("");
        Image image = new ImageIcon(this.getClass().getResource("/manager.png")).getImage();
        img.setIcon(new ImageIcon(image));
        left.add(img);
        
        JLabel title = new JLabel("Manager Dashboard");
        title.setFont (title.getFont ().deriveFont (20.0f));
        left.add(title);
      
        //--------------Right Panel ----------------------//
        JPanel right = new JPanel();
        right.setLayout(new FlowLayout());
        right.setBackground(Color.gray);
        
        JLabel img2 = new JLabel("");
        Image image2 = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
        img2.setIcon(new ImageIcon(image2));
        
        JLabel wel = new JLabel("Welcome:   ");
        
        JLabel user = new JLabel(cabecera + "    ");
        user.setFont (user.getFont ().deriveFont (18.0f));
        user.setForeground(Color.blue);
        
        logout =  new JButton("  Logout");
        Image image0 = new ImageIcon(this.getClass().getResource("/logoutIcon.png")).getImage();
        logout.setIcon(new ImageIcon(image0));
        
        right.add(img2);
        right.add(wel);
        right.add(user);
        right.add(logout);
        
        
      // add to top panel
      top.add(left, BorderLayout.WEST);
      top.add(right,BorderLayout.EAST);
      
    //-----------------Bottom Panel----------------------------------//
      JPanel bottom = new JPanel();
      FlowLayout fl = new FlowLayout();
      fl.setAlignment(0);
      bottom.setLayout(fl);
      
      Border outerBorder = BorderFactory.createEtchedBorder();
      Border innerBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
      bottom.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

      
          
      filter = new JButton("  filter");
      Image image4 = new ImageIcon(this.getClass().getResource("/filter.png")).getImage();
      filter.setIcon(new ImageIcon(image4));
      
      
      JLabel refresh = new JLabel("   ");
      Image image3 = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
      refresh.setIcon(new ImageIcon(image3));
      
      cost = new JButton("See Costs (Owner)");
      
      bottom.add(refresh);
      bottom.add(new JLabel("Filter"));
      getListTech();
      bottom.add(filterByTech);
      bottom.add(filter);
      bottom.add(cost);
      
      this.add(top, BorderLayout.NORTH);
      this.add(bottom, BorderLayout.SOUTH);
      
      // ------------------ add listeners -----------------------------//
      filter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          
          
        }
        
      });
      logout.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          optionLogout();
          
        }
        
      });
      
      filter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          String te = filterByTech.getSelectedItem().toString();
          String[] parts = te.split(" ");
          String tech = parts[4];
          new TechTickets(tech);
          
        }
        
      });
      cost.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          new Owner();
          
        }
        
      });
      
      refresh.addMouseListener(new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent arg0) {
          m.dispose();
          new Manager(m.getCabecera());
          
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
        m, "Are you sure you want to logout?"
        + "",
        "Logout",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
      
        if (n == 0) {
          m.dispose();
          new Login();
        }
    }
    
    
    public void getListTech() {
      try {
      Connection conn = DBConnect.getConnection();
      Statement stmt = conn.createStatement();

      stmt = conn.createStatement();
      
      ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE type = 'TechSupport';");

      // loop over results

      filterByTech = new JComboBox();
      DefaultComboBoxModel combo2 = new DefaultComboBoxModel();
        
        while (rs.next()) {
          String name = rs.getString("firstName");
          combo2.addElement("Tickets By:   " + name);
        }
         
        filterByTech.setModel(combo2);
        
      } catch(SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
      }
      
      
    }
    
    
}
