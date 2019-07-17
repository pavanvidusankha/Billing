package project2;



//reports
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//
import model.Bill;
import model.BillItemsPK;
import model.Cashpayment;
import model.Cheqpayment;
import dbpath.db;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;

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
import javax.swing.DefaultComboBoxModel;
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.util.Date;

import project.messageBox;
import static sun.security.krb5.Confounder.bytes;

public class PayGUI extends javax.swing.JFrame {

    ArrayList products = new ArrayList<>();
    int productsQ[] = new int[1000];
    int x, y, maxi = 0, usr = 1, pwd = 1, xx, yy, phone, land, empid, loanid;
    String txtu = "", txtp = "", path = null, savePath = null, empType, sDate, eDate, savePath1 = null;
    ImageIcon imageIcon;
    BufferedImage imge;
    File savefile;
    

    public PayGUI() throws ClassNotFoundException {

        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 0));
        setLocationRelativeTo(null);

        initComponents();

        jPanel1.setBackground(new Color(0, 255, 0, 0));

        SetValues();
        AutoGenerate();
        productdown();
        AutoCompleteDecorator.decorate(jComboBox1);
        ChReDate1.getJCalendar().setMinSelectableDate(new java.util.Date());
        ChqReDate1.getJCalendar().setMinSelectableDate(new java.util.Date());
        
        
         //demo      
        DemoButton1.setEnabled(false);
        DemoButton2.setEnabled(false);
        DemoButton3.setEnabled(false);

    }

    public void AutoGenerate() throws ClassNotFoundException {

        //get current date time with Date()
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateTextField.setText(dateFormat.format(date));
        PayButton.setEnabled(false);
        Connection con;

        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            con = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());
            //generating user details
            String Bry = "SELECT * FROM users where userid=1";
            Statement stt = con.createStatement();
            ResultSet rss = stt.executeQuery(Bry);

            while (rss.next()) {
                CashierField.setText(rss.getString("name"));
            }
            //Generating Bill details
            String Bqry = "SELECT count(ID) FROM Bill";
            Statement sttt = con.createStatement();
            ResultSet rsst = sttt.executeQuery(Bqry);
            int id = 0;
            while (rsst.next()) {
                id = rsst.getInt(1);

            }
            id++;
            BillIDTextField.setText(String.valueOf(id));

        } catch (SQLException ex) {
            Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void SetValues() {

        ChqNoField.setEnabled(false);
        ChReDate1.setEnabled(false);
        bankField.setEnabled(false);
        ChqAmountField.setEnabled(false);
        GmoneyField.setEnabled(false);
        BalField.setEnabled(false);
        CashAmount1.setEnabled(false);
        CqeAmount1.setEnabled(false);
        chqNoField1.setEnabled(false);
        ChqReDate1.setEnabled(false);
        BankField1.setEnabled(false);
    }

    public void StoreData() throws ParseException, ClassNotFoundException, SQLException {

        int uleft = 0, temp = 0;
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        //Time time=localDateFormat.format(new Date());
        // 1) create a java calendar instance
        Calendar calendar = Calendar.getInstance();

// 2) get a java.util.Date from the calendar instance.
//    this date will represent the current instant, or "now".
        java.util.Date now = calendar.getTime();

// 3) a java current time (now) instance
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        Bill bill = new Bill();

        bill.setId(Integer.parseInt(BillIDTextField.getText()));
        bill.setCname(CnameField.getText().toLowerCase());

        //User ID is not connected yet
        bill.setUserId(1);

        bill.setCphone(TelField.getText());
        bill.setDate(new java.util.Date());
        bill.setDiscount(Double.parseDouble(DiscountTextField.getText()));
        bill.setTotprice(Double.parseDouble(FinalPriceTextField.getText()));
        // bill((java.sql.Time)currentTimestamp);

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        java.util.Date date = new java.util.Date(stamp.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //java.util.Date date = new java.util.Date();
        bill.setDate(date);

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn;
            ResultSet rs;

            conn = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

            PreparedStatement stm;

            stm = conn.prepareStatement("INSERT INTO BILL (ID,TOTPRICE,DISCOUNT,USER_ID,CNAME,CPHONE,BDATE,BTIME) VALUES(?,?,?,?,?,?,?,?)");
            stm.setInt(1, bill.getId());
            stm.setDouble(2, bill.getTotprice());
            stm.setDouble(3, bill.getDiscount());
            stm.setInt(4, bill.getUserId());
            stm.setString(5, bill.getCname());
            stm.setString(6, bill.getCphone());
            stm.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
            //stm.setDate(7, (java.sql.Date) bill.getDate());
            stm.setTimestamp(8, currentTimestamp);
            stm.execute();
            conn.close();

            BillItemsPK BlItems = new BillItemsPK();
            DefaultTableModel dtm = (DefaultTableModel) ItemTable.getModel();
            int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
            Object[][] tableData = new Object[nRow][nCol];
            for (int i = 0; i < nRow; i++) {
                for (int j = 0; j < nCol; j++) {
                    tableData[i][j] = dtm.getValueAt(i, j);

                }

                BlItems.setBillId(bill.getId());
                BlItems.setItemId(Integer.valueOf(tableData[i][0].toString()));
                //int itemID=(Integer.valueOf((BlItems.getItemId())))-1;
                BlItems.setquantity(Integer.valueOf(tableData[i][3].toString()));
                //int uleft = Integer.parseInt(UniLeftField.getText()) - BlItems.getquantity();

                try {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                    Connection con1;

                    con1 = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

                    PreparedStatement stmk;
                    stmk = con1.prepareStatement("SELECT * FROM PRODUCTS WHERE ID= ?");
                    stmk.setInt(1, BlItems.getItemId());
                    rs = stmk.executeQuery();

                    while (rs.next()) {
                        temp = Integer.parseInt(rs.getString("QUANTITY"));
                        uleft = temp - BlItems.getquantity();
                    }

                    con1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                    Connection con;

                    con = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

                    PreparedStatement stm1;
                    stm1 = con.prepareStatement("INSERT INTO BILL_ITEMS (BILL_ID,ITEM_ID,QUANTITY,BDATE) VALUES(?,?,?,?)");
                    stm1.setInt(1, BlItems.getBillId());
                    stm1.setInt(2, BlItems.getItemId());
                    stm1.setInt(3, BlItems.getquantity());
                    stm1.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
                    stm1.execute();

                    //updating the inventory
//                    Statement st;
//                    String sql1="UPDATE `products` SET `quantity`=`quantity` - '"+BlItems.getquantity()+"' WHERE `id`='"+BlItems.getItemId()+"'";
//                    st=con.createStatement();  
//            
//                    st.executeUpdate(sql1);
                    PreparedStatement stmm1;
                    stmm1 = con.prepareStatement("UPDATE PRODUCTS SET QUANTITY = ? WHERE ID = ? ");
                    stmm1.setInt(1, uleft);
                    stmm1.setInt(2, BlItems.getItemId());
                    stmm1.executeUpdate();

                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "database connection failed");
            e.printStackTrace();
        }

        if (ChqAmountField.isEnabled() == true) {
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection con;
                PreparedStatement stm1;

                con = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());
                String Cqry = "SELECT MAX(PAYID) FROM CHEQPAYMENT";
                Statement stttt = con.createStatement();
                ResultSet rsstt = stttt.executeQuery(Cqry);
                int ccid = 0;
                while (rsstt.next()) {
                    ccid = rsstt.getInt(1);

                }
                ccid++;

                Cheqpayment ch = new Cheqpayment();
                ch.setAmount(Double.parseDouble(ChqAmountField.getText()));
                ch.setBank(bankField.getText());
                ch.setBillid(bill.getId());
                ch.setCheqno(ChqNoField.getText());
                ch.setCheqdate(DateTextField.getText());
                ch.setPayid(ccid);

                stm1 = con.prepareStatement("INSERT INTO CHEQPAYMENT (BILLID,BANK,AMOUNT,CHEQNO,CHEQDATE,CUSTNAME,STATUS,PAYID) VALUES(?,?,?,?,?,?,?,?)");
                stm1.setInt(1, ch.getBillid());
                stm1.setDouble(3, ch.getAmount());
                stm1.setString(2, ch.getBank());
                stm1.setString(4, ch.getCheqno());
                stm1.setString(5, ch.getCheqdate());
                stm1.setString(6, bill.getCname());
                stm1.setBoolean(7, false);
                stm1.setInt(8, ch.getPayid());
                stm1.execute();
                con.close();
                JOptionPane.showMessageDialog(this, "Record added Successfully ");
                generatePDF();
                newGenarate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (GmoneyField.isEnabled() == true) {

            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection con;

                con = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

                PreparedStatement stm1;

                String Bqry = "SELECT count(PAYID) FROM CASHPAYMENT";
                Statement sttt = con.createStatement();
                ResultSet rsst = sttt.executeQuery(Bqry);
                int id = 0;
                while (rsst.next()) {
                    id = rsst.getInt(1);

                }
                id++;

                Cashpayment cp = new Cashpayment();
                cp.setBillid(bill.getId());
                cp.setAmount(bill.getTotprice());
                cp.setGivenmoney(Double.parseDouble(GmoneyField.getText()));
                cp.setPayid(id);

                stm1 = con.prepareStatement("INSERT INTO CASHPAYMENT (PAYID,BILLID,GIVENMONEY,AMOUNT) VALUES(?,?,?,?)");
                stm1.setInt(1, cp.getPayid());
                stm1.setInt(2, cp.getBillid());
                stm1.setDouble(3, cp.getGivenmoney());
                stm1.setDouble(4, cp.getAmount());

                stm1.execute();
                con.close();
                JOptionPane.showMessageDialog(this, "Record added Successfully ");
                generatePDF();
                newGenarate();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (BankField1.isEnabled() == true) {

            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection con;

                con = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

                PreparedStatement stm1;
                String Bqry = "SELECT count(PAYID) FROM CASHPAYMENT";
                Statement sttt = con.createStatement();
                ResultSet rsst = sttt.executeQuery(Bqry);
                int cid = 0;
                while (rsst.next()) {
                    cid = rsst.getInt(1);

                }
                cid++;

                String Cqry = "SELECT MAX(PAYID) FROM CHEQPAYMENT";
                Statement stttt = con.createStatement();
                ResultSet rsstt = stttt.executeQuery(Cqry);
                int ccid = 0;
                while (rsstt.next()) {
                    ccid = rsstt.getInt(1);

                }
                ccid++;

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
                String date1 = dateFormat1.format(ChqReDate1.getDate());

                Cheqpayment ch = new Cheqpayment();
                ch.setAmount(Double.parseDouble(CqeAmount1.getText()));
                ch.setBank(BankField1.getText());
                ch.setBillid(bill.getId());
                ch.setCheqno(chqNoField1.getText());
                ch.setCheqdate(date1);
                ch.setPayid(ccid);

                Cashpayment cp = new Cashpayment();
                cp.setBillid(bill.getId());
                cp.setAmount(Double.parseDouble(CashAmount1.getText()));
                cp.setGivenmoney(Double.parseDouble(CashAmount1.getText()));
                cp.setPayid(cid);

                stm1 = con.prepareStatement("INSERT INTO CHEQPAYMENT (BILLID,BANK,AMOUNT,CHEQNO,CHEQDATE,PAYID,CUSTNAME,STATUS) VALUES(?,?,?,?,?,?,?,?)");
                stm1.setInt(1, ch.getBillid());
                stm1.setDouble(3, ch.getAmount());
                stm1.setString(2, ch.getBank());
                stm1.setString(4, ch.getCheqno());
                stm1.setString(5, ch.getCheqdate());
                stm1.setInt(6, ch.getPayid());
                stm1.setString(7, bill.getCname());
                stm1.setBoolean(8, false);
                stm1.execute();

                stm1 = con.prepareStatement("INSERT INTO CASHPAYMENT (BILLID,GIVENMONEY,AMOUNT,PAYID) VALUES(?,?,?,?)");
                stm1.setInt(1, cp.getBillid());
                stm1.setDouble(2, cp.getGivenmoney());
                stm1.setDouble(3, cp.getAmount());
                stm1.setInt(4, cp.getPayid());

                stm1.execute();
                JOptionPane.showMessageDialog(this, "Record added Successfully ");
                con.close();
                 generatePDF();
                newGenarate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void productdown() throws ClassNotFoundException {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn;
            ResultSet rs;

            conn = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

//            System.out.println("connected");
            PreparedStatement stm = conn.prepareStatement("Select * from products");
            rs = stm.executeQuery();
//            String sql = "Select * from products";
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                jComboBox1.addItem(rs.getString("ID"));
            }
        } catch (SQLException e) {
        }

    }

    public void newGenarate() throws ClassNotFoundException {
        jComboBox1.removeAllItems();

        //setting all values clear
        clearAll();
        CnameField.setText("");
        TelField.setText("");
        DiscountTextField.setText("0");
        QuantityTextField.setText("");
        productdown();
        jComboBox1.setSelectedIndex(0);
        ChqNoField.setText("");
        ChqNoField.setEnabled(false);
        ChReDate1.setDate(null);
        ChReDate1.setEnabled(false);
        bankField.setText("");
        bankField.setEnabled(false);
        ChqAmountField.setText("");
        ChqAmountField.setEnabled(false);
        GmoneyField.setEnabled(false);
        GmoneyField.setText("");
        BalField.setText("");
        CashAmount1.setText("");
                CashAmount1.setEnabled(false);

        CqeAmount1.setText("");
                CqeAmount1.setEnabled(false);

        chqNoField1.setText("");
                chqNoField1.setEnabled(false);

        ChqReDate1.setDate(null);
                ChqReDate1.setEnabled(false);

        BankField1.setText("");
                BankField1.setEnabled(false);


//demo
DemoButton1.setEnabled(false);
DemoButton2.setEnabled(false);
DemoButton3.setEnabled(false);
        //AutoCompleteDecorator.decorate(jComboBox1);
//Setting billID after one record was added
        Connection con;
        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String Bqry = "SELECT count(ID) FROM Bill";
            con = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());
            Statement sttt = con.createStatement();
            ResultSet rsst = sttt.executeQuery(Bqry);
            int id = 0;
            while (rsst.next()) {
                id = rsst.getInt(1);

            }
            id++;
            BillIDTextField.setText(String.valueOf(id));
        } catch (SQLException ex) {
            Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jpanelVisible() {

        //employee Jframe all disable panel in first time
    }

    public void updateTotal() {
        double sum = 0;
        for (int i = 0; i < ItemTable.getRowCount(); i++) {
            sum = sum + Double.parseDouble(ItemTable.getValueAt(i, 4).toString());
        }

        TotalPriceTextField.setText(Double.toString(sum));
        FinalPriceTextField.setText(Double.toString(sum));
    }

    public void SetDiscount() {
        try {

            double val = Double.parseDouble(TotalPriceTextField.getText());
            double dis = Double.parseDouble(DiscountTextField.getText());
            double finalp = val - val * dis / 100;
            FinalPriceTextField.setText(String.valueOf(finalp));
        } catch (NumberFormatException e) {

        }

    }

    public boolean isValid(JTextField jb) {
        try {
            Double.parseDouble(jb.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        log = new javax.swing.JLabel();
        mini = new javax.swing.JLabel();
        max = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BillPanel = new javax.swing.JPanel();
        CashierField = new javax.swing.JTextField();
        DateLabel = new javax.swing.JLabel();
        BillNoLabel = new javax.swing.JLabel();
        DateTextField = new javax.swing.JTextField();
        BillIDTextField = new javax.swing.JTextField();
        CashierLabel = new javax.swing.JLabel();
        CustomerPanel = new javax.swing.JPanel();
        CNameLabel = new javax.swing.JLabel();
        CnameField = new javax.swing.JTextField();
        TelLabel = new javax.swing.JLabel();
        TelField = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        NameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        QuantityTextField = new javax.swing.JFormattedTextField();
        UPriceField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        AddButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        UniLeftField = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ItemTable = new javax.swing.JTable();
        ClrAllButton = new javax.swing.JButton();
        ClrSelectedButton = new javax.swing.JButton();
        PayButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        TotalPriceTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        DiscountTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        FinalPriceTextField = new javax.swing.JTextField();
        BackButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        CashRadioButton = new javax.swing.JRadioButton();
        CCPanel = new javax.swing.JPanel();
        CashAmount1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        CqeAmount1 = new javax.swing.JTextField();
        BankField1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        chqNoField1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ChqReDate1 = new com.toedter.calendar.JDateChooser();
        ChqReDate = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        BalField = new javax.swing.JTextField();
        GmoneyField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ChequeRadioButton = new javax.swing.JRadioButton();
        ChequePanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        ChqAmountField = new javax.swing.JTextField();
        ChqNoField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        bankField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ChReDate1 = new com.toedter.calendar.JDateChooser();
        CashChequeRadioButton = new javax.swing.JRadioButton();
        DemoButton1 = new javax.swing.JButton();
        DemoButton2 = new javax.swing.JButton();
        DemoButton3 = new javax.swing.JButton();
        mainDemoButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lgtpnl = new javax.swing.JPanel();
        piclogot = new javax.swing.JLabel();

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
        log.setBounds(1150, 10, 20, 20);

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
        mini.setBounds(1180, 10, 20, 20);

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
        max.setBounds(1210, 10, 20, 20);

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
        close.setBounds(1240, 10, 20, 20);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Billing & Sales");
        jLabel6.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        jPanel1.add(jLabel6);
        jLabel6.setBounds(569, 10, 150, 20);

        BillPanel.setBackground(new java.awt.Color(255, 255, 255));
        BillPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Information"));

        CashierField.setEditable(false);
        CashierField.setBackground(new java.awt.Color(153, 255, 153));
        CashierField.setColumns(12);

        DateLabel.setText("Date");

        BillNoLabel.setText("Bill No");

        DateTextField.setEditable(false);
        DateTextField.setBackground(new java.awt.Color(153, 255, 153));
        DateTextField.setColumns(12);

        BillIDTextField.setEditable(false);
        BillIDTextField.setBackground(new java.awt.Color(153, 255, 153));
        BillIDTextField.setColumns(12);

        CashierLabel.setText("Cashier ");

        javax.swing.GroupLayout BillPanelLayout = new javax.swing.GroupLayout(BillPanel);
        BillPanel.setLayout(BillPanelLayout);
        BillPanelLayout.setHorizontalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BillNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CashierLabel))
                .addGap(30, 30, 30)
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CashierField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BillIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(DateLabel)
                .addGap(18, 18, 18)
                .addComponent(DateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        BillPanelLayout.setVerticalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BillNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateLabel)
                    .addComponent(DateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BillIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CashierLabel)
                    .addComponent(CashierField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(BillPanel);
        BillPanel.setBounds(10, 60, 600, 90);

        CustomerPanel.setBackground(new java.awt.Color(255, 255, 255));
        CustomerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Details"));

        CNameLabel.setText(" Name");

        CnameField.setBackground(new java.awt.Color(255, 255, 204));
        CnameField.setColumns(12);

        TelLabel.setText("Telephone");

        TelField.setBackground(new java.awt.Color(255, 255, 204));
        TelField.setColumns(12);
        try {
            TelField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TelField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CustomerPanelLayout = new javax.swing.GroupLayout(CustomerPanel);
        CustomerPanel.setLayout(CustomerPanelLayout);
        CustomerPanelLayout.setHorizontalGroup(
            CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CNameLabel)
                .addGap(39, 39, 39)
                .addComponent(CnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(TelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TelField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        CustomerPanelLayout.setVerticalGroup(
            CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CNameLabel)
                    .addComponent(CnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TelLabel)
                    .addComponent(TelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(CustomerPanel);
        CustomerPanel.setBounds(10, 160, 610, 65);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Details"));

        NameField.setEditable(false);
        NameField.setColumns(12);

        jLabel4.setText("Unit Price Rs.");

        jLabel3.setText("Name");

        jLabel2.setText("ID");

        QuantityTextField.setColumns(4);
        QuantityTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        QuantityTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                QuantityTextFieldPropertyChange(evt);
            }
        });

        UPriceField.setEditable(false);
        UPriceField.setColumns(6);

        jLabel5.setText("Quantity");

        AddButton.setText("Add");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Units Left");

        UniLeftField.setBackground(new java.awt.Color(240, 240, 240));
        UniLeftField.setColumns(5);
        UniLeftField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UniLeftFieldActionPerformed(evt);
            }
        });

        jComboBox1.setEditable(true);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(AddButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(QuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(UPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(UniLeftField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(UPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(UniLeftField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddButton))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 230, 690, 130);

        ItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Unit Price (Rs.)", "Quantity", "Total (Rs.)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ItemTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ItemTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(ItemTable);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 370, 690, 270);

        ClrAllButton.setText("Clear All");
        ClrAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClrAllButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ClrAllButton);
        ClrAllButton.setBounds(30, 650, 100, 23);

        ClrSelectedButton.setText("Clear Selected");
        ClrSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClrSelectedButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ClrSelectedButton);
        ClrSelectedButton.setBounds(140, 650, 130, 23);

        PayButton.setText("PAY ");
        PayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayButtonActionPerformed(evt);
            }
        });
        jPanel1.add(PayButton);
        PayButton.setBounds(340, 710, 90, 40);

        jLabel9.setText("Sub Total Rs.");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(490, 660, 90, 14);

        TotalPriceTextField.setEditable(false);
        TotalPriceTextField.setBackground(new java.awt.Color(153, 255, 51));
        TotalPriceTextField.setColumns(12);
        TotalPriceTextField.setToolTipText("");
        jPanel1.add(TotalPriceTextField);
        TotalPriceTextField.setBounds(590, 660, 102, 20);

        jLabel22.setText("Discount");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(490, 690, 90, 14);

        DiscountTextField.setBackground(new java.awt.Color(255, 255, 0));
        DiscountTextField.setText("0");
        DiscountTextField.setToolTipText("");
        DiscountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DiscountTextFieldKeyReleased(evt);
            }
        });
        jPanel1.add(DiscountTextField);
        DiscountTextField.setBounds(590, 690, 100, 20);

        jLabel10.setText("%");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(700, 690, 11, 14);

        jLabel8.setText("Total  Price Rs.");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(490, 720, 90, 14);

        FinalPriceTextField.setBackground(new java.awt.Color(255, 204, 0));
        FinalPriceTextField.setColumns(12);
        jPanel1.add(FinalPriceTextField);
        FinalPriceTextField.setBounds(590, 720, 102, 20);

        BackButton.setText("Return to Dashboard");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton);
        BackButton.setBounds(810, 640, 180, 30);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        CashRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(CashRadioButton);
        CashRadioButton.setText("Pay With Cash");
        CashRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CashRadioButtonMouseClicked(evt);
            }
        });
        CashRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CashRadioButtonActionPerformed(evt);
            }
        });

        CCPanel.setBackground(new java.awt.Color(255, 255, 255));
        CCPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cash/Cheque"));
        CCPanel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        CashAmount1.setColumns(12);
        CashAmount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CashAmount1KeyReleased(evt);
            }
        });

        jLabel20.setText("Bank");

        jLabel18.setText("Cheque Amount");

        CqeAmount1.setBackground(new java.awt.Color(240, 240, 240));
        CqeAmount1.setColumns(10);
        CqeAmount1.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        CqeAmount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CqeAmount1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CqeAmount1KeyTyped(evt);
            }
        });

        BankField1.setColumns(12);
        BankField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BankField1ActionPerformed(evt);
            }
        });

        jLabel17.setText("Cash Amount");

        chqNoField1.setColumns(12);

        jLabel21.setText("Cheque Number");

        jLabel19.setText("Cheque Return Date");

        ChqReDate1.setToolTipText("");
        ChqReDate1.setMinSelectableDate(null);

        javax.swing.GroupLayout CCPanelLayout = new javax.swing.GroupLayout(CCPanel);
        CCPanel.setLayout(CCPanelLayout);
        CCPanelLayout.setHorizontalGroup(
            CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCPanelLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CCPanelLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BankField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CCPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ChqReDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCPanelLayout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addGap(65, 65, 65)
                            .addComponent(chqNoField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CCPanelLayout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(65, 65, 65)
                                .addComponent(CqeAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CCPanelLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(80, 80, 80)
                                .addComponent(CashAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(101, 101, 101))
        );
        CCPanelLayout.setVerticalGroup(
            CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(CashAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(CqeAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chqNoField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ChqReDate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(BankField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        ChqReDate.setBackground(new java.awt.Color(255, 255, 255));
        ChqReDate.setBorder(javax.swing.BorderFactory.createTitledBorder("Cash"));
        ChqReDate.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel11.setText("Given Money   Rs.");

        BalField.setEditable(false);
        BalField.setColumns(12);
        BalField.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        GmoneyField.setColumns(12);
        GmoneyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GmoneyFieldActionPerformed(evt);
            }
        });
        GmoneyField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GmoneyFieldKeyReleased(evt);
            }
        });

        jLabel12.setText("Balance           Rs.");

        javax.swing.GroupLayout ChqReDateLayout = new javax.swing.GroupLayout(ChqReDate);
        ChqReDate.setLayout(ChqReDateLayout);
        ChqReDateLayout.setHorizontalGroup(
            ChqReDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChqReDateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChqReDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ChqReDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BalField, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(GmoneyField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ChqReDateLayout.setVerticalGroup(
            ChqReDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChqReDateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChqReDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GmoneyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ChqReDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(BalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        ChequeRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(ChequeRadioButton);
        ChequeRadioButton.setText("Pay with Cheque");
        ChequeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChequeRadioButtonActionPerformed(evt);
            }
        });

        ChequePanel.setBackground(new java.awt.Color(255, 255, 255));
        ChequePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cheque"));
        ChequePanel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel13.setText("Cheque Number");

        ChqAmountField.setEditable(false);
        ChqAmountField.setColumns(12);
        ChqAmountField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ChqAmountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChqAmountFieldActionPerformed(evt);
            }
        });
        ChqAmountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ChqAmountFieldKeyReleased(evt);
            }
        });

        ChqNoField.setColumns(12);

        jLabel14.setText("Bank");

        bankField.setColumns(12);

        jLabel15.setText("Cheque Return Date ");

        jLabel16.setText("Amount");

        ChReDate1.setToolTipText("");
        ChReDate1.setMinSelectableDate(null);

        javax.swing.GroupLayout ChequePanelLayout = new javax.swing.GroupLayout(ChequePanel);
        ChequePanel.setLayout(ChequePanelLayout);
        ChequePanelLayout.setHorizontalGroup(
            ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChequePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChequePanelLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(26, 26, 26))
                        .addGroup(ChequePanelLayout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(75, 75, 75)))
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ChReDate1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(ChqAmountField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(ChqNoField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(bankField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ChequePanelLayout.setVerticalGroup(
            ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChequePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(ChqAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(ChqNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ChReDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(ChequePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(46, 46, 46))
        );

        CashChequeRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(CashChequeRadioButton);
        CashChequeRadioButton.setText("Pay With Cash/Cheque");
        CashChequeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CashChequeRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ChequeRadioButton)
                            .addComponent(CashRadioButton)
                            .addComponent(CashChequeRadioButton)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ChqReDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChequePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(CCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CashRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ChequeRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CashChequeRadioButton)
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ChqReDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChequePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(CCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(710, 80, 520, 570);

        DemoButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        DemoButton1.setText("DEMO A");
        DemoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemoButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(DemoButton1);
        DemoButton1.setBounds(760, 690, 80, 30);

        DemoButton2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        DemoButton2.setText("DEMO B");
        DemoButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemoButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(DemoButton2);
        DemoButton2.setBounds(860, 690, 80, 30);

        DemoButton3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        DemoButton3.setText("DEMO C");
        DemoButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemoButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(DemoButton3);
        DemoButton3.setBounds(970, 690, 80, 30);

        mainDemoButton.setText("DEMO");
        mainDemoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainDemoButtonActionPerformed(evt);
            }
        });
        jPanel1.add(mainDemoButton);
        mainDemoButton.setBounds(60, 710, 80, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/framenew.png"))); // NOI18N
        jLabel1.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 800);

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

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 800);

        setBounds(0, 0, 1296, 839);
    }// </editor-fold>//GEN-END:initComponents
  public void clearAll() {
        DefaultTableModel model = (DefaultTableModel) ItemTable.getModel();
//clearing all table entries
        int rows = ItemTable.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        products.clear();
        
        buttonGroup1.clearSelection();
//        CashRadioButton.setSelected(false);
//        ChequeRadioButton.setSelected(false);
//        CashChequeRadioButton.setSelected(false);


        DiscountTextField.setText("");
        updateTotal();
        PayButton.setEnabled(false);
    }
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
            setExtendedState(this.getExtendedState() | PayGUI.MAXIMIZED_BOTH);

        }


    }//GEN-LAST:event_maxMouseClicked

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

    private void TelFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelFieldActionPerformed

    private void QuantityTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_QuantityTextFieldPropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_QuantityTextFieldPropertyChange

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed

        //Creating model
        DefaultComboBoxModel<String> Cmodel = (DefaultComboBoxModel<String>) jComboBox1.getModel();
        //validations
        if (products.contains(jComboBox1.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Selected products already included in the list");
        } else if (Cmodel.getIndexOf(jComboBox1.getSelectedItem()) == -1) {
            JOptionPane.showMessageDialog(jPanel1, "Wrong Product ID");

        } else if (QuantityTextField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Please Enter the Quantity");
        } //validating quantity with stored items
        else if (Double.parseDouble(QuantityTextField.getText()) > Double.parseDouble(UniLeftField.getText())) {
            JOptionPane.showMessageDialog(this, "Quantity is higher than the units left");
        } else {

            try {
                //calculate the units total price
                double TotalUnitPrice = Double.parseDouble(UPriceField.getText()) * Double.parseDouble(QuantityTextField.getText());

                String[] table = {jComboBox1.getSelectedItem().toString(), NameField.getText(), UPriceField.getText(), QuantityTextField.getText(), String.valueOf(TotalUnitPrice)};
                DefaultTableModel model = (DefaultTableModel) ItemTable.getModel();
                model.addRow(table);

                products.add(jComboBox1.getSelectedItem().toString());

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please Enter a Lower Number");
            }

            updateTotal();
        }
        PayButton.setEnabled(true);
        
        
            //demo      
       
       
        // TODO add your handling code here:
    }//GEN-LAST:event_AddButtonActionPerformed

    private void UniLeftFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UniLeftFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UniLeftFieldActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        try {
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection conn;
//                ResultSet rs;
                conn = DriverManager.getConnection(db.getPath(), db.getUser(), db.getPass());

                String sql = "Select * from products where id= ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, (String) jComboBox1.getSelectedItem());

                ResultSet rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {

                    UniLeftField.setText(rs.getString("quantity"));
                    //productsQ[i]=(Integer.valueOf(rs.getString("quantity")));

                    // productsQ.add(Integer.parseInt(rs.getString("quantity")));
                    NameField.setText(rs.getString("name"));
                    UPriceField.setText(rs.getString("uprice"));
                    i++;
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException e) {

        }
        
     
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1KeyReleased

    private void ItemTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ItemTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ItemTablePropertyChange

    private void ClrAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClrAllButtonActionPerformed

        clearAll();
        DiscountTextField.setText("0");

        // TODO add your handling code here:
    }//GEN-LAST:event_ClrAllButtonActionPerformed

    private void ClrSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClrSelectedButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) ItemTable.getModel();

        int[] rows = ItemTable.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            model.removeRow(rows[i] - i);
            products.remove(rows[i]);
        }
        if (ItemTable.getRowCount() == 0) {
            PayButton.setEnabled(false);
        }

        updateTotal();
        SetDiscount();

        // TODO add your handling code here:
    }//GEN-LAST:event_ClrSelectedButtonActionPerformed

    private void PayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayButtonActionPerformed

        //validating user inputs
        DefaultTableModel model = (DefaultTableModel) ItemTable.getModel();
        if (!isValid(DiscountTextField)) {
            JOptionPane.showMessageDialog(this, "Please Enter numeric value for discount");
        } else if (Double.parseDouble(FinalPriceTextField.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Final price is invalid.Please Check the discount value");
        } else if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please Add items to purchase ");
        } else if (CnameField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Customer Name");
        } else if (isValid(CnameField)) {
            JOptionPane.showMessageDialog(this, "Please Enter Customer Name only in letters");
        } else if (TelField.getText().matches("   -       ")) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Telephone number");
        } else if (CashChequeRadioButton.isSelected() == false && ChequeRadioButton.isSelected() == false && CashRadioButton.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Please Select a Payment Option");
        } else if (CashChequeRadioButton.isSelected()) {

            if (CashAmount1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter the cash amount correctly");
            } else if (chqNoField1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter the cheque number");
            } else if (BankField1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter the bank name");
            } else if (!isValid(CqeAmount1)) {
                JOptionPane.showMessageDialog(this, "Please Enter the cash amount correctly");
            } else if (!isValid(chqNoField1)) {
                JOptionPane.showMessageDialog(this, "Please Enter the Cheque No correctly in Numbers");
            } else if (!isValid(CashAmount1)) {
                JOptionPane.showMessageDialog(this, "Please Enter the cash amount correctly in Numbers");
            } else if (ChqReDate1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Please Enter the cheque date correctly");
            } 
              else {
                try {
                    StoreData();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (ChequeRadioButton.isSelected() == true) {
            if (ChqAmountField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Fill  all the cheque amount correctly");
            } else if (!isValid(ChqNoField)) {
                JOptionPane.showMessageDialog(this, "Please Fill  All the Cheque No in Numbers");
            } else if (ChReDate1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Please Fill  All the Cheque Date Correctly");
            } else if (ChqAmountField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Fill  All the Cheque Amount Correctly");
            } else if (ChqNoField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Fill  All The Cheque No Correctly");
            } else if (bankField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Fill  All The Bank Name");
            } else if (isValid(bankField)) {
                JOptionPane.showMessageDialog(this, "Please Fill  all the Bank Details in Letters");
            }
            else if (!isValid(ChqAmountField)) {
                JOptionPane.showMessageDialog(this, "Please Add items to the list and click  the desired payment button");
            }else {
                try {
                    StoreData();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (CashRadioButton.isSelected() == true) {
            if (GmoneyField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Fill  all the Payment details correctly");
            } else if (!isValid(BalField)) {
                JOptionPane.showMessageDialog(this, "Please enter the money value correctly");
            } else {
                try {
                    StoreData();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_PayButtonActionPerformed

    private void DiscountTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiscountTextFieldKeyReleased
   
    try{    
        
        if(Integer.parseInt(DiscountTextField.getText())>=100){
        JOptionPane.showMessageDialog(this,"Discount can't be higher than 99% ");
    }
            if(Integer.parseInt(DiscountTextField.getText())<=0){
        JOptionPane.showMessageDialog(this,"Discount can't be lower than 1% ");
    }
            else{ 
        SetDiscount();
            }
    }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Invalid number entered ");

    }
    }//GEN-LAST:event_DiscountTextFieldKeyReleased

    private void CashRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CashRadioButtonActionPerformed

        
         DefaultTableModel t=(DefaultTableModel)ItemTable.getModel();
             int number_of_rows = t.getRowCount();
             
             if(number_of_rows==0)
                    JOptionPane.showMessageDialog(this, "Please Add Items to the table");

             else{
        
        //enabling cash values
        GmoneyField.setEnabled(true);
        BalField.setEnabled(true);

        //disabling cheque values
        ChqAmountField.setEnabled(false);
        ChqNoField.setEnabled(false);
        ChReDate1.setEnabled(false);
        bankField.setEnabled(false);

        //disabling Cash/cheque values
        CashAmount1.setEnabled(false);
        CqeAmount1.setEnabled(false);
        chqNoField1.setEnabled(false);
        ChqReDate1.setEnabled(false);
        BankField1.setEnabled(false);
        
        //demo
         DemoButton1.setEnabled(true);
         DemoButton2.setEnabled(false);
         DemoButton3.setEnabled(false);

             }

    }//GEN-LAST:event_CashRadioButtonActionPerformed

    private void CashAmount1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CashAmount1KeyReleased
        // TODO add your handling code here:
        try {
            double fprice = Double.parseDouble(FinalPriceTextField.getText());
            double bal = Math.round(fprice - Double.parseDouble(CashAmount1.getText()));
            if (bal <= 0) {
                CqeAmount1.setText("Please Enter Less Value Than Total Price");
            } else {
                CqeAmount1.setText(String.valueOf(bal));
            }
        } catch (NumberFormatException e) {
            CqeAmount1.setText("Please enter value in numeric");
        }
    }//GEN-LAST:event_CashAmount1KeyReleased

    private void CqeAmount1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CqeAmount1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CqeAmount1KeyPressed

    private void CqeAmount1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CqeAmount1KeyTyped

        if (CashAmount1.equals(null)) {
            CqeAmount1.setText("Please Enter cash amount");
        } else {

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_CqeAmount1KeyTyped

    private void BankField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BankField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BankField1ActionPerformed

    private void GmoneyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GmoneyFieldActionPerformed

    }//GEN-LAST:event_GmoneyFieldActionPerformed

    private void GmoneyFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GmoneyFieldKeyReleased
        try {
            double fprice = Double.parseDouble(FinalPriceTextField.getText());
            double bal = Math.round(Double.parseDouble(GmoneyField.getText()) - fprice);
            if (bal < 0) {
                BalField.setText("Not Enough Money");
            } else {
                BalField.setText(String.valueOf(bal));
            }
        } catch (NumberFormatException e) {

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_GmoneyFieldKeyReleased

    private void ChequeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChequeRadioButtonActionPerformed

            DefaultTableModel t=(DefaultTableModel)ItemTable.getModel();
             int number_of_rows = t.getRowCount();
             
             if(number_of_rows==0)
                    JOptionPane.showMessageDialog(this, "Please Add Items to the table");

             else{



        //enabling cash values
        GmoneyField.setEnabled(false);
        BalField.setEnabled(false);

        //disabling cheque values
        ChqAmountField.setEnabled(true);
        ChqNoField.setEnabled(true);
        ChReDate1.setEnabled(true);
        bankField.setEnabled(true);
        
        if(FinalPriceTextField.getText().equals("")){
                    ChqAmountField.setText("no products in the list");

        }else
        ChqAmountField.setText(FinalPriceTextField.getText());

        //disabling Cash/cheque values
        CashAmount1.setEnabled(false);
        CqeAmount1.setEnabled(false);
        chqNoField1.setEnabled(false);
        ChqReDate1.setEnabled(false);
        BankField1.setEnabled(false);
        
        //demo
         DemoButton1.setEnabled(false);
         DemoButton2.setEnabled(true);
         DemoButton3.setEnabled(false);
             }
    }//GEN-LAST:event_ChequeRadioButtonActionPerformed

    private void ChqAmountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChqAmountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChqAmountFieldActionPerformed

    private void ChqAmountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChqAmountFieldKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_ChqAmountFieldKeyReleased

    private void CashChequeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CashChequeRadioButtonActionPerformed
  
        
        
        
             DefaultTableModel t=(DefaultTableModel)ItemTable.getModel();
             int number_of_rows = t.getRowCount();
             
             if(number_of_rows==0)
                    JOptionPane.showMessageDialog(this, "Please Add Items to the table");

             else{






        // TODO add your handling code here:
        GmoneyField.setEnabled(false);
        BalField.setEnabled(false);

        //disabling cheque values
        ChqAmountField.setEnabled(false);
        ChqNoField.setEnabled(false);
        ChReDate1.setEnabled(false);
        bankField.setEnabled(false);

        //disabling Cash/cheque values
        CashAmount1.setEnabled(true);
        CqeAmount1.setEnabled(false);
        chqNoField1.setEnabled(true);
        ChqReDate1.setEnabled(true);
        BankField1.setEnabled(true);
        
        //demo
         DemoButton1.setEnabled(false);
         DemoButton2.setEnabled(false);
         DemoButton3.setEnabled(true);
         
             }
    }//GEN-LAST:event_CashChequeRadioButtonActionPerformed

    private void DemoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemoButton1ActionPerformed
        
        
           
                

                CashRadioButton.setSelected(true);
                GmoneyField.setText("5000");

                QuantityTextField.setText("1");
                
                
            
            
           
                
          
       

    }//GEN-LAST:event_DemoButton1ActionPerformed

    private void DemoButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemoButton2ActionPerformed
                
                

                ChequeRadioButton.setSelected(true);
                ChqNoField.setText("537632");
                ChReDate1.setDate(new Date());
                bankField.setText("Peoples' Bank");

                QuantityTextField.setText("2");
                
                        // TODO add your handling code here:
    }//GEN-LAST:event_DemoButton2ActionPerformed

    private void DemoButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemoButton3ActionPerformed

                    
                

             
                CashAmount1.setText("100");
                chqNoField1.setText("496277");
                ChqReDate1.setDate(new Date());
                BankField1.setText("Commercial Bank");

                      // TODO add your handling code here:
    }//GEN-LAST:event_DemoButton3ActionPerformed

    private void mainDemoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainDemoButtonActionPerformed
                
                CnameField.setText("Sampath De Silva");
                TelField.setText("077-3271118");
                QuantityTextField.setText("3"); 

    }//GEN-LAST:event_mainDemoButtonActionPerformed

    private void CashRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CashRadioButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CashRadioButtonMouseClicked

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        SalesGUI frame = new SalesGUI(); // TODO add your handling code here:
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

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
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                try {
                    new PayGUI().setVisible(true);
                    
                   

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PayGUI.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField BalField;
    private javax.swing.JTextField BankField1;
    private javax.swing.JTextField BillIDTextField;
    private javax.swing.JLabel BillNoLabel;
    private javax.swing.JPanel BillPanel;
    private javax.swing.JPanel CCPanel;
    private javax.swing.JLabel CNameLabel;
    private javax.swing.JTextField CashAmount1;
    private javax.swing.JRadioButton CashChequeRadioButton;
    private javax.swing.JRadioButton CashRadioButton;
    private javax.swing.JTextField CashierField;
    private javax.swing.JLabel CashierLabel;
    private com.toedter.calendar.JDateChooser ChReDate1;
    private javax.swing.JPanel ChequePanel;
    private javax.swing.JRadioButton ChequeRadioButton;
    private javax.swing.JTextField ChqAmountField;
    private javax.swing.JTextField ChqNoField;
    private javax.swing.JPanel ChqReDate;
    private com.toedter.calendar.JDateChooser ChqReDate1;
    private javax.swing.JButton ClrAllButton;
    private javax.swing.JButton ClrSelectedButton;
    private javax.swing.JTextField CnameField;
    private javax.swing.JTextField CqeAmount1;
    private javax.swing.JPanel CustomerPanel;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JTextField DateTextField;
    private javax.swing.JButton DemoButton1;
    private javax.swing.JButton DemoButton2;
    private javax.swing.JButton DemoButton3;
    private javax.swing.JTextField DiscountTextField;
    private javax.swing.JTextField FinalPriceTextField;
    private javax.swing.JTextField GmoneyField;
    private javax.swing.JTable ItemTable;
    private javax.swing.JTextField NameField;
    private javax.swing.JButton PayButton;
    private javax.swing.JFormattedTextField QuantityTextField;
    private javax.swing.JFormattedTextField TelField;
    private javax.swing.JLabel TelLabel;
    private javax.swing.JTextField TotalPriceTextField;
    private javax.swing.JTextField UPriceField;
    private javax.swing.JTextField UniLeftField;
    private javax.swing.JTextField bankField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField chqNoField1;
    private javax.swing.JLabel close;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lgtpnl;
    private javax.swing.JLabel log;
    private javax.swing.JButton mainDemoButton;
    private javax.swing.JLabel max;
    private javax.swing.JLabel mini;
    private javax.swing.JLabel piclogot;
    // End of variables declaration//GEN-END:variables

   
    public void generatePDF() throws DocumentException {
    
   Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 19,
            Font.UNDERLINE|Font.BOLD ,BaseColor.GREEN);
   
    Font TFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD ,BaseColor.BLACK);
   
     Font EredFont = new Font(Font.FontFamily.TIMES_ROMAN, 15,
            Font.BOLD ,BaseColor.RED);
    
        try {
            Document doc =new Document();
            
             Date date=new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String titleDate=dateFormat.format(date);
     
         DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
         
            PdfWriter.getInstance(doc, new FileOutputStream("Bill-B"+BillIDTextField.getText()+".pdf"));
            doc.open();
            
            //adding table headers
            
                   Paragraph paragraph = new Paragraph("JITHUL GREEN COOL",redFont);
                paragraph.setAlignment(Element.ALIGN_CENTER);
               // paragraph.setFont(redFont);
               // paragraph.
                doc.add(paragraph);
                
               doc.add(new Phrase("\n"));
                
              doc.add(new Paragraph("Bill No       - B"+BillIDTextField.getText(),TFont)); 
              doc.add(new Paragraph(
                "Report generated by - " + CashierField.getText() + ", " + titleDate,TFont));
              doc.add(new Paragraph("Customer name - "+CnameField.getText(),TFont));
                           doc.add(new Phrase("\n"));

              if(BankField1.isEnabled()){
                doc.add(new Paragraph("Payment Mehod          - Cash & Cheque")); 
                doc.add(new Paragraph("Cash Amount              - Rs."+CashAmount1.getText()));
                doc.add(new Paragraph("Cheque  No                 - "+chqNoField1.getText())); 
                doc.add(new Paragraph("Cheque  Amount         - Rs."+CqeAmount1.getText())); 
                doc.add(new Paragraph("Cheque  Return Date   - "+dateFormat1.format(ChqReDate1.getDate()))); 
                doc.add(new Paragraph("Bank                            - "+BankField1.getText())); 

                

 
              }
                if(ChqAmountField.isEnabled()){
                doc.add(new Paragraph("Payment Mehod         - Cheque")); 
                doc.add(new Paragraph("Cheque  Amount        - Rs."+ChqAmountField.getText())); 
                doc.add(new Paragraph("Cheque  No               - "+ChqNoField.getText())); 
                doc.add(new Paragraph("Cheque  Return Date - "+dateFormat1.format(ChReDate1.getDate()))); 
                doc.add(new Paragraph("Bank                          - "+bankField.getText())); 

                
              }
                
                if(GmoneyField.isEnabled()){
                   doc.add(new Paragraph("Payment Mehod      - Cash"));   
                }
              
             doc.add(new Phrase("\n"));
            PdfPTable pdfTable = new PdfPTable(ItemTable.getColumnCount());
            
            for (int i = 0; i < ItemTable.getColumnCount(); i++) {
                PdfPCell cell3 = new PdfPCell(new Paragraph(ItemTable.getColumnName(i),TFont));
                cell3.setBorderColor(BaseColor.BLUE);
                pdfTable.addCell(cell3);
            }
            //extracting data from the JTable and inserting it to PdfPTable
            for (int rows = 0; rows < ItemTable.getRowCount() ; rows++) {
                for (int cols = 0; cols < ItemTable.getColumnCount(); cols++) {
                    pdfTable.addCell(ItemTable.getModel().getValueAt(rows, cols).toString());

                }
            }
            doc.add(pdfTable);
            doc.add(new Phrase("\n"));
           Paragraph totP= new Paragraph("                                                                                                              Sub Total      - Rs."+TotalPriceTextField.getText());
           doc.add(totP);
           
           Paragraph Dis= new Paragraph("                                                                                                              Discount        - "+DiscountTextField.getText()+"%");
           doc.add(Dis);
           
             Paragraph finalp= new Paragraph("                                                                                                              Final  Price   - Rs."+FinalPriceTextField.getText(),TFont);
            doc.add(finalp);
            
            
            if(GmoneyField.isEnabled()){
                   Paragraph gm= new Paragraph("                                                                                                              Given Money - Rs."+GmoneyField.getText());
                    doc.add(gm);
            
                Paragraph bal= new Paragraph("                                                                                                              Balance         - Rs."+BalField.getText());
                doc.add(bal);
            }
            doc.add(new Phrase("\n"));
              Paragraph eparagraph = new Paragraph("THANK YOU & COME AGAIN !!!",EredFont);
                eparagraph.setAlignment(Element.ALIGN_CENTER);
               // paragraph.setFont(redFont);
               // paragraph.
                doc.add(eparagraph);
                
            doc.close();
            System.out.println("generated pdf");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PayGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
}
