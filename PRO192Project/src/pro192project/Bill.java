package pro192project;

/**
 * Bill - Thực thể Hóa đơn tiền phòng và điện nước
 * @author Nguyễn Đăng Huy, Hồ Trương Anh Quân
 */
public class Bill {
    private String billId;
    private String roomId;
    private String billingMonth;
    private long oldElectricityIndex;
    private long newElectricityIndex;
    private long oldWaterIndex;
    private long newWaterIndex;
    private PriceSnapshot priceSnapshot;
    private boolean paid;

    public Bill(String billId, String roomId, String billingMonth, 
                long oldElectricityIndex, long newElectricityIndex, 
                long oldWaterIndex, long newWaterIndex, 
                PriceSnapshot priceSnapshot) {
        this.billId = billId;
        this.roomId = roomId;
        this.billingMonth = billingMonth;
        this.oldElectricityIndex = oldElectricityIndex;
        this.newElectricityIndex = newElectricityIndex;
        this.oldWaterIndex = oldWaterIndex;
        this.newWaterIndex = newWaterIndex;
        this.priceSnapshot = priceSnapshot;
        this.paid = false;
    }

    public Bill(String roomId, String billingMonth, PriceSnapshot priceSnapshot,
                long oldElectricityIndex, long newElectricityIndex,
                long oldWaterIndex, long newWaterIndex) {
        this("", roomId, billingMonth, oldElectricityIndex, newElectricityIndex, oldWaterIndex, newWaterIndex, priceSnapshot);
    }

    public long getElectricityUsage() {
        return this.newElectricityIndex - this.oldElectricityIndex;
    }

    public long getWaterUsage() {
        return this.newWaterIndex - this.oldWaterIndex;
    }

    public long getElectricityCost() {
        return this.getElectricityUsage() * this.priceSnapshot.getElectricityRate();
    }

    public long getWaterCost() {
        return this.getWaterUsage() * this.priceSnapshot.getWaterRate();
    }

    public long getRoomCharge() {
        long charge = this.priceSnapshot.getRoomPrice();
        if ("SERVICE".equalsIgnoreCase(this.priceSnapshot.getRoomType())) {
            charge += this.priceSnapshot.getServiceFee();
        }
        return charge;
    }

    public long getTotalAmount() {
        return this.getRoomCharge() + this.getElectricityCost() + this.getWaterCost();
    }

    public void markAsPaid() {
        this.paid = true;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public String getBillId() {
        return this.billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public String getBillingMonth() {
        return this.billingMonth;
    }

    public long getOldElectricityIndex() {
        return this.oldElectricityIndex;
    }

    public long getNewElectricityIndex() {
        return this.newElectricityIndex;
    }

    public long getOldWaterIndex() {
        return this.oldWaterIndex;
    }

    public long getNewWaterIndex() {
        return this.newWaterIndex;
    }

    public PriceSnapshot getPriceSnapshot() {
        return this.priceSnapshot;
    }
}
