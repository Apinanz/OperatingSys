
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

class DownloadPage extends JFrame {

    Client client = new Client();

    private JPanel topPanel, topPanel1;

    private JTable table;

    private JScrollPane scrollPane, scrollPane1;

    private String[] columnNames;

    private String[][] dataValues;

    JButton button = new JButton();

    public DownloadPage() {

        DefaultTableModel model = new DefaultTableModel();

        setTitle("Downloader");

        setSize(500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        topPanel = new JPanel();

        topPanel.setLayout(new BorderLayout());

        getContentPane().add(topPanel);

        columnNames = new String[]{"File", "File Type", "Size", "Download"};

        dataValues = new String[client.file][4];
        for (int i = 0; i < client.file; i++) {
            dataValues[i][0] = client.fileName[i][0].toString();
            dataValues[i][1] = client.fileName[i][1].toString();
            dataValues[i][2] = client.fileName[i][2].toString() + " KB";
            dataValues[i][3] = client.fileName[i][0].toString();
        }
        model.setDataVector(dataValues, columnNames);

        table = new JTable(model);

        table.getColumn("Download").setCellRenderer(new ButtonRenderer(dataValues));

        table.getColumn("Download").setCellEditor(new ButtonEditor(new JCheckBox()));

        scrollPane = new JScrollPane(table);

        topPanel.add(scrollPane, BorderLayout.CENTER);

        button.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                int temp = JOptionPane.showConfirmDialog(topPanel1, "Do you want to download "
                        + button.getName() + " ?", "Customized Dialog",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:/Users/tubti/OneDrive - Silpakorn University/Documents/Thread/among.png"));

                if (temp == 0) {
                    try {
                        
                        new BackgroundWorker().execute();

                    } catch (Exception e) {
                        //TODO: handle exception
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

    public class myTableModel extends DefaultTableModel {

        String dat;

        JButton button = new JButton("");

        myTableModel(String tname) {

            super(dataValues, columnNames);

            dat = tname;

        }

        public boolean isCellEditable(int row, int cols) {

            if (dat == "owntable") {

                if (cols == 0) {
                    return false;
                }

            }

            return true;

        }

    }

    public class BackgroundWorker extends SwingWorker<Void, Void> {

        private JProgressBar pb;
        private JDialog dialog;

        public BackgroundWorker() {

            addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress".equalsIgnoreCase(evt.getPropertyName())) {
                        if (dialog == null) {
                            dialog = new JDialog();
                            dialog.setTitle("Processing");
                            dialog.setLayout(new GridBagLayout());
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.insets = new Insets(2, 2, 2, 2);
                            gbc.weightx = 10;
                            gbc.gridy = 0;
                            dialog.add(new JLabel("Processing..."), gbc);
                            pb = new JProgressBar();
                            pb.setStringPainted(true);
                            gbc.gridy = 1;
                            dialog.add(pb, gbc);
                            dialog.pack();
                            dialog.setLocationRelativeTo(null);
                            dialog.setModal(true);
                            JDialog.setDefaultLookAndFeelDecorated(true);
                            dialog.setVisible(true);
                        }
                        pb.setValue(getProgress());
                    }
                }
            });
        }

        @Override
        protected void done() {
            if (dialog != null) {
                dialog.dispose();
            }
        }

        @Override
        protected Void doInBackground() throws Exception {

            DataOutputStream dout = new DataOutputStream(client.clientSocket.getOutputStream());
            DataInputStream din = new DataInputStream(client.clientSocket.getInputStream());
            dout.writeUTF(button.getName());
            String filePath = "C:/Users/tubti/OneDrive - Silpakorn University/Documents/Thread/Client/" + button.getName();
            byte[] data = new byte[(int) din.readInt()];

            File fileDownload = new File(filePath);
            FileOutputStream fout = new FileOutputStream(fileDownload);

            int count;

            long total = 0;

            while ((count = din.read(data)) != -1) {
                total += count;
                setProgress((int)((total*100)/data.length));
                fout.write(data, 0, count);
                if(total == data.length){
                    break;
                }
            }
            return null;
        }
    }
}
