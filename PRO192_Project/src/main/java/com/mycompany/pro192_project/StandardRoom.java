/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pro192_project;

/**
 *
 * @author LOQ
 */
public class StandardRoom extends Room {

    public StandardRoom(String roomId, int capacity, long baseRent) {
        super(roomId, capacity, baseRent);
    }
    
    @Override
    public long calculateRoomCharge(){
        return super.getBaseRent();
    }
    
    @Override
    public String getRoomType(){
        return "STANDARD";
    }
    
    @Override
    public long getServiceFee(){
        return 0L;
    }
    
}
