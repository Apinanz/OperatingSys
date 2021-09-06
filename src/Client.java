import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client {
    public static final int PORT = 8087;

    public static void main(String[] args) {

        JFrame frameLog = new JFrame();
        frameLog.setTitle("Client");
        frameLog.setSize(500, 800);
        frameLog.setVisible(true);
        // connect to server
        try {

            Socket clientSocket = new Socket("localhost", 8087);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can't Connected. Please Try Again!", "ERROR CONNECTED",
                    JOptionPane.ERROR_MESSAGE);

        }

    }
}
