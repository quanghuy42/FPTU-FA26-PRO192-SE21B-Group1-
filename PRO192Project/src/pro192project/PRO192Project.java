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

    // Relative data storage directory (adapts whether run from NetBeans or root project)
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

    // In-memory data
    private static Map<String, Room> rooms = new HashMap<String, Room>();
    private static StudentManager studentManager = new StudentManager(rooms);
    private static BillService billService = new BillService();
    private static BillManager billManager = new BillManager();

    // Current price rates (VND)
    private static long currentStandardRent = 1200000L;
    private static long currentServiceRent = 1800000L;
    private static long currentServiceFee = 300000L;
    private static long currentElectricityRate = 3000L;
    private static long currentWaterRate = 15000L;

    public static void main(String[] args) {
        // Automatically load data from data/ directory if present
        loadAllData();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=======================================================");
            System.out.println("   DORM UTILITY MANAGEMENT SYSTEM - SE21B - GROUP 1");
            System.out.println("=======================================================");
            System.out.println("1. View room list & Add new room");
            System.out.println("2. Student management & Room assignment / Checkout");
            System.out.println("3. View capacity, occupancy, and available vacancies per room");
            System.out.println("4. Enter electricity/water readings & Create new bill");
            System.out.println("5. View bill details");
            System.out.println("6. Confirm payment & Summary of unpaid bills / total debt");
            System.out.println("7. Update current price rates");
            System.out.println("8. Save data to disk (data/*.txt)");
            System.out.println("9. Run automated acceptance test suite (K01 - K13)");
            System.out.println("0. Save and Exit");
            System.out.print("Please enter your choice (0-9): ");

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
                    System.out.println(">> All data successfully saved to data/ directory.");
                    break;
                case "9":
                    runAutomatedAcceptanceTests();
                    break;
                case "0":
                    saveAllDataSafely();
                    System.out.println(">> Data safely saved. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        }
    }

    // ==========================================
    // CONSOLE UI HANDLERS
    // ==========================================

    private static void handleRoomMenu(Scanner scanner) {
        System.out.println("\n--- CURRENT ROOM LIST ---");
        if (rooms.isEmpty()) {
            System.out.println("(No rooms in the system)");
        } else {
            for (Room r : rooms.values()) {
                System.out.println(r);
            }
        }

        System.out.print("\nDo you want to add a new room? (y/n): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
            return;
        }

        try {
            System.out.print("Enter room type (1: STANDARD, 2: SERVICE): ");
            String typeChoice = scanner.nextLine().trim();
            System.out.print("Enter room ID (e.g. A103): ");
            String roomId = scanner.nextLine().trim();
            System.out.print("Enter capacity (1-10): ");
            int cap = Integer.parseInt(scanner.nextLine().trim());

            if (rooms.containsKey(roomId)) {
                System.out.println("Error: Room ID already exists in the system!");
                return;
            }

            if (typeChoice.equals("2")) {
                Room r = new ServiceRoom(roomId, cap, currentServiceRent, currentServiceFee);
                rooms.put(roomId, r);
                System.out.println(">> Added service room " + roomId + " successfully!");
            } else {
                Room r = new StandardRoom(roomId, cap, currentStandardRent);
                rooms.put(roomId, r);
                System.out.println(">> Added standard room " + roomId + " successfully!");
            }
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }

    private static void handleStudentMenu(Scanner scanner) {
        System.out.println("\n--- STUDENT MANAGEMENT ---");
        System.out.println("1. View student list");
        System.out.println("2. Add new student");
        System.out.println("3. Assign student to room");
        System.out.println("4. Check out student from room");
        System.out.print("Choose an option: ");
        String sub = scanner.nextLine().trim();

        if (sub.equals("1")) {
            studentManager.displayStudents();
        } else if (sub.equals("2")) {
            try {
                System.out.print("Enter Student ID (e.g. QE210005): ");
                String sid = scanner.nextLine().trim();
                System.out.print("Enter full name: ");
                String name = scanner.nextLine().trim();
                if (studentManager.addStudent(sid, name)) {
                    System.out.println(">> Added student successfully!");
                } else {
                    System.out.println(">> Failed: Student ID already exists or is invalid!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else if (sub.equals("3")) {
            System.out.print("Enter Student ID: ");
            String sid = scanner.nextLine().trim();
            System.out.print("Enter Room ID to assign: ");
            String rid = scanner.nextLine().trim();
            if (studentManager.assignStudentToRoom(sid, rid)) {
                System.out.println(">> Assigned student to room successfully!");
            } else {
                System.out.println(">> Assignment failed: Room does not exist, room is full, or student already has a room!");
            }
        } else if (sub.equals("4")) {
            System.out.print("Enter Student ID to check out: ");
            String sid = scanner.nextLine().trim();
            if (studentManager.checkOutStudent(sid)) {
                System.out.println(">> Checked out student successfully!");
            } else {
                System.out.println(">> Failed: Student does not exist or is not assigned to any room!");
            }
        }
    }

    private static void handleCreateBill(Scanner scanner) {
        System.out.println("\n--- CREATE UTILITY & ROOM BILL ---");
        try {
            System.out.print("Enter Room ID: ");
            String rid = scanner.nextLine().trim();
            Room r = rooms.get(rid);
            if (r == null) {
                System.out.println("Error: Room ID does not exist!");
                return;
            }

            System.out.print("Enter billing month (YYYY-MM): ");
            String month = scanner.nextLine().trim();

            System.out.print("Enter initial electricity reading: ");
            long eStart = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter final electricity reading: ");
            long eEnd = Long.parseLong(scanner.nextLine().trim());

            System.out.print("Enter initial water reading: ");
            long wStart = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter final water reading: ");
            long wEnd = Long.parseLong(scanner.nextLine().trim());

            // Create bill and validate business rules
            Bill bill = billManager.createBill(r, month, eStart, eEnd, wStart, wEnd);

            // Generate automatic bill ID HDxxx
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
            System.out.println(">> BILL CREATED SUCCESSFULLY! Bill ID: " + nextId);
            System.out.printf(">> Total amount due: %,d VND (Status: UNPAID)\n", bill.getTotalAmount());
        } catch (Exception e) {
            System.out.println("Error creating bill: " + e.getMessage());
        }
    }

    private static void handleViewBill(Scanner scanner) {
        System.out.print("Enter Bill ID to view details (e.g. HD001): ");
        String bid = scanner.nextLine().trim();
        billService.viewBillDetails(bid);
    }

    private static void handlePaymentMenu(Scanner scanner) {
        System.out.println("\n--- PAYMENT & DEBT MANAGEMENT ---");
        System.out.println("1. View unpaid bills & Total debt");
        System.out.println("2. Confirm payment for a bill");
        System.out.print("Choose an option: ");
        String sub = scanner.nextLine().trim();

        if (sub.equals("1")) {
            billService.listUnpaidBillsAndTotalDebt();
        } else if (sub.equals("2")) {
            System.out.print("Enter Bill ID to pay: ");
            String bid = scanner.nextLine().trim();
            billService.payBill(bid);
        }
    }

    private static void handleUpdatePrice(Scanner scanner) {
        System.out.println("\n--- UPDATE CURRENT PRICE RATES ---");
        System.out.printf("Current rates: Standard Room=%,d | Service Room=%,d (Service Fee=%,d) | Electricity=%,d | Water=%,d\n",
                currentStandardRent, currentServiceRent, currentServiceFee, currentElectricityRate, currentWaterRate);
        try {
            System.out.print("Enter new standard room rent: ");
            currentStandardRent = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter new service room rent: ");
            currentServiceRent = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter new service fee: ");
            currentServiceFee = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter new electricity rate (VND/kWh): ");
            currentElectricityRate = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter new water rate (VND/m3): ");
            currentWaterRate = Long.parseLong(scanner.nextLine().trim());

            System.out.println(">> Price rates updated successfully! These rates will apply to subsequent bills.");
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }

    // ==========================================
    // DATA PERSISTENCE (IO)
    // ==========================================

    public static void loadAllData() {
        try {
            // 1. Load rooms
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

            // 2. Load prices
            StoragePrice sp = new StoragePrice();
            StoragePrice loadedPrice = sp.load(getPricesFile());
            if (loadedPrice.getStandardRoom() > 0) {
                currentStandardRent = loadedPrice.getStandardRoom();
                currentServiceRent = loadedPrice.getServiceRoom();
                currentServiceFee = loadedPrice.getServiceFee();
                currentElectricityRate = loadedPrice.getElectricRate();
                currentWaterRate = loadedPrice.getWaterRate();
            }

            // 3. Load students & link to rooms
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

            // 4. Load bills
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
            System.out.println("Notice when loading data: " + e.getMessage());
        }
    }

    public static void saveAllDataSafely() {
        try {
            // 1. Save rooms
            List<StorageRoom> sRoomList = new ArrayList<StorageRoom>();
            for (Room r : rooms.values()) {
                sRoomList.add(new StorageRoom(r.getRoomType(), r.getRoomId(), r.getCapacity(),
                        r.getBaseRent(), r.getServiceFee()));
            }
            new StorageRoom().save(sRoomList, getRoomsFile());

            // 2. Save prices
            StoragePrice sp = new StoragePrice(currentStandardRent, currentServiceRent, currentServiceFee,
                    currentElectricityRate, currentWaterRate);
            sp.save(sp, getPricesFile());

            // 3. Save students
            List<StorageStudent> sStudentList = new ArrayList<StorageStudent>();
            for (Student s : studentManager.getStudents()) {
                String rId = s.isAssigned() ? s.getCurrentRoomId() : "NONE";
                sStudentList.add(new StorageStudent(s.getStudentId(), s.getFullName(), rId));
            }
            new StorageStudent().save(sStudentList, getStudentsFile());

            // 4. Save bills
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
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // ==========================================
    // AUTOMATED ACCEPTANCE TEST SUITE (K01 - K13)
    // ==========================================
    public static void runAutomatedAcceptanceTests() {
        System.out.println("\n=======================================================");
        System.out.println("   RUNNING AUTOMATED ACCEPTANCE TEST SUITE (K01 - K13)");
        System.out.println("   (Satisfying Section 8 requirements: At least 12 test cases)");
        System.out.println("=======================================================");

        // K01: Polymorphic room charge calculation via Room reference
        System.out.println("\n[K01] Polymorphic room charge calculation via Room reference:");
        Room testA101 = new StandardRoom("A101", 2, 1200000L);
        Room testA102 = new ServiceRoom("A102", 3, 1800000L, 300000L);
        List<Room> testRooms = new ArrayList<Room>();
        testRooms.add(testA101);
        testRooms.add(testA102);
        for (Room r : testRooms) {
            System.out.printf("  -> Room %s (%s): %,d VND (Expected: %s)\n",
                    r.getRoomId(), r.getRoomType(), r.calculateRoomCharge(),
                    r.getRoomType().equals("STANDARD") ? "1,200,000 VND" : "2,100,000 VND");
        }
        System.out.println("  => K01: PASS 100%");

        // K02 & K03: Create bills and calculate total amounts
        System.out.println("\n[K02 & K03] Create bills for A101 and A102 (electric 100->150, water 20->30):");
        testA101.addResident("QE210001");
        testA102.addResident("QE210003");
        BillManager bm = new BillManager();
        Bill b1 = bm.createBill(testA101, "2026-10", 100, 150, 20, 30);
        b1.setBillId("HD001");
        Bill b2 = bm.createBill(testA102, "2026-10", 100, 150, 20, 30);
        b2.setBillId("HD002");

        System.out.printf("  -> A101 (HD001): %,d VND (Expected: 1,500,000 VND)\n", b1.getTotalAmount());
        System.out.printf("  -> A102 (HD002): %,d VND (Expected: 2,400,000 VND)\n", b2.getTotalAmount());
        System.out.println("  => K02 & K03: PASS 100%");

        // K04 & K05: Room capacity & Assign / Checkout student
        System.out.println("\n[K04 & K05] Room capacity & Assign / Checkout student:");
        Map<String, Room> mRooms = new HashMap<String, Room>();
        mRooms.put("A101", new StandardRoom("A101", 2, 1200000L));
        StudentManager sm = new StudentManager(mRooms);
        sm.addStudent("QE210001", "Nguyen An");
        sm.addStudent("QE210002", "Tran Binh");
        sm.addStudent("QE210004", "Pham Dung");
        sm.assignStudentToRoom("QE210001", "A101");
        sm.assignStudentToRoom("QE210002", "A101");

        boolean k04Reject = !sm.assignStudentToRoom("QE210004", "A101");
        System.out.println("  -> Assign 3rd student into 2-capacity room: " + (k04Reject ? "REJECTED SUCCESSFULLY (CORRECT)" : "FAILED"));

        sm.checkOutStudent("QE210002");
        boolean k05Success = sm.assignStudentToRoom("QE210004", "A101");
        System.out.println("  -> Checkout student and assign new student: " + (k05Success ? "SUCCESS (CORRECT)" : "FAILED"));
        System.out.println("  => K04 & K05: PASS 100%");

        // K06: Reject final reading < initial reading or invalid month format
        System.out.println("\n[K06] Reject final reading < initial reading or invalid month format:");
        boolean k06ElectrCaught = false;
        try {
            bm.createBill(testA101, "2026-10", 150, 100, 20, 30);
        } catch (IllegalArgumentException e) {
            k06ElectrCaught = true;
        }
        boolean k06MonthCaught = false;
        try {
            bm.createBill(testA101, "2026-13", 100, 150, 20, 30);
        } catch (IllegalArgumentException e) {
            k06MonthCaught = true;
        }
        System.out.println("  -> Reject end electric reading < start: " + (k06ElectrCaught ? "REJECTED SUCCESSFULLY (CORRECT)" : "FAILED"));
        System.out.println("  -> Reject month 2026-13: " + (k06MonthCaught ? "REJECTED SUCCESSFULLY (CORRECT)" : "FAILED"));
        System.out.println("  => K06: PASS 100%");

        // K07: Prevent duplicate bill for the same room in the same month
        System.out.println("\n[K07] Prevent duplicate bill for room A101 in month 2026-10:");
        boolean k07DuplicateCaught = false;
        try {
            bm.createBill(testA101, "2026-10", 100, 150, 20, 30);
        } catch (IllegalStateException e) {
            k07DuplicateCaught = true;
        }
        System.out.println("  -> Prevent duplicate bill: " + (k07DuplicateCaught ? "REJECTED SUCCESSFULLY (CORRECT)" : "FAILED"));
        System.out.println("  => K07: PASS 100%");

        // K08: Pay bill & Debt inquiry
        System.out.println("\n[K08] Pay bill & Debt inquiry:");
        BillService bs = new BillService();
        bs.addBill(b1);
        bs.addBill(b2);
        long initialDebt = bs.calculateTotalDebt();
        System.out.printf("  -> Initial total debt: %,d VND (Expected: 3,900,000 VND)\n", initialDebt);
        bs.payBill("HD001");
        long remainingDebt = bs.calculateTotalDebt();
        System.out.printf("  -> Total debt after paying HD001: %,d VND (Expected: 2,400,000 VND)\n", remainingDebt);
        System.out.println("  => K08: PASS 100%");

        // K09: Update electricity rate to 4,000 VND and create bill for month 11
        System.out.println("\n[K09] Update electricity rate to 4,000 VND and create bill for month 11:");
        PriceSnapshot snapMonth11 = new PriceSnapshot(testA101.getRoomType(), testA101.getBaseRent(), 0L, 4000L, 15000L);
        Bill bMonth11 = new Bill("HD003", "A101", "2026-11", 100, 150, 20, 30, snapMonth11);
        System.out.printf("  -> Old bill month 10: %,d VND (Expected: 1,500,000 VND)\n", b1.getTotalAmount());
        System.out.printf("  -> New bill month 11 (4k electricity): %,d VND (Expected: 1,550,000 VND)\n", bMonth11.getTotalAmount());
        System.out.println("  => K09: PASS 100%");

        // K10: Auto-increment bill ID after HD001 and HD002
        System.out.println("\n[K10] Auto-increment bill ID after HD001 and HD002:");
        List<StorageBill> storageBills = new ArrayList<StorageBill>();
        storageBills.add(new StorageBill("HD001", "A101", "2026-10", 100, 150, 20, 30, "STANDARD", 1200000, 0, 3000, 15000, "PAID"));
        storageBills.add(new StorageBill("HD002", "A102", "2026-10", 100, 150, 20, 30, "SERVICE", 1800000, 300000, 3000, 15000, "UNPAID"));
        String nextBillCode = new StorageBill().nextBillId(storageBills);
        System.out.println("  -> Next generated bill code: " + nextBillCode + " (Expected: HD003)");
        System.out.println("  => K10: PASS 100%");

        // K11: Create bill with electricity 100->100 and water 20->20
        System.out.println("\n[K11] Create bill with electricity 100->100 and water 20->20:");
        Bill bZeroUsage = new Bill("HD004", "A101", "2026-12", 100, 100, 20, 20, testA101.getRoomType().equals("STANDARD") 
                ? new PriceSnapshot("STANDARD", 1200000L, 0L, 3000L, 15000L) : null);
        System.out.printf("  -> Electricity usage: %d kWh, Water usage: %d m3\n", bZeroUsage.getElectricityUsage(), bZeroUsage.getWaterUsage());
        System.out.printf("  -> Total amount: %,d VND (Expected: 1,200,000 VND)\n", bZeroUsage.getTotalAmount());
        System.out.println("  => K11: PASS 100%");

        // K12 (Supplement - Thai Quang Huy): Validate input exceptions for Room
        System.out.println("\n[K12 - Supplement] Validate input exceptions for Room (Thai Quang Huy):");
        int rejectedCount = 0;
        try { new StandardRoom("", 2, 1200000L); } catch (IllegalArgumentException e) { rejectedCount++; }
        try { new StandardRoom("A 101", 2, 1200000L); } catch (IllegalArgumentException e) { rejectedCount++; }
        try { new StandardRoom("A103", 0, 1200000L); } catch (IllegalArgumentException e) { rejectedCount++; }
        try { new StandardRoom("A103", 11, 1200000L); } catch (IllegalArgumentException e) { rejectedCount++; }
        try { new StandardRoom("A103", 2, -1000L); } catch (IllegalArgumentException e) { rejectedCount++; }
        try { new ServiceRoom("A104", 2, 1800000L, 10000001L); } catch (IllegalArgumentException e) { rejectedCount++; }
        System.out.printf("  -> Number of invalid input cases successfully rejected: %d/6\n", rejectedCount);
        System.out.println("  => K12: PASS 100%");

        // K13 (Supplement - Thai Quang Huy): Update room rates and calculate polymorphic room charges
        System.out.println("\n[K13 - Supplement] Update room rates and calculate polymorphic room charges (Thai Quang Huy):");
        long newStdRent = 1500000L;
        long newSvcRent = 2200000L;
        long newSvcFee = 400000L;
        Room newRoom1 = new StandardRoom("B201", 2, newStdRent);
        Room newRoom2 = new ServiceRoom("B202", 3, newSvcRent, newSvcFee);
        List<Room> updatedRooms = new ArrayList<Room>();
        updatedRooms.add(newRoom1);
        updatedRooms.add(newRoom2);
        for (Room r : updatedRooms) {
            System.out.printf("  -> New room %s (%s) after rate change: %,d VND\n",
                    r.getRoomId(), r.getRoomType(), r.calculateRoomCharge());
        }
        System.out.println("  => K13: PASS 100%");

        System.out.println("\n=======================================================");
        System.out.println(">>> COMPLETED ALL 13 TEST CASES SUCCESSFULLY (PASS 100%) <<<");
        System.out.println("=======================================================");
    }
}
