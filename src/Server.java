
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public static void main(String[] args) {

        //Reader name file
        StringBuilder sb = new StringBuilder();
        String path = "C:\\Users\\api_q\\OneDrive\\เดสก์ท็อป\\file.txt";
        File file = new File(path);

        ArrayList<String> arrFile = new ArrayList<String>();
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(file));
            String fileLoader;
            while ((fileLoader = bufReader.readLine()) != null) {
                arrFile.add(fileLoader);
                System.out.println(fileLoader);
            }

        } catch (IOException e) {
            System.out.println("file not found");
        }
        int index = 0;
        String[][] arr = new String[arrFile.size()][2];
        String[] col = {"ไฟล์ทั้งหมด","ขนาด"};
        for (String object : arrFile) {
            arr[index++][0] = object;
        }

        //Frame Server
        JFrame frameServer = new JFrame();
        frameServer.setSize(500, 800);
        frameServer.setTitle("Server");
        frameServer.setLayout(new BoxLayout(frameServer.getContentPane(), BoxLayout.Y_AXIS));
        frameServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameServer.setVisible(true);
        
        //menuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuLog = new JMenu("Log");
        menuBar.add(menuFile);
        menuBar.add(menuLog);
        frameServer.setJMenuBar(menuBar);
        
        //ตารางชื่อไฟล์และขนาด
        JPanel jpFile = new JPanel();
        JTable jtFile = new JTable(arr,col);
        JScrollPane jspFile = new JScrollPane(jtFile);
        jpFile.setBounds(0,0,500,400);
        jpFile.add(jspFile);
        frameServer.getContentPane().add(jpFile);
    }
}
