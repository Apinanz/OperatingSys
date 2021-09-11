
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
<<<<<<< HEAD
    public static final int PORT = 8087;
    File[] fileName;
    public static void main(String[] args) throws IOException {
       new Server().Model();
    }

    private void Model() throws IOException {
        // Reader name file
        // String path = "C:/Users/api_q/OneDrive/เดสก์ท็อป/OperatingProj";
        String path = "C:/Users/tubti/OneDrive - Silpakorn University/Documents/Thread/server/";
        File file = new File(path);
        fileName = file.listFiles();
        String[][] arr = new String[fileName.length][2];
        for (int i = 0; i < fileName.length; i++) {
            arr[i][0] = fileName[i].getName();
            arr[i][1] = fileName[i].length()/1024+1 +" "+"KB";
        }
        
        String[] col = { "ไฟล์ทั้งหมด", "ขนาด" };

        // frame Main Server
        JFrame frameMain = new JFrame();
        frameMain.setSize(500, 800);
        frameMain.setTitle("Welcome to Server");
        frameMain.setVisible(true);

        JPanel jpMain = new JPanel();
//        jpMain.setLayout(new CardLayout());

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
=======
>>>>>>> bffa9baf73afd500b850d25478b34e95bfd67c5c

    ServerSocket socketServer;
    Socket socketClient;
    final int PORT = 8080;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {

        try {
            socketServer = new ServerSocket(PORT);
            ServerSocket serverSocket = new ServerSocket(8087);
            while (true) {
                System.out.println("Waiting Connecting from client : " + PORT);
                socketClient = socketServer.accept();
                new HandleClient(socketClient, serverSocket).start();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}

<<<<<<< HEAD
        });

        jpMain.add(jpBut, "Panel 2");
        frameMain.getContentPane().add(jpMain, BorderLayout.NORTH);
=======
class HandleClient extends Thread {
>>>>>>> bffa9baf73afd500b850d25478b34e95bfd67c5c

    Server server;
    ServerSocket socketServer;
    Socket socketClient;
    DataInputStream din;
    DataOutputStream dout;
    String path = "C:/Users/tubti/OneDrive - Silpakorn University/Documents/Thread/server/";
    File file = new File(path);
    File[] fileName;

    public HandleClient(Socket socket, ServerSocket serverSocket) {
        this.socketClient = socket;
        this.socketServer = serverSocket;
    }

    public void sendNameAllFileToClient() {
        try {
            fileName = file.listFiles();
            din = new DataInputStream(socketClient.getInputStream());
            dout = new DataOutputStream(socketClient.getOutputStream());
            dout.writeInt(fileName.length);
            for (File f : fileName) {
                dout.writeUTF(f.getName());
            }
            for (File f : fileName) {
                dout.writeUTF("" + Files.probeContentType(f.toPath())); // ชนิดข้อมูลไฟล์
            }
            for (File f : fileName) {
                long tem = f.length()/1024+1;
                dout.writeUTF("" + tem); // ขนาดไฟล์
            }
            sendFileReqToClient();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void sendFileReqToClient() throws IOException {

        String reqFile;
        try {
            din = new DataInputStream(socketClient.getInputStream());
            dout = new DataOutputStream(socketClient.getOutputStream());

            while (true) {
                reqFile = din.readUTF();
                System.out.println("req file from client : " + reqFile);
                for (int i = 0; i < fileName.length; i++) {
                    if (reqFile.equals(fileName[i].getName())) {
                        File file = fileName[i];
                        dout.writeInt((int) file.length());
                        int sizeFile = (int) file.length() / 10;
                        for (int start = 0; start < 10; start++) {
                            System.out.println("index : " + start);
                            Socket socket = socketServer.accept();
                            int s = start;
                            int fileLength = start == 9 ? (int) file.length() - (sizeFile * 9) : sizeFile;
                            int indexStart = s * sizeFile;
                            new Thread(() -> {
                                try {
                                    DataOutputStream doutClient = new DataOutputStream(socket.getOutputStream());
                                    DataInputStream dinClient = new DataInputStream(new FileInputStream(file.getAbsolutePath()));
                                    doutClient.writeInt(indexStart);
                                    doutClient.writeInt(fileLength);
                                    byte[] dataPatial = new byte[fileLength];

                                    System.out.println("Start : " + indexStart);
                                    System.out.println("File : " + fileLength);

                                    System.out.println(Thread.currentThread().getName() + " start :" + indexStart + "end : " + (indexStart + fileLength) + " flieLength :" + fileLength);

                                    dinClient.skip(indexStart);
                                    dinClient.read(dataPatial);
                                    System.out.println(Thread.currentThread().getName() + " skiped ");
                                    int send = 0;

                                    doutClient.write(dataPatial);

                                    System.out.println("finish");
                                    doutClient.close();
                                    dinClient.close();
                                    socket.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println(ex);
                                }

                            }).start();
                        }
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Not Send");
        }

    }

    public void run() {
        sendNameAllFileToClient();
    }
}
