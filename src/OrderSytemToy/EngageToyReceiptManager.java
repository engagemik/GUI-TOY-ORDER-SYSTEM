package OrderSytemToy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EngageToyReceiptManager {

    private static final String RECEIPT_FILE = "last_receipt.txt"; // Path to the file

    // Reads the last receipt number from the file
    public static int readLastReceiptNumber() {
        try (BufferedReader br = new BufferedReader(new FileReader(RECEIPT_FILE))) {
            String line = br.readLine();
            if (line != null) {
                return Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading receipt number: " + e.getMessage());
        }
        return 0; // Return 0 if file does not exist or is empty
    }

    // Writes the last receipt number to the file
    public static void writeLastReceiptNumber(int receiptNumber) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RECEIPT_FILE))) {
            bw.write(String.valueOf(receiptNumber));
        } catch (IOException e) {
            System.err.println("Error writing receipt number: " + e.getMessage());
        }
    }
}
