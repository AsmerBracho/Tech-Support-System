package manager;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Manager extends JFrame {
  
  private String cabecera ="";
  Toolbar toolbar = null;
  TicketsInfo t = null; 
  Chart chart = null; 
  
  public Manager(String n) {
    super("Manager");
    cabecera = n;
    this.setSize(1200,750);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());
    
    
    toolbar = new Toolbar(this);
    t = new TicketsInfo(this);
    
    chart = new Chart("JFreeChart: PopulationChartDemo1.java");
    
    
    this.add(toolbar, BorderLayout.NORTH);
    this.add(t,BorderLayout.CENTER);
    this.add(chart, BorderLayout.SOUTH);
  }
  
  
  public String getCabecera() {
    return cabecera;
  }

  public void setCabecera(String cabecera) {
    this.cabecera = cabecera;
  }
}
