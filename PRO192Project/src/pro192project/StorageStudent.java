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
public class StorageStudent {
    private String id;
    private String name;
    private String roomId; 

    public StorageStudent(String id, String name, String roomId) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
    }

    // Constructor rỗng: chỉ dùng để gọi save()/load()
    public StorageStudent() {
        this("", "", null);
    }

    public String getId() { return this.id; }
    public String getName() { return this.name; }
    public String getRoomId() { return this.roomId; }

    // Biến đối tượng thành một dòng chữ
    public String toLine() {
        String room = (this.roomId == null) ? "NONE" : this.roomId;
        return this.id + "|" + this.name + "|" + room;
    }

    // ===== LƯU =====
    public void save(List<StorageStudent> list, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        for (StorageStudent s : list) {
            lines.add(s.toLine());
        }
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.write(tmp, lines, StandardCharsets.UTF_8);
        // Ghi xong hết mới đè file thật -> lỗi giữa chừng thì file cũ vẫn còn nguyên
        Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
    }

    // ===== ĐỌC =====
    public List<StorageStudent> load(Path file) throws IOException {
        List<StorageStudent> result = new ArrayList<>();
        if (!Files.exists(file)) {
            return result; // lần chạy đầu: chưa có file thì danh sách rỗng
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
            if (p.length != 3) {
                throw new IOException(file + ", dong " + lineNo
                        + ": can 3 truong nhung co " + p.length);
            }
            String sid = p[0].trim();
            String name = p[1].trim();
            String room = p[2].trim();

            if (sid.isEmpty() || sid.matches(".*\\s.*")) {
                throw new IOException(file + ", dong " + lineNo + ": MSSV rong hoac chua khoang trang");
            }
            if (name.isEmpty()) {
                throw new IOException(file + ", dong " + lineNo + ": ho ten rong");
            }
            if (room.isEmpty() || room.matches(".*\\s.*")) {
                throw new IOException(file + ", dong " + lineNo + ": ma phong rong hoac chua khoang trang");
            }
            if (!seen.add(sid)) {
                throw new IOException(file + ", dong " + lineNo + ": MSSV bi trung: " + sid);
            }
            result.add(new StorageStudent(sid, name, room.equals("NONE") ? null : room));
        }
        return result;
    }
}
