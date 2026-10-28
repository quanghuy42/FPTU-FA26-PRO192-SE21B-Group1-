package com.mycompany.pricesnapshot;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillManager {
    public Bill createBill(Room room, String billingMonth,
                           int electricityStart, int electricityEnd,
                           int waterStart, int waterEnd){
        if (room == null){
            try {
                throw new Exception("Loi: Doi tuong phong khong ton tai!");
            } catch (Exception ex) {
                Logger.getLogger(BillManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (room.getCurrentOccupancy() <= 0){
            try {
                throw new Exception("Loi nghiep vu: Phong nay hien dang trong (khong co nguoi o),khong the lap hoa don!");
            } catch (Exception ex) {
                Logger.getLogger(BillManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!isValidBillingMonth(billingMonth)){
            try {
                throw new Exception("Loi dinh dang: Thang lap hoa don pahi tuan theo dinh dang YYYY-MM (Vi du: 2026-06).");
            } catch (Exception ex) {
                Logger.getLogger(BillManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (electricityEnd < electricityStart){
            try {
                throw new Exception("Loi nhap du lieu: Chi so dien cuoi khong duoc nho hon chi so dau!");
            } catch (Exception ex) {
                Logger.getLogger(BillManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (waterEnd < waterStart){
            try {
                throw new Exception("Loi nhap du lieu: Chi so nuoc cuoi khong duoc nho hon chi so dau!");
            } catch (Exception ex) {
                Logger.getLogger(BillManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (isBillExists(room.getRoomId(), billingMonth)){
            try {
                throw new Exception("Loi trung lap: Phong nay da co hoa don trong thang " + billingMonth + "roi!");
            } catch (Exception ex) {
                Logger.getLogger(BillManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        double roomCharge = room.calculateRoomCharge();
        double serviceFee = room.getServiceFee();
        PriceSnapshot snapshot = new PriceSnapshot(roomCharge,serviceFee);
        return new Bill(room.getRoomId(),billingMonth,snapshot,electricityStart,electricityEnd,waterStart,waterEnd);
    }
    private boolean isValidBillingMonth(String monthStr){
        if (monthStr == null || !monthStr.matches("^\\d{4}-(0[1-9]|1[0-2])$")) {
            return false;
        }
        try {
            LocalDate.parse(monthStr + "-01", DateTimeFormatter.ofPattern("YYYY-MM-dd"));
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }
    private boolean isBillExists(String roomId, String billingMonth){
        return false;
    }
}
