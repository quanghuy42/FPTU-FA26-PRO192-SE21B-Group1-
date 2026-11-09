/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro192project;

public class PriceConfig {
    private long standardRoomRent;
    private long serviceRoomRent;
    private long serviceFee;
    private long electricRate;
    private long waterRate;
    
    public PriceConfig() {
        this.standardRoomRent = 1200000L;
        this.serviceRoomRent = 1800000L;
        this.serviceFee = 300000L;
        this.electricRate = 3000L;
        this.waterRate = 15000L;
    }

    public PriceConfig(long standardRoomRent, long serviceRoomRent, long serviceFee, 
            long electricRate, long waterRate) {
        this.validateMoney(standardRoomRent, "Standard room rent");
        this.validateMoney(serviceRoomRent, "Service room rent");
        this.validateMoney(serviceFee, "Service fee");
        this.validateMoney(electricRate, "Electric rate");
        this.validateMoney(waterRate, "Water rate");
        
        this.standardRoomRent = standardRoomRent;
        this.serviceRoomRent = serviceRoomRent;
        this.serviceFee = serviceFee;
        this.electricRate = electricRate;
        this.waterRate = waterRate;
    }

    public long getStandardRoomRent() {
        return this.standardRoomRent;
    }

    public void setStandardRoomRent(long standardRoomRent) {
        this.validateMoney(standardRoomRent, "Standard room rent");
        this.standardRoomRent = standardRoomRent;
    }

    public long getServiceRoomRent() {
        return this.serviceRoomRent;
    }

    public void setServiceRoomRent(long serviceRoomRent) {
        this.validateMoney(serviceRoomRent, "Service room rent");
        this.serviceRoomRent = serviceRoomRent;
    }

    public long getServiceFee() {
        return this.serviceFee;
    }

    public void setServiceFee(long serviceFee) {
        this.validateMoney(serviceFee, "Service fee");
        this.serviceFee = serviceFee;
    }

    public long getElectricRate() {
        return this.electricRate;
    }

    public void setElectricRate(long electricRate) {
        this.validateMoney(electricRate, "Electric rate");
        this.electricRate = electricRate;
    }

    public long getWaterRate() {
        return this.waterRate;
    }

    public void setWaterRate(long waterRate) {
        this.validateMoney(waterRate, "Water rate");
        this.waterRate = waterRate;
    }

    private void validateMoney(long value, String fieldName) {
        if (value < 0 || value > 10_000_000L) {
            throw new IllegalArgumentException(fieldName + " must be between 0 and 10,000,000 VND.");
        }
    }
}
