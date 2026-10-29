package pro192project;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BillManager {
    private static final int MAX_METER_READING = 1_000_000;
    private static final DateTimeFormatter BILLING_MONTH_FORMAT =
            DateTimeFormatter.ofPattern("uuuu-MM");

    private final Map<String, Set<String>> billedMonthsByRoom = new HashMap<>();

    public Bill createBill(Room room, String billingMonth,
                           int electricityStart, int electricityEnd,
                           int waterStart, int waterEnd) {
        if (room == null) {
            throw new IllegalArgumentException("Loi: Doi tuong phong khong ton tai!");
        }

        String roomId = room.getRoomId();
        Room.validateRoomId(roomId);
        if (room.getResidentCount() <= 0) {
            throw new IllegalStateException(
                    "Loi nghiep vu: Phong nay hien dang trong (khong co nguoi o), khong the lap hoa don!");
        }
        if (!isValidBillingMonth(billingMonth)) {
            throw new IllegalArgumentException(
                    "Loi dinh dang: Thang lap hoa don phai theo dinh dang YYYY-MM (Vi du: 2026-06).");
        }

        validateMeterReading(electricityStart, "Chi so dien dau");
        validateMeterReading(electricityEnd, "Chi so dien cuoi");
        validateMeterReading(waterStart, "Chi so nuoc dau");
        validateMeterReading(waterEnd, "Chi so nuoc cuoi");

        if (electricityEnd < electricityStart) {
            throw new IllegalArgumentException(
                    "Loi nhap du lieu: Chi so dien cuoi khong duoc nho hon chi so dau!");
        }
        if (waterEnd < waterStart) {
            throw new IllegalArgumentException(
                    "Loi nhap du lieu: Chi so nuoc cuoi khong duoc nho hon chi so dau!");
        }

        Set<String> billedMonths = billedMonthsByRoom.get(roomId);
        if (billedMonths != null && billedMonths.contains(billingMonth)) {
            throw new IllegalStateException(
                    "Loi trung lap: Phong nay da co hoa don trong thang " + billingMonth + "!");
        }

        long roomCharge = room.calculateRoomCharge();
        long serviceFee = room.getServiceFee();
        if (roomCharge < 0 || serviceFee < 0) {
            throw new IllegalStateException("Tien phong va phi dich vu khong duoc am.");
        }

        PriceSnapshot snapshot = new PriceSnapshot(roomCharge, serviceFee);
        Bill bill = new Bill(roomId, billingMonth, snapshot, electricityStart,
                electricityEnd, waterStart, waterEnd);
        billedMonthsByRoom.computeIfAbsent(roomId, key -> new HashSet<String>())
                .add(billingMonth);
        return bill;
    }

    private boolean isValidBillingMonth(String monthStr) {
        if (monthStr == null || !monthStr.matches("^\\d{4}-(0[1-9]|1[0-2])$")) {
            return false;
        }
        try {
            return YearMonth.parse(monthStr, BILLING_MONTH_FORMAT).getYear() > 0;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void validateMeterReading(int reading, String fieldName) {
        if (reading < 0 || reading > MAX_METER_READING) {
            throw new IllegalArgumentException(
                    fieldName + " phai tu 0 den " + MAX_METER_READING + ".");
        }
    }
}
