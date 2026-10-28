/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pricesnapshot;

import java.util.Scanner;


public class Main {
    public static void main(String[]args){
        BillManager manager = new BillManager();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=============================================");
        System.out.println("  CHUONG TRINH KIEM THU LAP HOA DON  ");
        System.out.println("=============================================");
        
        try {
            System.out.println("Nhap ma phong (Vi du: P101): ");
            String roomId = scanner.nextLine();
            System.out.println("Nhap so nguoi dang o (currentOccupancy >0 la co nguoi): ");
            int occupancy = Integer.parseInt(scanner.nextLine());
            Room room = new Room(roomId, occupancy);
            System.out.print("Nhap thang lap hoa don (Dinh dang YYYY-MM, vi du 2026-06: ");
            String billingMonth = scanner.nextLine();
            System.out.print("Nhap chi so dien dau: ");
           int eStart = Integer.parseInt(scanner.nextLine());
           System.out.print("Nhap chi so dien cuoi: ");
           int eEnd = Integer.parseInt(scanner.nextLine());
           System.out.print("Nhap chi so nuoc dau: ");
           int wStart = Integer.parseInt(scanner.nextLine());
           System.out.print("Nhap chi so nuoc cuoi: ");
           int wEnd = Integer.parseInt(scanner.nextLine());
           System.out.println("\n------------------------------------");
           System.out.println("DANG XU LY LAP HOA DON...");
           Bill newBill = manager.createBill(room, billingMonth, eStart, eEnd, wStart, wEnd);
           System.out.println("=> [THANH CONG] Lap hoa don cho phong " + newBill.getRoomId() + "thanh cong!");
           System.out.println("=> Thang: " + newBill.getBillingMonth());
        }catch (NumberFormatException e){
            System.out.println("[LOI NHAP DU LIEU] Vui long nhap dung dinh dang so cho chi so va so nguoi!");
        }catch (Exception e){
            System.out.println("\n=> [THAT BAI] " + e.getMessage());
        }finally {
            scanner.close();
        }
    }
}
