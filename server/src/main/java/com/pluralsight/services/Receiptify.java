package com.pluralsight.services;

import com.pluralsight.models.Customer;

import javax.swing.text.html.HTML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receiptify {
    private LocalDateTime now = LocalDateTime.now();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
    private final String FILENAME = now.format(dtf);
    private final String DIRECTORY = "receipts";
    private final String TAG = ".txt";
    private final String COMPLETE = DIRECTORY + "/" + FILENAME + TAG;
    public void writeToReceipt(Customer customer) {
        File directory = new File(DIRECTORY);
        if(!directory.exists()) directory.mkdir();

        File file = new File(COMPLETE);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("=".repeat(50));
            writer.println(" ".repeat(19) + "Your Receipt");
            writer.println("=".repeat(50));
            writer.println(customer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Open file for receipt: " + COMPLETE);
    }
}
