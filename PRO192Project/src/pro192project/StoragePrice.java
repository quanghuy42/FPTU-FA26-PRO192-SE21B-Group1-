/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro192project;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khai Nguyen 
 */
public class StoragePrice {
    private long standardRoom;
    private long serviceRoom;
    private long serviceFee;
    private long electricRate;
    private long waterRate;

    public StoragePrice(long standardRoom, long serviceRoom, long serviceFee,
                        long electricRate, long waterRate) {
        this.standardRoom = standardRoom;
        this.serviceRoom = serviceRoom;
        this.serviceFee = serviceFee;
        this.electricRate = electricRate;
        this.waterRate = waterRate;
    }

    // Constructor rá»—ng: chá»‰ dĂ¹ng Ä‘á»ƒ gá»i save()/load()
    public StoragePrice() {
        this(0, 0, 0, 0, 0);
    }

    public long getStandardRoom() { return this.standardRoom; }
    public long getServiceRoom() { return this.serviceRoom; }
    public long getServiceFee() { return this.serviceFee; }
    public long getElectricRate() { return this.electricRate; }
    public long getWaterRate() { return this.waterRate; }

    public String toLine() {
        return this.standardRoom + "|" + this.serviceRoom + "|" + this.serviceFee
                + "|" + this.electricRate + "|" + this.waterRate;
    }

    // ===== LÆ¯U (file chá»‰ cĂ³ Ä‘Ăºng má»™t dĂ²ng) =====
    public void save(StoragePrice price, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(price.toLine());
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.write(tmp, lines, StandardCharsets.UTF_8);
        Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
    }

    // ===== Äá»ŒC =====  (tráº£ vá» null náº¿u chÆ°a cĂ³ file)
    public StoragePrice load(Path file) throws IOException {
        if (!Files.exists(file)) {
            return null;
        }
        List<String> all = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<Integer> lineNos = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if (!all.get(i).trim().isEmpty()) {
                lines.add(all.get(i).trim());
                lineNos.add(i + 1);
            }
        }
        if (lines.size() != 1) {
            throw new IOException(file + ": phai co dung 1 dong gia nhung co " + lines.size());
        }
        int lineNo = lineNos.get(0);
        String[] p = lines.get(0).split("\\|", -1);
        if (p.length != 5) {
            throw new IOException(file + ", dong " + lineNo
                    + ": can 5 truong nhung co " + p.length);
        }
        return new StoragePrice(
                parseNumber(p[0], "tien phong thuong", file, lineNo),
                parseNumber(p[1], "tien phong dich vu", file, lineNo),
                parseNumber(p[2], "phi dich vu", file, lineNo),
                parseNumber(p[3], "don gia dien", file, lineNo),
                parseNumber(p[4], "don gia nuoc", file, lineNo));
    }

    private long parseNumber(String text, String field, Path file, int lineNo) throws IOException {
        try {
            long v = Long.parseLong(text.trim());
            if (v < 0) {
                throw new IOException(file + ", dong " + lineNo + ": " + field + " khong duoc am");
            }
            return v;
        } catch (NumberFormatException e) {
            throw new IOException(file + ", dong " + lineNo + ": " + field
                    + " khong phai so nguyen: '" + text + "'");
        }
    }
}
