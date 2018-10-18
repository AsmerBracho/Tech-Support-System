package techSupport;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.ResultSetMetaData;

import resources.DBConnect;

public class TicketPanel extends JPanel {
  JTable table = null;
  JButton close = null;
  JButton delete = null;
  JButton ver = null;
  int theId;
  TechSupport ticket = null;

  public TicketPanel(TechSupport ref) {
    setLayout(new BorderLayout());
    ticket = ref;
    Border innerBorder = BorderFactory.createTitledBorder("Tickets");
    Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    showTickets();

    theId = -1;
    JPanel b = new JPanel();
    FlowLayout fl = new FlowLayout();
    fl.setAlignment(2);
    b.setLayout(fl);
    
    ver = new JButton("  See Ticket");
    Image ver1 = new ImageIcon(this.getClass().getResource("/ver.png")).getImage();
    ver.setIcon(new ImageIcon(ver1));
    
    
    close = new JButton("  Close Ticket");
    Image image = new ImageIcon(this.getClass().getResource("/close.png")).getImage();
    close.setIcon(new ImageIcon(image));

    delete = new JButton("  Delete Ticket");
    Image image2 = new ImageIcon(this.getClass().getResource("/delete.png")).getImage();
    delete.setIcon(new ImageIcon(image2));
    
    b.add(ver);
    b.add(close);
    b.add(delete);

    this.add(b, BorderLayout.SOUTH);

    // add listeners
    ver.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (theId == -1) {
          JOptionPane.showMessageDialog(ticket, "Please Select a ticket from the table");
   
        } else {
          ver();
        }
        
      }
      
    });
    close.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        closeTicket();

      }

    });
    table.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent arg0) {
        theId = Integer.parseInt(getValue(0));

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
    delete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteTicket();

      }

    });

  } // End of Constructor

  public String getValue(int numb) {
    int row = table.getSelectedRow();
    int column = numb;
    String o = (String) table.getModel().getValueAt(row, column);
    return o;
  }

  public void deleteTicket() {
    try { 
      Connection conn = DBConnect.getConnection();
      Statement stm = conn.createStatement();
      
      if (theId == -1) {
        JOptionPane.showMessageDialog(this, "Please Select a ticket from the table");
  
      } else {
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(
          ticket, "Are you sure you want to Delete this ticket?"
          + "",
          "Delete Ticket",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);
        
          if (n == 0) {
            stm.execute("DELETE FROM `ticket` WHERE id = '" + theId + "';");
            JOptionPane.showMessageDialog(this, "Ticket Deleted");
            ticket.dispose();
            new TechSupport(ticket.getCabecera());
          }
      }
        
    }catch(SQLException ex){
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());

  }
  }
  public void ver() {
    new ViewTicket(this);
  }
  public void showTickets() {
    try {
      Connection conn = DBConnect.getConnection();
      Statement stmt = conn.createStatement();

      stmt = conn.createStatement();
      ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM ticket");
      r.next();
      int count = r.getInt("rowcount");
      r.close();


      ResultSet rs = stmt.executeQuery("select * from ticket;");

      // loop over results

      ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
      int columnsNumber = rsmd.getColumnCount();

      // some database code


      String[][] data = new String[count][columnsNumber];
      int counter = 0;

      while (rs.next()) {

        String id = rs.getString("id");
        data[counter][0] = id;

        String subjet = rs.getString("subjet");
        data[counter][1] = subjet;

        String priority = rs.getString("priority");
        data[counter][2] = priority;

        String epoch = rs.getString("date");

        Date date = new Date(Long.parseLong(epoch));
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy         HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        data[counter][3] = formatted;

        String tech = rs.getString("tech");
        data[counter][4] = tech;

        String status = rs.getString("status");
        data[counter][5] = status;

        String closed = rs.getString("close_date");
        if ("0".equals(closed)) {
          data[counter][6] = "";
        } else {
          Date date2 = new Date(Long.parseLong(closed));
          DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy         HH:mm:ss");
          format2.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
          String formatted2 = format2.format(date2);
          data[counter][6] = formatted2;

        }
        
        String description = rs.getString("description");
        data[counter][7] = description;
        
        counter = counter + 1;
      }

      String[] colNames = {"ID", "Subjet", "Priority", "Date", "Teach", "Status", "Closed Date", "Description"};

      table = new JTable(data, colNames);
      TableColumn colA = table.getColumn("ID");
      colA.setMinWidth(0);
      colA.setMaxWidth(0);
      TableColumn colDes = table.getColumn("Description");
      colDes.setMinWidth(0);
      colDes.setMaxWidth(0);
      
      TableColumn colB = table.getColumn("Subjet");
      colB.setMinWidth(350);
      TableColumn colC = table.getColumnModel().getColumn(2);
      colC.setMinWidth(80);
      colC.setMaxWidth(80);
      TableColumn colD = table.getColumnModel().getColumn(4);
      colD.setMinWidth(90);
      colD.setMaxWidth(90);
      TableColumn colE = table.getColumnModel().getColumn(5);
      colE.setMinWidth(100);
      colE.setMaxWidth(100);
      
      JScrollPane sr = new JScrollPane(table);

      this.add(sr, BorderLayout.CENTER);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

  public void closeTicket() {
    String status = "Closed";
    long date = Instant.now().toEpochMilli();
    try {
      Connection conn = DBConnect.getConnection();
      Statement stm = conn.createStatement();

      if (theId == -1) {
        JOptionPane.showMessageDialog(this, "Please Select a ticket from the table");

      } else {
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(ticket,
            "Are you sure you want to close this ticket?" + "", "Close Ticket",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (n == 0) {
          stm.execute("UPDATE `gui_assignment`.`ticket` SET close_date = '" + date + "', status = '"
              + status + "' WHERE id = '" + theId + "';");
          JOptionPane.showMessageDialog(this, "Ticket Closed");
          ticket.dispose();
          new TechSupport(ticket.getCabecera());
        }
      }

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());

    }
  }
}
