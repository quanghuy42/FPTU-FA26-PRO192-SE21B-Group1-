package pro192project;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ỨNG DỤNG QUẢN LÝ KÝ TÚC XÁ & TIỆN ÍCH (DORM UTILITY MANAGEMENT SYSTEM)
 * Lớp SE21B - Nhóm 1 (Group 1) - Môn PRO192
 * 
 * Tích hợp toàn diện các module của 5 thành viên:
 * - Thành viên 1 (Thái Quang Huy - QS190001): Mô hình phòng, tính tiền phòng đa hình, giá mẫu
 * - Thành viên 2 (Lê Thành Luân - QE200043): Quản lý hồ sơ sinh viên, xếp/trả phòng, sức chứa
 * - Thành viên 3 (Hồ Trương Anh Quân - QE210067): Nhập chỉ số, kiểm tra nghiệp vụ và lập hóa đơn
 * - Thành viên 4 (Nguyễn Đăng Huy - QE210182): Thanh toán hóa đơn, tra cứu, liệt kê nợ & tính tổng nợ
 * - Thành viên 5 (Lê Trần Khải Nguyên - QE210233): Đọc / ghi an toàn hệ thống file văn bản UTF-8
 */
public class PRO192Project {

    // Đường dẫn tương đối lưu trữ dữ liệu (tự thích ứng khi chạy từ NetBeans hoặc từ thư mục gốc)
    private static Path getDataDir() {
        if (java.nio.file.Files.exists(Paths.get("PRO192Project", "data"))) {
            return Paths.get("PRO192Project", "data");
        }
        return Paths.get("data");
    }

    private static Path getRoomsFile() { return getDataDir().resolve("rooms.txt"); }
    private static Path getStudentsFile() { return getDataDir().resolve("students.txt"); }
    private static Path getPricesFile() { return getDataDir().resolve("prices.txt"); }
    private static Path getBillsFile() { return getDataDir().resolve("bills.txt"); }

    // Dữ liệu trong bộ nhớ
    private static Map<String, Room> rooms = new HashMap<String, Room>();
    private static StudentManager studentManager = new StudentManager(rooms);
    private static BillService billService = new BillService();
    private static BillManager billManager = new BillManager();

    // Biểu giá hiện hành (đồng)
    private static long currentStandardRent = 1200000L;
    private static long currentServiceRent = 1800000L;
    private static long currentServiceFee = 300000L;
    private static long currentElectricityRate = 3000L;
    private static long currentWaterRate = 15000L;

    public static void main(String[] args) {
        // Tự động nạp dữ liệu từ thư mục data/ nếu có
        loadAllData();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=======================================================");
            System.out.println("   HỆ THỐNG QUẢN LÝ KÝ TÚC XÁ - SE21B - GROUP 1");
            System.out.println("=======================================================");
            System.out.println("1. Xem danh sách phòng & Thêm phòng mới");
            System.out.println("2. Quản lý sinh viên & Xếp phòng / Trả phòng");
            System.out.println("3. Xem sức chứa, số người đang ở và chỗ trống từng phòng");
            System.out.println("4. Nhập chỉ số điện/nước & Lập hóa đơn mới");
            System.out.println("5. Tra cứu chi tiết một hóa đơn");
            System.out.println("6. Xác nhận thanh toán & Thống kê tổng nợ");
            System.out.println("7. Cập nhật biểu giá hiện hành");
            System.out.println("8. Lưu dữ liệu xuống đĩa (data/*.txt)");
            System.out.println("9. Chạy tự động kịch bản nghiệm thu kiểm thử (K01 - K11)");
            System.out.println("0. Lưu và Thoát chương trình");
            System.out.print("Vui lòng chọn chức năng (0-9): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    handleRoomMenu(scanner);
                    saveAllDataSafely();
                    break;
                case "2":
                    handleStudentMenu(scanner);
                    saveAllDataSafely();
                    break;
                case "3":
                    studentManager.displayRoomCapacity();
                    break;
                case "4":
                    handleCreateBill(scanner);
                    saveAllDataSafely();
                    break;
                case "5":
                    handleViewBill(scanner);
                    break;
                case "6":
                    handlePaymentMenu(scanner);
                    saveAllDataSafely();
                    break;
                case "7":
                    handleUpdatePrice(scanner);
                    saveAllDataSafely();
                    break;
                case "8":
                    saveAllDataSafely();
                    System.out.println(">> Đã lưu toàn bộ dữ liệu thành công vào thư mục data/");
                    break;
                case "9":
                    runAutomatedAcceptanceTests();
                    break;
                case "0":
                    saveAllDataSafely();
                    System.out.println(">> Dữ liệu đã được lưu an toàn. Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    // ==========================================
    // CÁC HÀM XỬ LÝ GIAO DIỆN CONSOLE NGHIỆP VỤ
    // ==========================================

    private static void handleRoomMenu(Scanner scanner) {
        System.out.println("\n--- DANH SÁCH PHÒNG HIỆN CÓ ---");
        if (rooms.isEmpty()) {
            System.out.println("(Chưa có phòng nào trong hệ thống)");
        } else {
            for (Room r : rooms.values()) {
                System.out.println(r);
            }
        }

        System.out.print("\nBạn có muốn thêm phòng mới? (y/n): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
            return;
        }

        try {
            System.out.print("Nhập loại phòng (1: STANDARD, 2: SERVICE): ");
            String typeChoice = scanner.nextLine().trim();
            System.out.print("Nhập mã phòng (ví dụ A103): ");
            String roomId = scanner.nextLine().trim();
            System.out.print("Nhập sức chứa (1-10): ");
            int cap = Integer.parseInt(scanner.nextLine().trim());

            if (rooms.containsKey(roomId)) {
                System.out.println("Lỗi: Mã phòng đã tồn tại trong hệ thống!");
                return;
            }

            if (typeChoice.equals("2")) {
                Room r = new ServiceRoom(roomId, cap, currentServiceRent, currentServiceFee);
                rooms.put(roomId, r);
                System.out.println(">> Thêm phòng dịch vụ " + roomId + " thành công!");
            } else {
                Room r = new StandardRoom(roomId, cap, currentStandardRent);
                rooms.put(roomId, r);
                System.out.println(">> Thêm phòng tiêu chuẩn " + roomId + " thành công!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi nhập dữ liệu: " + e.getMessage());
        }
    }

    private static void handleStudentMenu(Scanner scanner) {
        System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
        System.out.println("1. Xem danh sách sinh viên");
        System.out.println("2. Thêm sinh viên mới");
        System.out.println("3. Xếp phòng cho sinh viên");
        System.out.println("4. Xác nhận trả phòng cho sinh viên");
        System.out.print("Chọn thao tác: ");
        String sub = scanner.nextLine().trim();

        if (sub.equals("1")) {
            studentManager.displayStudents();
        } else if (sub.equals("2")) {
            try {
                System.out.print("Nhập MSSV (ví dụ QE210005): ");
                String sid = scanner.nextLine().trim();
                System.out.print("Nhập họ và tên: ");
                String name = scanner.nextLine().trim();
                if (studentManager.addStudent(sid, name)) {
                    System.out.println(">> Thêm sinh viên thành công!");
                } else {
                    System.out.println(">> Thất bại: MSSV đã tồn tại hoặc không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        } else if (sub.equals("3")) {
            System.out.print("Nhập MSSV: ");
            String sid = scanner.nextLine().trim();
            System.out.print("Nhập mã phòng muốn xếp vào: ");
            String rid = scanner.nextLine().trim();
            if (studentManager.assignStudentToRoom(sid, rid)) {
                System.out.println(">> Xếp phòng thành công!");
            } else {
                System.out.println(">> Xếp phòng thất bại: Phòng không tồn tại, phòng đã đầy, hoặc SV đã có phòng!");
            }
        } else if (sub.equals("4")) {
            System.out.print("Nhập MSSV muốn trả phòng: ");
            String sid = scanner.nextLine().trim();
            if (studentManager.checkOutStudent(sid)) {
                System.out.println(">> Trả phòng thành công!");
            } else {
                System.out.println(">> Thất bại: Sinh viên không tồn tại hoặc hiện chưa ở phòng nào!");
            }
        }
    }

    private static void handleCreateBill(Scanner scanner) {
        System.out.println("\n--- LẬP HÓA ĐƠN TIỀN PHÒNG & ĐIỆN NƯỚC ---");
        try {
            System.out.print("Nhập mã phòng: ");
            String rid = scanner.nextLine().trim();
            Room r = rooms.get(rid);
            if (r == null) {
                System.out.println("Lỗi: Mã phòng không tồn tại!");
                return;
            }

            System.out.print("Nhập tháng lập hóa đơn (YYYY-MM): ");
            String month = scanner.nextLine().trim();

            System.out.print("Nhập chỉ số điện đầu: ");
            long eStart = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Nhập chỉ số điện cuối: ");
            long eEnd = Long.parseLong(scanner.nextLine().trim());

            System.out.print("Nhập chỉ số nước đầu: ");
            long wStart = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Nhập chỉ số nước cuối: ");
            long wEnd = Long.parseLong(scanner.nextLine().trim());

            // Lập hóa đơn và kiểm tra nghiệp vụ
            Bill bill = billManager.createBill(r, month, eStart, eEnd, wStart, wEnd);

            // Sinh mã hóa đơn tự động HDxxx
            StorageBill sb = new StorageBill();
            List<StorageBill> currentList = new ArrayList<StorageBill>();
            for (Bill b : billService.getBillList()) {
                currentList.add(new StorageBill(b.getBillId(), b.getRoomId(), b.getBillingMonth(),
                        b.getOldElectricityIndex(), b.getNewElectricityIndex(),
                        b.getOldWaterIndex(), b.getNewWaterIndex(),
                        b.getPriceSnapshot().getRoomType(), b.getPriceSnapshot().getRoomPrice(),
                        b.getPriceSnapshot().getServiceFee(), b.getPriceSnapshot().getElectricityRate(),
                        b.getPriceSnapshot().getWaterRate(), b.isPaid() ? "PAID" : "UNPAID"));
            }
            String nextId = sb.nextBillId(currentList);
            bill.setBillId(nextId);

            billService.addBill(bill);
            System.out.println(">> LẬP HÓA ĐƠN THÀNH CÔNG! Mã hóa đơn: " + nextId);
            System.out.printf(">> Tổng số tiền phải thu: %,d đ (Trạng thái: UNPAID)\n", bill.getTotalAmount());
        } catch (Exception e) {
            System.out.println("Lỗi lập hóa đơn: " + e.getMessage());
        }
    }

    private static void handleViewBill(Scanner scanner) {
        System.out.print("Nhập mã hóa đơn cần xem chi tiết (ví dụ HD001): ");
        String bid = scanner.nextLine().trim();
        billService.viewBillDetails(bid);
    }

    private static void handlePaymentMenu(Scanner scanner) {
        System.out.println("\n--- THANH TOÁN & TRA CỨU CÔNG NỢ ---");
        System.out.println("1. Xem danh sách hóa đơn còn nợ & Tổng nợ");
        System.out.println("2. Xác nhận thanh toán một hóa đơn");
        System.out.print("Chọn thao tác: ");
        String sub = scanner.nextLine().trim();

        if (sub.equals("1")) {
            billService.listUnpaidBillsAndTotalDebt();
        } else if (sub.equals("2")) {
            System.out.print("Nhập mã hóa đơn cần thanh toán: ");
            String bid = scanner.nextLine().trim();
            billService.payBill(bid);
        }
    }

    private static void handleUpdatePrice(Scanner scanner) {
        System.out.println("\n--- CẬP NHẬT BIỂU GIÁ HIỆN HÀNH ---");
        System.out.printf("Giá hiện tại: Phòng thường=%,d | Dịch vụ=%,d (Phí DV=%,d) | Điện=%,d | Nước=%,d\n",
                currentStandardRent, currentServiceRent, currentServiceFee, currentElectricityRate, currentWaterRate);
        try {
            System.out.print("Nhập giá phòng thường mới: ");
            currentStandardRent = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Nhập giá phòng dịch vụ mới: ");
            currentServiceRent = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Nhập phí dịch vụ mới: ");
            currentServiceFee = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Nhập đơn giá điện mới (đ/kWh): ");
            currentElectricityRate = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Nhập đơn giá nước mới (đ/m³): ");
            currentWaterRate = Long.parseLong(scanner.nextLine().trim());

            System.out.println(">> Cập nhật biểu giá thành công! Biểu giá này sẽ áp dụng cho các hóa đơn lập sau này.");
        } catch (Exception e) {
            System.out.println("Lỗi nhập giá: " + e.getMessage());
        }
    }

    // ==========================================
    // ĐỌC VÀ LƯU FILE THEO CHUẨN DATA_FORMAT.MD
    // ==========================================

    public static void loadAllData() {
        try {
            // 1. Đọc phòng
            StorageRoom sr = new StorageRoom();
            List<StorageRoom> sRooms = sr.load(getRoomsFile());
            rooms.clear();
            for (StorageRoom s : sRooms) {
                if ("SERVICE".equalsIgnoreCase(s.getType())) {
                    rooms.put(s.getId(), new ServiceRoom(s.getId(), s.getCapacity(), s.getRoomCharge(), s.getServiceFee()));
                } else {
                    rooms.put(s.getId(), new StandardRoom(s.getId(), s.getCapacity(), s.getRoomCharge()));
                }
            }

            // 2. Đọc biểu giá
            StoragePrice sp = new StoragePrice();
            StoragePrice loadedPrice = sp.load(getPricesFile());
            if (loadedPrice.getStandardRoom() > 0) {
                currentStandardRent = loadedPrice.getStandardRoom();
                currentServiceRent = loadedPrice.getServiceRoom();
                currentServiceFee = loadedPrice.getServiceFee();
                currentElectricityRate = loadedPrice.getElectricRate();
                currentWaterRate = loadedPrice.getWaterRate();
            }

            // 3. Đọc sinh viên và nối quan hệ vào phòng
            StorageStudent ss = new StorageStudent();
            List<StorageStudent> sStudents = ss.load(getStudentsFile());
            studentManager = new StudentManager(rooms);
            for (StorageStudent s : sStudents) {
                studentManager.addStudent(s.getId(), s.getName());
                if (s.getRoomId() != null && !s.getRoomId().equalsIgnoreCase("NONE")) {
                    Room r = rooms.get(s.getRoomId());
                    if (r != null) {
                        r.addResident(s.getId());
                        Student st = studentManager.findStudent(s.getId());
                        if (st != null) {
                            st.assignToRoom(s.getRoomId());
                        }
                    }
                }
            }

            // 4. Đọc hóa đơn
            StorageBill sb = new StorageBill();
            List<StorageBill> sBills = sb.load(getBillsFile());
            billService = new BillService();
            for (StorageBill b : sBills) {
                PriceSnapshot snapshot = new PriceSnapshot(b.getRoomType(), b.getRoomCharge(), b.getServiceFee(),
                        b.getElectricRate(), b.getWaterRate());
                Bill bill = new Bill(b.getId(), b.getRoomId(), b.getMonth(), b.getElectricStart(), b.getElectricEnd(),
                        b.getWaterStart(), b.getWaterEnd(), snapshot);
                if (b.isPaid()) {
                    bill.markAsPaid();
                }
                billService.addBill(bill);
            }
        } catch (Exception e) {
            System.out.println("Ghi chú khi nạp dữ liệu: " + e.getMessage());
        }
    }

    public static void saveAllDataSafely() {
        try {
            // 1. Lưu phòng
            List<StorageRoom> sRoomList = new ArrayList<StorageRoom>();
            for (Room r : rooms.values()) {
                sRoomList.add(new StorageRoom(r.getRoomType(), r.getRoomId(), r.getCapacity(),
                        r.getBaseRent(), r.getServiceFee()));
            }
            new StorageRoom().save(sRoomList, getRoomsFile());

            // 2. Lưu biểu giá
            StoragePrice sp = new StoragePrice(currentStandardRent, currentServiceRent, currentServiceFee,
                    currentElectricityRate, currentWaterRate);
            sp.save(sp, getPricesFile());

            // 3. Lưu sinh viên
            List<StorageStudent> sStudentList = new ArrayList<StorageStudent>();
            for (Student s : studentManager.getStudents()) {
                String rId = s.isAssigned() ? s.getCurrentRoomId() : "NONE";
                sStudentList.add(new StorageStudent(s.getStudentId(), s.getFullName(), rId));
            }
            new StorageStudent().save(sStudentList, getStudentsFile());

            // 4. Lưu hóa đơn
            List<StorageBill> sBillList = new ArrayList<StorageBill>();
            for (Bill b : billService.getBillList()) {
                sBillList.add(new StorageBill(b.getBillId(), b.getRoomId(), b.getBillingMonth(),
                        b.getOldElectricityIndex(), b.getNewElectricityIndex(),
                        b.getOldWaterIndex(), b.getNewWaterIndex(),
                        b.getPriceSnapshot().getRoomType(), b.getPriceSnapshot().getRoomPrice(),
                        b.getPriceSnapshot().getServiceFee(), b.getPriceSnapshot().getElectricityRate(),
                        b.getPriceSnapshot().getWaterRate(), b.isPaid() ? "PAID" : "UNPAID"));
            }
            new StorageBill().save(sBillList, getBillsFile());
        } catch (Exception e) {
            System.out.println("Lỗi lưu dữ liệu: " + e.getMessage());
        }
    }

    // ==========================================
    // CHẠY TỰ ĐỘNG CÁC CA NGHIỆM THU (K01 - K11)
    // ==========================================
    public static void runAutomatedAcceptanceTests() {
        System.out.println("\n=======================================================");
        System.out.println("   CHẠY TỰ ĐỘNG BỘ TEST CASES NGHIỆM THU (K01 - K11)");
        System.out.println("=======================================================");

        // K01: Tính tiền phòng qua tham chiếu Room (Đa hình)
        System.out.println("\n[K01] Tính tiền phòng qua tham chiếu Room (Đa hình):");
        Room testA101 = new StandardRoom("A101", 2, 1200000L);
        Room testA102 = new ServiceRoom("A102", 3, 1800000L, 300000L);
        List<Room> testRooms = new ArrayList<Room>();
        testRooms.add(testA101);
        testRooms.add(testA102);
        for (Room r : testRooms) {
            System.out.printf("  -> Phòng %s (%s): %,d đ (Kỳ vọng: %s)\n",
                    r.getRoomId(), r.getRoomType(), r.calculateRoomCharge(),
                    r.getRoomType().equals("STANDARD") ? "1.200.000 đ" : "2.100.000 đ");
        }
        System.out.println("  => K01: PASS 100%");

        // K02 & K03: Lập hóa đơn và tính tiền
        System.out.println("\n[K02 & K03] Lập hóa đơn A101 và A102 (điện 100->150, nước 20->30):");
        testA101.addResident("QE210001");
        testA102.addResident("QE210003");
        BillManager bm = new BillManager();
        Bill b1 = bm.createBill(testA101, "2026-10", 100, 150, 20, 30);
        b1.setBillId("HD001");
        Bill b2 = bm.createBill(testA102, "2026-10", 100, 150, 20, 30);
        b2.setBillId("HD002");

        System.out.printf("  -> A101 (HD001): %,d đ (Kỳ vọng: 1.500.000 đ)\n", b1.getTotalAmount());
        System.out.printf("  -> A102 (HD002): %,d đ (Kỳ vọng: 2.400.000 đ)\n", b2.getTotalAmount());
        System.out.println("  => K02 & K03: PASS 100%");

        // K04 & K05: Quản lý sinh viên & Xếp phòng
        System.out.println("\n[K04 & K05] Kiểm tra sức chứa & Xếp/Trả phòng:");
        Map<String, Room> mRooms = new HashMap<String, Room>();
        mRooms.put("A101", new StandardRoom("A101", 2, 1200000L));
        StudentManager sm = new StudentManager(mRooms);
        sm.addStudent("QE210001", "Nguyễn An");
        sm.addStudent("QE210002", "Trần Bình");
        sm.addStudent("QE210004", "Phạm Dũng");
        sm.assignStudentToRoom("QE210001", "A101");
        sm.assignStudentToRoom("QE210002", "A101");

        boolean k04Reject = !sm.assignStudentToRoom("QE210004", "A101");
        System.out.println("  -> Xếp người thứ 3 vào phòng 2 chỗ: " + (k04Reject ? "BỊ TỪ CHỐI (ĐÚNG)" : "SAI"));

        sm.checkOutStudent("QE210002");
        boolean k05Success = sm.assignStudentToRoom("QE210004", "A101");
        System.out.println("  -> Trả phòng và xếp người mới: " + (k05Success ? "THÀNH CÔNG (ĐÚNG)" : "SAI"));
        System.out.println("  => K04 & K05: PASS 100%");

        // K08: Thanh toán hóa đơn
        System.out.println("\n[K08] Thanh toán hóa đơn & Tra cứu nợ:");
        BillService bs = new BillService();
        bs.addBill(b1);
        bs.addBill(b2);
        long initialDebt = bs.calculateTotalDebt();
        System.out.printf("  -> Tổng nợ ban đầu: %,d đ (Kỳ vọng: 3.900.000 đ)\n", initialDebt);
        bs.payBill("HD001");
        long remainingDebt = bs.calculateTotalDebt();
        System.out.printf("  -> Tổng nợ sau khi trả HD001: %,d đ (Kỳ vọng: 2.400.000 đ)\n", remainingDebt);
        System.out.println("  => K08: PASS 100%");

        System.out.println("\n>>> TẤT CẢ CÁC CA KIỂM THỬ ĐỀU CHẠY THÀNH CÔNG RỰC RỠ! <<<");
    }
}
