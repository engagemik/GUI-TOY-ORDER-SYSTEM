package OrderSytemToy;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


public class Tables1 extends javax.swing.JFrame {
    
    public Tables1() {
    
        initComponents();
        setupTABLE_CUSTO_INFO(); 
        setupTablePayments();
        setComponentProperties();
        
        SEARCH_CUSTOMER.setText("Ex: Sname or Fname or RECEIPT");
        SEARCH_CUSTOMER.setForeground(Color.GRAY); 
        SEARCH_WHO_PAID.setText("Ex: Sname or Fname or date or RECEIPT");
        SEARCH_WHO_PAID.setForeground(Color.GRAY);
        NET_ALL_TOTAL_TEXT.setText("GET NET PRICE from the CUSTOMER PAYMENT");
        NET_ALL_TOTAL_TEXT.setForeground(Color.GRAY);
        
        configureTableColumns();
        addCopyFunctionalityToTable(TABLE_CUSTO_INFO);
        addCopyFunctionalityToTable(TABLE_PAYMENTS);
                                                   
            // Add FocusListener to handle the placeholder behavior
            SEARCH_CUSTOMER.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (SEARCH_CUSTOMER.getText().equals("Ex: Sname or Fname or RECEIPT")) {
                    SEARCH_CUSTOMER.setText("");  // Clear the placeholder
                    SEARCH_CUSTOMER.setForeground(Color.BLACK); // Set typing color to black
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (SEARCH_CUSTOMER.getText().isEmpty()) {
                    SEARCH_CUSTOMER.setText("Ex: Sname or Fname or RECEIPT");  // Reset placeholder if no text
                    SEARCH_CUSTOMER.setForeground(Color.GRAY);  // Reset color to gray
                }
            }    
            });
            
            // Add FocusListener to handle the placeholder behavior
            SEARCH_WHO_PAID.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (SEARCH_WHO_PAID.getText().equals("Ex: Sname or Fname or date or RECEIPT")) {
                    SEARCH_WHO_PAID.setText("");  // Clear the placeholder
                    SEARCH_WHO_PAID.setForeground(Color.BLACK); // Set typing color to black
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (SEARCH_WHO_PAID.getText().isEmpty()) {
                    SEARCH_WHO_PAID.setText("Ex: Sname or Fname or date or RECEIPT");  // Reset placeholder if no text
                    SEARCH_WHO_PAID.setForeground(Color.GRAY);  // Reset color to gray
                }
            }    
            }); 
            
            SwingUtilities.invokeLater(new Runnable() {    
            @Override
            public void run() {
                populateTableCUSTO_INFO();
                populateTablePAYMENTS();
            }
        });
    }
    
    private void setComponentProperties() { 
            NET_ALL_TOTAL_TEXT.setEditable(false);
            NET_ALL_TOTAL_TEXT.setBackground(java.awt.Color.WHITE);
            NET_ALL_TOTAL_TEXT.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));

    }
    
    private void configureTableColumns() {
            TABLE_CUSTO_INFO.getColumnModel().getColumn(0).setPreferredWidth(2);
            TABLE_CUSTO_INFO.getColumnModel().getColumn(1).setPreferredWidth(500);
            TABLE_CUSTO_INFO.getColumnModel().getColumn(2).setPreferredWidth(100);
            TABLE_CUSTO_INFO.getColumnModel().getColumn(3).setPreferredWidth(70);
                        
            TABLE_PAYMENTS.getColumnModel().getColumn(0).setPreferredWidth(10);
            TABLE_PAYMENTS.getColumnModel().getColumn(1).setPreferredWidth(150);
            TABLE_PAYMENTS.getColumnModel().getColumn(2).setPreferredWidth(700);
            TABLE_PAYMENTS.getColumnModel().getColumn(3).setPreferredWidth(1);
            TABLE_PAYMENTS.getColumnModel().getColumn(4).setPreferredWidth(40);
            TABLE_PAYMENTS.getColumnModel().getColumn(5).setPreferredWidth(90);
            TABLE_PAYMENTS.getColumnModel().getColumn(6).setPreferredWidth(85);   
    }
// Now define the method to set up the copy drag functionality

    private void setupTablePayments() {
    // Enable mouse copy drag on the table
    TABLE_PAYMENTS.setCellSelectionEnabled(true); // Allow selecting individual cells
    TABLE_PAYMENTS.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); // Allow continuous selection of rows or columns

    // Enable copy action using keyboard (Ctrl+C)
    InputMap inputMap = TABLE_PAYMENTS.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    KeyStroke copyKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK);
    inputMap.put(copyKeyStroke, "copy");

    ActionMap actionMap = TABLE_PAYMENTS.getActionMap();
    actionMap.put("copy", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get selected cells
            int[] selectedRows = TABLE_PAYMENTS.getSelectedRows();
            int[] selectedColumns = TABLE_PAYMENTS.getSelectedColumns();

            if (selectedRows.length == 0 || selectedColumns.length == 0) {
                return; // No selection
            }

            StringBuilder sb = new StringBuilder();

            // Copy selected cells' contents
            for (int row : selectedRows) {
                for (int col : selectedColumns) {
                    Object cellValue = TABLE_PAYMENTS.getValueAt(row, col);
                    sb.append(cellValue != null ? cellValue.toString() : "").append("\t");
                }
                sb.append("\n");
            }

            // Copy the text to the system clipboard
            StringSelection selection = new StringSelection(sb.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    });
}    

 private void setupTABLE_CUSTO_INFO() {
    // Enable mouse copy drag on the table
    TABLE_CUSTO_INFO.setCellSelectionEnabled(true); // Allow selecting individual cells
    TABLE_CUSTO_INFO.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); // Allow continuous selection of rows or columns

    // Enable copy action using keyboard (Ctrl+C)
    InputMap inputMap = TABLE_CUSTO_INFO.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    KeyStroke copyKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK);
    inputMap.put(copyKeyStroke, "copy");

    ActionMap actionMap = TABLE_CUSTO_INFO.getActionMap();
    actionMap.put("copy", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get selected cells
            int[] selectedRows = TABLE_CUSTO_INFO.getSelectedRows();
            int[] selectedColumns = TABLE_CUSTO_INFO.getSelectedColumns();

            if (selectedRows.length == 0 || selectedColumns.length == 0) {
                return; // No selection
            }

            StringBuilder sb = new StringBuilder();

            // Copy selected cells' contents
            for (int row : selectedRows) {
                for (int col : selectedColumns) {
                    Object cellValue = TABLE_CUSTO_INFO.getValueAt(row, col);
                    sb.append(cellValue != null ? cellValue.toString() : "").append("\t");
                }
                sb.append("\n");
            }

            // Copy the text to the system clipboard
            StringSelection selection = new StringSelection(sb.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    });
}        
    

 
 

    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jTablePanel = new javax.swing.JPanel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jPInfoLabel = new javax.swing.JLabel();
        javax.swing.JLabel jPInfoLabel1 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        TABLE_PAYMENTS = new javax.swing.JTable();
        jBtnRefresh = new javax.swing.JButton();
        DELETE_INFO = new javax.swing.JButton();
        jBntBacktoEinfo = new javax.swing.JButton();
        DELETE_PAYMENTS = new javax.swing.JButton();
        EDIT_INFO = new javax.swing.JButton();
        CUSTOMER_PAYedit = new javax.swing.JButton();
        javax.swing.JLabel jSearchLBL = new javax.swing.JLabel();
        SEARCH_CUSTOMER = new javax.swing.JTextField();
        jSearchLBL1 = new javax.swing.JLabel();
        SEARCH_WHO_PAID = new javax.swing.JTextField();
        SCROLL_CUS_INFO = new javax.swing.JScrollPane();
        TABLE_CUSTO_INFO = new javax.swing.JTable();
        NET_ALL_TOTAL_TEXT = new javax.swing.JTextField();
        NET_all_TOTAL_btn = new javax.swing.JButton();
        jBAGONG_BUWAN = new javax.swing.JTextField();
        jSearchLBL2 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jTBlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Tabledashboard");
        setMinimumSize(new java.awt.Dimension(1358, 800));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1380, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTablePanel.setPreferredSize(new java.awt.Dimension(1380, 710));
        jTablePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(207, 207, 253));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPInfoLabel.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jPInfoLabel.setText("CUSTOMER INFORMATION");

        jPInfoLabel1.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jPInfoLabel1.setText("CUSTOMER PAYMENT");

        TABLE_PAYMENTS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {}, // Start with an empty data array
            new String [] {
                "REC ID", "FULL NAME", "SELECTED ITEM/S", "QTY", "NET PRICE", "PAYMENT DATE", "RECEIPT NO."}

        ));
        jScrollPane1.setViewportView(TABLE_PAYMENTS);

        jBtnRefresh.setText("Refresh");
        jBtnRefresh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRefreshActionPerformed(evt);
            }
        });

        DELETE_INFO.setText("Delete Personal Info");
        DELETE_INFO.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        DELETE_INFO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETE_INFOActionPerformed(evt);
            }
        });

        jBntBacktoEinfo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBntBacktoEinfo.setText("Back to Main");
        jBntBacktoEinfo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBntBacktoEinfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBntBacktoEinfoActionPerformed(evt);
            }
        });

        DELETE_PAYMENTS.setText("Delete Payments");
        DELETE_PAYMENTS.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        DELETE_PAYMENTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETE_PAYMENTSActionPerformed(evt);
            }
        });

        EDIT_INFO.setText("Edit Customer Information");
        EDIT_INFO.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        EDIT_INFO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDIT_INFOActionPerformed(evt);
            }
        });

        CUSTOMER_PAYedit.setText("Edit Customer Payments");
        CUSTOMER_PAYedit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CUSTOMER_PAYedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUSTOMER_PAYeditActionPerformed(evt);
            }
        });

        jSearchLBL.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jSearchLBL.setText("Search:");

        SEARCH_CUSTOMER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEARCH_CUSTOMERActionPerformed(evt);
            }
        });

        jSearchLBL1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jSearchLBL1.setText("Search:");

        SEARCH_WHO_PAID.setText("Ex: Sname or Fname or RESULT or DATE");
        SEARCH_WHO_PAID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEARCH_WHO_PAIDActionPerformed(evt);
            }
        });

        TABLE_CUSTO_INFO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "RECORD ID", "FULL NAME", "NUMBER", "RECEIPT NUMBER"
            }
        ));
        SCROLL_CUS_INFO.setViewportView(TABLE_CUSTO_INFO);

        NET_ALL_TOTAL_TEXT.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        NET_ALL_TOTAL_TEXT.setForeground(new java.awt.Color(0, 102, 0));
        NET_ALL_TOTAL_TEXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NET_ALL_TOTAL_TEXTActionPerformed(evt);
            }
        });

        NET_all_TOTAL_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NET_all_TOTAL_btn.setText("TOTAL SALES");
        NET_all_TOTAL_btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        NET_all_TOTAL_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NET_all_TOTAL_btnActionPerformed(evt);
            }
        });

        jBAGONG_BUWAN.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jBAGONG_BUWAN.setForeground(new java.awt.Color(0, 102, 0));
        jBAGONG_BUWAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAGONG_BUWANActionPerformed(evt);
            }
        });

        jSearchLBL2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jSearchLBL2.setText("Search DATE:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jBtnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jBntBacktoEinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(327, 327, 327)
                        .addComponent(jSearchLBL2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAGONG_BUWAN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(NET_all_TOTAL_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NET_ALL_TOTAL_TEXT, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(535, 535, 535)
                                .addComponent(jSearchLBL)
                                .addGap(4, 4, 4)
                                .addComponent(SEARCH_CUSTOMER, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EDIT_INFO, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DELETE_INFO, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SCROLL_CUS_INFO, javax.swing.GroupLayout.PREFERRED_SIZE, 1325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(553, 553, 553)
                                .addComponent(jSearchLBL1)
                                .addGap(4, 4, 4)
                                .addComponent(SEARCH_WHO_PAID, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CUSTOMER_PAYedit, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DELETE_PAYMENTS, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1325, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSearchLBL, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SEARCH_CUSTOMER, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(SCROLL_CUS_INFO, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jPInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(CUSTOMER_PAYedit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DELETE_PAYMENTS, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SEARCH_WHO_PAID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSearchLBL1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBAGONG_BUWAN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NET_ALL_TOTAL_TEXT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NET_all_TOTAL_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSearchLBL2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(588, 588, 588)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBntBacktoEinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(DELETE_INFO, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(EDIT_INFO, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {NET_ALL_TOTAL_TEXT, jBAGONG_BUWAN});

        jTablePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 87, 1350, 650));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(1336, 70));

        jTBlabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jTBlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTBlabel.setText("CUSTOMER LIST | ORDER LIST");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTBlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1336, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTBlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTablePanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 1350, -1));

        getContentPane().add(jTablePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    
    
    
    
    
    private void SEARCH_WHO_PAIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEARCH_WHO_PAIDActionPerformed
        String searchTerm = SEARCH_WHO_PAID.getText().trim(); // Get the search term

            if (searchTerm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name, item, or receipt to search.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Split the search term into individual terms for more flexible searching (name, item, date)
            String[] searchTerms = searchTerm.split("\\s*,\\s*|\\s+");

            // Build SQL query dynamically based on the number of search terms
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT * FROM table1_details WHERE (");

            // Loop through the search terms and add SQL conditions
            for (int i = 0; i < searchTerms.length; i++) {
                if (i > 0) {
                    sqlBuilder.append(" OR "); // Add 'OR' between multiple search terms
                }
                // Use LIKE operator to match partial terms in name, receipt, item, or payment date
                sqlBuilder.append("(LOWER(full_name) LIKE ? OR LOWER(receipt_number) LIKE ? OR LOWER(selected_item_name) LIKE ? OR LOWER(payment_date) LIKE ?)");
            }
            sqlBuilder.append(")");

            // Establish a database connection
            DbConnectionTOY dbConn = new DbConnectionTOY();
            Connection conn = dbConn.getConnection();

            if (conn == null) {
                System.out.println("Database connection is null.");
                return; // Exit if connection fails
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlBuilder.toString())) {
                // Set the search terms in the prepared statement
                for (int i = 0; i < searchTerms.length; i++) {
                    String term = "%" + searchTerms[i].toLowerCase() + "%";
                    stmt.setString(4 * i + 1, term); // Set for full_name
                    stmt.setString(4 * i + 2, term); // Set for receipt_number
                    stmt.setString(4 * i + 3, term); // Set for selected_item_name               
                    stmt.setString(4 * i + 4, term); // Set for payment_date as a string (date search)
                }

                // Execute the query
                try (ResultSet rs = stmt.executeQuery()) {
                    // Get the model for the TABLE_PAYMENTS
                    DefaultTableModel model = (DefaultTableModel) TABLE_PAYMENTS.getModel();
                    model.setRowCount(0); // Clear existing rows

                    // Iterate through the result set and populate the table
                    while (rs.next()) {
                        int recordId = rs.getInt("record_id");
                        String fullName = rs.getString("full_name");
                        String selectedItemName = rs.getString("selected_item_name");
                        int qty = rs.getInt("qty");
                        double netPrice = rs.getDouble("net_price");
                        String paymentDate = rs.getString("payment_date");
                        String receiptNumber = rs.getString("receipt_number");

                        // Add a new row to the table model
                        model.addRow(new Object[]{recordId, fullName, selectedItemName, qty, netPrice, paymentDate, receiptNumber});
                    }

                    // Show a message if no results were found
                    if (model.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "No records found for the search term.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error fetching payment records: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Error fetching payment records: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                dbConn.closeConnection(); // Ensure the connection is closed after operations
            }

    }//GEN-LAST:event_SEARCH_WHO_PAIDActionPerformed

    private void SEARCH_CUSTOMERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEARCH_CUSTOMERActionPerformed
                                           
        // Get the search term from a text field (assuming there is a JTextField named SEARCH_CUSTOMER)
        String searchTerm = SEARCH_CUSTOMER.getText().trim(); // Adjust the name of your search input field accordingly

        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name to search.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Split the search term into individual terms for a more flexible search
        String[] searchTerms = searchTerm.split("\\s*,\\s*|\\s+");

        // Build SQL query dynamically based on the number of search terms
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM table1 WHERE ");
        sqlBuilder.append("(");

        for (int i = 0; i < searchTerms.length; i++) {
            if (i > 0) {
                sqlBuilder.append(" OR "); // Add OR between terms
            }
            // Append conditions for surname, firstname, and gender with case-insensitive comparison
            sqlBuilder.append("LOWER(name) LIKE ? OR LOWER(number) LIKE ? OR LOWER(receipt_number) LIKE ?");
        }
        sqlBuilder.append(")");

        // Establish a database connection
        DbConnectionTOY dbConn = new DbConnectionTOY();
        Connection conn = dbConn.getConnection();

        if (conn == null) {
            System.out.println("Database connection is null.");
            return; // Exit if connection fails
        }

        // Prepare the SQL query
        try (PreparedStatement stmt = conn.prepareStatement(sqlBuilder.toString())) {
            // Set the parameters for the prepared statement
            for (int i = 0; i < searchTerms.length; i++) {
                String likePattern = "%" + searchTerms[i].toLowerCase() + "%"; // Use lowercase for case-insensitivity
                stmt.setString(i * 3 + 1, likePattern); // Set name search
                stmt.setString(i * 3 + 2, likePattern); // Set number search
                stmt.setString(i * 3 + 3, likePattern); // Set receipt number search
            }

            try (ResultSet rs = stmt.executeQuery()) {
                // Get the model for the table
                DefaultTableModel model = (DefaultTableModel) TABLE_CUSTO_INFO.getModel();
                model.setRowCount(0); // Clear existing rows

                // Iterate through the result set and populate the table
                while (rs.next()) {
                    int recordId = rs.getInt("record_id");
                    String name = rs.getString("name");
                    String number = rs.getString("number");
                    String receiptNumber = rs.getString("receipt_number");

                    // Add a new row to the table model
                    model.addRow(new Object[]{recordId, name, number, receiptNumber});
                }

                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No records found for the search term.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching customer information: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error fetching customer information: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            dbConn.closeConnection(); // Ensure the connection is closed after operations
        }


    }//GEN-LAST:event_SEARCH_CUSTOMERActionPerformed

    private void CUSTOMER_PAYeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUSTOMER_PAYeditActionPerformed
        // Get the selected row from the payments table
        int selectedRow = TABLE_PAYMENTS.getSelectedRow();

        if (selectedRow == -1) {
            // No row is selected
            JOptionPane.showMessageDialog(this, "Please select a payment record to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Step 1: Prompt for password
        String password = JOptionPane.showInputDialog(null, "Enter password to edit the payment record:", 
                                                      "Password Required", JOptionPane.WARNING_MESSAGE);

        // Set the password required for editing
        String correctPassword = "mik"; // Replace with the actual password

        // Check if the password dialog was canceled or the entered password is incorrect
        if (password == null) {
            JOptionPane.showMessageDialog(null, "Password input was canceled. Editing aborted.", 
                                          "Action Canceled", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (!password.equals(correctPassword)) {
            JOptionPane.showMessageDialog(null, "Incorrect password. Editing aborted.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve data from the selected row
        int recordId = (int) TABLE_PAYMENTS.getValueAt(selectedRow, 0); // Assuming first column is record_id
        String fullName = (String) TABLE_PAYMENTS.getValueAt(selectedRow, 1);
        String selectedItemName = (String) TABLE_PAYMENTS.getValueAt(selectedRow, 2);
        int qty = (int) TABLE_PAYMENTS.getValueAt(selectedRow, 3); // Assuming quantity is an integer
        double netPrice = (double) TABLE_PAYMENTS.getValueAt(selectedRow, 4); // Assuming net price is a double
        String paymentDate = (String) TABLE_PAYMENTS.getValueAt(selectedRow, 5);
        String receiptNumber = (String) TABLE_PAYMENTS.getValueAt(selectedRow, 6);

        // Create input fields for editing payment details
        JTextField txtFullName = new JTextField(fullName);
        JTextField txtItemName = new JTextField(selectedItemName);
        JTextField txtQty = new JTextField(String.valueOf(qty));
        JTextField txtNetPrice = new JTextField(String.valueOf(netPrice));
        JTextField txtPaymentDate = new JTextField(paymentDate);
        JTextField txtReceiptNumber = new JTextField(receiptNumber);

        // Set preferred size for input fields
        int fieldWidth = 25;
        Dimension fieldSize = new Dimension(fieldWidth * txtFullName.getFont().getSize(), txtFullName.getPreferredSize().height);

        txtFullName.setPreferredSize(fieldSize);
        txtItemName.setPreferredSize(fieldSize);
        txtQty.setPreferredSize(fieldSize);
        txtNetPrice.setPreferredSize(fieldSize);
        txtPaymentDate.setPreferredSize(fieldSize);
        txtReceiptNumber.setPreferredSize(fieldSize);

        // Panel to hold input fields
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtFullName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Item Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtItemName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtQty, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Net Price:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(txtNetPrice, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Payment Date:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(txtPaymentDate, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Receipt Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(txtReceiptNumber, gbc);

        // Show the input dialog to the user
        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Payment Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Perform the update if confirmed
            DbConnectionTOY dbConn = new DbConnectionTOY();
            Connection conn = dbConn.getConnection();

            if (conn == null) {
                System.out.println("Database connection is null.");
                return; // Exit if connection fails
            }

            String updateQuery = "UPDATE table1_details SET full_name = ?, selected_item_name = ?, qty = ?, net_price = ?, payment_date = ?, receipt_number = ? WHERE record_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                // Set the updated values
                stmt.setString(1, txtFullName.getText());
                stmt.setString(2, txtItemName.getText());
                stmt.setInt(3, Integer.parseInt(txtQty.getText()));
                stmt.setDouble(4, Double.parseDouble(txtNetPrice.getText()));
                stmt.setString(5, txtPaymentDate.getText());
                stmt.setString(6, txtReceiptNumber.getText());
                stmt.setInt(7, recordId); // Use the record ID to update the correct row

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Payment record updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Optionally, refresh the table to show updated data
                    populateTablePAYMENTS();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Unable to update the record.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                System.err.println("Error updating payment record: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Error updating payment record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                dbConn.closeConnection(); // Ensure the connection is closed after operations
            }
        }
        
    }//GEN-LAST:event_CUSTOMER_PAYeditActionPerformed

    private void EDIT_INFOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDIT_INFOActionPerformed
        // Get the selected row index from the customer info table
        int selectedRow = TABLE_CUSTO_INFO.getSelectedRow();

        if (selectedRow == -1) {
             // No row is selected
            JOptionPane.showMessageDialog(this, "Please select a customer record to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Step 1: Prompt for password
        String password = JOptionPane.showInputDialog(null, "Enter password to edit the record:", 
                                                      "Password Required", JOptionPane.WARNING_MESSAGE);

        // Set the password you require for editing
        String correctPassword = "mik"; // Replace with the actual password

        // Check if the password dialog was canceled or the entered password is incorrect
        if (password == null) {
            JOptionPane.showMessageDialog(null, "Password input was canceled. Editing aborted.", 
                                          "Action Canceled", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (!password.equals(correctPassword)) {
            JOptionPane.showMessageDialog(null, "Incorrect password. Editing aborted.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if the password is incorrect
        }
        
        // Retrieve the record ID and current values of the selected customer
        int recordId = (int) TABLE_CUSTO_INFO.getValueAt(selectedRow, 0); // Assuming record_id is the first column
        String currentName = (String) TABLE_CUSTO_INFO.getValueAt(selectedRow, 1);
        String currentNumber = (String) TABLE_CUSTO_INFO.getValueAt(selectedRow, 2);
        String currentReceiptNumber = (String) TABLE_CUSTO_INFO.getValueAt(selectedRow, 3);

        // Create input fields for editing the customer information
        JTextField txtName = new JTextField(currentName);
        JTextField txtNumber = new JTextField(currentNumber);
        JTextField txtReceiptNumber = new JTextField(currentReceiptNumber);

        // Set preferred size for input fields
        int fieldWidth = 25; // Adjust the width as needed for compact view
        Dimension fieldSize = new Dimension(fieldWidth * txtName.getFont().getSize(), txtName.getPreferredSize().height);

        txtName.setPreferredSize(fieldSize);
        txtNumber.setPreferredSize(fieldSize);
        txtReceiptNumber.setPreferredSize(fieldSize);
        // Panel to hold input fields with GridBagLayout for better alignment
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtNumber, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Receipt Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtReceiptNumber, gbc);

        // Show the dialog with input fields
        int option = JOptionPane.showConfirmDialog(this, panel, "Edit Customer Info", JOptionPane.OK_CANCEL_OPTION);

        // Check for cancellation
        if (option != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "Edit operation was canceled.", "Action Canceled", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Establish a database connection
        DbConnectionTOY dbConn = new DbConnectionTOY();
        Connection conn = dbConn.getConnection();

        if (conn == null) {
            System.out.println("Database connection is null.");
            return; // Exit if connection fails
        }

        // SQL query to update the selected customer record
        String updateQuery = "UPDATE table1 SET name = ?, number = ?, receipt_number = ? WHERE record_id = ?";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setString(1, txtName.getText());
            updateStmt.setString(2, txtNumber.getText());
            updateStmt.setString(3, txtReceiptNumber.getText());
            updateStmt.setInt(4, recordId); // Set the record ID

            // Execute the update operation
            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer record updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update the customer record.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Refresh the customer info table to reflect changes
            populateTableCUSTO_INFO();
        } catch (SQLException e) {
            System.err.println("Error updating customer record: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error updating customer record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            dbConn.closeConnection(); // Ensure the connection is closed after operations
        }                            
        

    }//GEN-LAST:event_EDIT_INFOActionPerformed

    private void DELETE_PAYMENTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETE_PAYMENTSActionPerformed
        // Get the selected row from the payments table
        int selectedRow = TABLE_PAYMENTS.getSelectedRow();

        if (selectedRow == -1) {
            // No row is selected
            JOptionPane.showMessageDialog(this, "Please select a payment record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Step 1: Prompt for password
        String password = JOptionPane.showInputDialog(null, "Enter password to delete the payment record:", 
                                                      "Password Required", JOptionPane.WARNING_MESSAGE);

        String correctPassword = "mik"; // Replace with actual password

        // Check if password dialog was canceled or password is incorrect
        if (password == null) {
            JOptionPane.showMessageDialog(null, "Password input was canceled. Deletion aborted.", 
                                          "Action Canceled", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (!password.equals(correctPassword)) {
            JOptionPane.showMessageDialog(null, "Incorrect password. Deletion aborted.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Step 2: Confirm deletion
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this payment record?", 
                                                         "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmation == JOptionPane.NO_OPTION) {
            // If the user decides not to delete
            JOptionPane.showMessageDialog(this, "Deletion canceled.", "Action Canceled", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Retrieve the record ID from the selected row (assuming the first column holds the record ID)
        int recordId = (int) TABLE_PAYMENTS.getValueAt(selectedRow, 0);

        // Step 3: Perform the deletion from the database
        DbConnectionTOY dbConn = new DbConnectionTOY();
        Connection conn = dbConn.getConnection();

        if (conn == null) {
            System.out.println("Database connection is null.");
            return; // Exit if the connection fails
        }

        String deleteQuery = "DELETE FROM table1_details WHERE record_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
            // Set the record ID to delete
            stmt.setInt(1, recordId);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Payment record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Optionally, refresh the table to show updated data
                populateTablePAYMENTS();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Unable to delete the record.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting payment record: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error deleting payment record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            dbConn.closeConnection(); // Ensure the connection is closed after operations
        }   
        
    }//GEN-LAST:event_DELETE_PAYMENTSActionPerformed

    private void jBntBacktoEinfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBntBacktoEinfoActionPerformed
       // Close the current window and reopen the EngageToy window
       this.dispose(); // Close the current window

       // Assuming you want to go back to the EngageToy main functionality
       EngageToy engageToy = new EngageToy();  // Create a new instance of EngageToy
       engageToy.setVisible(true);             // Set it visible to the user

    }//GEN-LAST:event_jBntBacktoEinfoActionPerformed

    private void DELETE_INFOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETE_INFOActionPerformed
        // Get the selected row index from the customer info table
        int selectedRow = TABLE_CUSTO_INFO.getSelectedRow();

        if (selectedRow == -1) {
            // No row is selected
            JOptionPane.showMessageDialog(this, "Please select a customer record to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve the record ID of the selected customer
        int recordId = (int) TABLE_CUSTO_INFO.getValueAt(selectedRow, 0); // Assuming record_id is the first column

        // Prompt for password
        String password = JOptionPane.showInputDialog(this, "Enter password to delete the record:", 
                                                      "Password Required", JOptionPane.WARNING_MESSAGE);

        // Set the password you require for deletion
        String correctPassword = "mik"; // Replace with the actual password

        if (password == null) {
            // User pressed Cancel
            JOptionPane.showMessageDialog(this, "Password input was canceled. Deletion aborted.", 
                                          "Action Canceled", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (!password.equals(correctPassword)) {
            JOptionPane.showMessageDialog(this, "Incorrect password. Deletion aborted.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this customer record?", 
                                                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return; // Exit if the user does not confirm deletion
        }

        // Establish a database connection
        DbConnectionTOY dbConn = new DbConnectionTOY();
        Connection conn = dbConn.getConnection();

        if (conn == null) {
            System.out.println("Database connection is null.");
            return; // Exit if connection fails
        }

        // SQL query to delete the selected customer record
        String deleteQuery = "DELETE FROM table1 WHERE record_id = ?"; // Adjust the table name as necessary

        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
            deleteStmt.setInt(1, recordId); // Set the record ID

            // Execute the delete operation
            int rowsAffected = deleteStmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete the customer record.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Refresh the customer info table to reflect changes
            populateTableCUSTO_INFO();
        } catch (SQLException e) {
            System.err.println("Error deleting customer record: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error deleting customer record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            dbConn.closeConnection(); // Ensure the connection is closed after operations
        }

    }//GEN-LAST:event_DELETE_INFOActionPerformed

    private void jBtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRefreshActionPerformed
        populateTableCUSTO_INFO(); // Refresh data
        populateTablePAYMENTS(); // Refresh data
        // Reset the total text field to default state
        NET_ALL_TOTAL_TEXT.setText("Get net price from customer payment"); // Set to default value or empty as needed
        NET_ALL_TOTAL_TEXT.setForeground(Color.GRAY); // Optionally reset coloral   
         
    }//GEN-LAST:event_jBtnRefreshActionPerformed

    private void NET_ALL_TOTAL_TEXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NET_ALL_TOTAL_TEXTActionPerformed
        NET_ALL_TOTAL_TEXT.setEditable(false);    
        
    }//GEN-LAST:event_NET_ALL_TOTAL_TEXTActionPerformed

    private void NET_all_TOTAL_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NET_all_TOTAL_btnActionPerformed
        DefaultTableModel model = (DefaultTableModel) TABLE_PAYMENTS.getModel();
        double total = 0.0;

       // Loop through selected rows
    int[] selectedRows = TABLE_PAYMENTS.getSelectedRows();
    if (selectedRows.length == 0) {
        JOptionPane.showMessageDialog(this, 
            "Please select and highlight at least one row to calculate the total net sales.\n" + 
            "                                                 e.g. NET PRICE", 
            "Warning", 
            JOptionPane.WARNING_MESSAGE);
        return; // Exit if no rows are selected
    }

        for (int rowIndex : selectedRows) {
            double netPrice = (double) model.getValueAt(rowIndex, 4); // Column index 4 for net_price
            total += netPrice;
        }

        // Manually format the total as a string with commas and two decimal places
        String formattedTotal = String.format("%.2f", total);
        formattedTotal = formattedTotal.replace(",", ""); // Ensure no extra commas

        // Add commas for thousands
        StringBuilder sb = new StringBuilder(formattedTotal);
        int pointIndex = formattedTotal.indexOf(".");
        int length = pointIndex > 0 ? pointIndex : formattedTotal.length();
        for (int i = length - 3; i > 0; i -= 3) {
            sb.insert(i, ",");
        }

        // Set the formatted total in the NET_ALL_TOTAL_TEXT field with "Php" prefix
        NET_ALL_TOTAL_TEXT.setText("Php " + sb.toString()); // Ensure only one "Php" with space

        // Change the text color to dark green
        NET_ALL_TOTAL_TEXT.setForeground(new Color(0, 100, 0)); // Dark green color

        // Show message box highlighting the selection
        JOptionPane.showMessageDialog(this, "Total net sales calculated for the selected rows:\n\n" +
                "Net Price: " + sb.toString() + "\n" +
                "Please refer to the Net Price tab or Date column.", 
                "Net Sales Total", JOptionPane.INFORMATION_MESSAGE);

        // Optionally, you can set focus on the NET_ALL_TOTAL_TEXT field to emphasize the result
        NET_ALL_TOTAL_TEXT.requestFocus();
    
    }//GEN-LAST:event_NET_all_TOTAL_btnActionPerformed

    private void jBAGONG_BUWANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAGONG_BUWANActionPerformed
        // Get the date input from the text field
    String dateInput = jBAGONG_BUWAN.getText().trim(); // Use jBAGONG_BUWAN as the JTextField

    // Check if the input is empty
    if (dateInput.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a date to search.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Create the SQL query to find records by payment date
    String sql = "SELECT * FROM table1_details WHERE payment_date LIKE ?"; // Adjust table name as needed

    // Establish a database connection
    DbConnectionTOY dbConn = new DbConnectionTOY();
    Connection conn = dbConn.getConnection();

    if (conn == null) {
        System.out.println("Database connection is null.");
        return; // Exit if connection fails
    }

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Use LIKE operator to match the date (this allows partial matches)
        stmt.setString(1, "%" + dateInput + "%"); // Use '%' for partial matches

        // Execute the query
        try (ResultSet rs = stmt.executeQuery()) {
            // Get the model for the TABLE_PAYMENTS
            DefaultTableModel model = (DefaultTableModel) TABLE_PAYMENTS.getModel();
            model.setRowCount(0); // Clear existing rows

            // Iterate through the result set and populate the table
            while (rs.next()) {
                int recordId = rs.getInt("record_id");
                String fullName = rs.getString("full_name");
                String selectedItemName = rs.getString("selected_item_name");
                int qty = rs.getInt("qty");
                double netPrice = rs.getDouble("net_price");
                String paymentDate = rs.getString("payment_date");
                String receiptNumber = rs.getString("receipt_number");

                // Add a new row to the table model
                model.addRow(new Object[]{recordId, fullName, selectedItemName, qty, netPrice, paymentDate, receiptNumber});
            }

            // Show a message if no results were found
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No records found for the entered date.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error fetching payment records: " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Error fetching payment records: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        dbConn.closeConnection(); // Ensure the connection is closed after operations
    }
    
    }//GEN-LAST:event_jBAGONG_BUWANActionPerformed

   private void populateTableCUSTO_INFO() {
    // Establish a database connection
    DbConnectionTOY dbConn = new DbConnectionTOY();
    Connection conn = dbConn.getConnection();

    if (conn == null) {
        System.out.println("Database connection is null.");
        return; // Exit if connection fails
    }

    // SQL query to fetch data from table1
    String query = "SELECT * FROM table1";
    
    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        // Get the model for the table
        DefaultTableModel model = (DefaultTableModel) TABLE_CUSTO_INFO.getModel();
        model.setRowCount(0); // Clear existing rows

        // Iterate through the result set and populate the table
        while (rs.next()) {
            int recordId = rs.getInt("record_id");
            String name = rs.getString("name");
            String number = rs.getString("number");
            String receiptNumber = rs.getString("receipt_number");

            // Add a new row to the table model
            model.addRow(new Object[]{recordId, name, number, receiptNumber});
        }
    } catch (SQLException e) {
        System.err.println("Error fetching customer information: " + e.getMessage());
    } finally {
        dbConn.closeConnection(); // Ensure the connection is closed after operations
    }
} 
  
//==========================================================================================================================================================//
//==========================================================================================================================================================//
  
   private void populateTablePAYMENTS() {
    // Establish a database connection
    DbConnectionTOY dbConn = new DbConnectionTOY();
    Connection conn = dbConn.getConnection();

    if (conn == null) {
        System.out.println("Database connection is null.");
        return; // Exit if connection fails
    }

    // SQL query to fetch data from table1_details
    String query = "SELECT * FROM table1_details";

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        // Get the model for the table
        DefaultTableModel model = (DefaultTableModel) TABLE_PAYMENTS.getModel();
        model.setRowCount(0); // Clear existing rows

        // Iterate through the result set and populate the table
        while (rs.next()) {
            int recordId = rs.getInt("record_id");
            String fullName = rs.getString("full_name");
            String selectedItemName = rs.getString("selected_item_name");
            int qty = rs.getInt("qty");
            double netPrice = rs.getDouble("net_price");
            String paymentDate = rs.getString("payment_date");
            String receiptNumber = rs.getString("receipt_number");

            // Add a new row to the table model
            model.addRow(new Object[]{recordId, fullName, selectedItemName, qty, netPrice, paymentDate, receiptNumber});
        }
    } catch (SQLException e) {
        System.err.println("Error fetching payment details: " + e.getMessage());
    } finally {
        dbConn.closeConnection(); // Ensure the connection is closed after operations
    }
}

//========================================================================================================================
//========================================================================================================================  
     
private void addCopyFunctionalityToTable(JTable table) {
                // Create a popup menu and add Copy and Paste menu items
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");

        popupMenu.add(copyMenuItem);
        popupMenu.add(pasteMenuItem);

        // Add a mouse listener to show the popup menu on right-click
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        // Add action listener to the Copy menu item
        copyMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copySelectionToClipboard(table);
            }
        });

        // Add action listener to the Paste menu item
        pasteMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pasteFromClipboard(table);
            }
        });

        // Assign key bindings for Copy (Ctrl+C) and Paste (Ctrl+V)
        KeyStroke copyKeyStroke = KeyStroke.getKeyStroke("ctrl C");
        KeyStroke pasteKeyStroke = KeyStroke.getKeyStroke("ctrl V");

        table.getInputMap(JTable.WHEN_FOCUSED).put(copyKeyStroke, "Copy");
        table.getInputMap(JTable.WHEN_FOCUSED).put(pasteKeyStroke, "Paste");

        table.getActionMap().put("Copy", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copySelectionToClipboard(table);
            }
        });

        table.getActionMap().put("Paste", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pasteFromClipboard(table);
            }
        });
    }

    private void copySelectionToClipboard(JTable table) {
        int[] selectedRows = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();

        if (selectedRows.length > 0 && selectedColumns.length > 0) {
            StringBuilder sb = new StringBuilder();

            // Create a list of columns to copy (to avoid repeated column headers)
            int lastColumn = selectedColumns[selectedColumns.length - 1];

            for (int row : selectedRows) {
                for (int col : selectedColumns) {
                    sb.append(table.getValueAt(row, col).toString());
                    if (col != lastColumn) {
                        sb.append("\t");  // Separate columns with a tab
                    }
                }
                sb.append("\n");  // Newline for the next row
            }

            // Copy the data to the clipboard
            StringSelection stringSelection = new StringSelection(sb.toString());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        }
    }

    private void pasteFromClipboard(JTable table) {
        try {
            // Get the clipboard content
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable transferable = clipboard.getContents(null);

            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String pastedData = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                String[] rows = pastedData.split("\n");

                DefaultTableModel model = (DefaultTableModel) table.getModel();

                for (String row : rows) {
                    String[] cells = row.split("\t");
                    // Add a new row to the table if it has cells to add
                    if (cells.length > 0) {
                        model.addRow(cells);
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error pasting data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

        
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new Tables().setVisible(true);
                new Tables1().setVisible(true);
               
            }
        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CUSTOMER_PAYedit;
    private javax.swing.JButton DELETE_INFO;
    private javax.swing.JButton DELETE_PAYMENTS;
    private javax.swing.JButton EDIT_INFO;
    private javax.swing.JTextField NET_ALL_TOTAL_TEXT;
    private javax.swing.JButton NET_all_TOTAL_btn;
    private javax.swing.JScrollPane SCROLL_CUS_INFO;
    private javax.swing.JTextField SEARCH_CUSTOMER;
    private javax.swing.JTextField SEARCH_WHO_PAID;
    private javax.swing.JTable TABLE_CUSTO_INFO;
    private javax.swing.JTable TABLE_PAYMENTS;
    private javax.swing.JTextField jBAGONG_BUWAN;
    private javax.swing.JButton jBntBacktoEinfo;
    private javax.swing.JButton jBtnRefresh;
    private javax.swing.JLabel jSearchLBL1;
    private javax.swing.JLabel jSearchLBL2;
    // End of variables declaration//GEN-END:variables
}
