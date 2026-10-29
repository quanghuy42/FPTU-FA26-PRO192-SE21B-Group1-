/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro192project;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Thái Quang Huy
 */
public abstract class Room {
    private String roomId;
    private int capacity;
    private long baseRent;
    private List<String> residentIds;
    
    public Room(String roomId, int capacity, long baseRent){
        Room.validateRoomId(roomId);
        Room.validateCapacity(capacity);
        Room.validateMoney(baseRent, "Tien phong");
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
        Room.validateMoney(baseRent, "Tien phong");
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
        this.residentIds.add(studentId);
        return true;
    }
    
    public boolean removeResident(String studentId){
        return this.residentIds.remove(studentId);
    }
    
    public boolean addStudent(String studentId){
        return this.addResident(studentId);
    }
    
    public boolean removeStudent(String studentId){
        return this.removeResident(studentId);
    }
    
    public int getCurrentOccupancy(){
        return this.getResidentCount();
    }
    
    public int getAvailableSlots(){
        return this.getVacancy();
    }
    
    public List<String> getResidentIds(){
        return new ArrayList<String>(this.residentIds);
    }
    
    public static void validateRoomId(String roomId){
        if(roomId == null || roomId.isEmpty()){
            throw new IllegalArgumentException("Ma phong khong duoc rong.");
        }
        for(int i = 0; i < roomId.length(); i++){
            if(Character.isWhitespace(roomId.charAt(i))){
                throw new IllegalArgumentException("Ma phong khong duoc chua khoang trang.");
            }
        }
    }
    
    public static void validateCapacity(int capacity){
        if(capacity < 1 || capacity > 10){
            throw new IllegalArgumentException("Suc chua phai tu 1 den 10 nguoi.");
        }
    }
    
    public static void validateMoney(long value, String fieldName){
        if(value < 0 || value > 10_000_000L){
            throw new IllegalArgumentException(fieldName + " phai tu 0 den 10.000.000 dong.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s | %s | Capacity: %d | Occupied: %d | Vacant: %d | Rent/month: %,d VND",
                this.roomId, this.getRoomType(), this.capacity,
                this.getResidentCount(), this.getVacancy(), this.calculateRoomCharge());
    }  
}
