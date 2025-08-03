package OrderSytemToy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DbConnectionTOY {

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:derby://localhost:1527/TOY_KONEXT";
    private static final String USER = "A"; // Replace with your database username
    private static final String PASS = "A"; // Replace with your database password

    private Connection connection;

    // Constructor to establish the database connection
    public DbConnectionTOY() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to return the connection
    public Connection getConnection() {
        return connection;
    }
    
    public void insertToTable1(String fulLName, String number, int qty, String selectedItemName, double netPrice, String receiptNumber) {
        // Adjust the query for your table1
        String checkRecordQuery = "SELECT record_id FROM table1 WHERE name = ? AND number = ?";
        String insertRecordQuery = "INSERT INTO table1 (record_id, name, number, receipt_number) VALUES (?, ?, ?, ?)";
        String insertDetailsQuery = "INSERT INTO table1_details (record_id, full_name, selected_item_name, qty, net_price, payment_date, receipt_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Step 1: Check if record exists in table1
            int recordId = -1; // to hold record_id
            try (PreparedStatement checkStmt = connection.prepareStatement(checkRecordQuery)) {
                checkStmt.setString(1, fulLName); // Use the full name
                checkStmt.setString(2, number);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    // Record exists, retrieve its ID
                    recordId = rs.getInt("record_id");
                }
            }

            // Step 2: Insert new record into table1 if not found
            if (recordId == -1) {
                // Get the next record_id by finding the max existing ID
                String getMaxIdQuery = "SELECT COALESCE(MAX(record_id), 0) + 1 AS next_id FROM table1";
                try (PreparedStatement maxIdStmt = connection.prepareStatement(getMaxIdQuery);
                     ResultSet maxIdResult = maxIdStmt.executeQuery()) {
                    if (maxIdResult.next()) {
                        recordId = maxIdResult.getInt("next_id");
                    }
                }

                // Insert new record with the new record_id into table1
                try (PreparedStatement insertRecordStmt = connection.prepareStatement(insertRecordQuery)) {
                    insertRecordStmt.setInt(1, recordId); // Set the new record ID
                    insertRecordStmt.setString(2, fulLName); // Store full name
                    insertRecordStmt.setString(3, number);
                    insertRecordStmt.setString(4, receiptNumber); // Store receipt number in table1
                    insertRecordStmt.executeUpdate();
                }
            }

            // Step 3: Align selected item names vertically (if necessary)
            String alignedItemNames = formatSelectedItemNames(selectedItemName);

            // Step 4: Insert details into table1_details
            try (PreparedStatement insertDetailsStmt = connection.prepareStatement(insertDetailsQuery)) {
                insertDetailsStmt.setInt(1, recordId); // Use the record ID
                insertDetailsStmt.setString(2, fulLName); // Include full name in details table
                insertDetailsStmt.setString(3, alignedItemNames); // Selected item name(s)
                insertDetailsStmt.setInt(4, qty); // Quantity
                insertDetailsStmt.setDouble(5, netPrice); // Net price

                // Format the current date
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
                String formattedDate = dateFormatter.format(new Date());
                insertDetailsStmt.setString(6, formattedDate); // Set the formatted payment date
                insertDetailsStmt.setString(7, receiptNumber); // Include receipt number in details table

                // Logging for debugging
                System.out.println("Adding to batch: Record ID = " + recordId + ", Customer Name = " + fulLName +
                        ", Item = " + alignedItemNames + ", Quantity = " + qty +
                        ", Net Price = " + netPrice + ", Date = " + formattedDate +
                        ", Receipt Number = " + receiptNumber);

                // Execute the insert details
                insertDetailsStmt.executeUpdate();
            }

            System.out.println("Details inserted successfully into table1.");
        } catch (SQLException e) {
            System.err.println("Failed to insert record into table1: " + e.getMessage());
        }
    }

    
    private String formatSelectedItemNames(String selectedItemName) {
    // Split items by commas or new lines, depending on how they are originally formatted
    String[] items = selectedItemName.split(",\\s*|\\n"); // Split by comma or new line
    StringBuilder alignedNames = new StringBuilder();
    
    // Append each item, separating them by a comma and space
    for (int i = 0; i < items.length; i++) {
        alignedNames.append(items[i].trim());
        if (i < items.length - 1) {
            alignedNames.append(", "); // Add a comma and space between items
        }
    }
    
    return alignedNames.toString(); // Return formatted string with commas
    }
    
public double calculateTotalNetPrice(Date startDate, Date endDate) {
    double totalNetPrice = 0.0;

    // Format the dates to a standard SQL format
    SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
    String startDateFormatted = dateFormatter.format(startDate);
    String endDateFormatted = dateFormatter.format(endDate);

    String query = "SELECT SUM(net_price) AS total_net_price FROM table1_details " +
                   "WHERE payment_date BETWEEN ? AND ?";

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, startDateFormatted);
        pstmt.setString(2, endDateFormatted);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            totalNetPrice = rs.getDouble("total_net_price");
        }
    } catch (SQLException e) {
        System.err.println("Failed to calculate total net price: " + e.getMessage());
    }

    return totalNetPrice;
}


    // Method to close the database connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                // Handle exceptions when closing the connection
                System.err.println("Failed to close the database connection: " + e.getMessage());
            }
        }
    }
}

  

   