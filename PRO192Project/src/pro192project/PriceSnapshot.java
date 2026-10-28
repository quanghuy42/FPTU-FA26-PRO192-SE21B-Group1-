/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Huy
 */
public class PriceSnapshot {
    private String roomType;
    private long roomPrice;
    private long serviceFee;
    private long electricityRate;
    private long waterRate;

    public PriceSnapshot(String roomType, long roomPrice, long serviceFee, long electricityRate, long waterRate) {
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.serviceFee = serviceFee;
        this.electricityRate = electricityRate;
        this.waterRate = waterRate;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public long getRoomPrice() {
        return this.roomPrice;
    }

    public long getServiceFee() {
        return this.serviceFee;
    }

    public long getElectricityRate() {
        return this.electricityRate;
    }

    public long getWaterRate() {
        return this.waterRate;
    }
}
