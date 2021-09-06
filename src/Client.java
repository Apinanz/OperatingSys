import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client {
    public static final int PORT = 8087;
    Socket clientSocket;
    DataInputStream din;
    DataOutputStream dout;

    public static void main(String[] args) {
        new Client();
        JFrame frameLog = new JFrame();
        frameLog.setTitle("Client");
        frameLog.setSize(500, 800);
        frameLog.setVisible(true);

    }

    public Client() {
        // connect to server

        try {

            clientSocket = new Socket("localhost", 8087);
            din = new DataInputStream(clientSocket.getInputStream());
            dout = new DataOutputStream(clientSocket.getOutputStream());

            listenForInput();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can't Connected. Please Try Again!", "ERROR CONNECTED",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public void listenForInput() {
        while (true) {

        }
    }
}
