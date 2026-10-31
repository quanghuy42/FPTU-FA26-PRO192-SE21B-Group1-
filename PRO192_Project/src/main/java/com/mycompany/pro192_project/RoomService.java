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
public class RoomService {
    private List<Room> rooms;
    private PriceConfig priceConfig;

    public RoomService(PriceConfig priceConfig, List<Room> rooms) {
        this.rooms = (rooms != null) ? rooms : new ArrayList<>();
        this.priceConfig = (priceConfig != null) ? priceConfig : new PriceConfig();
    }
    
    public Room addStandardRoom(String roomId, int capacity){
        if(this.findRoomById(roomId) != null){
            throw new IllegalArgumentException("Ma phong da ton tai: " + roomId);
        }
        Room room = new StandardRoom(roomId, capacity, this.priceConfig.getStandardRoomRent());
        this.rooms.add(room);
        return room;
    }
    
    public Room addServiceRoom(String roomId, int capacity){
        if(this.findRoomById(roomId) != null){
            throw new IllegalArgumentException("Ma phong da ton tai: " + roomId);
        }
        Room room = new ServiceRoom(roomId, capacity, this.priceConfig.getServiceRoomRent(), 
                this.priceConfig.getServiceFee());
        this.rooms.add(room);
        return room;
    } 
    
    public Room findRoomById(String roomId){
        if(roomId == null){
            return null;
        }
        for(Room r : this.rooms){
            if(r.getRoomId().equalsIgnoreCase(roomId.trim())){
                return r;
            }
        }
        return null;
    }
    
    public List<Room> getAllRooms(){
        return new ArrayList<>(this.rooms);
    }
    
    public PriceConfig getPriceConfig(){
        return this.priceConfig;
    }
    
    public void updateStandardRoomRent(long newRent){
        this.priceConfig.setStandardRoomRent(newRent);
    }
    
    public void updateServiceRoomRent(long newRent){
        this.priceConfig.setServiceRoomRent(newRent);
    }
    
    public void updateServiceFee(long newFee){
        this.priceConfig.setServiceFee(newFee);
    }
    
    public void updateElectricRate(long newRate){
        this.priceConfig.setElectricRate(newRate);
    }
    
    public void updateWaterRent(long newRate){
        this.priceConfig.setWaterRate(newRate);
    }
}
