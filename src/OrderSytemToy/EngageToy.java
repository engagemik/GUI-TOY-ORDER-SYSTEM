package OrderSytemToy;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class EngageToy extends javax.swing.JFrame {

    private boolean isSubmitted = false; // Flag to track submission
    private boolean isPaymentProcessed = false; // Flag to track if payment has been processed

    private DbConnectionTOY dbConnection;
    private JLabel dateLabel;
    private JLabel timeLabel;
    //private Timer timer;

    public EngageToy() {
        initComponents();
        dateLabel = new JLabel();
        timeLabel = new JLabel();
        dbConnection = new DbConnectionTOY(); // Establish connection here
        initializeDefaultReceipt();
        jAMOUNTCASHfield.setText("Php ");
        jTextAreaRESIBO.setEditable(false);
        jSUBTOTALfield.setText("");
        // Other initialization code
        setComponentProperties();
        setCurrentDate();
        startClock();

        jTxtNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                // Allow only digits (0-9) and prevent other characters
                if (!Character.isDigit(c)) {
                    evt.consume(); // Reject the non-digit character
                }
            }
        });

        jAMOUNTCASHfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!isPaymentProcessed) {
                        jPAYbtnActionPerformed(null); // Call the method to process payment if not already processed
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Payment has already been processed. Cannot process again.");
                    }
                }
            }
        });

    }

    private void setCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
        String formattedDate = formatter.format(currentDate);
        String formattedTime = timeFormatter.format(currentDate);

        jLCurrentTime.setText(formattedTime);
        jLCurrentDate.setText(formattedDate);

    }

    private void startClock() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
                String formattedTime = timeFormatter.format(new Date());
                jLCurrentTime.setText(formattedTime);
            }
        });
        timer.start();
    }

    //private void updateDateTime() {
    //    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    //    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    //    Date now = new Date();
    //    dateLabel.setText("Date: " + dateFormat.format(now));
    //    timeLabel.setText("Time: " + timeFormat.format(now));
    //}
    private void setComponentProperties() {

        jPRICEfield.setEditable(false);
        jPRICEfield4.setEditable(false);
        jPRICEfield8.setEditable(false);
        jPRICEfield1.setEditable(false);
        jPRICEfield5.setEditable(false);
        jPRICEfield9.setEditable(false);
        jPRICEfield2.setEditable(false);
        jPRICEfield6.setEditable(false);
        jPRICEfield10.setEditable(false);
        jPRICEfield3.setEditable(false);
        jPRICEfield7.setEditable(false);
        jPRICEfield11.setEditable(false);

        jSUBTOTALfield.setEditable(false);
        jORDERfield.setEditable(false);
        jTAXPAIDfield.setEditable(false);
        jNETPRICEfield.setEditable(false);
        jCHANGEfield.setEditable(false);
        ITEMAREA.setEditable(false);

        jSHOWlogs.setEnabled(false); // Initially disabled

        jPRICEfield.setBackground(java.awt.Color.WHITE);
        jPRICEfield.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield1.setBackground(java.awt.Color.WHITE);
        jPRICEfield1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield2.setBackground(java.awt.Color.WHITE);
        jPRICEfield2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield3.setBackground(java.awt.Color.WHITE);
        jPRICEfield3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield4.setBackground(java.awt.Color.WHITE);
        jPRICEfield4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield5.setBackground(java.awt.Color.WHITE);
        jPRICEfield5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield6.setBackground(java.awt.Color.WHITE);
        jPRICEfield6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield7.setBackground(java.awt.Color.WHITE);
        jPRICEfield7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield8.setBackground(java.awt.Color.WHITE);
        jPRICEfield8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield9.setBackground(java.awt.Color.WHITE);
        jPRICEfield9.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield10.setBackground(java.awt.Color.WHITE);
        jPRICEfield10.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jPRICEfield11.setBackground(java.awt.Color.WHITE);
        jPRICEfield11.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));

        jSUBTOTALfield.setBackground(java.awt.Color.WHITE);
        jSUBTOTALfield.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));

        jORDERfield.setBackground(java.awt.Color.WHITE);
        jORDERfield.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));

        jTAXPAIDfield.setBackground(java.awt.Color.WHITE);
        jTAXPAIDfield.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));

        jNETPRICEfield.setBackground(java.awt.Color.WHITE);
        jNETPRICEfield.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));

        jCHANGEfield.setBackground(java.awt.Color.WHITE);
        jCHANGEfield.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));

        jTxtFullName.setBackground(java.awt.Color.WHITE);
        jTxtFullName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        jTxtNumber.setBackground(java.awt.Color.WHITE);
        jTxtNumber.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        ITEMAREA.setBackground(java.awt.Color.WHITE);

    }

    // Inner class to hold toy details
    private class ToyDetails {

        String selectedToy;
        String quantityText;
        String priceText;
        double price = 0;
        int quantity = 0;
    }

    // Example method to process payment
    private void processPayment(double amount) {
        // Implementation of payment generation
        // This could be updating some fields, making a transaction, etc.
        System.out.println("");//Payment of Php " + amount + " has been processed INSERT INSIDE PARENTHESIS.

    }

    private static long receiptNumber = 1; // Start from 000000000001

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel MAIN = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane = new javax.swing.JScrollPane();
        javax.swing.JPanel jPanelBoardORDER = new javax.swing.JPanel();
        javax.swing.JPanel jTITLE = new javax.swing.JPanel();
        javax.swing.JLabel jTOYLABEL = new javax.swing.JLabel();
        javax.swing.JPanel jPanelINFO = new javax.swing.JPanel();
        javax.swing.JLabel jCname = new javax.swing.JLabel();
        javax.swing.JLabel jCnumber = new javax.swing.JLabel();
        jTxtNumber = new javax.swing.JTextField();
        jLTime = new javax.swing.JLabel();
        jLDate = new javax.swing.JLabel();
        jLCurrentTime = new javax.swing.JLabel();
        jLCurrentDate = new javax.swing.JLabel();
        jTxtFullName = new javax.swing.JTextField();
        javax.swing.JPanel jTOYORDERPROCESS = new javax.swing.JPanel();
        jCbTransformers = new javax.swing.JComboBox<>();
        javax.swing.JLabel jTransformerCbinerTXT = new javax.swing.JLabel();
        javax.swing.JLabel jPRICEtxt = new javax.swing.JLabel();
        jPRICEfield = new javax.swing.JTextField();
        javax.swing.JLabel jQUANTITYtxt = new javax.swing.JLabel();
        jCbQUANTITY = new javax.swing.JComboBox<>();
        javax.swing.JLabel jSUBTOTALtxt = new javax.swing.JLabel();
        jSUBTOTALfield = new javax.swing.JTextField();
        jSUBMITbtn = new javax.swing.JButton();
        jCbTransformers1 = new javax.swing.JComboBox<>();
        jPRICEfield1 = new javax.swing.JTextField();
        jCbQUANTITY1 = new javax.swing.JComboBox<>();
        jCbTransformers2 = new javax.swing.JComboBox<>();
        jPRICEfield2 = new javax.swing.JTextField();
        jCbQUANTITY2 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jTransformerCbinerTXT1 = new javax.swing.JLabel();
        jCbTransformers3 = new javax.swing.JComboBox<>();
        jPRICEfield3 = new javax.swing.JTextField();
        jCbQUANTITY3 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLEGOTECHNICHStxt = new javax.swing.JLabel();
        jCbTransformers4 = new javax.swing.JComboBox<>();
        jPRICEfield4 = new javax.swing.JTextField();
        javax.swing.JLabel jGUNDAMtxt = new javax.swing.JLabel();
        jCbTransformers5 = new javax.swing.JComboBox<>();
        jPRICEfield5 = new javax.swing.JTextField();
        javax.swing.JLabel jMACROSStxt = new javax.swing.JLabel();
        jCbTransformers6 = new javax.swing.JComboBox<>();
        jPRICEfield6 = new javax.swing.JTextField();
        jCbQUANTITY6 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jZOIDStxt = new javax.swing.JLabel();
        jCbTransformers7 = new javax.swing.JComboBox<>();
        jPRICEfield7 = new javax.swing.JTextField();
        jCbQUANTITY7 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jDIE_CASTtxt = new javax.swing.JLabel();
        jPRICEfield8 = new javax.swing.JTextField();
        jCbTransformers8 = new javax.swing.JComboBox<>();
        jCbQUANTITY8 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jFUNKO_POPtxt = new javax.swing.JLabel();
        jCbTransformers9 = new javax.swing.JComboBox<>();
        jPRICEfield9 = new javax.swing.JTextField();
        jCbQUANTITY9 = new javax.swing.JComboBox<>();
        jCbQUANTITY4 = new javax.swing.JComboBox<>();
        jCbQUANTITY5 = new javax.swing.JComboBox<>();
        jCbTransformers10 = new javax.swing.JComboBox<>();
        jPRICEfield10 = new javax.swing.JTextField();
        jCbQUANTITY10 = new javax.swing.JComboBox<>();
        jCbTransformers11 = new javax.swing.JComboBox<>();
        jPRICEfield11 = new javax.swing.JTextField();
        jCbQUANTITY11 = new javax.swing.JComboBox<>();
        javax.swing.JPanel jORDERSUMMARY = new javax.swing.JPanel();
        javax.swing.JLabel jORDERtotaLtxt = new javax.swing.JLabel();
        javax.swing.JLabel jTAXtxt = new javax.swing.JLabel();
        jORDERfield = new javax.swing.JTextField();
        javax.swing.JLabel jNETPRICEtxt = new javax.swing.JLabel();
        jTAXPAIDfield = new javax.swing.JTextField();
        jNETPRICEfield = new javax.swing.JTextField();
        jPAYbtn = new javax.swing.JButton();
        javax.swing.JLabel jAMOUNTtxt = new javax.swing.JLabel();
        jAMOUNTCASHfield = new javax.swing.JTextField();
        javax.swing.JLabel jCHANGEtxt = new javax.swing.JLabel();
        jCHANGEfield = new javax.swing.JTextField();
        jPRINTreceipt = new javax.swing.JButton();
        javax.swing.JLabel jORDERtotaLtxt1 = new javax.swing.JLabel();
        AREAPANE = new javax.swing.JScrollPane();
        ITEMAREA = new javax.swing.JTextArea();
        REMOVEITEMS = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRESETbtn = new javax.swing.JButton();
        EXIT_CLOSE = new javax.swing.JButton();
        javax.swing.JPanel jReceiptPanel = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrolLRECEIPT = new javax.swing.JScrollPane();
        jTextAreaRESIBO = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jSHOWlogs = new javax.swing.JButton();
        PASSWORD = new javax.swing.JPasswordField();
        javax.swing.JLabel PASSWORD_TXT = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ORDER DASHBOARD");
        setFocusTraversalPolicyProvider(true);
        setLocationByPlatform(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1368, 730));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MAIN.setPreferredSize(new java.awt.Dimension(1368, 800));

        jScrollPane.setPreferredSize(new java.awt.Dimension(1368, 800));

        jPanelBoardORDER.setBackground(new java.awt.Color(153, 153, 255));
        jPanelBoardORDER.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelBoardORDER.setPreferredSize(new java.awt.Dimension(1300, 1000));

        jTITLE.setBackground(new java.awt.Color(204, 204, 255));
        jTITLE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTITLE.setPreferredSize(new java.awt.Dimension(1320, 62));

        jTOYLABEL.setBackground(new java.awt.Color(204, 204, 255));
        jTOYLABEL.setFont(new java.awt.Font("Bebas", 1, 48)); // NOI18N
        jTOYLABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTOYLABEL.setText("TOYS");

        javax.swing.GroupLayout jTITLELayout = new javax.swing.GroupLayout(jTITLE);
        jTITLE.setLayout(jTITLELayout);
        jTITLELayout.setHorizontalGroup(
            jTITLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTOYLABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jTITLELayout.setVerticalGroup(
            jTITLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTITLELayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTOYLABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelINFO.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("CUSTOMER DETAILS"), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jCname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCname.setText("CUSTOMER NAME:");

        jCnumber.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCnumber.setText("CUSTOMER NUMBER:");

        jTxtNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNumberActionPerformed(evt);
            }
        });

        jLTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLTime.setText("Time:");

        jLDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLDate.setText("Date:");

        jLCurrentTime.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLCurrentTime.setForeground(new java.awt.Color(0, 102, 0));
        jLCurrentTime.setText("0");

        jLCurrentDate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLCurrentDate.setForeground(new java.awt.Color(0, 102, 0));
        jLCurrentDate.setText("0");

        jTxtFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFullNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelINFOLayout = new javax.swing.GroupLayout(jPanelINFO);
        jPanelINFO.setLayout(jPanelINFOLayout);
        jPanelINFOLayout.setHorizontalGroup(
            jPanelINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelINFOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCnumber, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelINFOLayout.createSequentialGroup()
                        .addComponent(jLTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLCurrentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelINFOLayout.createSequentialGroup()
                        .addComponent(jLDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLCurrentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78))
        );
        jPanelINFOLayout.setVerticalGroup(
            jPanelINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelINFOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLTime)
                        .addComponent(jLCurrentTime))
                    .addComponent(jTxtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelINFOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDate)
                    .addComponent(jLCurrentDate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelINFOLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTxtFullName, jTxtNumber});

        jTOYORDERPROCESS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "ORDER PROCESSING", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jCbTransformers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "DEVASTATOR", "BRUTICUS MAXIMUS", "SUPERION", "DEFENSOR", "PREDAKING", "VULCANUS DINOBOT", "OPTIMUS/JETFIRE", "METROPLEX"
            , "BARICADE", "GRIMLOCK AOYI", "THE FALLEN"}));
jCbTransformers.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jCbTransformersActionPerformed(evt);
    }
    });

    jTransformerCbinerTXT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jTransformerCbinerTXT.setText("TF COMBINERS|Transformers");

    jPRICEtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jPRICEtxt.setText("PRICE");

    jPRICEfield.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfieldActionPerformed(evt);
        }
    });

    jQUANTITYtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jQUANTITYtxt.setText("QTY");

    jCbQUANTITY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITYActionPerformed(evt);
        }
    });

    jSUBTOTALtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jSUBTOTALtxt.setText("SUB TOTAL");

    jSUBTOTALfield.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jSUBTOTALfield.setForeground(new java.awt.Color(102, 0, 0));
    jSUBTOTALfield.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jSUBTOTALfieldActionPerformed(evt);
        }
    });

    jSUBMITbtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jSUBMITbtn.setText("SUBMIT");
    jSUBMITbtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
    jSUBMITbtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jSUBMITbtnActionPerformed(evt);
        }
    });

    jCbTransformers1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "DEVASTATOR", "BRUTICUS MAXIMUS", "SUPERION", "DEFENSOR", "PREDAKING", "VULCANUS DINOBOT", "OPTIMUS/JETFIRE"
        , "JETFIRE COMMANDER 17.5cm", "MEGATANK GALVATRON", "SHOCKWAVE", "IRONHIDE", "SENTINEL PRIME"}));
jCbTransformers1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
    jCbTransformers1ActionPerformed(evt);
    }
    });

    jPRICEfield1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield1ActionPerformed(evt);
        }
    });

    jCbQUANTITY1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY1ActionPerformed(evt);
        }
    });

    jCbTransformers2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "DEVASTATOR", "BRUTICUS MAXIMUS", "SUPERION", "DEFENSOR", "PREDAKING", "VULCANUS DINOBOT", "OPTIMUS/JETFIRE"
        , "BUMBLE BEE YELLOW CAMARO", "SIEGE SERIES OPTIMUS W. CONTAINER", "STARSCREAM", "STARSCREAM JINBAO", "ULTRA MAGNUS", "OMEGA SUPREME", "BLACKOUT", "JAZZ"}));
jCbTransformers2.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
    jCbTransformers2ActionPerformed(evt);
    }
    });

    jPRICEfield2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield2ActionPerformed(evt);
        }
    });

    jCbQUANTITY2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY2ActionPerformed(evt);
        }
    });

    jTransformerCbinerTXT1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jTransformerCbinerTXT1.setText("ROBOTECH COMBINERS");

    jCbTransformers3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "VOLTES V", "VOLTRON LION", "DAIMOS GX-43", "DX CHOGOKIN COMBATTLER V", "GX-70 MAZINGER Z", "GX-88 VOLTRON DAIRUGGER", "MIGHTY ATOM ASTRO BOY DELUXE" , "CHOUDENSHI BIOMAN BIO ROBO" }));
    jCbTransformers3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbTransformers3ActionPerformed(evt);
        }
    });

    jPRICEfield3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield3ActionPerformed(evt);
        }
    });

    jCbQUANTITY3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY3ActionPerformed(evt);
        }
    });

    jLEGOTECHNICHStxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLEGOTECHNICHStxt.setText("LEGO TECHNICS");

    jCbTransformers4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "PEUGEOT 24H LEMANS", "TECHNINC FORD GT", "MERCEDEZ-AMG F1 RACECAR", "TECHNIC PORCHE 911", "ASTON MARTIN PORCHE 911", "MCLAREN F1", "FERRARI 488 GTE" , "FAST & FURIOUS DODGE CHARGER", "TECHNIC FERRARI SF90 F1", "FORMULA 1 BWT PINK F1", "GHOST BUSTER" }));
    jCbTransformers4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbTransformers4ActionPerformed(evt);
        }
    });

    jPRICEfield4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield4ActionPerformed(evt);
        }
    });

    jGUNDAMtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jGUNDAMtxt.setText("GUNDAM");

    jCbTransformers5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "GUNDAM HEAVY ARMS", "WING GUNDAM", "GUNDAM DEATHSCYTHE", "GUNDAM SANDROCK", "WING GUNDAM ZERO", "WS-03 MAGANAC", "TALLGEESE EW" , "GUNDAM SHENLONG", "MULTI TALLGEESE FLUEGEL EW", "Waltz Gundam Deathscythe", "Waltz Gundam Sandrock", "Waltz Gundam Heavyarms", "Waltz Gundam Zero 1/144", "Waltz Gundam Wing ZERO 1/100", "Waltz Gundam Epyon OZ-13MS 1/100", "Waltz Gundam Shenlong 1/100", "Waltz Gundam Altron 1/100"}));
    jCbTransformers5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbTransformers5ActionPerformed(evt);
        }
    });

    jPRICEfield5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield5ActionPerformed(evt);
        }
    });

    jMACROSStxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jMACROSStxt.setText("MACROSS");

    jCbTransformers6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "MACROSS V1-S STEALTH", "MACROSS FORTRESS VF-A", "MACROSS VF 1S VALKYRIE", "MACROSS ROBOTECH FRONTIER", "MACROSS MESSIAH VALKYRIE", "MACROSS VF-1S SUPER VALKYRIE", "MACROSS ARMORED VF-1J VALKYRIE" , "MACROSS VF-1J U.N. SPACY", "MACROSS DX VF-A FORTRESS", "MACROSS VF-4G LIGHTNING III"}));
    jCbTransformers6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbTransformers6ActionPerformed(evt);
        }
    });

    jPRICEfield6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield6ActionPerformed(evt);
        }
    });

    jCbQUANTITY6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY6ActionPerformed(evt);
        }
    });

    jZOIDStxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jZOIDStxt.setText("ZOIDS");

    jCbTransformers7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "ZOID MAD THUNDER", "ZOID ULTRASAURUS", "ZOID SEISMOSAURUS BLACK TITAN", "ZOID DEATH SAURER", "ZOID IRON KONG", "ZOID GOJULAS GUNNER" , "ZOID GENOSAURER", "ZOID LIGER ZERO"}));
    jCbTransformers7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbTransformers7ActionPerformed(evt);
        }
    });

    jPRICEfield7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield7ActionPerformed(evt);
        }
    });

    jCbQUANTITY7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY7ActionPerformed(evt);
        }
    });

    jDIE_CASTtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jDIE_CASTtxt.setText("DIECAST TOYS (cars)");

    jPRICEfield8.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield8ActionPerformed(evt);
        }
    });

    jCbTransformers8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "MSZ AUDI RS5 DTM PINK 1:32", "MSZ AUDI RS5 DTM ORANGE 1:32", "MSZ AUDI RS5 DTM BLACK 1:32", "AUDI R8 LMS RED 1:32", "MERCEDES AMG C63 BLUE 1:32", "MERCEDES AMG C63 BLACK 1:32", "BMW M3 DTM WHITE 1:32", "BMW M3 DTM YELLOW 1:32", "BMW M3 DTM BLACK 1:32", "BMW M4 DTM WHITE 1:32", "BMW M4 DTM BLACK 1:32", "BENTLEY CONTINENTAL GT3 BLK 1:32", "BMW M6 PS4 BLUE 1:32"}));
    jCbTransformers8.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbTransformers8ActionPerformed(evt);
        }
    });

    jCbQUANTITY8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY8.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY8ActionPerformed(evt);
        }
    });

    jFUNKO_POPtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jFUNKO_POPtxt.setText("FUNKO POP");

    jCbTransformers9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "BOBBA FETT", "ANIMATION RORONOA ZORO", "JOHN WICK", "DEMON SLAYER TANJIRO", "DEADPOOL BBQ MASTER", "ONE PIECE ONAMI", "LUKE SKY WALKER", "BLACK PANTHER", "BLACKPINK", "HARLEY QUINN DC ANIMATED"
        , "SPIDER-MAN SPIDERVERSE", "BATMAN HIKARI 85TH ANNIVERSARY", "DARTH VADER", "MARVEL-GHOST RIDER#1", "THE CROW - ERIC DRAVEN GLOW", "EDWARD SCISSORHANDS", "DOCTOR STRANGE", "JOHN WICK CHAPTER 4", "NEZUKO KAMADO (blood demon art)"}));
jCbTransformers9.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
    jCbTransformers9ActionPerformed(evt);
    }
    });

    jPRICEfield9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield9ActionPerformed(evt);
        }
    });

    jCbQUANTITY9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY9ActionPerformed(evt);
        }
    });

    jCbQUANTITY4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY4ActionPerformed(evt);
        }
    });

    jCbQUANTITY5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY5ActionPerformed(evt);
        }
    });

    jCbTransformers10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "MSZ AUDI RS5 DTM PINK 1:32", "MSZ AUDI RS5 DTM ORANGE 1:32", "MSZ AUDI RS5 DTM BLACK 1:32", "AUDI R8 LMS RED 1:32", "MERCEDES AMG C63 BLUE 1:32", "MERCEDES AMG C63 BLACK 1:32", "BMW M3 DTM WHITE 1:32", "BMW M3 DTM YELLOW 1:32", "BMW M3 DTM BLACK 1:32", "BMW M4 DTM WHITE 1:32", "BMW M4 DTM BLACK 1:32", "BENTLEY CONTINENTAL GT3 BLK 1:32", "BMW M6 PS4 BLUE 1:32"}));
    jCbTransformers10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbTransformers10ActionPerformed(evt);
        }
    });

    jPRICEfield10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield10ActionPerformed(evt);
        }
    });

    jCbQUANTITY10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY10ActionPerformed(evt);
        }
    });

    jCbTransformers11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "MSZ AUDI RS5 DTM PINK 1:32", "MSZ AUDI RS5 DTM ORANGE 1:32", "MSZ AUDI RS5 DTM BLACK 1:32", "AUDI R8 LMS RED 1:32", "MERCEDES AMG C63 BLUE 1:32", "MERCEDES AMG C63 BLACK 1:32", "BMW M3 DTM WHITE 1:32", "BMW M3 DTM YELLOW 1:32", "BMW M3 DTM BLACK 1:32", "BMW M4 DTM WHITE 1:32", "BMW M4 DTM BLACK 1:32", "BENTLEY CONTINENTAL GT3 BLK 1:32", "BMW M6 PS4 BLUE 1:32"}));
    jCbTransformers11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbTransformers11ActionPerformed(evt);
        }
    });

    jPRICEfield11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRICEfield11ActionPerformed(evt);
        }
    });

    jCbQUANTITY11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
    jCbQUANTITY11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCbQUANTITY11ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jTOYORDERPROCESSLayout = new javax.swing.GroupLayout(jTOYORDERPROCESS);
    jTOYORDERPROCESS.setLayout(jTOYORDERPROCESSLayout);
    jTOYORDERPROCESSLayout.setHorizontalGroup(
        jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                    .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jDIE_CASTtxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jZOIDStxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jMACROSStxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLEGOTECHNICHStxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTransformerCbinerTXT1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTransformerCbinerTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                        .addComponent(jCbTransformers4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jGUNDAMtxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCbTransformers11, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18))
                .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                    .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jFUNKO_POPtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addComponent(jCbTransformers9, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(20, 20, 20)))
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                    .addComponent(jPRICEfield6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jCbQUANTITY6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jSUBMITbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTOYORDERPROCESSLayout.createSequentialGroup()
                    .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPRICEfield4)
                        .addComponent(jPRICEfield2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTOYORDERPROCESSLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPRICEfield3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                .addComponent(jPRICEfield1)
                                .addComponent(jPRICEfield, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPRICEfield5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPRICEtxt))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGap(18, 18, 18)
                    .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCbQUANTITY5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCbQUANTITY4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCbQUANTITY3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCbQUANTITY1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCbQUANTITY2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCbQUANTITY, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jQUANTITYtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSUBTOTALtxt)
                                .addComponent(jSUBTOTALfield, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                    .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                            .addComponent(jPRICEfield7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jCbQUANTITY7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                            .addComponent(jPRICEfield8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jCbQUANTITY8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                            .addComponent(jPRICEfield10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jCbQUANTITY10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPRICEfield9)
                                .addComponent(jPRICEfield11, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCbQUANTITY9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCbQUANTITY11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jTOYORDERPROCESSLayout.setVerticalGroup(
        jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTransformerCbinerTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jQUANTITYtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jSUBTOTALtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCbTransformers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEfield, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCbQUANTITY, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jSUBTOTALfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCbTransformers1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCbQUANTITY1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCbTransformers2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCbQUANTITY2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPRICEfield6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCbQUANTITY6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCbTransformers3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPRICEfield3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCbQUANTITY3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLEGOTECHNICHStxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jPRICEfield4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCbTransformers4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCbQUANTITY4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jGUNDAMtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCbTransformers5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPRICEfield5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCbQUANTITY5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jMACROSStxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCbTransformers6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jZOIDStxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTOYORDERPROCESSLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jSUBMITbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jTOYORDERPROCESSLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTransformerCbinerTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(4, 4, 4)
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCbTransformers7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEfield7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCbQUANTITY7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jDIE_CASTtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCbTransformers8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEfield8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCbQUANTITY8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCbTransformers10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEfield10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCbQUANTITY10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCbTransformers11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEfield11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCbQUANTITY11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jFUNKO_POPtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jTOYORDERPROCESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCbTransformers9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPRICEfield9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCbQUANTITY9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(23, 23, 23))
    );

    jTOYORDERPROCESSLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCbQUANTITY, jCbTransformers, jCbTransformers1, jCbTransformers10, jCbTransformers11, jCbTransformers2, jCbTransformers3, jCbTransformers4, jCbTransformers5, jCbTransformers6, jCbTransformers7, jCbTransformers8, jCbTransformers9, jSUBTOTALfield});

    jTOYORDERPROCESSLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCbQUANTITY3, jCbQUANTITY4, jCbQUANTITY5});

    jORDERSUMMARY.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "ORDER SUMMARY", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

    jORDERtotaLtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jORDERtotaLtxt.setText("TOTAL OF TOY/S ORDERED");

    jTAXtxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jTAXtxt.setText("TAX CHARGE (vat)");

    jORDERfield.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jORDERfieldActionPerformed(evt);
        }
    });

    jNETPRICEtxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jNETPRICEtxt.setText("NET PRICE");

    jTAXPAIDfield.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTAXPAIDfieldActionPerformed(evt);
        }
    });

    jNETPRICEfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jNETPRICEfield.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jNETPRICEfieldActionPerformed(evt);
        }
    });

    jPAYbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jPAYbtn.setText("PAY");
    jPAYbtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
    jPAYbtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPAYbtnActionPerformed(evt);
        }
    });

    jAMOUNTtxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jAMOUNTtxt.setText("AMOUNT CASH");

    jAMOUNTCASHfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jAMOUNTCASHfield.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jAMOUNTCASHfieldActionPerformed(evt);
        }
    });

    jCHANGEtxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jCHANGEtxt.setText("CHANGE");

    jCHANGEfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jCHANGEfield.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCHANGEfieldActionPerformed(evt);
        }
    });

    jPRINTreceipt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jPRINTreceipt.setText("Print Receipt");
    jPRINTreceipt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
    jPRINTreceipt.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPRINTreceiptActionPerformed(evt);
        }
    });

    jORDERtotaLtxt1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jORDERtotaLtxt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jORDERtotaLtxt1.setText("ITEM SELECTED");

    ITEMAREA.setColumns(20);
    ITEMAREA.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
    ITEMAREA.setForeground(new java.awt.Color(0, 102, 0));
    ITEMAREA.setRows(5);
    ITEMAREA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
    AREAPANE.setViewportView(ITEMAREA);

    REMOVEITEMS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    REMOVEITEMS.setText("Remove Items");
    REMOVEITEMS.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
    REMOVEITEMS.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            REMOVEITEMSActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jORDERSUMMARYLayout = new javax.swing.GroupLayout(jORDERSUMMARY);
    jORDERSUMMARY.setLayout(jORDERSUMMARYLayout);
    jORDERSUMMARYLayout.setHorizontalGroup(
        jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jORDERSUMMARYLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jORDERtotaLtxt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jORDERSUMMARYLayout.createSequentialGroup()
                    .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTAXtxt)
                        .addComponent(jNETPRICEtxt)
                        .addComponent(jAMOUNTtxt))
                    .addGap(5, 5, 5)
                    .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jNETPRICEfield, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jAMOUNTCASHfield)
                        .addComponent(jTAXPAIDfield)))
                .addComponent(AREAPANE, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jORDERSUMMARYLayout.createSequentialGroup()
                    .addComponent(jCHANGEtxt)
                    .addGap(18, 18, 18)
                    .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jORDERSUMMARYLayout.createSequentialGroup()
                            .addComponent(jPAYbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jCHANGEfield)))
                .addGroup(jORDERSUMMARYLayout.createSequentialGroup()
                    .addComponent(jORDERtotaLtxt)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jORDERfield, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jORDERSUMMARYLayout.createSequentialGroup()
                    .addComponent(REMOVEITEMS, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                    .addComponent(jPRINTreceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    jORDERSUMMARYLayout.setVerticalGroup(
        jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jORDERSUMMARYLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jORDERtotaLtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jORDERfield, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jORDERtotaLtxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(AREAPANE, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTAXtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTAXPAIDfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jNETPRICEtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jNETPRICEfield, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jAMOUNTtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jAMOUNTCASHfield, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCHANGEtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCHANGEfield, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(jPAYbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
            .addGroup(jORDERSUMMARYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jPRINTreceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(REMOVEITEMS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    jORDERSUMMARYLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jORDERfield, jTAXPAIDfield});

    jPanel1.setBackground(new java.awt.Color(153, 153, 255));
    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jRESETbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jRESETbtn.setText("Set bill again");
    jRESETbtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
    jRESETbtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jRESETbtnActionPerformed(evt);
        }
    });

    EXIT_CLOSE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    EXIT_CLOSE.setText("EXIT");
    EXIT_CLOSE.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
    EXIT_CLOSE.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            EXIT_CLOSEActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jRESETbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(EXIT_CLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jRESETbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addComponent(EXIT_CLOSE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(12, Short.MAX_VALUE))
    );

    jReceiptPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECEIPT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

    jTextAreaRESIBO.setColumns(20);
    jTextAreaRESIBO.setFont(new java.awt.Font("Monospaced", 1, 11)); // NOI18N
    jTextAreaRESIBO.setRows(5);
    jScrolLRECEIPT.setViewportView(jTextAreaRESIBO);

    javax.swing.GroupLayout jReceiptPanelLayout = new javax.swing.GroupLayout(jReceiptPanel);
    jReceiptPanel.setLayout(jReceiptPanelLayout);
    jReceiptPanelLayout.setHorizontalGroup(
        jReceiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrolLRECEIPT, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
    );
    jReceiptPanelLayout.setVerticalGroup(
        jReceiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrolLRECEIPT)
    );

    jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jSHOWlogs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jSHOWlogs.setText("Show Logs");
    jSHOWlogs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
    jSHOWlogs.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jSHOWlogsActionPerformed(evt);
        }
    });

    PASSWORD.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            PASSWORDActionPerformed(evt);
        }
    });

    PASSWORD_TXT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    PASSWORD_TXT.setText("Password:");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PASSWORD_TXT)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jSHOWlogs, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addComponent(PASSWORD))
            .addGap(134, 134, 134))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addComponent(jSHOWlogs, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PASSWORD_TXT))
            .addContainerGap(14, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanelBoardORDERLayout = new javax.swing.GroupLayout(jPanelBoardORDER);
    jPanelBoardORDER.setLayout(jPanelBoardORDERLayout);
    jPanelBoardORDERLayout.setHorizontalGroup(
        jPanelBoardORDERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBoardORDERLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelBoardORDERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBoardORDERLayout.createSequentialGroup()
                    .addComponent(jTOYORDERPROCESS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelBoardORDERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jORDERSUMMARY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelBoardORDERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jReceiptPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanelINFO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTITLE, javax.swing.GroupLayout.DEFAULT_SIZE, 1325, Short.MAX_VALUE))
            .addContainerGap())
    );
    jPanelBoardORDERLayout.setVerticalGroup(
        jPanelBoardORDERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBoardORDERLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTITLE, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanelINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelBoardORDERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTOYORDERPROCESS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBoardORDERLayout.createSequentialGroup()
                    .addComponent(jORDERSUMMARY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBoardORDERLayout.createSequentialGroup()
                    .addComponent(jReceiptPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(201, 201, 201))
    );

    jScrollPane.setViewportView(jPanelBoardORDER);

    javax.swing.GroupLayout MAINLayout = new javax.swing.GroupLayout(MAIN);
    MAIN.setLayout(MAINLayout);
    MAINLayout.setHorizontalGroup(
        MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    MAINLayout.setVerticalGroup(
        MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    getContentPane().add(MAIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 824));

    getAccessibleContext().setAccessibleDescription("");

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void jRESETbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRESETbtnActionPerformed
        // Close the current window and reopen the EngageToy window
        this.dispose(); // Close the current window

        // Assuming you want to go back to the EngageToy main functionality
        EngageToy engageToy = new EngageToy();  // Create a new instance of EngageToy
        engageToy.setVisible(true);             // Set it visible to the user


    }//GEN-LAST:event_jRESETbtnActionPerformed

    private void jSHOWlogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSHOWlogsActionPerformed
        this.dispose(); // Close the current window

        // Open the Tables1 JFrame
        Tables1 tablesFrame = new Tables1();
        tablesFrame.setVisible(true); // Show the new window

    }//GEN-LAST:event_jSHOWlogsActionPerformed

    private void jPRINTreceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRINTreceiptActionPerformed
        try {
            // Retrieve the actual customer name from a GUI component
            String fullName = jTxtFullName.getText(); // Replace with your actual GUI component

            // Get the current date and format it
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new java.util.Date());

            // Set up the path and file name for saving the receipt
            String directoryPath = "C:/Users/Engage Mik/Documents/ORDER TOYS/";
            String receiptFileName = "Receipt_" + fullName + "_" + date + "_" + System.currentTimeMillis() + ".pdf"; // Filename with name and date
            String filePath = directoryPath + receiptFileName; // Save to specified directory

            // Create directory if it doesn't exist
            java.io.File directory = new java.io.File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory
            }

            // Use iText 5 classes
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(filePath));

            // Open the document
            document.open();

            // Retrieve the necessary details from the GUI
            String numberText = jTxtNumber.getText().trim();
            String quantityText = jORDERfield.getText().trim();
            String netPriceText = jNETPRICEfield.getText();
            String taxPaidText = jTAXPAIDfield.getText();
            String changeText = jCHANGEfield.getText();
            String cashAmountText = jAMOUNTCASHfield.getText();
            String selectedToy = ITEMAREA.getText(); // Ensure this field is correctly set

            // Parse quantity correctly
            int quantity = Integer.parseInt(quantityText); // Ensure quantity is parsed correctly
            String formattedReceiptNumber = String.format("%012d", EngageToyReceiptManager.readLastReceiptNumber() + 1); // Increment for the receipt number

            // Get the current date and time for the receipt
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
            Date currentDate = new Date();
            String formattedDate = dateFormatter.format(currentDate);
            String formattedTime = timeFormatter.format(currentDate);

            // Build the receipt details with alignment
            StringBuilder receiptBuilder = new StringBuilder();
            receiptBuilder.append("========================================================\n");
            receiptBuilder.append("TRANSFORMERS COMBINER TOYS / ROBOT DEFORMATION TOYS  \n");
            receiptBuilder.append("                   FAIRVIEW, NORTH FAIRVIEW, QUEZON CITY          \n");
            receiptBuilder.append("========================================================\n");
            receiptBuilder.append(String.format("    RECEIPT NUMBER : %s\n", formattedReceiptNumber));
            receiptBuilder.append(String.format("     CUSTOMER NAME : %-30s\n", fullName));
            receiptBuilder.append(String.format("            NUMBER : %-29s\n", numberText));
            receiptBuilder.append("        SELECTED TOY(S) :          \n" + selectedToy.trim() + "\n");
            receiptBuilder.append("                         \n");
            receiptBuilder.append(String.format("         QUANTITY  : %-29d\n", quantity));
            receiptBuilder.append(String.format("         NET PRICE : %-23s\n", netPriceText));
            receiptBuilder.append(String.format("      TAX Amt(vat) : %-22s\n", taxPaidText));
            receiptBuilder.append(String.format("    AMOUNT OF CASH : %-17s\n", cashAmountText));
            receiptBuilder.append(String.format("            CHANGE : %-30s\n", changeText));
            receiptBuilder.append("\n");
            receiptBuilder.append(String.format("     DATE: %-20s TIME: %-30s\n", formattedDate, formattedTime));
            receiptBuilder.append("=======================================================\n");
            receiptBuilder.append("             **** THANK YOU FOR BUYING ENGAGE TOYS! ****      \n");
            receiptBuilder.append("                  https://web.facebook.com/paulomiguel.cortez.54    \n");
            receiptBuilder.append("=======================================================\n");

            // Add content from JTextArea to the PDF document
            com.itextpdf.text.Paragraph paragraph = new com.itextpdf.text.Paragraph(receiptBuilder.toString());
            document.add(paragraph);

            // Close the document
            document.close();

            // Show success message
            javax.swing.JOptionPane.showMessageDialog(this, "Receipt saved to: " + filePath);

            // Automatically open the PDF after creation (this works on most systems)
            try {
                java.awt.Desktop.getDesktop().open(new java.io.File(filePath));
            } catch (IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Could not open the PDF file automatically.");
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error generating PDF: " + e.getMessage());
        }

    }//GEN-LAST:event_jPRINTreceiptActionPerformed

    private void jCHANGEfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCHANGEfieldActionPerformed
        jCHANGEfield.setEditable(false);

    }//GEN-LAST:event_jCHANGEfieldActionPerformed

    private void jAMOUNTCASHfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAMOUNTCASHfieldActionPerformed
        // Ensure "Php " is set if the field is blank
        if (jAMOUNTCASHfield.getText().isEmpty()) {
            jAMOUNTCASHfield.setText("Php ");
            return; // Exit here since the user hasn't entered a number yet
        }

        // Get the text input from jAMOUNTCASHfield, strip "Php" if already present
        String inputText = jAMOUNTCASHfield.getText().replace("Php", "").replace(",", "").trim();

        // Try to parse it as a number to validate
        try {
            double cashAmount = Double.parseDouble(inputText);

            // Format the input with "Php", commas, and 2 decimal places
            NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));
            currencyFormat.setMinimumFractionDigits(2);  // Ensure .00 is added to the number
            currencyFormat.setMaximumFractionDigits(2);  // Limit it to two decimal places

            String formattedCash = String.format("Php %s", currencyFormat.format(cashAmount));

            // Set the formatted value back to the text field
            jAMOUNTCASHfield.setText(formattedCash);

            // Process the payment
            processPayment(cashAmount); // Call your method to handle the payment

        } catch (NumberFormatException e) {
            // If the input is not valid (e.g., not a number), just reset it to "Php "
            jAMOUNTCASHfield.setText("Php ");
        }

    }//GEN-LAST:event_jAMOUNTCASHfieldActionPerformed

    private void EXIT_CLOSEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXIT_CLOSEActionPerformed
        // Show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION);

        // If the user clicks "Yes", close the program
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_EXIT_CLOSEActionPerformed

    private void jPAYbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPAYbtnActionPerformed
        if (!isSubmitted) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please submit your information before proceeding with payment.");
            return; // Exit the method if not submitted
        }
        if (isPaymentProcessed) {
            javax.swing.JOptionPane.showMessageDialog(this, "Payment has already been processed. Cannot process again.");
            return; // Exit the method if payment has already been processed
        }
        // Disable the payment button immediately to prevent further clicks
        jPAYbtn.setEnabled(false); // Prevent multiple clicks

        // Retrieve the full name and number
        String fullName = jTxtFullName.getText().trim();
        String numberText = jTxtNumber.getText().trim();
        String quantityText = jORDERfield.getText().trim(); // Add this line for quantity input

        // Get the net price from jNETPRICEfield (remove "Php" and commas if present)
        String netPriceText = jNETPRICEfield.getText().replace("Php", "").replace(",", "").trim();
        // Get the amount of cash from jAMOUNTCASHfield
        String cashText = jAMOUNTCASHfield.getText().replace("Php", "").replace(",", "").trim();
        // Get the selected toy from ITEMAREA
        String selectedToy = ITEMAREA.getText(); // Ensure this field is correctly set

        // Validate that all fields are not empty
        if (fullName.isEmpty() || numberText.isEmpty() || netPriceText.isEmpty() || cashText.isEmpty() || quantityText.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter all required fields, including the amount of cash and quantity.");
            return;
        }

        try {
            // Parse the net price and cash amounts
            double netPrice = Double.parseDouble(netPriceText);
            double cashAmount = Double.parseDouble(cashText.replace(",", "")); // Ensure to parse without commas
            int quantity = Integer.parseInt(quantityText); // Parse quantity

            // Validate if the user has provided enough cash
            if (cashAmount < netPrice) {
                javax.swing.JOptionPane.showMessageDialog(this, "Insufficient cash! Please provide enough to cover the total.");
                return;
            }

            // Calculate the change
            double change = cashAmount - netPrice;

            // Format the amounts in PHP currency format
            NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));
            String formattedChange = String.format("Php %s", currencyFormat.format(change));
            String formattedCashAmount = String.format("Php %s", currencyFormat.format(cashAmount)); // Create formatted string

            // Set the change to the jCHANGEfield
            jCHANGEfield.setText(formattedChange);

            // Display a success message
            javax.swing.JOptionPane.showMessageDialog(this, "Payment successful! Change: " + formattedChange);

            // Get the current date and time
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
            Date currentDate = new Date();
            String formattedDate = dateFormatter.format(currentDate);
            String formattedTime = timeFormatter.format(currentDate);

            // Use EngageToyReceiptManager to manage the receipt number
            int receiptNumber = EngageToyReceiptManager.readLastReceiptNumber();
            String formattedReceiptNumber = String.format("%012d", receiptNumber);

            // Increment and save the receipt number
            receiptNumber++;
            EngageToyReceiptManager.writeLastReceiptNumber(receiptNumber);

            // Split the selected toy names by newline for better alignment
            String[] selectedToys = selectedToy.split("\n");
            StringBuilder alignedToys = new StringBuilder();
            for (String toy : selectedToys) {
                alignedToys.append(String.format("          %-30s\n", toy.trim()));  // Adjust %-20s for desired alignment width
            }
            // Prepare aligned date and time columns
            String dateColumn = String.format("%-20s", formattedDate);
            String timeColumn = String.format("%-20s", formattedTime);
            // Prepare the receipt details
            String receiptDetails = String.format(
                    "=======================================================\n"
                    + "  TRANSFORMERS COMBINER TOYS / ROBOT DEFORMATION TOYS  \n"
                    + "        FAIRVIEW, NORTH FAIRVIEW, QUEZON CITY          \n"
                    + "=======================================================\n"
                    + "    RECEIPT NUMBER : %s\n"
                    + // Use formatted receipt number
                    "     CUSTOMER NAME : %-30s\n"
                    + "            NUMBER : %-29s\n"
                    + "        SELECTED TOY/S :\n%s"
                    + // Display aligned toys
                    "     \n"
                    + "     \n"
                    + "     \n"
                    + "     \n"
                    + "         QUANTITY  : %-29d\n"
                    + // Add quantity to receipt
                    "         NET PRICE : %-23s\n"
                    + "      TAX Amt(vat) : %-22s\n"
                    + "    AMOUNT OF CASH : %-17s\n"
                    + "            CHANGE : %-30s\n"
                    + "\n"
                    + "     DATE: %-20s TIME: %-30s\n"
                    + "=======================================================\n"
                    + "      **** THANK YOU FOR BUYING ENGAGE TOYS! ****      \n"
                    + "     https://web.facebook.com/paulomiguel.cortez.54    \n"
                    + "=======================================================\n",
                    formattedReceiptNumber,
                    fullName,
                    numberText,
                    alignedToys.toString(),
                    quantity, // Include quantity in the receipt
                    jNETPRICEfield.getText(),
                    jTAXPAIDfield.getText(),
                    formattedCashAmount,
                    jCHANGEfield.getText(),
                    dateColumn,
                    timeColumn
            );

            // Display the receipt details in the JTextArea
            jTextAreaRESIBO.setText(receiptDetails);

            // Display the receipt details in the JTextArea
            jTextAreaRESIBO.setText(receiptDetails);

            // Create an instance of your database connection class
            DbConnectionTOY dbConnection = new DbConnectionTOY();

            // Insert the payment details into the database
            dbConnection.insertToTable1(fullName, numberText, quantity, selectedToy, netPrice, formattedReceiptNumber);

            // Close the database connection
            dbConnection.closeConnection();

            // Disable fields and buttons after payment
            jAMOUNTCASHfield.setEditable(false);
            jAMOUNTCASHfield.setEditable(false);
            jAMOUNTCASHfield.setBackground(java.awt.Color.WHITE);
            jAMOUNTCASHfield.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
            jPAYbtn.setEnabled(false);

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: Please enter valid numeric values for the price and cash amount.");
        }

        // After displaying the receipt, ensure the JTextArea is non-editable
        jTextAreaRESIBO.setEditable(false);
        // After successful payment processing
        isPaymentProcessed = true; // Set the flag to indicate payment has been processed

    }//GEN-LAST:event_jPAYbtnActionPerformed

    private void jNETPRICEfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNETPRICEfieldActionPerformed
        jNETPRICEfield.setEditable(false);

    }//GEN-LAST:event_jNETPRICEfieldActionPerformed

    private void jTAXPAIDfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTAXPAIDfieldActionPerformed
        jTAXPAIDfield.setEditable(false);
    }//GEN-LAST:event_jTAXPAIDfieldActionPerformed

    private void jORDERfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jORDERfieldActionPerformed
        jORDERfield.setEditable(false);

    }//GEN-LAST:event_jORDERfieldActionPerformed

    private void jCbQUANTITY5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY5ActionPerformed

    private void jCbQUANTITY4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY4ActionPerformed

    private void jCbQUANTITY9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY9ActionPerformed

    private void jPRICEfield9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield9ActionPerformed
        jPRICEfield9.setEditable(false);

    }//GEN-LAST:event_jPRICEfield9ActionPerformed

    private void jCbTransformers9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers9ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers9.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "BOBBA FETT":
                    price = 679.00;
                    break;
                case "ANIMATION RORONOA ZORO":
                    price = 899.00;
                    break;
                case "JOHN WICK":
                    price = 670.00;
                    break;
                case "DEMON SLAYER TANJIRO":
                    price = 679.00;
                    break;
                case "DEADPOOL BBQ MASTER":
                    price = 899.00;
                    break;
                case "ONE PIECE ONAMI":
                    price = 1200.00;
                    break;
                case "LUKE SKY WALKER":
                    price = 2299.00;
                    break;
                case "BLACK PANTHER":
                    price = 679.00;
                    break;
                case "BLACKPINK":
                    price = 700.00;
                    break;
                case "HARLEY QUINN DC ANIMATED":
                    price = 4581.00;
                    break;
                case "SPIDER-MAN SPIDERVERSE":
                    price = 725.00;
                    break;
                case "BATMAN HIKARI 85TH ANNIVERSARY":
                    price = 900.00;
                    break;
                case "DARTH VADER":
                    price = 1500.00;
                    break;
                case "MARVEL-GHOST RIDER#1":
                    price = 1500.00;
                    break;
                case "THE CROW - ERIC DRAVEN GLOW":
                    price = 950.00;
                    break;
                case "EDWARD SCISSORHANDS":
                    price = 725.00;
                    break;
                case "DOCTOR STRANGE":
                    price = 950.00;
                    break;
                case "JOHN WICK CHAPTER 4":
                    price = 700.00;
                    break;
                case "NEZUKO KAMADO (blood demon art)":
                    price = 1200.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield9.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield9.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield9.setText("");
        }

    }//GEN-LAST:event_jCbTransformers9ActionPerformed

    private void jCbQUANTITY8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY8ActionPerformed

    private void jCbTransformers8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers8ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers8.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "MSZ AUDI RS5 DTM PINK 1:32":
                    price = 299.00;
                    break;
                case "MSZ AUDI RS5 DTM ORANGE 1:32":
                    price = 299.00;
                    break;
                case "MSZ AUDI RS5 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "AUDI R8 LMS RED 1:32":
                    price = 299.00;
                    break;
                case "MERCEDES AMG C63 BLUE 1:32":
                    price = 299.00;
                    break;
                case "MERCEDES AMG C63 BLACK 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM WHITE 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM YELLOW 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "BMW M4 DTM WHITE 1:32":
                    price = 299.00;
                    break;
                case "BMW M4 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "BENTLEY CONTINENTAL GT3 WT 1:32":
                    price = 299.00;
                    break;
                case "BENTLEY CONTINENTAL GT3 BLK 1:32":
                    price = 299.00;
                    break;
                case "BMW M6 PS4 BLUE 1:32":
                    price = 399.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield8.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield8.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield8.setText("");
        }

    }//GEN-LAST:event_jCbTransformers8ActionPerformed

    private void jPRICEfield8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield8ActionPerformed
        jPRICEfield8.setEditable(false);

    }//GEN-LAST:event_jPRICEfield8ActionPerformed

    private void jCbQUANTITY7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY7ActionPerformed

    private void jPRICEfield7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield7ActionPerformed
        jPRICEfield7.setEditable(false);
    }//GEN-LAST:event_jPRICEfield7ActionPerformed

    private void jCbTransformers7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers7ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers7.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "ZOID MAD THUNDER":
                    price = 8699.00;
                    break;
                case "ZOID ULTRASAURUS":
                    price = 9599.00;
                    break;
                case "ZOID SEISMOSAURUS BLACK TITAN":
                    price = 9999.00;
                    break;
                case "ZOID DEATH SAURER":
                    price = 8000.00;
                    break;
                case "ZOID IRON KONG":
                    price = 4499.00;
                    break;
                case "ZOID GOJULAS GUNNER":
                    price = 7499.00;
                    break;
                case "ZOID GENOSAURER":
                    price = 4499.00;
                    break;
                case "ZOID LIGER ZERO":
                    price = 3299.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield7.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield7.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield7.setText("");
        }

    }//GEN-LAST:event_jCbTransformers7ActionPerformed

    private void jCbQUANTITY6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY6ActionPerformed

    private void jPRICEfield6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield6ActionPerformed
        jPRICEfield6.setEditable(false);

    }//GEN-LAST:event_jPRICEfield6ActionPerformed

    private void jCbTransformers6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers6ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers6.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "MACROSS V1-S STEALTH":
                    price = 2699.00;
                    break;
                case "MACROSS FORTRESS VF-A":
                    price = 2699.00;
                    break;
                case "MACROSS VF 1S VALKYRIE":
                    price = 2899.00;
                    break;
                case "MACROSS ROBOTECH FRONTIER":
                    price = 2399.00;
                    break;
                case "MACROSS MESSIAH VALKYRIE":
                    price = 2399.00;
                    break;
                case "MACROSS VF-1S SUPER VALKYRIE":
                    price = 2999.00;
                    break;
                case "MACROSS ARMORED VF-1J VALKYRIE":
                    price = 2299.00;
                    break;
                case "MACROSS VF-1J U.N. SPACY":
                    price = 2399.00;
                    break;
                case "MACROSS DX VF-A FORTRESS":
                    price = 2799.00;
                    break;
                case "MACROSS VF-4G LIGHTNING III":
                    price = 2999.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield6.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield6.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield6.setText("");
        }

    }//GEN-LAST:event_jCbTransformers6ActionPerformed

    private void jPRICEfield5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield5ActionPerformed
        jPRICEfield5.setEditable(false);
    }//GEN-LAST:event_jPRICEfield5ActionPerformed

    private void jCbTransformers5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers5ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers5.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "GUNDAM HEAVY ARMS":
                    price = 2399.00;
                    break;
                case "WING GUNDAM":
                    price = 2200.00;
                    break;
                case "GUNDAM DEATHSCYTHE":
                    price = 2700.00;
                    break;
                case "GUNDAM SANDROCK":
                    price = 2700.00;
                    break;
                case "WING GUNDAM ZERO":
                    price = 2399.00;
                    break;
                case "WS-03 MAGANAC":
                    price = 2100.00;
                    break;
                case "TALLGEESE EW":
                    price = 2700.00;
                    break;
                case "GUNDAM SHENLONG":
                    price = 2100.00;
                    break;
                case "MULTI TALLGEESE FLUEGEL EW":
                    price = 5880.00;
                    break;
                case "Waltz Gundam Deathscythe":
                    price = 2750.00;
                    break;
                case "Waltz Gundam Sandrock":
                    price = 3270.00;
                    break;
                case "Waltz Gundam Heavyarms":
                    price = 7900.00;
                    break;
                case "Waltz Gundam Zero 1/144":
                    price = 2744.90;
                    break;
                case "Waltz Gundam Wing ZERO 1/100":
                    price = 5760.00;
                    break;
                case "Waltz Gundam Epyon OZ-13MS 1/100":
                    price = 3400.00;
                    break;
                case "Waltz Gundam Shenlong 1/100":
                    price = 3182.00;
                    break;
                case "Waltz Gundam Altron 1/100":
                    price = 5430.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield5.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield5.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield5.setText("");
        }

    }//GEN-LAST:event_jCbTransformers5ActionPerformed

    private void jPRICEfield4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield4ActionPerformed
        jPRICEfield4.setEditable(false);
    }//GEN-LAST:event_jPRICEfield4ActionPerformed

    private void jCbTransformers4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers4ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers4.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "PEUGEOT 24H LEMANS":
                    price = 2000.00;
                    break;
                case "TECHNINC FORD GT":
                    price = 1500.00;
                    break;
                case "MERCEDEZ-AMG F1 RACECAR":
                    price = 1800.00;
                    break;
                case "TECHNIC PORCHE 911":
                    price = 1200.00;
                    break;
                case "ASTON MARTIN PORCHE 911":
                    price = 1300.00;
                    break;
                case "MCLAREN F1":
                    price = 1700.00;
                    break;
                case "FERRARI 488 GTE":
                    price = 1600.00;
                    break;
                case "FAST & FURIOUS DODGE CHARGER":
                    price = 1600.00;
                    break;
                case "LAMBORGHINI GREEN SPORTS":
                    price = 1500.00;
                    break;
                case "TECHNIC FERRARI SF90 F1":
                    price = 1200.00;
                    break;
                case "FORMULA 1 BWT PINK F1":
                    price = 1600.00;
                    break;
                case "GHOST BUSTER":
                    price = 1100.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield4.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield4.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield4.setText("");
        }

    }//GEN-LAST:event_jCbTransformers4ActionPerformed

    private void jCbQUANTITY3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY3ActionPerformed

    }//GEN-LAST:event_jCbQUANTITY3ActionPerformed

    private void jPRICEfield3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield3ActionPerformed
        jPRICEfield3.setEditable(false);
    }//GEN-LAST:event_jPRICEfield3ActionPerformed

    private void jCbTransformers3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers3ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers3.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "VOLTES V":
                    price = 11999.00;
                    break;
                case "VOLTRON LION":
                    price = 3799.00;
                    break;
                case "DAIMOS GX-43":
                    price = 31000.00;
                    break;
                case "DX CHOGOKIN COMBATTLER V":
                    price = 25000;
                    break;
                case "GX-70 MAZINGER Z":
                    price = 8999.00;
                    break;
                case "GX-88 VOLTRON DAIRUGGER":
                    price = 28000.00;
                    break;
                case "MIGHTY ATOM ASTRO BOY DELUXE":
                    price = 5000.00;
                    break;
                case "CHOUDENSHI BIOMAN BIO ROBO":
                    price = 5000.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield3.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield3.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield3.setText("");
        }

    }//GEN-LAST:event_jCbTransformers3ActionPerformed

    private void jCbQUANTITY2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY2ActionPerformed

    private void jPRICEfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield2ActionPerformed
        jPRICEfield2.setEditable(false);

    }//GEN-LAST:event_jPRICEfield2ActionPerformed

    private void jCbTransformers2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers2ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers2.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "DEVASTATOR":
                    price = 6999.00;
                    break;
                case "BRUTICUS MAXIMUS":
                    price = 5999.00;
                    break;
                case "SUPERION":
                    price = 3999.00;
                    break;
                case "DEFENSOR":
                    price = 6499.00;
                    break;
                case "PREDAKING":
                    price = 4499.00;
                    break;
                case "VULCANUS DINOBOT":
                    price = 3999.00;
                    break;
                case "OPTIMUS/JETFIRE":
                    price = 4999.00;
                    break;
                case "BUMBLE BEE YELLOW CAMARO":
                    price = 1100.00;
                    break;
                case "SIEGE SERIES OPTIMUS W. CONTAINER":
                    price = 2555.00;
                    break;
                case "STARSCREAM":
                    price = 1176.00;
                    break;
                case "STARSCREAM JINBAO":
                    price = 1189.00;
                    break;
                case "ULTRA MAGNUS":
                    price = 1400.00;
                    break;
                case "OMEGA SUPREME":
                    price = 3900.00;
                    break;
                case "BLACKOUT":
                    price = 4198.00;
                    break;
                case "JAZZ":
                    price = 3333.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield2.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield2.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield2.setText("");
        }

    }//GEN-LAST:event_jCbTransformers2ActionPerformed

    private void jCbQUANTITY1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY1ActionPerformed

    }//GEN-LAST:event_jCbQUANTITY1ActionPerformed

    private void jPRICEfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield1ActionPerformed
        jPRICEfield1.setEditable(false);

    }//GEN-LAST:event_jPRICEfield1ActionPerformed

    private void jCbTransformers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers1ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers1.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "DEVASTATOR":
                    price = 6999.00;
                    break;
                case "BRUTICUS MAXIMUS":
                    price = 5999.00;
                    break;
                case "SUPERION":
                    price = 3999.00;
                    break;
                case "DEFENSOR":
                    price = 6499.00;
                    break;
                case "PREDAKING":
                    price = 4499.00;
                    break;
                case "VULCANUS DINOBOT":
                    price = 3999.00;
                    break;
                case "OPTIMUS/JETFIRE":
                    price = 4999.00;
                    break;
                case "JETFIRE COMMANDER 17.5cm":
                    price = 3833.00;
                    break;
                case "MEGATANK GALVATRON":
                    price = 4999.00;
                    break;
                case "SHOCKWAVE":
                    price = 2044.00;
                    break;
                case "IRONHIDE":
                    price = 1765.00;
                    break;
                case "SENTINEL PRIME":
                    price = 1380.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield1.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield1.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield1.setText("");
        }

    }//GEN-LAST:event_jCbTransformers1ActionPerformed

    private void jSUBMITbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSUBMITbtnActionPerformed
        // Retrieve user inputs for all toys
        String fullName = jTxtFullName.getText();
        String numberText = jTxtNumber.getText();

        // Validate that required fields (name and number) are filled
        if (fullName.isEmpty() || numberText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please ensure your name and number are filled in correctly.");
            return;
        }

        // Arrays to handle toy components
        JComboBox[] toySelectors = {jCbTransformers, jCbTransformers1, jCbTransformers2, jCbTransformers3, jCbTransformers4,
            jCbTransformers5, jCbTransformers6, jCbTransformers7, jCbTransformers8, jCbTransformers9,
            jCbTransformers10, jCbTransformers11};
        JComboBox[] quantitySelectors = {jCbQUANTITY, jCbQUANTITY1, jCbQUANTITY2, jCbQUANTITY3, jCbQUANTITY4,
            jCbQUANTITY5, jCbQUANTITY6, jCbQUANTITY7, jCbQUANTITY8, jCbQUANTITY9,
            jCbQUANTITY10, jCbQUANTITY11};
        JTextField[] priceFields = {jPRICEfield, jPRICEfield1, jPRICEfield2, jPRICEfield3, jPRICEfield4,
            jPRICEfield5, jPRICEfield6, jPRICEfield7, jPRICEfield8, jPRICEfield9,
            jPRICEfield10, jPRICEfield11};

        StringBuilder message = new StringBuilder();
        boolean hasEmptyComboBox = false;
        double subtotal = 0;
        int totalQuantity = 0;
        StringBuilder combinedToys = new StringBuilder();

        // Loop through the toy selections
        for (int i = 0; i < toySelectors.length; i++) {
            ToyDetails toy = getToyDetails(toySelectors[i], quantitySelectors[i], priceFields[i]);

            // Validate toy selections and quantities
            if (toy.quantity > 0 && (toy.selectedToy == null || "-".equals(toy.selectedToy))) {
                message.append("Toy ").append(i + 1).append(" is selected without a valid toy selection.\n");
                hasEmptyComboBox = true;
            }
            if (toy.selectedToy != null && !"-".equals(toy.selectedToy) && toy.quantity == 0) {
                message.append("Toy ").append(i + 1).append(" is selected but quantity is zero.\n");
                hasEmptyComboBox = true;
            }

            // Calculate subtotal and total quantity
            if (!"-".equals(quantitySelectors[i].getSelectedItem())) {
                subtotal += toy.price * toy.quantity;
                totalQuantity += toy.quantity;
                combinedToys.append(toy.selectedToy).append("\n");

            }
        }

        // Notify the user if there are any combo boxes with unselected toys or quantities
        if (hasEmptyComboBox) {
            JOptionPane.showMessageDialog(this, message.toString());
            return;
        }

        // If subtotal is zero, notify the user
        if (subtotal == 0) {
            JOptionPane.showMessageDialog(this, "Subtotal is zero. Please select at least one toy.");
            jSUBTOTALfield.setText("");
            jNETPRICEfield.setText("");
            jTAXPAIDfield.setText("");
            jORDERfield.setText("");
            ITEMAREA.setText("");
            return;
        }

        // Update UI fields
        double tax = subtotal * 0.02; // 2% tax
        double netPrice = subtotal + tax;

        // Format numbers with commas
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        // Update fields with formatted values
        jSUBTOTALfield.setText("Php " + formatter.format(subtotal));
        jNETPRICEfield.setText("Php " + formatter.format(netPrice));
        jTAXPAIDfield.setText("Php " + formatter.format(tax));
        jORDERfield.setText(String.valueOf(totalQuantity));

        // Update the ITEMAREA field with the combined string
        ITEMAREA.setText(combinedToys.toString());

        // Show confirmation dialog before proceeding
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure of your inputs? Please check before proceeding!",
                "Confirm Input",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return; // If the user chooses 'No', exit the method
        }

        // Disable editing after submission
        disableInputs();
        // Your existing submission logic here...

        // Set the flag to true when submission is successful
        isSubmitted = true;
        // Disable the submit button after successful submission
        jSUBMITbtn.setEnabled(false); // Disable the submit button
    }

    private void disableInputs() {
        jTxtFullName.setEditable(false);
        jTxtNumber.setEditable(false);
        for (JComboBox toySelector : new JComboBox[]{jCbTransformers, jCbTransformers1, jCbTransformers2, jCbTransformers3, jCbTransformers4, jCbTransformers5, jCbTransformers6,
            jCbTransformers7, jCbTransformers8, jCbTransformers9, jCbTransformers10, jCbTransformers11}) {
            toySelector.setEnabled(false);
        }
        for (JComboBox quantitySelector : new JComboBox[]{jCbQUANTITY, jCbQUANTITY1, jCbQUANTITY2, jCbQUANTITY3, jCbQUANTITY4, jCbQUANTITY5, jCbQUANTITY6,
            jCbQUANTITY7, jCbQUANTITY8, jCbQUANTITY9, jCbQUANTITY10, jCbQUANTITY11}) {
            quantitySelector.setEnabled(false);
        }
        for (JTextField priceField : new JTextField[]{jPRICEfield, jPRICEfield1, jPRICEfield2, jPRICEfield3, jPRICEfield4, jPRICEfield5,
            jPRICEfield6, jPRICEfield7, jPRICEfield8, jPRICEfield9, jPRICEfield10, jPRICEfield11}) {
            priceField.setEditable(false);
        }
    }

    // Method to get toy details
    private ToyDetails getToyDetails(JComboBox<String> toyComboBox, JComboBox<String> quantityComboBox, JTextField priceField) {
        ToyDetails toyDetails = new ToyDetails();
        toyDetails.selectedToy = (String) toyComboBox.getSelectedItem();
        toyDetails.quantityText = (String) quantityComboBox.getSelectedItem();
        toyDetails.priceText = priceField.getText();

        // Handle empty or invalid values
        try {
            if (toyDetails.priceText.isEmpty()) {
                toyDetails.price = 0;
            } else {
                toyDetails.price = Double.parseDouble(toyDetails.priceText.replaceAll("[^0-9.]", ""));
            }

            if (toyDetails.quantityText == null || toyDetails.quantityText.equals("-")) {
                toyDetails.quantity = 0;
            } else {
                toyDetails.quantity = Integer.parseInt(toyDetails.quantityText);
            }

        } catch (NumberFormatException e) {
            toyDetails.price = 0;
            toyDetails.quantity = 0;
        }

        return toyDetails;

    }//GEN-LAST:event_jSUBMITbtnActionPerformed

    private void jSUBTOTALfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSUBTOTALfieldActionPerformed
        // Similarly, initialize other fields if needed
        jSUBTOTALfield.setEditable(false);

    }//GEN-LAST:event_jSUBTOTALfieldActionPerformed

    private void jCbQUANTITYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITYActionPerformed

    }//GEN-LAST:event_jCbQUANTITYActionPerformed

    private void jPRICEfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfieldActionPerformed
        jPRICEfield.setEditable(false);

    }//GEN-LAST:event_jPRICEfieldActionPerformed

    private void jCbTransformersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformersActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "DEVASTATOR":
                    price = 6999.00;
                    break;
                case "BRUTICUS MAXIMUS":
                    price = 5999.00;
                    break;
                case "SUPERION":
                    price = 3999.00;
                    break;
                case "DEFENSOR":
                    price = 6499.00;
                    break;
                case "PREDAKING":
                    price = 4499.00;
                    break;
                case "VULCANUS DINOBOT":
                    price = 3999.00;
                    break;
                case "OPTIMUS/JETFIRE":
                    price = 4999.00;
                    break;
                case "METROPLEX":
                    price = 13999.00;
                    break;
                case "BARICADE":
                    price = 1600.00;
                    break;
                case "GRIMLOCK AOYI":
                    price = 3650.00;
                    break;
                case "THE FALLEN":
                    price = 4451.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield.setText(formattedPrice);

            // The jSELECTEDITEMfield will not be updated
        } else {
            // If no toy is selected, clear the price field
            jPRICEfield.setText("");
        }

    }//GEN-LAST:event_jCbTransformersActionPerformed

    private void jTxtNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNumberActionPerformed

    }//GEN-LAST:event_jTxtNumberActionPerformed

    private void jTxtFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFullNameActionPerformed

    private void REMOVEITEMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMOVEITEMSActionPerformed
        // Reset all toy selection combo boxes to the default value "-"
        JComboBox[] toySelectors = {
            jCbTransformers, jCbTransformers1, jCbTransformers2, jCbTransformers3, jCbTransformers4,
            jCbTransformers5, jCbTransformers6, jCbTransformers7, jCbTransformers8, jCbTransformers9,
            jCbTransformers10, jCbTransformers11
        };

        JComboBox[] quantitySelectors = {
            jCbQUANTITY, jCbQUANTITY1, jCbQUANTITY2, jCbQUANTITY3, jCbQUANTITY4,
            jCbQUANTITY5, jCbQUANTITY6, jCbQUANTITY7, jCbQUANTITY8, jCbQUANTITY9,
            jCbQUANTITY10, jCbQUANTITY11
        };
        // Resetting toy and quantity selections
        resetComboBoxes(toySelectors, "-");
        resetComboBoxes(quantitySelectors, "-");

        // Clear the price fields
        JTextField[] priceFields = {
            jPRICEfield, jPRICEfield1, jPRICEfield2, jPRICEfield3, jPRICEfield4,
            jPRICEfield5, jPRICEfield6, jPRICEfield7, jPRICEfield8, jPRICEfield9,
            jPRICEfield10, jPRICEfield11
        };
        clearTextFields(priceFields);

        // Clear subtotal, tax, net price, and order quantity
        clearSubtotalFields();

        // Re-enable inputs for the user to make new selections
        enableInputs();

        // Re-enable the submit button after resetting
        jSUBMITbtn.setEnabled(true); // Enable the submit button again
    }

// Method to reset combo boxes to the default item
    private void resetComboBoxes(JComboBox[] comboBoxes, String defaultItem) {
        for (JComboBox comboBox : comboBoxes) {
            comboBox.setSelectedItem(defaultItem); // Reset each combo box
        }
    }

// Method to clear all text fields
    private void clearTextFields(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            textField.setText(""); // Clear each text field
        }
    }

// Method to clear subtotal and related fields
    private void clearSubtotalFields() {
        jSUBTOTALfield.setText("");   // Clear subtotal
        jNETPRICEfield.setText("");   // Clear net price
        jTAXPAIDfield.setText("");    // Clear tax
        jORDERfield.setText("");      // Clear order quantity
        ITEMAREA.setText("");         // Clear item list display
    }

// Method to re-enable all input fields
    private void enableInputs() {
        // Enable toy selectors
        for (JComboBox toySelector : new JComboBox[]{
            jCbTransformers, jCbTransformers1, jCbTransformers2, jCbTransformers3, jCbTransformers4,
            jCbTransformers5, jCbTransformers6, jCbTransformers7, jCbTransformers8, jCbTransformers9,
            jCbTransformers10, jCbTransformers11
        }) {
            toySelector.setEnabled(true);
        }

        // Enable quantity selectors
        for (JComboBox quantitySelector : new JComboBox[]{
            jCbQUANTITY, jCbQUANTITY1, jCbQUANTITY2, jCbQUANTITY3, jCbQUANTITY4,
            jCbQUANTITY5, jCbQUANTITY6, jCbQUANTITY7, jCbQUANTITY8, jCbQUANTITY9,
            jCbQUANTITY10, jCbQUANTITY11
        }) {
            quantitySelector.setEnabled(true);
        }

        // Enable price fields for editing
        for (JTextField priceField : new JTextField[]{
            jPRICEfield, jPRICEfield1, jPRICEfield2, jPRICEfield3, jPRICEfield4,
            jPRICEfield5, jPRICEfield6, jPRICEfield7, jPRICEfield8, jPRICEfield9,
            jPRICEfield10, jPRICEfield11
        }) {
            priceField.setEditable(true);
        }

        // Re-enable name and number fields
        jTxtFullName.setEditable(true);
        jTxtNumber.setEditable(true);


    }//GEN-LAST:event_REMOVEITEMSActionPerformed

    private void jCbTransformers10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers10ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers10.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "MSZ AUDI RS5 DTM PINK 1:32":
                    price = 299.00;
                    break;
                case "MSZ AUDI RS5 DTM ORANGE 1:32":
                    price = 299.00;
                    break;
                case "MSZ AUDI RS5 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "AUDI R8 LMS RED 1:32":
                    price = 299.00;
                    break;
                case "MERCEDES AMG C63 BLUE 1:32":
                    price = 299.00;
                    break;
                case "MERCEDES AMG C63 BLACK 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM WHITE 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM YELLOW 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "BMW M4 DTM WHITE 1:32":
                    price = 299.00;
                    break;
                case "BMW M4 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "BENTLEY CONTINENTAL GT3 WT 1:32":
                    price = 299.00;
                    break;
                case "BENTLEY CONTINENTAL GT3 BLK 1:32":
                    price = 299.00;
                    break;
                case "BMW M6 PS4 BLUE 1:32":
                    price = 399.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield10.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield10.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield10.setText("");
        }
    }//GEN-LAST:event_jCbTransformers10ActionPerformed

    private void jPRICEfield10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield10ActionPerformed
        jPRICEfield10.setEditable(false);

    }//GEN-LAST:event_jPRICEfield10ActionPerformed

    private void jCbQUANTITY10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY10ActionPerformed

    private void jCbTransformers11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTransformers11ActionPerformed
        // Get the selected toy
        String selectedToy = (String) jCbTransformers11.getSelectedItem();

        // Create a NumberFormat instance for currency formatting with the PHP locale
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("en", "PH"));

        // Initialize price variable
        double price = 0.0;

        // Set the price based on the selected toy
        if (selectedToy != null) {
            switch (selectedToy) {
                case "MSZ AUDI RS5 DTM PINK 1:32":
                    price = 299.00;
                    break;
                case "MSZ AUDI RS5 DTM ORANGE 1:32":
                    price = 299.00;
                    break;
                case "MSZ AUDI RS5 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "AUDI R8 LMS RED 1:32":
                    price = 299.00;
                    break;
                case "MERCEDES AMG C63 BLUE 1:32":
                    price = 299.00;
                    break;
                case "MERCEDES AMG C63 BLACK 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM WHITE 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM YELLOW 1:32":
                    price = 299.00;
                    break;
                case "BMW M3 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "BMW M4 DTM WHITE 1:32":
                    price = 299.00;
                    break;
                case "BMW M4 DTM BLACK 1:32":
                    price = 299.00;
                    break;
                case "BENTLEY CONTINENTAL GT3 WT 1:32":
                    price = 299.00;
                    break;
                case "BENTLEY CONTINENTAL GT3 BLK 1:32":
                    price = 299.00;
                    break;
                case "BMW M6 PS4 BLUE 1:32":
                    price = 399.00;
                    break;
                default:
                    // If no valid toy is selected, clear the price field
                    jPRICEfield11.setText("");
                    return; // Exit method if no valid toy is selected
            }

            // Format the price and set it to the price field
            String formattedPrice = String.format("Php %s", currencyFormat.format(price));
            jPRICEfield11.setText(formattedPrice);
        } else {
            // If no toy is selected, clear the price and selected item fields
            jPRICEfield11.setText("");
        }

    }//GEN-LAST:event_jCbTransformers11ActionPerformed

    private void jPRICEfield11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPRICEfield11ActionPerformed
        jPRICEfield11.setEditable(false);
    }//GEN-LAST:event_jPRICEfield11ActionPerformed

    private void jCbQUANTITY11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQUANTITY11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQUANTITY11ActionPerformed

    private void PASSWORDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PASSWORDActionPerformed
        // Get the password entered by the user
        String enteredPassword = new String(PASSWORD.getPassword());

        // Define the correct password
        String correctPassword = "mikmikjordan";  // Replace with the actual correct password

        // Check if the entered password is correct
        if (enteredPassword.equals(correctPassword)) {
            // Password is correct, enable the jSHOWlogs button
            jSHOWlogs.setEnabled(true); // Enable the button
            JOptionPane.showMessageDialog(null, "Access granted. You can now view logs.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            // Optionally, clear the password field after successful login
            PASSWORD.setText("");
        } else {
            // Password is incorrect, show an error message
            JOptionPane.showMessageDialog(null, "Incorrect password. Access denied.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Optionally, clear the password field
            PASSWORD.setText("");
        }

    }//GEN-LAST:event_PASSWORDActionPerformed

    // Add a document listener to the subtotal field
    private void initializeDefaultReceipt() {
        // Get the current date and time for the placeholder receipt
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
        Date currentDate = new Date();
        String formattedDate = dateFormatter.format(currentDate);
        String formattedTime = timeFormatter.format(currentDate);

        // Prepare a placeholder receipt indicating no transaction has occurred
        String defaultReceipt = String.format(
                "=======================================================\n"
                + "  TRANSFORMERS COMBINER TOYS / ROBOT DEFORMATION TOYS  \n"
                + "         FAIRVIEW, NORTH FAIRVIEW, QUEZON CITY         \n"
                + "=======================================================\n"
                + "  RECIEPT NO.: \n"
                + " Please complete the transaction to generate a receipt.\n"
                + "        NAME: %-30s\n"
                + "        NUMBER: %-29s\n"
                + "        SELECTED TOY/S: %-20s\n"
                + "\n"
                + " [NO TRANSACTION YET]\n"
                + " Please complete the transaction to generate a receipt.\n"
                + "\n"
                + "\n"
                + "        NET PRICE: %-23s\n"
                + " Please complete the transaction to generate a receipt.\n"
                + "        AMOUNT OF CASH: %-17s\n"
                + "        CHANGE: %-30s\n"
                + "\n"
                + "        DATE: %-30s\n"
                + "        TIME: %-30s\n"
                + "\n"
                + " [NO TRANSACTION YET]\n"
                + " Please complete the transaction to generate a receipt.\n"
                + "\n"
                + "=======================================================\n"
                + "      **** THANK YOU FOR BUYING ENGAGE TOYS! ****      \n"
                + "    https://web.facebook.com/paulomiguel.cortez.54     \n"
                + "=======================================================\n",
                "N/A",
                "N/A",
                "No item selected",
                "Php 0.00",
                "Php 0.00",
                "Php 0.00",
                formattedDate,
                formattedTime
        );

        // Set the default receipt in the JTextArea
        jTextAreaRESIBO.setText(defaultReceipt);

        // Set the background color to a darker color for contrast (optional)
        jTextAreaRESIBO.setBackground(java.awt.Color.WHITE);

        // Disable editing of the receipt text area at the start
        jTextAreaRESIBO.setEditable(false);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EngageToy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AREAPANE;
    private javax.swing.JButton EXIT_CLOSE;
    private javax.swing.JTextArea ITEMAREA;
    private javax.swing.JPasswordField PASSWORD;
    private javax.swing.JButton REMOVEITEMS;
    private javax.swing.JTextField jAMOUNTCASHfield;
    private javax.swing.JTextField jCHANGEfield;
    private javax.swing.JComboBox<String> jCbQUANTITY;
    private javax.swing.JComboBox<String> jCbQUANTITY1;
    private javax.swing.JComboBox<String> jCbQUANTITY10;
    private javax.swing.JComboBox<String> jCbQUANTITY11;
    private javax.swing.JComboBox<String> jCbQUANTITY2;
    private javax.swing.JComboBox<String> jCbQUANTITY3;
    private javax.swing.JComboBox<String> jCbQUANTITY4;
    private javax.swing.JComboBox<String> jCbQUANTITY5;
    private javax.swing.JComboBox<String> jCbQUANTITY6;
    private javax.swing.JComboBox<String> jCbQUANTITY7;
    private javax.swing.JComboBox<String> jCbQUANTITY8;
    private javax.swing.JComboBox<String> jCbQUANTITY9;
    private javax.swing.JComboBox<String> jCbTransformers;
    private javax.swing.JComboBox<String> jCbTransformers1;
    private javax.swing.JComboBox<String> jCbTransformers10;
    private javax.swing.JComboBox<String> jCbTransformers11;
    private javax.swing.JComboBox<String> jCbTransformers2;
    private javax.swing.JComboBox<String> jCbTransformers3;
    private javax.swing.JComboBox<String> jCbTransformers4;
    private javax.swing.JComboBox<String> jCbTransformers5;
    private javax.swing.JComboBox<String> jCbTransformers6;
    private javax.swing.JComboBox<String> jCbTransformers7;
    private javax.swing.JComboBox<String> jCbTransformers8;
    private javax.swing.JComboBox<String> jCbTransformers9;
    private javax.swing.JLabel jLCurrentDate;
    private javax.swing.JLabel jLCurrentTime;
    private javax.swing.JLabel jLDate;
    private javax.swing.JLabel jLTime;
    private javax.swing.JTextField jNETPRICEfield;
    private javax.swing.JTextField jORDERfield;
    private javax.swing.JButton jPAYbtn;
    private javax.swing.JTextField jPRICEfield;
    private javax.swing.JTextField jPRICEfield1;
    private javax.swing.JTextField jPRICEfield10;
    private javax.swing.JTextField jPRICEfield11;
    private javax.swing.JTextField jPRICEfield2;
    private javax.swing.JTextField jPRICEfield3;
    private javax.swing.JTextField jPRICEfield4;
    private javax.swing.JTextField jPRICEfield5;
    private javax.swing.JTextField jPRICEfield6;
    private javax.swing.JTextField jPRICEfield7;
    private javax.swing.JTextField jPRICEfield8;
    private javax.swing.JTextField jPRICEfield9;
    private javax.swing.JButton jPRINTreceipt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jRESETbtn;
    private javax.swing.JButton jSHOWlogs;
    private javax.swing.JButton jSUBMITbtn;
    private javax.swing.JTextField jSUBTOTALfield;
    private javax.swing.JTextField jTAXPAIDfield;
    private javax.swing.JTextArea jTextAreaRESIBO;
    private javax.swing.JTextField jTxtFullName;
    private javax.swing.JTextField jTxtNumber;
    // End of variables declaration//GEN-END:variables
}
