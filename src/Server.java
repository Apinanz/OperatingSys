
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class Server {
    public static final int PORT = 8087;

    public static void main(String[] args) throws IOException {
        Client client = new Client();

        // Reader name file
        String path = "C:/Users/api_q/OneDrive/เดสก์ท็อป/OperatingSysProj/OperatingSysProj/file.txt";
        File file = new File(path);

        ArrayList<String> arrFile = new ArrayList<String>();
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(file));
            String fileLoader;
            while ((fileLoader = bufReader.readLine()) != null) {
                arrFile.add(fileLoader);
            }

        } catch (IOException e) {
            System.out.println("file not found");
        }
        int index = 0;
        String[][] arr = new String[arrFile.size()][2];
        String[] col = { "ไฟล์ทั้งหมด", "ขนาด" };
        for (String object : arrFile) {
            arr[index++][0] = object;
        }

        // frame Main Server
        JFrame frameMain = new JFrame();
        frameMain.setSize(500, 800);
        frameMain.setTitle("Welcome to Server");
        frameMain.setVisible(true);

        JPanel jpMain = new JPanel();
        jpMain.setLayout(new CardLayout());

        // ส่วนของหน้าmain ปุ่ม File
        JPanel jpBut = new JPanel();
        JButton butFile = new JButton("File");
        butFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Frame Server
                JFrame frameServer = new JFrame();
                frameServer.setSize(500, 800);
                frameServer.setTitle("Server");
                frameServer.setLayout(new BoxLayout(frameServer.getContentPane(), BoxLayout.Y_AXIS));
                frameServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameServer.setVisible(true);
                frameMain.setVisible(false);

                // ตารางชื่อไฟล์และขนาด
                JPanel jpFile = new JPanel();
                JTable jtFile = new JTable(arr, col);
                JScrollPane jspFile = new JScrollPane(jtFile);
                jpFile.setBounds(0, 0, 500, 400);
                jpFile.add(jspFile);
                frameServer.getContentPane().add(jpFile);

                JPanel jpBack = new JPanel();
                JButton butBack = new JButton("Back");
                butBack.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frameServer.setVisible(false);
                        frameMain.setVisible(true);
                    }

                });
                jpBack.add(butBack);
                frameServer.add(jpBack);
            }

        });
        jpBut.add(butFile);

        // ส่วนของหน้าmain ปุ่ม log
        JButton butLog = new JButton("Log");
        jpBut.add(butLog);
        butLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Jframe
                JFrame frameLog = new JFrame();
                frameLog.setLayout(new BoxLayout(frameLog.getContentPane(), BoxLayout.Y_AXIS));
                frameLog.setTitle("Log");
                frameLog.setSize(500, 800);

                // Panel and Lable
                JPanel jpLog = new JPanel();
                JLabel jlLog = new JLabel();
                jlLog.setText("Waiting to connecting from Client " + client.PORT);

                jlLog.setText("Connecting from Client " + client.PORT);
                jpLog.add(jlLog);
                System.out.println("connect");

                frameLog.add(jpLog);
                frameLog.setVisible(true);
                frameMain.setVisible(false);

                JPanel jpBack = new JPanel();
                JButton butBack = new JButton("Back");
                butBack.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frameLog.setVisible(false);
                        frameMain.setVisible(true);
                    }

                });
                jpBack.add(butBack);
                frameLog.add(jpBack);

            }

        });

        jpMain.add(jpBut, "Panel 2");
        frameMain.getContentPane().add(jpMain, BorderLayout.CENTER);

        // ส่วนของ connecting

        ServerSocket serverSocket = new ServerSocket(8087);
        try {
            Socket clientSocket = serverSocket.accept();

        } catch (Exception e) {
            System.out.println("0");
        }

    }
}
