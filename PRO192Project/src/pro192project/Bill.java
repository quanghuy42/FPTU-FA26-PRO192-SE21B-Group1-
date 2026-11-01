package pro192project;

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
        this("", roomId, billingMonth, oldElectricityIndex, newElectricityIndex,
                oldWaterIndex, newWaterIndex, priceSnapshot);
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public long getOldElectricityIndex() {
        return oldElectricityIndex;
    }

    public long getNewElectricityIndex() {
        return newElectricityIndex;
    }

    public long getOldWaterIndex() {
        return oldWaterIndex;
    }

    public long getNewWaterIndex() {
        return newWaterIndex;
    }

    public PriceSnapshot getPriceSnapshot() {
        return priceSnapshot;
    }

    public long getElectricityUsage() {
        return newElectricityIndex - oldElectricityIndex;
    }

    public long getWaterUsage() {
        return newWaterIndex - oldWaterIndex;
    }

    public long getElectricityCost() {
        return getElectricityUsage() * priceSnapshot.getElectricityRate();
    }

    public long getWaterCost() {
        return getWaterUsage() * priceSnapshot.getWaterRate();
    }

    public long getRoomCharge() {
        long roomCharge = priceSnapshot.getRoomPrice();
        if ("SERVICE".equalsIgnoreCase(priceSnapshot.getRoomType())) {
            roomCharge = roomCharge + priceSnapshot.getServiceFee();
        }
        return roomCharge;
    }

    public long getTotalAmount() {
        return getRoomCharge() + getElectricityCost() + getWaterCost();
    }

    public boolean isPaid() {
        return paid;
    }

    public void markAsPaid() {
        paid = true;
    }
}
