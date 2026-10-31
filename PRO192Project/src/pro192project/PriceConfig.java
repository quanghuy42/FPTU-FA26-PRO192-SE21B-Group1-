/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro192project;

/**
 * Quản lý đơn giá hiện hành của ký túc xá.
 * Giá mặc định:
 * - Phòng thường: 1.200.000 đ/tháng
 * - Phòng dịch vụ: 1.800.000 đ/tháng, phí dịch vụ: 300.000 đ/tháng
 * - Điện: 3.000 đ/kWh, Nước: 15.000 đ/m3
 *
 * @author Thái Quang Huy
 */
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
        Room.validateMoney(standardRoomRent, "Standard room rent");
        Room.validateMoney(serviceRoomRent, "Service room rent");
        Room.validateMoney(serviceFee, "Service fee");
        Room.validateMoney(electricRate, "Electric rate");
        Room.validateMoney(waterRate, "Water rate");
        
        this.standardRoomRent = standardRoomRent;
        this.serviceRoomRent = serviceRoomRent;
        this.serviceFee = serviceFee;
        this.electricRate = electricRate;
        this.waterRate = waterRate;
    }

    public long getStandardRoomRent() {
        return standardRoomRent;
    }

    public void setStandardRoomRent(long standardRoomRent) {
        Room.validateMoney(standardRoomRent, "Standard room rent");
        this.standardRoomRent = standardRoomRent;
    }

    public long getServiceRoomRent() {
        return serviceRoomRent;
    }

    public void setServiceRoomRent(long serviceRoomRent) {
        Room.validateMoney(serviceRoomRent, "Service room rent");
        this.serviceRoomRent = serviceRoomRent;
    }

    public long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(long serviceFee) {
        Room.validateMoney(serviceFee, "Service fee");
        this.serviceFee = serviceFee;
    }

    public long getElectricRate() {
        return electricRate;
    }

    public void setElectricRate(long electricRate) {
        Room.validateMoney(electricRate, "Electric rate");
        this.electricRate = electricRate;
    }

    public long getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(long waterRate) {
        Room.validateMoney(waterRate, "Water rate");
        this.waterRate = waterRate;
    }
}
