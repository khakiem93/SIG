/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Khalid
 */
public class InvoiceHeader {
    public int invoiceNum;
    public String customerName;
    public String invoiceDate;
    public ArrayList<InvoiceLine> invoiceLines;
    public int invoiceTotal;
}
