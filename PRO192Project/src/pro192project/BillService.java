package pro192project;

import java.util.ArrayList;
import java.util.List;

/**
 * BillService - Quản lý và xử lý nghiệp vụ hóa đơn
 * @author Nguyễn Đăng Huy
 */
public class BillService {
    private List<Bill> billList;

    public BillService() {
        this.billList = new ArrayList<Bill>();
    }

    public void addBill(Bill bill) {
        this.billList.add(bill);
    }

    public List<Bill> getBillList() {
        return this.billList;
    }

    public Bill findBillById(String billId) {
        if (billId == null) {
            return null;
        }
        for (int i = 0; i < this.billList.size(); i++) {
            Bill b = this.billList.get(i);
            if (b.getBillId().equalsIgnoreCase(billId)) {
                return b;
            }
        }
        return null;
    }

    public void viewBillDetails(String billId) {
        Bill targetBill = findBillById(billId);

        if (targetBill == null) {
            System.out.println("Bill not found with ID: " + billId);
            return;
        }

        System.out.println("\n========== BILL DETAILS: " + targetBill.getBillId() + " ==========");
        System.out.println("Room ID: " + targetBill.getRoomId());
        System.out.println("Billing Month: " + targetBill.getBillingMonth());
        System.out.println("---------------------------------------------------------");
        System.out.printf("Room Charge: %,d VND\n", targetBill.getPriceSnapshot().getRoomPrice());
        
        if ("SERVICE".equalsIgnoreCase(targetBill.getPriceSnapshot().getRoomType())) {
            System.out.printf("Service Fee: %,d VND\n", targetBill.getPriceSnapshot().getServiceFee());
        }
        
        System.out.printf("Electricity Usage: %d kWh -> Amount: %,d VND\n",
                targetBill.getElectricityUsage(), targetBill.getElectricityCost());
        System.out.printf("Water Usage: %d m3 -> Amount: %,d VND\n",
                targetBill.getWaterUsage(), targetBill.getWaterCost());
        System.out.println("---------------------------------------------------------");
        System.out.printf("TOTAL BILL AMOUNT: %,d VND\n", targetBill.getTotalAmount());
        System.out.println("Status: " + (targetBill.isPaid() ? "PAID" : "UNPAID"));
        System.out.println("=========================================================");
    }

    public boolean payBill(String billId) {
        Bill targetBill = findBillById(billId);

        if (targetBill == null) {
            System.out.println("Bill not found with ID: " + billId);
            return false;
        }

        if (targetBill.isPaid()) {
            System.out.println("This bill has already been paid! No additional payment collected.");
            return false;
        } else {
            targetBill.markAsPaid();
            System.out.printf("Payment successful for bill %s with amount %,d VND.\n", 
                    targetBill.getBillId(), targetBill.getTotalAmount());
            return true;
        }
    }

    public void listUnpaidBillsAndTotalDebt() {
        System.out.println("\n================== LIST OF UNPAID BILLS ==================");
        System.out.printf("%-10s | %-10s | %-10s | %s\n", "Bill ID", "Room ID", "Month", "Unpaid Amount (VND)");
        System.out.println("------------------------------------------------------------");

        long totalDebt = 0;
        int count = 0;

        for (int i = 0; i < this.billList.size(); i++) {
            Bill b = this.billList.get(i);
            if (!b.isPaid()) {
                System.out.printf("%-10s | %-10s | %-10s | %,d\n",
                        b.getBillId(), b.getRoomId(), b.getBillingMonth(), b.getTotalAmount());
                totalDebt += b.getTotalAmount();
                count++;
            }
        }

        System.out.println("------------------------------------------------------------");
        if (count == 0) {
            System.out.println("Great! There are no unpaid bills.");
        } else {
            System.out.printf("Total unpaid bills: %d\n", count);
            System.out.printf("TOTAL DEBT AMOUNT: %,d VND\n", totalDebt);
        }
        System.out.println("=============================================================");
    }

    public long calculateTotalDebt() {
        long totalDebt = 0;
        for (int i = 0; i < this.billList.size(); i++) {
            Bill b = this.billList.get(i);
            if (!b.isPaid()) {
                totalDebt += b.getTotalAmount();
            }
        }
        return totalDebt;
    }
}
