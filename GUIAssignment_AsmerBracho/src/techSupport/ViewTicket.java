package techSupport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

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

public class ViewTicket extends JFrame {
  JTextField subjet = null;
  JTextField priority = null;
  JTextField tech = null;
  JTextArea description = null;
 

  TicketPanel t = null;

  public ViewTicket(TicketPanel ref) {
    super("View Ticket");
    t = ref;
    this.setSize(630, 400);

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
    Image image = new ImageIcon(this.getClass().getResource("/lupa.png")).getImage();
    img.setIcon(new ImageIcon(image));

    JLabel title = new JLabel("View Ticket");
    title.setFont(title.getFont().deriveFont(20.0f));

    toolbar.add(img);
    toolbar.add(title);

    JPanel right = new JPanel();
    right.setBackground(Color.GRAY);
    FlowLayout fl2 = new FlowLayout();
    fl2.setAlignment(2);
    right.setLayout(fl2);
    
    
    big.add(toolbar, BorderLayout.WEST);
    big.add(right, BorderLayout.EAST);

    JPanel form = new JPanel();

    Border innerBorder = BorderFactory.createTitledBorder("Create Ticket");
    Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    form.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    GridBagLayout gr = new GridBagLayout();
    form.setLayout(gr);

    subjet = new JTextField(10);
    priority = new JTextField(10);
    tech = new JTextField(10);
    description = new JTextArea(10, 40);
    
    //--------------------Set Views values //----------------------------------
    
    
      subjet.setText(t.getValue(1));
      priority.setText(t.getValue(2));
      tech.setText(t.getValue(4));
      description.setText(t.getValue(7));
      
      ver(false);
    
    //------------------------------------------------------------------------

    // ------------Add elements--------------------//
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


    // -------------------------------------------//


    this.add(big, BorderLayout.NORTH);
    this.add(form, BorderLayout.CENTER);
    
  }
  
  public void ver(boolean t) {
    subjet.setEditable(t);
    priority.setEditable(t);
    tech.setEditable(t);
    description.setEditable(t);
  }
}
