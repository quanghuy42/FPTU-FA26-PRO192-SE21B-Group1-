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
 * @author Khai Nguyen 
 */
public class StorageRoom {
    private String type;   
    private String id;
    private int capacity;
    private long roomCharge;
    private long serviceFee; // phòng thường = 0

    public StorageRoom(String type, String id, int capacity, long roomCharge, long serviceFee) {
        this.type = type;
        this.id = id;
        this.capacity = capacity;
        this.roomCharge = roomCharge;
        this.serviceFee = serviceFee;
    }

    // Constructor rỗng: chỉ dùng để gọi save()/load()
    public StorageRoom() {
        this("STANDARD", "", 1, 0, 0);
    }

    public String getType() { return this.type; }
    public String getId() { return this.id; }
    public int getCapacity() { return this.capacity; }
    public long getRoomCharge() { return this.roomCharge; }
    public long getServiceFee() { return this.serviceFee; }

    // Tiền phòng một tháng (đã gồm phí dịch vụ nếu có)
    public long calculateRoomCharge() {
        return this.roomCharge + this.serviceFee;
    }

    public String toLine() {
        if (this.type.equals("SERVICE")) {
            return "SERVICE|" + this.id + "|" + this.capacity + "|"
                    + this.roomCharge + "|" + this.serviceFee;
        }
        return "STANDARD|" + this.id + "|" + this.capacity + "|" + this.roomCharge;
    }

    // ===== LƯU =====
    public void save(List<StorageRoom> list, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        for (StorageRoom r : list) {
            lines.add(r.toLine());
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
    public List<StorageRoom> load(Path file) throws IOException {
        List<StorageRoom> result = new ArrayList<>();
        if (!Files.exists(file)) {
            return result;
        }
        Set<String> seen = new HashSet<>();
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            int lineNo = i + 1;
            String[] p = line.split("\\|", -1);
            String t = p[0].trim();

            int expected;
            if (t.equals("STANDARD")) {
                expected = 4;
            } else if (t.equals("SERVICE")) {
                expected = 5;
            } else {
                throw new IOException(file + ", dong " + lineNo
                        + ": loai phong khong hop le '" + t + "' (can STANDARD hoac SERVICE)");
            }
            if (p.length != expected) {
                throw new IOException(file + ", dong " + lineNo + ": phong " + t
                        + " can " + expected + " truong nhung co " + p.length);
            }

            String rid = p[1].trim();
            if (rid.isEmpty() || rid.matches(".*\\s.*")) {
                throw new IOException(file + ", dong " + lineNo + ": ma phong rong hoac chua khoang trang");
            }
            if (!seen.add(rid)) {
                throw new IOException(file + ", dong " + lineNo + ": ma phong bi trung: " + rid);
            }

            long cap = parseNumber(p[2], "suc chua", file, lineNo);
            if (cap < 1 || cap > 10) {
                throw new IOException(file + ", dong " + lineNo + ": suc chua phai tu 1 đen 10");
            }
            long charge = parseNumber(p[3], "tien phong", file, lineNo);
            long fee = (expected == 5) ? parseNumber(p[4], "phi dich vu", file, lineNo) : 0;

            result.add(new StorageRoom(t, rid, (int) cap, charge, fee));
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
