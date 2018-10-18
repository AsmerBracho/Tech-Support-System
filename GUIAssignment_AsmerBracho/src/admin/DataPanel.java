package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.ResultSetMetaData;

import login.Login;
import resources.DBConnect;

public class DataPanel extends JPanel {

  JTable table = null;
  Admin p = null;

  public DataPanel(Admin ref) {
    p = ref;
    setLayout(new BorderLayout());
    showUsers();

    Border innerBorder = BorderFactory.createTitledBorder("Users");
    Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    
    p.u.delete.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        deleteUser();
       
      }
      
    });
    
  }

  public void showUsers() {
    
    try {
      Connection conn = DBConnect.getConnection();
      Statement stmt = conn.createStatement();

      stmt = conn.createStatement();
      ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM users");
      r.next();
      int count = r.getInt("rowcount");
      r.close();


      ResultSet rs = stmt.executeQuery("select * from users;");

      // loop over results

      ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
      int columnsNumber = rsmd.getColumnCount();


      String[][] data = new String[count][columnsNumber];
      int counter = 0;

      while (rs.next()) {

        String id = rs.getString("id");
        data[counter][0] = id;

        String fn = rs.getString("firstName");
        data[counter][1] = fn;

        String ln = rs.getString("lastName");
        data[counter][2] = ln;

        String un = rs.getString("username");
        data[counter][3] = un;

        String pw = rs.getString("password");
        data[counter][4] = pw;

        String emmail = rs.getString("emmail");
        data[counter][5] = emmail;

        String ut = rs.getString("type");
        data[counter][6] = ut;

        counter = counter + 1;
      }

      String[] colNames =
          {"ID", "First Name", "Last Name", "Username", "Password", "Emmail", "User Type"};

      table = new JTable(data, colNames);
      
      // take off the index from the table
      TableColumn colC = table.getColumnModel().getColumn(0);
      colC.setMinWidth(0);
      colC.setMaxWidth(0);
      
      
      JScrollPane sr = new JScrollPane(table);

      this.add(sr, BorderLayout.CENTER);

      table.addMouseListener(new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
          p.u.getuFirstName().setText(getValue(1));
          p.u.getuLastName().setText(getValue(2));
          p.u.getuUsername().setText(getValue(3));
          p.u.getuPassword().setText(getValue(4));
          p.u.getuEmmail().setText(getValue(5));
          p.u.id = getValue(0);
          p.u.theId = Integer.parseInt(p.u.id);

        }

        @Override
        public void mouseEntered(MouseEvent e) {
          // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
          // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {
          // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
          // TODO Auto-generated method stub

        }

      });


    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }


  }

  public void ver() {
    this.setVisible(false);
  }

  public void deleteUser() {
    try {
      Connection conn = DBConnect.getConnection();
      Statement st = conn.createStatement();

      // update
      if (p.u.theId == -1) {
        JOptionPane.showMessageDialog(this, "Please Select an user from the table");

      } else {
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(
          this, "Are you sure you want to Delete this User?"
          + "",
          "Logout",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);
        
          if (n == 0) {
            st.execute("DELETE FROM `users` WHERE id = '" + p.u.theId + "';");
            JOptionPane.showMessageDialog(this, "User successfully Deleated");
            p.dispose();
            new Admin(p.cabecera);
          }
      }

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

  public String getValue(int numb) {
    int row = table.getSelectedRow();
    int column = numb;
    String o = (String) table.getModel().getValueAt(row, column);
    return o;
  }
}
