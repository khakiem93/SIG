/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.InvoiceHeader;
import com.mycompany.model.InvoiceLine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Khalid
 */
public class FileOperations {

    public ArrayList<InvoiceHeader> readFile() {
        ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("src//InvoiceHeader.csv"));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
            for (CSVRecord csvRecord : csvParser) {
                InvoiceHeader invoice = new InvoiceHeader();
                invoice.invoiceNum = Integer.parseInt(csvRecord.get(0));
                invoice.invoiceDate = csvRecord.get(1);
                invoice.customerName = csvRecord.get(2);
                invoiceHeaders.add(invoice);
            }
            inputStreamReader = new InputStreamReader(new FileInputStream("src//InvoiceLine.csv"));
            csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
            //ArrayList<InvoiceLine> lines = new ArrayList<InvoiceLine>();
            for (CSVRecord csvRecord : csvParser) {
                InvoiceLine line = new InvoiceLine();
                line.invoiceNumber = Integer.parseInt(csvRecord.get(0));
                line.itemName = csvRecord.get(1);
                line.itemPrice = Integer.parseInt(csvRecord.get(2));
                line.count = Integer.parseInt(csvRecord.get(3));
                line.totalItem = line.itemPrice * line.count;
                for (InvoiceHeader inv : invoiceHeaders) {

                    if (inv.invoiceNum == line.invoiceNumber) {
                        if (inv.invoiceLines == null) {
                            inv.invoiceLines = new ArrayList<InvoiceLine>();
                            inv.invoiceTotal = 0;
                        }
                        inv.invoiceLines.add(line);
                        inv.invoiceTotal += line.totalItem;
                    }

                }
                //lines.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (IOException ex) {
            System.out.println("Error Reading File");

        }
        return invoiceHeaders;
    }
      
}
