/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Khalid
 */
public class InvoiceLine {

    public int invoiceNumber;
    public String itemName;
    public int itemPrice;
    public int count;
    public int totalItem;

    public String toCSV() {
        String result = "";
        StringBuilder sb = new StringBuilder();
        sb.append(invoiceNumber).append(",");
        sb.append(itemName).append(",");
        sb.append(itemPrice).append(",");
        sb.append(count);
        result = sb.toString();
        return result;
    }
    
}
