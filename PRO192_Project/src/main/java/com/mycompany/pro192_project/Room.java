/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pro192_project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LOQ
 */
public abstract class Room {
    private String roomId;
    private int capacity;
    private long baseRent;
    private List<String> residentIds;

    public Room(String roomId, int capacity, long baseRent) {
        Room.validateRoomId(roomId);
        Room.validateCapacity(capacity);
        Room.validateMoney(baseRent, "Room rent");
        
        this.roomId = roomId;
        this.capacity = capacity;
        this.baseRent = baseRent;
        this.residentIds = new ArrayList<>();
    }
    
    public abstract long calculateRoomCharge();
    public abstract String getRoomType();
    public abstract long getServiceFee();

    public String getRoomId() {
        return roomId;
    }
    
    

    public int getCapacity() {
        return capacity;
    }

    public long getBaseRent() {
        return baseRent;
    }

    public void setBaseRent(long baseRent) {
        Room.validateMoney(baseRent, "Room rent");
        this.baseRent = baseRent;
    }
    
    public int getResidentCount(){
        return this.residentIds.size();
    }
    
    public int getVacancy(){
        return this.capacity - this.residentIds.size();
    }
    
    public boolean isFull(){
        return this.residentIds.size() >= this.capacity;
    }
    
    public boolean hasResident(String studentId){
        return this.residentIds.contains(studentId);
    }
    
    public boolean addResident(String studentId){
        if(this.isFull() || this.hasResident(studentId)){
            return false;
        }
        return this.residentIds.add(studentId);
    }
    
    public boolean removeResident(String studentId){
        return this.residentIds.remove(studentId);
    }
    
    public List<String> getResidentIds(){
        return new ArrayList<>(this.residentIds);
    }
    
    public static void validateRoomId(String roomId){
        if(roomId == null|| roomId.trim().isEmpty()){
            throw new IllegalArgumentException("Ma phong khong duoc de trong.");
        }
        for(int i = 0; i < roomId.length(); i++){
            if(Character.isWhitespace(roomId.charAt(i))){
                throw new IllegalArgumentException("Ma phong khong duoc chua khoang trang.");
            }
        }
    }
    
    public static void validateCapacity(int capacity){
        if(capacity < 1 || capacity > 10){
            throw new IllegalArgumentException("Suc chua phong phai tu 1 den 10 nguoi.");
        }
    }
    
    public static void validateMoney(long value, String fieldName){
        if(value < 0 || value > 10000000L){
            throw new IllegalArgumentException(fieldName + " phai nam trong khoang tu 0 den 10.000.000 VND.");
        }
    }
    
    @Override
    public String toString(){
        return String.format("%s | %s | Suc chua: %d | Dang o: %d | Con trong: %d"
                + "| Tien phong: &,d VND", this.roomId, this.getRoomType(), this.capacity,
                this.getResidentCount(), this.getVacancy(), this.calculateRoomCharge());
    }
    
}
