package techSupport.newTicket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import resources.DBConnect;
import techSupport.TechSupport;

public class NewTicket extends JFrame {
  JTextField subjet = null; 
  JComboBox priority = null; 
  JComboBox tech = null; 
  JTextArea description = null;
  JButton create = null;
  JButton close = null;
  
  TechSupport t = null; 
  public NewTicket(TechSupport ref) {
    super("New Ticket");
    t = ref; 
    this.setSize(630,400);
    
    Dimension d = getPreferredSize();
    d.height = 400;
    d.width = 630;
   
    this.setMinimumSize(d);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());
    
    JPanel big = new JPanel();
    big.setBackground(Color.GRAY);
    big.setLayout(new BorderLayout());
   
    JPanel toolbar = new JPanel();
    toolbar.setBackground(Color.GRAY);
    
    FlowLayout fl = new FlowLayout();
    fl.setAlignment(0);
    toolbar.setLayout(fl);
    
    JLabel img = new JLabel("");
    Image image = new ImageIcon(this.getClass().getResource("/ticketAdd.png")).getImage();
    img.setIcon(new ImageIcon(image));
    
    JLabel title = new JLabel("Create New Ticket");
    title.setFont (title.getFont ().deriveFont (20.0f));
            
    toolbar.add(img);
    toolbar.add(title);
    
    JPanel right = new JPanel();
    right.setBackground(Color.GRAY);
    FlowLayout fl2 = new FlowLayout();
    fl2.setAlignment(2);
    right.setLayout(fl2);
    
    JLabel clear = new JLabel("");
    Image image4 = new ImageIcon(this.getClass().getResource("/clear.png")).getImage();
    clear.setIcon(new ImageIcon(image4));
    
    right.add(clear);
    
    big.add(toolbar, BorderLayout.WEST);
    big.add(right, BorderLayout.EAST);
    
    JPanel form = new JPanel();
    
    Border innerBorder = BorderFactory.createTitledBorder("Create Ticket");
    Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    form.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    
    GridBagLayout gr = new GridBagLayout();
    form.setLayout(gr);
    
    subjet = new JTextField(10);
    priority = new JComboBox();
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    combo.addElement("Urgent");
    combo.addElement("Normal");
    combo.addElement("LongTerm");
    priority.setModel(combo);
    
    
    getListTech();
    
    description = new JTextArea(10,40);
    
    create = new JButton("    Create");
    Image image2 = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
    create.setIcon(new ImageIcon(image2));
    
        
    //------------Add elements--------------------//
    GridBagConstraints grid = new GridBagConstraints();
    
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 0;
    grid.fill = GridBagConstraints.HORIZONTAL;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    form.add(new JLabel("Subjet: "), grid);

    grid.gridx = 1;
    grid.gridy = 0;
    grid.anchor = GridBagConstraints.LINE_END;
    form.add(subjet, grid);

    // -----------------Priority-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 1;
    grid.fill = GridBagConstraints.HORIZONTAL;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    form.add(new JLabel("Priority: "), grid);

    grid.gridx = 1;
    grid.gridy = 1;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    form.add(priority, grid);

    // -----------------Tech-----------------------
    grid.weightx = 1;
    grid.weighty = 1;

    grid.gridx = 0;
    grid.gridy = 2;
    grid.fill = GridBagConstraints.HORIZONTAL;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    form.add(new JLabel("Tech: "), grid);

    grid.gridx = 1;
    grid.gridy = 2;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    form.add(tech, grid);

    // -----------------Description-----------------------
    grid.weightx = 1;
    grid.weighty = 4;

    grid.gridx = 0;
    grid.gridy = 3;
    grid.fill = GridBagConstraints.HORIZONTAL;
    grid.anchor = GridBagConstraints.LINE_START;
    grid.insets = new Insets(0, 20, 0, 10);
    form.add(new JLabel("Description:  "), grid);

    grid.gridx = 1;
    grid.gridy = 3;
    // add space grid.insets = new Insets(0,0,0,0);
    grid.anchor = GridBagConstraints.LINE_END;
    form.add(new JScrollPane(description), grid);

    
    //-------------------------------------------//
    
    
    this.add(big, BorderLayout.NORTH);
    this.add(form, BorderLayout.CENTER);
    this.add(create, BorderLayout.SOUTH);
    
    
    //add actionListener 
    
    create.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
       addNewTicket();
        
      }
      
    });
    clear.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent arg0) {
        clear();
        
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

  public void getListTech() {
    try {
    Connection conn = DBConnect.getConnection();
    Statement stmt = conn.createStatement();

    stmt = conn.createStatement();
    
    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE type = 'TechSupport';");

    // loop over results

    tech = new JComboBox();
    DefaultComboBoxModel combo2 = new DefaultComboBoxModel();
      while (rs.next()) {
        String name = rs.getString("firstName");
        combo2.addElement(name);
      }
       
      tech.setModel(combo2);
      
    } catch(SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    
    
  }
  
  public void addNewTicket() {
    String status = "Opened";
    long date = Instant.now().toEpochMilli();
    try { 
      Connection conn = DBConnect.getConnection();
      Statement stm = conn.createStatement();
      
      if(subjet.getText().isEmpty() == false && description.getText().isEmpty() == false) {
        stm.execute("INSERT INTO `gui_assignment`.`ticket`(`subjet`,`description`, `priority`,`date`,`tech`,`status`) "
            + "VALUES ('" + subjet.getText() + "', '" + description.getText() + "', '"
            + priority.getSelectedItem() + "', '" + date + "','"
            + tech.getSelectedItem() + "', '" +status+ "');");
        
        JOptionPane.showMessageDialog(this, "The ticket have been successfully created");
        this.dispose();
        t.dispose();
        new TechSupport(t.getCabecera());
        
      } else {
        JOptionPane.showMessageDialog(this, "Please fill in all fields");
      }
      
    } catch(SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
      
    }
  }

  public void clear() {
    subjet.setText("");
    priority.setSelectedIndex(0);
    tech.setSelectedIndex(0);
    description.setText("");
  }
}

