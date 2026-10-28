/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pricesnapshot;

public class Bill {
    private String roomId;
    private String billingMonth;
    private PriceSnapshot priceSnapshot;
    private int electricityStart, electricityEnd;
    private int waterStart, waterEnd;

    public Bill(String roomId, String billingMonth, PriceSnapshot priceSnapshot, int electricityStart, int electricityEnd, int waterStart, int waterEnd) {
        this.roomId = roomId;
        this.billingMonth = billingMonth;
        this.priceSnapshot = priceSnapshot;
        this.electricityStart = electricityStart;
        this.electricityEnd = electricityEnd;
        this.waterStart = waterStart;
        this.waterEnd = waterEnd;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getBillingMonth() {
        return billingMonth;
    }
    
}
