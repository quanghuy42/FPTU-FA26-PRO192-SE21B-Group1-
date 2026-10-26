/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Huy
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

    public String getRoomId() {
        return this.roomId;
    }

    public String getBillingMonth() {
        return this.billingMonth;
    }

    public PriceSnapshot getPriceSnapshot() {
        return this.priceSnapshot;
    }
}