/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dev.nguyen;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author HP
 */
public class StorageBill {
    private String id;
    private String roomId;
    private String month;        // YYYY-MM
    private long electricStart;
    private long electricEnd;
    private long waterStart;
    private long waterEnd;
    private String roomType;     
    private long roomCharge;
    private long serviceFee;
    private long electricRate;
    private long waterRate;
    private String status;      

    public StorageBill(String id, String roomId, String month,
                       long electricStart, long electricEnd, long waterStart, long waterEnd,
                       String roomType, long roomCharge, long serviceFee,
                       long electricRate, long waterRate, String status) {
        this.id = id;
        this.roomId = roomId;
        this.month = month;
        this.electricStart = electricStart;
        this.electricEnd = electricEnd;
        this.waterStart = waterStart;
        this.waterEnd = waterEnd;
        this.roomType = roomType;
        this.roomCharge = roomCharge;
        this.serviceFee = serviceFee;
        this.electricRate = electricRate;
        this.waterRate = waterRate;
        this.status = status;
    }

    // Constructor rỗng: chỉ dùng để gọi save()/load()/nextBillId()
    public StorageBill() {
        this("", "", "", 0, 0, 0, 0, "STANDARD", 0, 0, 0, 0, "UNPAID");
    }

    public String getId() { return this.id; }
    public String getRoomId() { return this.roomId; }
    public String getMonth() { return this.month; }
    public String getRoomType() { return this.roomType; }
    public String getStatus() { return this.status; }
    public boolean isPaid() { return this.status.equals("PAID"); }

    public long getElectricUsage() { return this.electricEnd - this.electricStart; }
    public long getWaterUsage() { return this.waterEnd - this.waterStart; }

    // Tổng = tiền phòng + phí dịch vụ + tiền điện + tiền nước (theo giá đã chụp)
    public long getTotal() {
        return this.roomCharge + this.serviceFee
                + getElectricUsage() * this.electricRate
                + getWaterUsage() * this.waterRate;
    }

    public String toLine() {
        return this.id + "|" + this.roomId + "|" + this.month + "|"
                + this.electricStart + "|" + this.electricEnd + "|"
                + this.waterStart + "|" + this.waterEnd + "|"
                + this.roomType + "|" + this.roomCharge + "|" + this.serviceFee + "|"
                + this.electricRate + "|" + this.waterRate + "|" + this.status;
    }

    // Mã hóa đơn mới = số lớn nhất đang có + 1 (HD001, HD002, ...)
    public String nextBillId(List<StorageBill> bills) {
        long max = 0;
        for (StorageBill b : bills) {
            String code = b.getId();
            if (code.startsWith("HD")) {
                try {
                    long n = Long.parseLong(code.substring(2));
                    if (n > max) {
                        max = n;
                    }
                } catch (NumberFormatException e) {
                    // bỏ qua mã không theo dạng HDxxx
                }
            }
        }
        return String.format("HD%03d", max + 1);
    }

    // ===== LƯU =====
    public void save(List<StorageBill> list, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        for (StorageBill b : list) {
            lines.add(b.toLine());
        }
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.write(tmp, lines, StandardCharsets.UTF_8);
        Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
    }

    // ===== ĐỌC =====
    public List<StorageBill> load(Path file) throws IOException {
        List<StorageBill> result = new ArrayList<>();
        if (!Files.exists(file)) {
            return result;
        }
        Set<String> seenIds = new HashSet<>();
        Set<String> seenRoomMonth = new HashSet<>();
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            int lineNo = i + 1;
            String[] p = line.split("\\|", -1);
            if (p.length != 13) {
                throw new IOException(file + ", dong " + lineNo
                        + ": can 13 truong nhung co " + p.length);
            }

            String bid = p[0].trim();
            String rid = p[1].trim();
            String month = p[2].trim();
            if (bid.isEmpty() || bid.matches(".*\\s.*")) {
                throw new IOException(file + ", dong " + lineNo + ": ma hoa don rong hoac chua khoang trang");
            }
            if (rid.isEmpty() || rid.matches(".*\\s.*")) {
                throw new IOException(file + ", dong " + lineNo + ": ma phong rong hoac chua khoang trang");
            }
            if (!month.matches("\\d{4}-(0[1-9]|1[0-2])")) {
                throw new IOException(file + ", dong " + lineNo + ": thang '" + month + "' khong đung dang YYYY-MM");
            }

            long eStart = parseNumber(p[3], "đien đeu", file, lineNo);
            long eEnd = parseNumber(p[4], "đien cuoi", file, lineNo);
            long wStart = parseNumber(p[5], "nuoc đau", file, lineNo);
            long wEnd = parseNumber(p[6], "nuoc cuoi", file, lineNo);
            if (eStart > 1000000 || eEnd > 1000000 || wStart > 1000000 || wEnd > 1000000) {
                throw new IOException(file + ", dong " + lineNo + ": chi so đien/nuoc vuot qua 1.000.000");
            }
            if (eEnd < eStart) {
                throw new IOException(file + ", dong " + lineNo + ": đien cuoi nho hon đien đau");
            }
            if (wEnd < wStart) {
                throw new IOException(file + ", dong " + lineNo + ": nuoc cuoi nho hon nuoc đau");
            }

            String type = p[7].trim();
            if (!type.equals("STANDARD") && !type.equals("SERVICE")) {
                throw new IOException(file + ", dong " + lineNo + ": loai phong khong hop le '" + type + "'");
            }
            long charge = parseNumber(p[8], "tien phong", file, lineNo);
            long fee = parseNumber(p[9], "phi dich vu", file, lineNo);
            long eRate = parseNumber(p[10], "đon gia đien", file, lineNo);
            long wRate = parseNumber(p[11], "đon gia nuoc", file, lineNo);

            String status = p[12].trim();
            if (!status.equals("UNPAID") && !status.equals("PAID")) {
                throw new IOException(file + ", dòng " + lineNo + ": trang thai '" + status
                        + "' khong hop le (can UNPAID hoac PAID)");
            }

            if (!seenIds.add(bid)) {
                throw new IOException(file + ", dong " + lineNo + ": ma hoa don bi trung: " + bid);
            }
            if (!seenRoomMonth.add(rid + "|" + month)) {
                throw new IOException(file + ", dong " + lineNo + ": phong " + rid
                        + " da co hoa don thang " + month);
            }

            result.add(new StorageBill(bid, rid, month, eStart, eEnd, wStart, wEnd,
                    type, charge, fee, eRate, wRate, status));
        }
        return result;
    }

    private long parseNumber(String text, String field, Path file, int lineNo) throws IOException {
        try {
            long v = Long.parseLong(text.trim());
            if (v < 0) {
                throw new IOException(file + ", dong " + lineNo + ": " + field + " khong đuoc am");
            }
            return v;
        } catch (NumberFormatException e) {
            throw new IOException(file + ", dong " + lineNo + ": " + field
                    + " khong phai so nguyen: '" + text + "'");
        }
    }
}
