/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Huy
 */
import java.util.ArrayList;
import java.util.List;

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

    public void viewBillDetails(String billId) {
        Bill targetBill = null;
        for (int i = 0; i < this.billList.size(); i++) {
            Bill b = this.billList.get(i);
            if (b.getBillId().equalsIgnoreCase(billId)) {
                targetBill = b;
                break;
            }
        }

        if (targetBill == null) {
            System.out.println("Không tìm thấy hóa đơn với mã: " + billId);
            return;
        }

        System.out.println("\n========== CHI TIẾT HÓA ĐƠN: " + targetBill.getBillId() + " ==========");
        System.out.println("Mã phòng: " + targetBill.getRoomId());
        System.out.println("Tháng hóa đơn: " + targetBill.getBillingMonth());
        System.out.println("---------------------------------------------------------");
        System.out.printf("Tiền phòng: %,d đ\n", targetBill.getPriceSnapshot().getRoomPrice());
        
        if ("SERVICE".equalsIgnoreCase(targetBill.getPriceSnapshot().getRoomType())) {
            System.out.printf("Phí dịch vụ: %,d đ\n", targetBill.getPriceSnapshot().getServiceFee());
        }
        
        System.out.printf("Tiêu thụ điện: %d kWh (Mới: %d - Cũ: %ều) -> Thành tiền: %,d đ\n",
                targetBill.getElectricityUsage(), targetBill.getElectricityCost()); // In chi tiết tiêu thụ
        System.out.printf("Tiêu thụ nước: %d m³ -> Thành tiền: %,d đ\n",
                targetBill.getWaterUsage(), targetBill.getWaterCost());
        System.out.println("---------------------------------------------------------");
        System.out.printf("TỔNG TIỀN HÓA ĐƠN: %,d đ\n", targetBill.getTotalAmount());
        System.out.println("Trạng thái: " + (targetBill.isPaid() ? "ĐÃ THANH TOÁN (PAID)" : "CÒN NỢ (UNPAID)"));
        System.out.println("=========================================================");
    }

    public void payBill(String billId) {
        Bill targetBill = null;
        for (int i = 0; i < this.billList.size(); i++) {
            Bill b = this.billList.get(i);
            if (b.getBillId().equalsIgnoreCase(billId)) {
                targetBill = b;
                break;
            }
        }

        if (targetBill == null) {
            System.out.println("Không tìm thấy hóa đơn với mã: " + billId);
            return;
        }

        if (targetBill.isPaid()) {
            System.out.println("Hóa đơn này đã được thanh toán trước đó rồi! Không thu thêm tiền.");[cite: 8]
        } else {
            targetBill.markAsPaid();
            System.out.printf("Thanh toán thành công cho hóa đơn %s với số tiền %,d đ.\n", 
                    targetBill.getBillId(), targetBill.getTotalAmount());
        }
    }

    public void listUnpaidBillsAndTotalDebt() {
        System.out.println("\n================== DANH SÁCH HÓA ĐƠN CÒN NỢ ==================");
        System.out.printf("%-10s | %-10s | %-10s | %s\n", "Mã HĐ", "Mã Phòng", "Tháng", "Số tiền nợ (đ)");
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
            System.out.println("Tuyệt vời! Không có hóa đơn nào đang còn nợ.");
        } else {
            System.out.printf("Tổng số hóa đơn còn nợ: %d\n", count);
            System.out.printf("TỔNG CỘNG TIỀN NỢ: %,d đ\n", totalDebt);
        }
        System.out.println("=============================================================");
    }
}