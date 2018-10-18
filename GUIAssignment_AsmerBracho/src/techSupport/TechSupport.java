package techSupport;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TechSupport extends JFrame {
  private String cabecera ="";
  TicketPanel tp = null;
  Toolbar toolbar = null;
  
  public TechSupport(String n) {
    super("TechSupport");
    cabecera = n;
    this.setSize(1200,650);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());
    
    toolbar = new Toolbar(this);
    tp = new TicketPanel(this);
    
    this.add(toolbar, BorderLayout.NORTH);
    this.add(tp, BorderLayout.CENTER);
  }

  public String getCabecera() {
    return cabecera;
  }

  public void setCabecera(String cabecera) {
    this.cabecera = cabecera;
  }
}
