import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Client {
    public static final int port = 8080;
    public static void main(String[] args) {


        //connect to server
        try {
            Socket socketCilen = new Socket("locathost", port);
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Can't Connected. Please Try Again!","ERROR CONNECTED",JOptionPane.ERROR_MESSAGE);

        }

    }
}
