
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Server {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        Client client = new Client();

        // Reader name file
        String path = "C:\\Users\\api_q\\OneDrive\\เดสก์ท็อป\\OperatingSysProj\\OperatingSysProj\\file.txt";
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

        // Frame Server
        JFrame frameServer = new JFrame();
        frameServer.setSize(500, 800);
        frameServer.setTitle("Server");
        frameServer.setLayout(new BoxLayout(frameServer.getContentPane(), BoxLayout.Y_AXIS));
        frameServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameServer.setVisible(true);

        // ตารางชื่อไฟล์และขนาด
        JPanel jpFile = new JPanel();
        JTable jtFile = new JTable(arr, col);
        JScrollPane jspFile = new JScrollPane(jtFile);
        jpFile.setBounds(0, 0, 500, 400);
        jpFile.add(jspFile);
        frameServer.getContentPane().add(jpFile);

        // ส่วนของ connecting
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            

            //Jframe
            JFrame frameLog = new JFrame();
            frameLog.setTitle("Log");
            frameLog.setSize(500, 800);

            //Panel and Lable
            JPanel jpLog = new JPanel();
            JLabel jlLog = new JLabel();
            jlLog.setText("Waiting to connecting from Client "+client.port);
            Socket  = serverSocket.accept();
            jlLog.setText("Connecting from Client "+client.port);
            jpLog.add(jlLog);
        } catch (Exception e) {
            System.out.println("0");
        }

    }
}
