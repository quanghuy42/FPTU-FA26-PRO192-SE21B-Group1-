/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pricesnapshot;


public class Room {
    private String roomId;
    private int currentOccupancy;

    public Room(String roomId, int currentOccupancy) {
        this.roomId = roomId;
        this.currentOccupancy = currentOccupancy;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }
    public double calculateRoomCharge(){
        return 2000000.0;
    }
    public double getServiceFee(){
        return 300000.0;
    }
}
