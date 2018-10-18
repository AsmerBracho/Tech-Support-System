package manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.ResultSetMetaData;

import resources.DBConnect;

public class TechTickets extends JFrame {

  JTable table = null;
  String tech = "";
  JButton close = null;

  public TechTickets(String tech) {
    super("Tech Tickets");
    this.tech = tech;
    this.setSize(1000, 230);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());


    JPanel toolbar = new JPanel();
      toolbar.setBackground(Color.GRAY);
  
      FlowLayout fl = new FlowLayout();
      fl.setAlignment(0);
      toolbar.setLayout(fl);
  
      JLabel img = new JLabel("");
      Image image = new ImageIcon(this.getClass().getResource("/ticketAdd.png")).getImage();
      img.setIcon(new ImageIcon(image));
  
      JLabel title = new JLabel("Tickets By:  " + tech);
      title.setFont(title.getFont().deriveFont(20.0f));
  
      toolbar.add(img);
      toolbar.add(title);
  
      showTickets();

    JPanel right = new JPanel();

      Border innerBorder = BorderFactory.createTitledBorder("Totals");
      Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
      right.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
  
      Dimension d = getPreferredSize();
      d.width = 300;
      right.setPreferredSize(d);
  
      right.setLayout(new GridLayout(3, 1));
  
      JLabel opened = new JLabel("Opened Tickets:   " + countTicket(1));
      Image o = new ImageIcon(this.getClass().getResource("/closed.png")).getImage();
      opened.setIcon(new ImageIcon(o));
  
      JLabel closed = new JLabel("Closed Tickets:   " + countTicket(0));
      Image c = new ImageIcon(this.getClass().getResource("/opened.png")).getImage();
      closed.setIcon(new ImageIcon(c));
  
      JLabel total = new JLabel("Total Tickets:   " + (countTicket(1) + countTicket(0)));
      Image t = new ImageIcon(this.getClass().getResource("/total.png")).getImage();
      total.setIcon(new ImageIcon(t));
  
      right.add(opened);
      right.add(closed);
      right.add(total);

    close = new JButton("Close");
    close.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        ver();
        
      }
      
    });
    this.add(right, BorderLayout.EAST);
    this.add(toolbar, BorderLayout.NORTH);
    this.add(close, BorderLayout.AFTER_LAST_LINE);
  }
  
  public void ver() {
    this.setVisible(false);
  }
  
  public int countTicket(int t) {
    String status = "";
    if (t == 0) {
      status = "Closed";
    } else {
      status = "Opened";
    }
    int count = 0;
    try {
      Connection conn = DBConnect.getConnection();
      Statement stmt = conn.createStatement();

      stmt = conn.createStatement();
      ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM ticket WHERE status = '"
          + status + "' and tech = '" + tech + "'");
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

  public void showTickets() {
    try {
      Connection conn = DBConnect.getConnection();
      Statement stmt = conn.createStatement();

      stmt = conn.createStatement();
      ResultSet r =
          stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM ticket  WHERE tech = '" + tech + "'");
      r.next();
      int count = r.getInt("rowcount");
      r.close();


      ResultSet rs = stmt.executeQuery("select * from ticket WHERE tech = '" + tech + "';");

      // loop over results

      ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
      int columnsNumber = rsmd.getColumnCount();

      // some database code


      String[][] data = new String[count][columnsNumber];
      int counter = 0;

      while (rs.next()) {

        String id = rs.getString("id");
        data[counter][0] = id;

        String tech = rs.getString("tech");
        data[counter][1] = tech;

        String epoch = rs.getString("date");

        Date date = new Date(Long.parseLong(epoch));
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy         HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        data[counter][2] = formatted;

        String closed = rs.getString("close_date");
        if ("0".equals(closed)) {
          data[counter][3] = "";
        } else {
          Date date2 = new Date(Long.parseLong(epoch));
          DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy         HH:mm:ss");
          format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
          String formatted2 = format2.format(date2);
          data[counter][3] = formatted2;

        }

        String status = rs.getString("status");
        data[counter][4] = status;

        counter = counter + 1;
      }

      String[] colNames = {"ID", "Teach", "Issue Date", "Closed Date", "Status"};

      table = new JTable(data, colNames);
      TableColumn colA = table.getColumn("ID");
      colA.setMinWidth(0);
      colA.setMaxWidth(0);



      // TableColumn colC = table.getColumnModel().getColumn(2);
      // colC.setMinWidth(80);
      // colC.setMaxWidth(80);

      JScrollPane sr = new JScrollPane(table);

      this.add(sr, BorderLayout.CENTER);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
