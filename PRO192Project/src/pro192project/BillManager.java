package pro192project;

import java.util.ArrayList;

public class BillManager {
    private ArrayList<Bill> bills = new ArrayList<Bill>();

    public Bill createBill(Room room, String month,
            long electricStart, long electricEnd,
            long waterStart, long waterEnd) {
        if (room == null) {
            System.out.println("Loi: Doi tuong phong khong ton tai!");
            return null;
        }

        if (room.getCurrentOccupancy() <= 0) {
            System.out.println("Loi nghiep vu: Phong nay hien dang trong, khong the lap hoa don!");
            return null;
        }

        if (!isValidBillingMonth(month)) {
            System.out.println("Loi dinh dang: Thang lap hoa don phai theo dang YYYY-MM.");
        }

        if (electricStart < 0 || electricEnd < 0
                || electricStart > 1000000 || electricEnd > 1000000) {
            System.out.println("Chi so dien phai tu 0 den 1000000.");
            return null;
        }
        if (waterStart < 0 || waterEnd < 0
                || waterStart > 1000000 || waterEnd > 1000000) {
            System.out.println("Chi so nuoc phai tu 0 den 1000000.");
            return null;
        }

        if (electricEnd < electricStart) {
            System.out.println("Loi nhap du lieu: Chi so dien cuoi khong duoc nho hon chi so dau!");
            return null;
        }
        if (waterEnd < waterStart) {
            System.out.println("Loi nhap du lieu: Chi so nuoc cuoi khong duoc nho hon chi so dau!");
            return null;
        }

        String roomId = room.getRoomId();
        for (Bill oldBill : bills) {
            if (oldBill.getRoomId().equalsIgnoreCase(roomId)
                    && oldBill.getBillingMonth().equals(month)) {
                System.out.println("Loi trung lap: Phong nay da co hoa don trong thang " + month + "!");
                return null;
            }
        }
        PriceSnapshot snapshot = new PriceSnapshot(
                room.getRoomType(), room.getBaseRent(), room.getServiceFee(), 3000L, 15000L);
        Bill bill = new Bill(roomId, month, snapshot,
                electricStart, electricEnd, waterStart, waterEnd);
        bills.add(bill);
        return bill;
    }

    public boolean isValidBillingMonth(String month) {
        if (month == null || month.length() != 7 || month.charAt(4) != '-') {
            return false;
        }

        for (int i = 0; i < month.length(); i++) {
            if (i != 4 && (month.charAt(i) < '0' || month.charAt(i) > '9')) {
                return false;
            }
        }

        int year = Integer.parseInt(month.substring(0, 4));
        int monthNumber = Integer.parseInt(month.substring(5, 7));
        return year > 0 && monthNumber >= 1 && monthNumber <= 12;
    }
}
