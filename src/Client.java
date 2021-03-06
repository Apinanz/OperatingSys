
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.*;


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
            fileName = new Object[file][4];
            for (int i = 0; i < file; i++) {
                fileName[i][0] = din.readUTF();
            }
            for (int i = 0; i < file; i++) {
                fileName[i][1] = din.readUTF(); // ชนิดข้อมูลไฟล์
            }
            for (int i = 0; i < file; i++) {
                fileName[i][2] = din.readInt()/1024+1; // ขนาดไฟล์
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



