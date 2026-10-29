package pro192project;

/**
 * PriceSnapshot - Bản chụp đơn giá tại thời điểm lập hóa đơn
 * @author Nguyễn Đăng Huy, Hồ Trương Anh Quân
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

    public PriceSnapshot(long roomCharge, long serviceFee) {
        this("STANDARD", roomCharge, serviceFee, 3000L, 15000L);
    }

    public String getRoomType() {
        return this.roomType;
    }

    public long getRoomPrice() {
        return this.roomPrice;
    }

    public long getRoomCharge() {
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
