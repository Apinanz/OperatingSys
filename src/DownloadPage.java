import java.awt.*;

import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.*;

import javax.swing.table.TableModel;

import javax.swing.table.DefaultTableModel;

import javax.swing.table.*;

class DownloadPage extends JFrame

{
    Client client = new Client();

    private JPanel topPanel, topPanel1;

    private JTable table;

    private JScrollPane scrollPane, scrollPane1;

    private String[] columnNames = new String[2];

    private String[][] dataValues;

    JButton button = new JButton();

    public DownloadPage()

    {
        DefaultTableModel model = new DefaultTableModel();

        setTitle("Downloader");

        setSize(500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        topPanel = new JPanel();

        topPanel.setLayout(new BorderLayout());

        getContentPane().add(topPanel);

        columnNames = new String[] { "File", "File Type", "Size", "Download" };

        dataValues = new String[client.file][4];
        for (int i = 0; i < client.file; i++) {
            dataValues[i][0] = client.fileName[i][0].toString();
            dataValues[i][1] = client.fileName[i][1].toString().substring(client.fileName[i][1].toString().indexOf("/") + 1, client.fileName[i][1].toString().length());
            dataValues[i][2] = client.fileName[i][2].toString() + " " + "KB";
            dataValues[i][3] = client.fileName[i][0].toString();
        }
        model.setDataVector(dataValues, columnNames);
        // TableModel model = new myTableModel("owntable");

        table = new JTable(model);

        // table.setModel(model);

        table.getColumn("Download").setCellRenderer(new ButtonRenderer(dataValues));

        table.getColumn("Download").setCellEditor(new ButtonEditor(new JCheckBox()));

        scrollPane = new JScrollPane(table);

        topPanel.add(scrollPane, BorderLayout.CENTER);

        button.addActionListener(

                new ActionListener()

                {

                    public void actionPerformed(ActionEvent event)

                {

                        int temp = JOptionPane.showConfirmDialog(table, "Do you want to download " + button.getName() + "?");

                        if (temp == 0) {
                            try {
                                DataOutputStream dout = new DataOutputStream(client.clientSocket.getOutputStream());
                                DataInputStream din = new DataInputStream(client.clientSocket.getInputStream());
                                dout.writeUTF(button.getName());
                                // String filePath = "C:/Users/api_q/OneDrive/เดสก์ท็อป/CilentFile/"+button.getName();
                                String filePath = "C:/Users/tubti/OneDrive - Silpakorn University/Documents/Thread/Client/" + button.getName();
                                byte[] data = new byte[(int) din.readInt()];
                                din.readFully(data, 0, data.length);
                                File fileDownload = new File(filePath);
                                try {
                                    FileOutputStream fout = new FileOutputStream(fileDownload);
                                    fout.write(data);

                                } catch (Exception e) {
                                    // TODO: handle exception
                                }

                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                        }

                    }

                }

        );

    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer(String[][] dataValues) {

            setOpaque(true);

        }

        public Component getTableCellRendererComponent(JTable table, Object value,

                boolean isSelected, boolean hasFocus, int row, int column) {

            setText("Download");
            setName(value.toString());

            return this;

        }

    }

    class ButtonEditor extends DefaultCellEditor {

        private String label;

        public ButtonEditor(JCheckBox checkBox) {

            super(checkBox);

        }

        public Component getTableCellEditorComponent(JTable table, Object value,

                boolean isSelected, int row, int column) {

            label = "Download";

            button.setText(label);
            button.setName(value.toString());

            return button;

        }

        public Object getCellEditorValue() {

            return new String(label);

        }

    }

    public class myTableModel extends DefaultTableModel

    {

        String dat;

        JButton button = new JButton("");

        myTableModel(String tname)

        {

            super(dataValues, columnNames);

            dat = tname;

        }

        public boolean isCellEditable(int row, int cols)

        {

            if (dat == "owntable")

            {

                if (cols == 0) {
                    return false;
                }

            }

            return true;

        }

    }
}