import javax.swing.SwingUtilities;

import login.Login;


public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              new Login();

            }
        });
        
    }

}
