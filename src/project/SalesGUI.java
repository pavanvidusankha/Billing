package project;


import dbpath.db;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.awt.print.PrinterException;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import project.messageBox;
import static sun.security.krb5.Confounder.bytes;


public class SalesGUI extends javax.swing.JFrame {

    int x, y, maxi = 0, usr = 1, pwd = 1, xx, yy, phone, land, empid, loanid;
    String txtu = "", txtp = "", path = null, savePath = null, empType, sDate, eDate, savePath1 = null;
    ImageIcon imageIcon;
    BufferedImage imge;
    File savefile;

    public SalesGUI() {

        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 0));
        setLocationRelativeTo(null);

        initComponents();

        jPanel1.setBackground(new Color(0, 255, 0, 0));

        jpanelVisible();

        home.setVisible(true);

        lgtpnl.setVisible(false);
        cheqTable.setAutoCreateRowSorter(true);
    }

    public void autoGenarate() {
        DefaultTableModel model = (DefaultTableModel) cheqTable.getModel();
        int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con;
            ResultSet rs;

            con = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

            Statement st = con.createStatement();

            String sql = "SELECT * FROM CHEQPAYMENT";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String custName = rs.getString("CUSTNAME");
                String bank = rs.getString("BANK");
                String cheqNo = rs.getString("CHEQNO");
                String cheqDate = rs.getString("CHEQDATE");
                model.addRow(new Object[]{custName, cheqNo, bank, cheqDate});
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SalesGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jpanelVisible() {

        //employee Jframe all disable panel in first time
        cheques.setVisible(false);

        SalesPanel.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        log = new javax.swing.JLabel();
        cheques = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cheqTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        print = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        SalesPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        SalesField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        MaxProduct = new javax.swing.JTextField();
        MaxProductAmount = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        LowestProjectField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        LowQuantityField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        MinProduct = new javax.swing.JTextField();
        MinProductValue = new javax.swing.JTextField();
        cal = new javax.swing.JPanel();
        loanD = new javax.swing.JPanel();
        attendance = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        addEmp = new javax.swing.JPanel();
        emp = new javax.swing.JPanel();
        type = new javax.swing.JPanel();
        lgtpnl = new javax.swing.JPanel();
        piclogot = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        slct = new javax.swing.JLabel();
        slcthome = new javax.swing.JLabel();
        slctemp = new javax.swing.JLabel();
        sclType = new javax.swing.JLabel();
        sales = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        slctCal = new javax.swing.JLabel();
        slctAtten = new javax.swing.JLabel();
        slctLoan = new javax.swing.JLabel();
        SalesInD = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mini = new javax.swing.JLabel();
        max = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(null);

        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jPanel1.setMinimumSize(new java.awt.Dimension(882, 662));
        jPanel1.setLayout(null);

        log.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        log.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        log.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                logAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        log.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                logFocusLost(evt);
            }
        });
        log.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logMouseExited(evt);
            }
        });
        jPanel1.add(log);
        log.setBounds(1160, 10, 20, 20);

        cheques.setBackground(new java.awt.Color(255, 255, 255));
        cheques.setLayout(null);

        cheqTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Cheque No", "Bank", "Return Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cheqTable);

        cheques.add(jScrollPane1);
        jScrollPane1.setBounds(60, 190, 770, 420);

        jButton1.setText("Validate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        cheques.add(jButton1);
        jButton1.setBounds(400, 660, 100, 40);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        cheques.add(jTextField1);
        jTextField1.setBounds(240, 120, 380, 30);

        print.setText("Print");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });
        cheques.add(print);
        print.setBounds(70, 660, 80, 30);

        jLabel17.setText("Search by using name,bank,cheque no or return date");
        cheques.add(jLabel17);
        jLabel17.setBounds(300, 90, 280, 20);

        jPanel1.add(cheques);
        cheques.setBounds(202, 43, 1075, 754);

        SalesPanel.setBackground(new java.awt.Color(255, 255, 255));
        SalesPanel.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Detail Report of Sales");
        SalesPanel.add(jLabel9);
        jLabel9.setBounds(310, 30, 160, 30);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal Sales Details"));

        jLabel10.setText("Total Sales by me       Rs.");

        SalesField.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(SalesField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(236, Short.MAX_VALUE))
        );

        SalesPanel.add(jPanel3);
        jPanel3.setBounds(50, 80, 320, 290);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Products Details"));

        jLabel11.setText("Most Sold Product");

        MaxProduct.setEditable(false);
        MaxProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaxProductActionPerformed(evt);
            }
        });

        MaxProductAmount.setEditable(false);

        jLabel13.setText("Low-on-Stock Product");

        LowestProjectField.setEditable(false);

        jLabel12.setText("Sold items ");

        jLabel14.setText("Product Quantity");

        LowQuantityField.setEditable(false);

        jLabel15.setText("Least Sold Product");

        jLabel16.setText("Sold Items");

        MinProduct.setEditable(false);

        MinProductValue.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MinProductValue, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(LowestProjectField)
                            .addComponent(LowQuantityField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MaxProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(MaxProductAmount)
                            .addComponent(MinProduct))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaxProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaxProductAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(MinProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(MinProductValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LowestProjectField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(LowQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        SalesPanel.add(jPanel4);
        jPanel4.setBounds(440, 80, 400, 250);

        jPanel1.add(SalesPanel);
        SalesPanel.setBounds(202, 43, 1075, 754);

        cal.setBackground(new java.awt.Color(255, 255, 255));
        cal.setLayout(null);
        jPanel1.add(cal);
        cal.setBounds(202, 43, 1075, 754);

        loanD.setBackground(new java.awt.Color(255, 255, 255));
        loanD.setLayout(null);
        jPanel1.add(loanD);
        loanD.setBounds(202, 43, 1075, 754);

        attendance.setBackground(new java.awt.Color(255, 255, 255));
        attendance.setLayout(null);
        jPanel1.add(attendance);
        attendance.setBounds(202, 43, 1075, 754);

        home.setBackground(new java.awt.Color(255, 255, 255));
        home.setLayout(null);
        jPanel1.add(home);
        home.setBounds(202, 43, 1075, 754);

        addEmp.setBackground(new java.awt.Color(255, 255, 255));
        addEmp.setLayout(null);
        jPanel1.add(addEmp);
        addEmp.setBounds(202, 43, 1075, 754);

        emp.setBackground(new java.awt.Color(255, 255, 255));
        emp.setLayout(null);
        jPanel1.add(emp);
        emp.setBounds(202, 43, 1075, 754);

        type.setBackground(new java.awt.Color(255, 255, 255));
        type.setLayout(null);
        jPanel1.add(type);
        type.setBounds(202, 43, 1075, 754);

        lgtpnl.setBackground(new java.awt.Color(0, 0, 0));
        lgtpnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lgtpnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lgtpnlMouseExited(evt);
            }
        });

        piclogot.setText("jLabel62");

        javax.swing.GroupLayout lgtpnlLayout = new javax.swing.GroupLayout(lgtpnl);
        lgtpnl.setLayout(lgtpnlLayout);
        lgtpnlLayout.setHorizontalGroup(
            lgtpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lgtpnlLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(piclogot, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        lgtpnlLayout.setVerticalGroup(
            lgtpnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lgtpnlLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(piclogot, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jPanel1.add(lgtpnl);
        lgtpnl.setBounds(1020, 10, 160, 220);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        slct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slctMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                slctMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                slctMouseExited(evt);
            }
        });
        jPanel2.add(slct);
        slct.setBounds(0, 40, 196, 30);

        slcthome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slcthomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                slcthomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                slcthomeMouseExited(evt);
            }
        });
        jPanel2.add(slcthome);
        slcthome.setBounds(0, 10, 196, 30);

        slctemp.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                slctempAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        slctemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slctempMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                slctempMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                slctempMouseExited(evt);
            }
        });
        jPanel2.add(slctemp);
        slctemp.setBounds(0, 70, 196, 30);

        sclType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sclTypeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sclTypeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sclTypeMouseExited(evt);
            }
        });
        jPanel2.add(sclType);
        sclType.setBounds(0, 100, 196, 30);

        sales.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                salesAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salesMouseExited(evt);
            }
        });
        jPanel2.add(sales);
        sales.setBounds(0, 220, 196, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow.png"))); // NOI18N
        jLabel8.setText("Sales in Details");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(20, 100, 160, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow.png"))); // NOI18N
        jLabel7.setText("View Cheques");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(20, 70, 160, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow.png"))); // NOI18N
        jLabel4.setText("Home");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 10, 160, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow.png"))); // NOI18N
        jLabel5.setText("Billing");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 40, 160, 30);

        slctCal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slctCalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                slctCalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                slctCalMouseExited(evt);
            }
        });
        jPanel2.add(slctCal);
        slctCal.setBounds(0, 130, 196, 30);

        slctAtten.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                slctAttenAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        slctAtten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slctAttenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                slctAttenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                slctAttenMouseExited(evt);
            }
        });
        jPanel2.add(slctAtten);
        slctAtten.setBounds(0, 160, 196, 30);

        slctLoan.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                slctLoanAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        slctLoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slctLoanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                slctLoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                slctLoanMouseExited(evt);
            }
        });
        jPanel2.add(slctLoan);
        slctLoan.setBounds(0, 190, 196, 30);

        SalesInD.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                SalesInDAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        SalesInD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalesInDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SalesInDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SalesInDMouseExited(evt);
            }
        });
        jPanel2.add(SalesInD);
        SalesInD.setBounds(0, 250, 196, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(3, 44, 196, 754);
        jPanel2.getAccessibleContext().setAccessibleName("");
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/line.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(200, 42, 1, 757);

        mini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mini.png"))); // NOI18N
        mini.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                miniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                miniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                miniMouseExited(evt);
            }
        });
        jPanel1.add(mini);
        mini.setBounds(1190, 10, 20, 20);

        max.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/max.png"))); // NOI18N
        max.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        max.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maxMouseExited(evt);
            }
        });
        jPanel1.add(max);
        max.setBounds(1220, 10, 20, 20);

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        close.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });
        jPanel1.add(close);
        close.setBounds(1250, 10, 20, 20);

        jLabel2.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel2MouseDragged(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        jLabel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jLabel2KeyTyped(evt);
            }
        });
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 1280, 42);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Billing & Sales");
        jLabel6.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        jPanel1.add(jLabel6);
        jLabel6.setBounds(569, 10, 150, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/framenew.png"))); // NOI18N
        jLabel1.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 800);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 800);

        setBounds(0, 0, 1296, 839);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged

        xx = evt.getXOnScreen();
        yy = evt.getYOnScreen();

        this.setLocation(xx - x, yy - y);

        if (maxi % 2 == 0) {
            setExtendedState(JFrame.NORMAL);
        }

    }//GEN-LAST:event_jLabel2MouseDragged

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed

        x = evt.getX();
        y = evt.getY();

    }//GEN-LAST:event_jLabel2MousePressed

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseClicked
        setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_miniMouseClicked

    private void maxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseClicked

        maxi++;

        if (maxi % 2 == 0) {
            setExtendedState(JFrame.NORMAL);
        } else if (maxi % 2 == 1) {
            jPanel1.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
            //jLabel1.setSize();
            setExtendedState(this.getExtendedState() | SalesGUI.MAXIMIZED_BOTH);

        }


    }//GEN-LAST:event_maxMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel2KeyTyped

    }//GEN-LAST:event_jLabel2KeyTyped

    private void maxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseEntered
        max.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/maxc.png")));
    }//GEN-LAST:event_maxMouseEntered

    private void miniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseEntered
        mini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minic.png")));
    }//GEN-LAST:event_miniMouseEntered

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/closec.png")));
    }//GEN-LAST:event_closeMouseEntered

    private void miniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miniMouseExited
        mini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mini.png")));
    }//GEN-LAST:event_miniMouseExited

    private void maxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseExited
        max.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/max.png")));
    }//GEN-LAST:event_maxMouseExited

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png")));
    }//GEN-LAST:event_closeMouseExited

    private void slctMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctMouseEntered
        slct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_slctMouseEntered

    private void slctMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctMouseExited
        slct.setIcon(null);
    }//GEN-LAST:event_slctMouseExited

    private void slcthomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slcthomeMouseEntered
        slcthome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_slcthomeMouseEntered

    private void slcthomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slcthomeMouseExited
        slcthome.setIcon(null);
    }//GEN-LAST:event_slcthomeMouseExited

    private void slctempMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctempMouseEntered
        slctemp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_slctempMouseEntered

    private void slctempMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctempMouseExited
        slctemp.setIcon(null);
    }//GEN-LAST:event_slctempMouseExited

    private void sclTypeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sclTypeMouseEntered
        sclType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_sclTypeMouseEntered

    private void sclTypeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sclTypeMouseExited
        sclType.setIcon(null);
    }//GEN-LAST:event_sclTypeMouseExited

    private void slcthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slcthomeMouseClicked
        jpanelVisible();
        home.setVisible(true);
    }//GEN-LAST:event_slcthomeMouseClicked

    private void slctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctMouseClicked
        jpanelVisible();
        addEmp.setVisible(true);
        try {
            MainGUI frame = new MainGUI();
            frame.setVisible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_slctMouseClicked

    private void slctempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctempMouseClicked
        jpanelVisible();
        cheques.setVisible(true);
        slctemp.setEnabled(false);
        autoGenarate();


    }//GEN-LAST:event_slctempMouseClicked

    public void retriveInfo() {

       
        //setting sales by cashier
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn;
//                
            conn = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());
            double price = 0;
//PreparedStatement ps=conn.prepareStatement("Select DateAdd(d,DateDiff(dd,0,[BDate])-DatePart(dd,[BDate])+1,0), Sum( TOTPRICE )as SUM1 From Bill Group By DateAdd(dd,DateDiff(dd,0,[BDate])-DatePart(dd,[BDate])+1,0)");
            //PreparedStatement ps=conn.prepareStatement("SELECT SUM(TOTPRICE) AS SUM1 FROM Bill WHERE DATEPART(mm, BDATE) = DATEPART(m, DATEADD(mm, -1, getdate())) AND DATEPART(yyyy, BDATE) = DATEPART(yyyy, DATEADD(mm, -1, getdate())) AND USER_ID=1");
            //PreparedStatement ps = conn.prepareStatement("SELECT SUM(TOTPRICE) AS SUM1 FROM BILL WHERE USER_ID=1");
             PreparedStatement ps=conn.prepareStatement("SELECT YEAR(bdate) as SalesYear, MONTH(bdate) as SalesMonth ,SUM(totPrice) AS TotalSales FROM bill WHERE USER_ID=1 GROUP BY YEAR(bdate), MONTH(bdate) ORDER BY YEAR(bdate), MONTH(bdate)");
            
            
            
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                price = Double.parseDouble(rs.getString("TotalSales"));
                DecimalFormat dc = new DecimalFormat("0.00");
                String formattedText = dc.format(price);
                SalesField.setText(formattedText);

            }
            //Setting max sales product
            
            PreparedStatement ps1 = conn.prepareStatement("SELECT YEAR(bdate) as SalesYear, MONTH(bdate) as SalesMonth ,PRODUCTS.NAME,BILL_ITEMS.ITEM_ID,SUM(BILL_ITEMS.QUANTITY) AS COUNT FROM BILL_ITEMS ,PRODUCTS WHERE PRODUCTS.ID=BILL_ITEMS.ITEM_ID GROUP BY BILL_ITEMS.ITEM_ID,PRODUCTS.ID,PRODUCTS.NAME,YEAR(bdate), MONTH(bdate) ORDER BY YEAR(bdate), MONTH(bdate),COUNT");
            //PreparedStatement ps1 = conn.prepareStatement("SELECT PRODUCTS.\"NAME\",BILL_ITEMS.ITEM_ID,SUM(BILL_ITEMS.QUANTITY) AS COUNT FROM BILL_ITEMS ,PRODUCTS WHERE PRODUCTS.ID=BILL_ITEMS.ITEM_ID GROUP BY BILL_ITEMS.ITEM_ID,PRODUCTS.ID,PRODUCTS.\"NAME\"\n");
            ResultSet rs1 = ps1.executeQuery();
            //Setting min salesproduct
            if (rs1.next()) {

                MinProduct.setText(rs1.getString("NAME"));
                MinProductValue.setText(rs1.getString("COUNT"));

            }
            
            while (rs1.next()) {

                MaxProduct.setText(rs1.getString("NAME"));
                MaxProductAmount.setText(rs1.getString("COUNT"));

            }
          

            //Setting low stock product
            PreparedStatement ps2 = conn.prepareStatement("SELECT NAME,QUANTITY FROM PRODUCTS ORDER BY QUANTITY ");
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {

                LowestProjectField.setText(rs2.getString("NAME"));
                LowQuantityField.setText(rs2.getString("QUANTITY"));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalesGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SalesGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void sclTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sclTypeMouseClicked
        jpanelVisible();

        SalesPanel.setVisible(true);
        retriveInfo();

    }//GEN-LAST:event_sclTypeMouseClicked

    private void slctempAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_slctempAncestorAdded

    }//GEN-LAST:event_slctempAncestorAdded

    private void logMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logMouseClicked
        lgtpnl.setVisible(true);
    }//GEN-LAST:event_logMouseClicked

    private void logMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logMouseEntered
        log.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout1.png")));
    }//GEN-LAST:event_logMouseEntered

    private void logMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logMouseExited
        log.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png")));
        lgtpnl.setVisible(false);
    }//GEN-LAST:event_logMouseExited

    private void logAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_logAncestorAdded

    }//GEN-LAST:event_logAncestorAdded

    private void logFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_logFocusLost

    }//GEN-LAST:event_logFocusLost

    private void lgtpnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgtpnlMouseEntered
        lgtpnl.setVisible(true);
    }//GEN-LAST:event_lgtpnlMouseEntered

    private void lgtpnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lgtpnlMouseExited
        lgtpnl.setVisible(false);
    }//GEN-LAST:event_lgtpnlMouseExited

    private void slctCalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctCalMouseClicked
        jpanelVisible();
        cal.setVisible(true);
    }//GEN-LAST:event_slctCalMouseClicked

    private void slctCalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctCalMouseEntered
        slctCal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_slctCalMouseEntered

    private void slctCalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctCalMouseExited
        slctCal.setIcon(null);
    }//GEN-LAST:event_slctCalMouseExited

    private void slctAttenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctAttenMouseClicked
        jpanelVisible();
        attendance.setVisible(true);
    }//GEN-LAST:event_slctAttenMouseClicked

    private void slctAttenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctAttenMouseEntered
        slctAtten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_slctAttenMouseEntered

    private void slctAttenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctAttenMouseExited
        slctAtten.setIcon(null);
    }//GEN-LAST:event_slctAttenMouseExited

    private void slctAttenAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_slctAttenAncestorAdded

    }//GEN-LAST:event_slctAttenAncestorAdded

    private void slctLoanAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_slctLoanAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_slctLoanAncestorAdded

    private void slctLoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctLoanMouseClicked
        jpanelVisible();

    }//GEN-LAST:event_slctLoanMouseClicked

    private void slctLoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctLoanMouseEntered
        slctLoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_slctLoanMouseEntered

    private void slctLoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slctLoanMouseExited
        slctLoan.setIcon(null);
    }//GEN-LAST:event_slctLoanMouseExited

    private void salesAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_salesAncestorAdded

    }//GEN-LAST:event_salesAncestorAdded

    private void salesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesMouseClicked
        jpanelVisible();
        loanD.setVisible(true);

    }//GEN-LAST:event_salesMouseClicked

    private void salesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesMouseEntered
        sales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_salesMouseEntered

    private void salesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesMouseExited
        sales.setIcon(null);
    }//GEN-LAST:event_salesMouseExited

    private void SalesInDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesInDMouseExited
        SalesInD.setIcon(null);
    }//GEN-LAST:event_SalesInDMouseExited

    private void SalesInDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesInDMouseEntered
        SalesInD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/select.png")));
    }//GEN-LAST:event_SalesInDMouseEntered

    private void SalesInDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesInDMouseClicked
        jpanelVisible();
        SalesPanel.setVisible(true);
    }//GEN-LAST:event_SalesInDMouseClicked

    private void SalesInDAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_SalesInDAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_SalesInDAncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) cheqTable.getModel();

        int[] rows = cheqTable.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            String selected = model.getValueAt(rows[i], 1).toString();
            model.removeRow(rows[i] - i);

            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection conn;

                conn = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

                PreparedStatement ps = conn.prepareStatement("delete from CHEQPAYMENT where CHEQNO='" + selected + "' ");
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Succesfully Validated");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SalesGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SalesGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MaxProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaxProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaxProductActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        
        DefaultTableModel table=(DefaultTableModel)cheqTable.getModel();
        String search=jTextField1.getText().toLowerCase();
        TableRowSorter <DefaultTableModel> tr= new TableRowSorter <DefaultTableModel>(table);
        cheqTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
        
        
    }//GEN-LAST:event_jTextField1KeyPressed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
                 MessageFormat header = new MessageFormat("Print Report");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
    try {
        cheqTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
    } catch (java.awt.print.PrinterAbortException e) {
    }    catch (PrinterException ex) {
            Logger.getLogger(SalesGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_printActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesGUI().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField LowQuantityField;
    private javax.swing.JTextField LowestProjectField;
    private javax.swing.JTextField MaxProduct;
    private javax.swing.JTextField MaxProductAmount;
    private javax.swing.JTextField MinProduct;
    private javax.swing.JTextField MinProductValue;
    private javax.swing.JTextField SalesField;
    private javax.swing.JLabel SalesInD;
    private javax.swing.JPanel SalesPanel;
    private javax.swing.JPanel addEmp;
    private javax.swing.JPanel attendance;
    private javax.swing.JPanel cal;
    private javax.swing.JTable cheqTable;
    private javax.swing.JPanel cheques;
    private javax.swing.JLabel close;
    private javax.swing.JPanel emp;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel lgtpnl;
    private javax.swing.JPanel loanD;
    private javax.swing.JLabel log;
    private javax.swing.JLabel max;
    private javax.swing.JLabel mini;
    private javax.swing.JLabel piclogot;
    private javax.swing.JButton print;
    private javax.swing.JLabel sales;
    private javax.swing.JLabel sclType;
    private javax.swing.JLabel slct;
    private javax.swing.JLabel slctAtten;
    private javax.swing.JLabel slctCal;
    private javax.swing.JLabel slctLoan;
    private javax.swing.JLabel slctemp;
    private javax.swing.JLabel slcthome;
    private javax.swing.JPanel type;
    // End of variables declaration//GEN-END:variables

    private Date dateFormat(java.util.Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
    
    

