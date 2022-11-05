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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khalid
 */
public class FileOperations {

    public ArrayList<InvoiceHeader> readFile(String headerPath, String LinePath) {
        ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
        try {
            Scanner scanIn = new Scanner(new BufferedReader(new FileReader(headerPath)));
            while (scanIn.hasNextLine()) {
                String InputLine = scanIn.nextLine();
                String InArray[] = InputLine.split(",");
                InvoiceHeader invoice = new InvoiceHeader();
                invoice.invoiceNum = Integer.parseInt(InArray[0]);
                invoice.invoiceDate = InArray[1];
                invoice.customerName = InArray[2];
                invoiceHeaders.add(invoice);
            }

            scanIn = new Scanner(new BufferedReader(new FileReader(LinePath)));
            while (scanIn.hasNextLine()) {
                String InputLine = scanIn.nextLine();
                String InArray[] = InputLine.split(",");
                InvoiceLine line = new InvoiceLine();
                line.invoiceNumber = Integer.parseInt(InArray[0]);
                line.itemName = InArray[1];
                line.itemPrice = Integer.parseInt(InArray[2]);
                line.count = Integer.parseInt(InArray[3]);
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
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (IOException ex) {
            System.out.println("Error Reading File");

        }
        return invoiceHeaders;
    }

    public void writeFile(ArrayList<InvoiceHeader> invoices, String headerPath, String LinePath) {
        FileOutputStream fos = null;
        try {
            String headers = "";
            String lines = "";
            for (InvoiceHeader inv : invoices) {
                String invoiceHeader = inv.toCSV();
                headers += invoiceHeader;
                headers += System.lineSeparator();
                for (InvoiceLine line : inv.invoiceLines) {
                    String invoiceLine = line.toCSV();
                    lines += invoiceLine;
                    lines += System.lineSeparator();
                }
            }
            File f = new File(headerPath);
            FileWriter fw = new FileWriter(f);
            fw.write(headers);
            fw.flush();
            fw.close();
            f = new File(LinePath);
            fw = new FileWriter(f);
            fw.write(lines);
            fw.flush();
            fw.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (IOException ex) {
            System.out.println("Error writting File");

        } 
    }

}
