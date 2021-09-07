
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.*;
import javax.swing.table.*;

public class Client {
    Socket clientSocket;
    DataInputStream din;
    DataOutputStream dout;
    Object[][] fileName;
    int file;
    public static void main(String[] args) {
        new Client().Model();

    }

    public Client() {
        // connect to server
        

        try {
            clientSocket = new Socket("localhost", 8087);
            din = new DataInputStream(clientSocket.getInputStream());
            dout = new DataOutputStream(clientSocket.getOutputStream());

            file = din.readInt();
            fileName = new Object[file][2];
            for (int i = 0; i < file; i++) {
                fileName[i][0] = din.readUTF();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can't Connected. Please Try Again!", "ERROR CONNECTED",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public void Model() {
        DownloadPage downloadPage = new DownloadPage();
        downloadPage.setVisible(true);

    }


}



