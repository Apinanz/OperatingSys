//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.FileOutputStream;
//import java.net.Socket;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import javax.swing.*;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTable;
//
//public class Client {
//    Socket clientSocket;
//    DataInputStream din;
//    DataOutputStream dout;
//    String[][] fileName;
//
//    public static void main(String[] args) {
//        new Client().Model();
//
//    }
//
//    public Client() {
//        // connect to server
//        int file;
//
//        try {
//            clientSocket = new Socket("localhost", 8087);
//            din = new DataInputStream(clientSocket.getInputStream());
//            dout = new DataOutputStream(clientSocket.getOutputStream());
//            
//
//            file = din.readInt();
//            fileName = new String[file][4];
//            for (int i = 0; i < file; i++) {
//                String n = din.readUTF();
//                fileName[i][0] = n; // ชื่อไฟล์
//            }
//            for (int i = 0; i < file; i++) {
//                fileName[i][1] = din.readUTF(); // ชนิดข้อมูลไฟล์
//            }
//            for (int i = 0; i < file; i++) {
//                String n = "" + din.readLong(); // ขนาดไฟล์
//                fileName[i][2] = n;
//            }
//            
//            
//
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Can't Connected. Please Try Again!", "ERROR CONNECTED",
//                    JOptionPane.ERROR_MESSAGE);
//
//        }
//    }
//
//    public void Model() {
//        JFrame frameFile = new JFrame();
//        frameFile.setTitle("Client");
//        frameFile.setSize(500, 800);
//        frameFile.setVisible(true);
//
//        String[] col = { "File", "File Type", "Size" ,"Download" };
////        String[] col = { "File", "Download" };
//        JPanel jpFile = new JPanel();
//        JTable jtFile = new JTable(fileName, col);
//        
//        JScrollPane jspFile = new JScrollPane(jtFile);
//        jpFile.setBounds(0, 0, 500, 400);
//        jpFile.add(jspFile);
//        frameFile.getContentPane().add(jpFile);
//        frameFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    }
//}



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
            fileName = new Object[file][4];
            for (int i = 0; i < file; i++) {
                fileName[i][0] = din.readUTF();
            }
            for (int i = 0; i < file; i++) {
                fileName[i][1] = din.readUTF(); // ชนิดข้อมูลไฟล์
            }
            for (int i = 0; i < file; i++) {
                String n = "" + din.readLong(); // ขนาดไฟล์
                fileName[i][2] = n;
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



