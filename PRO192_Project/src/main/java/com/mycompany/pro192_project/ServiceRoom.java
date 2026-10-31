/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pro192_project;

/**
 *
 * @author LOQ
 */
public class ServiceRoom extends Room {
    private long serviceFee;

    public ServiceRoom(String roomId, int capacity, long baseRent, long serviceFee) {
        super(roomId, capacity, baseRent);
        Room.validateMoney(serviceFee, "Phi dich vu");
        this.serviceFee = serviceFee;
    }
    
    @Override
    public long calculateRoomCharge(){
        return super.getBaseRent() + this.serviceFee;
    }
    
    @Override
    public String getRoomType(){
        return "SERVICE";
    }
    
    @Override
    public long getServiceFee(){
        return this.serviceFee;
    }
    
    public void setServiceFee(long serviceFee){
        Room.validateMoney(serviceFee, "Phi dich vu");
        this.serviceFee = serviceFee;
    }
}
