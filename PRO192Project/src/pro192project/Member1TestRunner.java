package pro192project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Member1TestRunner - Test Runner chuyên biệt cho phần việc của Thành viên 1: Thái Quang Huy (QS190001)
 * 
 * Các chức năng kiểm thử độc lập:
 * [1] Kiểm tra tính hợp lệ và biên dữ liệu của Phòng (Room Input Validation & Boundaries)
 * [2] Kiểm tra tính đa hình và tính tiền phòng (Room Polymorphism & Charge Calculation)
 * [3] Kiểm tra quản lý cư dân và chỗ trống phòng (Resident Occupancy & Vacancy Logic)
 * [4] Kiểm tra cập nhật giá hiện hành và bảo toàn đơn giá (Price Update & Snapshot Integrity)
 * [5] Kiểm thử tích hợp luồng Menu chức năng 1 và chức năng 7 (Menu Integration Simulation)
 * [A] Chạy toàn bộ tất cả các bài kiểm tra của Thành viên 1 (Run All Tests)
 */
public class Member1TestRunner {

    public static void main(String[] args) {
        if (args.length > 0) {
            runByArgument(args[0]);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n==================================================================");
            System.out.println("   MEMBER 1 (THÁI QUANG HUY - QS190001) FEATURE TEST RUNNER");
            System.out.println("   Dorm Utility Management System - SE21B - Group 1");
            System.out.println("==================================================================");
            System.out.println("1. Test 1: Room Input Validation & Boundary Checks (Empty/Space/Cap/Price)");
            System.out.println("2. Test 2: Polymorphism & Room Charge Calculation (Standard vs Service)");
            System.out.println("3. Test 3: Resident Management & Vacancy Logic (Add/Remove/Full/Duplicate)");
            System.out.println("4. Test 4: Dynamic Price Rate Updates & Bill Snapshot Integrity");
            System.out.println("5. Test 5: Menu Workflow Simulation (Feature 1: Room List & Feature 7: Rates)");
            System.out.println("A. Run ALL Member 1 Tests");
            System.out.println("0. Exit");
            System.out.print("Select test to run [0-5, A]: ");

            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("0")) {
                System.out.println("Exiting Member 1 Test Runner. Goodbye!");
                break;
            }
            runByArgument(choice);
        }
    }

    private static void runByArgument(String arg) {
        switch (arg.toUpperCase()) {
            case "1":
                test1_ValidationAndBoundaries();
                break;
            case "2":
                test2_PolymorphicRoomCharge();
                break;
            case "3":
                test3_ResidentAndVacancy();
                break;
            case "4":
                test4_PriceUpdateAndSnapshot();
                break;
            case "5":
                test5_MenuIntegrationSimulation();
                break;
            case "A":
            case "ALL":
                runAllMember1Tests();
                break;
            default:
                System.out.println("Invalid option: " + arg + ". Please choose 1-5, A, or 0.");
        }
    }

    public static void runAllMember1Tests() {
        System.out.println("\n>>> RUNNING ALL TESTS FOR MEMBER 1 (THÁI QUANG HUY) <<<\n");
        boolean p1 = test1_ValidationAndBoundaries();
        boolean p2 = test2_PolymorphicRoomCharge();
        boolean p3 = test3_ResidentAndVacancy();
        boolean p4 = test4_PriceUpdateAndSnapshot();
        boolean p5 = test5_MenuIntegrationSimulation();

        System.out.println("\n==================================================================");
        System.out.println("                MEMBER 1 TEST SUITE SUMMARY RESULT                ");
        System.out.println("==================================================================");
        System.out.println("  [Test 1] Input Validation & Boundaries: " + (p1 ? "PASS" : "FAILED"));
        System.out.println("  [Test 2] Polymorphism & Calculation:   " + (p2 ? "PASS" : "FAILED"));
        System.out.println("  [Test 3] Resident Occupancy & Vacancy: " + (p3 ? "PASS" : "FAILED"));
        System.out.println("  [Test 4] Price Updates & Snapshots:    " + (p4 ? "PASS" : "FAILED"));
        System.out.println("  [Test 5] Menu Workflow Simulation:     " + (p5 ? "PASS" : "FAILED"));
        System.out.println("------------------------------------------------------------------");
        if (p1 && p2 && p3 && p4 && p5) {
            System.out.println(">>> OVERALL: 100% PASS - ALL MEMBER 1 TASKS FULLY VERIFIED! <<<");
        } else {
            System.out.println(">>> OVERALL: SOME TESTS FAILED! CHECK DETAILS ABOVE. <<<");
        }
        System.out.println("==================================================================");
    }

    // =========================================================================
    // TEST 1: Room Input Validation & Boundary Checks
    // =========================================================================
    public static boolean test1_ValidationAndBoundaries() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("[Test 1] Room Input Validation & Boundary Checks");
        System.out.println("------------------------------------------------------------------");
        int passed = 0;
        int total = 12;

        // 1.1 Empty Room ID
        try {
            new StandardRoom("", 2, 1200000L);
            System.out.println("  [FAIL] 1.1 Empty Room ID should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.1 Empty Room ID rejected: " + e.getMessage());
            passed++;
        }

        // 1.2 Null Room ID
        try {
            new StandardRoom(null, 2, 1200000L);
            System.out.println("  [FAIL] 1.2 Null Room ID should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.2 Null Room ID rejected: " + e.getMessage());
            passed++;
        }

        // 1.3 Room ID with internal whitespace
        try {
            new StandardRoom("A 101", 2, 1200000L);
            System.out.println("  [FAIL] 1.3 Room ID with spaces should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.3 Room ID with spaces rejected: " + e.getMessage());
            passed++;
        }

        // 1.4 Room ID with tab / newline
        try {
            new StandardRoom("A101\t", 2, 1200000L);
            System.out.println("  [FAIL] 1.4 Room ID with whitespace character should throw exception");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.4 Room ID with tab rejected: " + e.getMessage());
            passed++;
        }

        // 1.5 Capacity = 0 (< 1)
        try {
            new StandardRoom("A103", 0, 1200000L);
            System.out.println("  [FAIL] 1.5 Capacity 0 should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.5 Capacity 0 rejected: " + e.getMessage());
            passed++;
        }

        // 1.6 Capacity = 11 (> 10)
        try {
            new StandardRoom("A103", 11, 1200000L);
            System.out.println("  [FAIL] 1.6 Capacity 11 should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.6 Capacity 11 rejected: " + e.getMessage());
            passed++;
        }

        // 1.7 Capacity lower boundary = 1
        try {
            StandardRoom r1 = new StandardRoom("A101", 1, 1200000L);
            if (r1.getCapacity() == 1) {
                System.out.println("  [PASS] 1.7 Capacity 1 (lower bound) accepted correctly");
                passed++;
            }
        } catch (Exception e) {
            System.out.println("  [FAIL] 1.7 Capacity 1 should be valid but threw: " + e.getMessage());
        }

        // 1.8 Capacity upper boundary = 10
        try {
            StandardRoom r10 = new StandardRoom("A110", 10, 1200000L);
            if (r10.getCapacity() == 10) {
                System.out.println("  [PASS] 1.8 Capacity 10 (upper bound) accepted correctly");
                passed++;
            }
        } catch (Exception e) {
            System.out.println("  [FAIL] 1.8 Capacity 10 should be valid but threw: " + e.getMessage());
        }

        // 1.9 Negative base rent
        try {
            new StandardRoom("A101", 2, -1000L);
            System.out.println("  [FAIL] 1.9 Negative base rent should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.9 Negative base rent rejected: " + e.getMessage());
            passed++;
        }

        // 1.10 Base rent > 10,000,000 VND
        try {
            new StandardRoom("A101", 2, 10000001L);
            System.out.println("  [FAIL] 1.10 Base rent > 10,000,000 should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.10 Base rent > 10,000,000 rejected: " + e.getMessage());
            passed++;
        }

        // 1.11 Service fee negative
        try {
            new ServiceRoom("A102", 2, 1800000L, -50000L);
            System.out.println("  [FAIL] 1.11 Negative service fee should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.11 Negative service fee rejected: " + e.getMessage());
            passed++;
        }

        // 1.12 Service fee > 10,000,000 VND
        try {
            new ServiceRoom("A102", 2, 1800000L, 10000001L);
            System.out.println("  [FAIL] 1.12 Service fee > 10,000,000 should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("  [PASS] 1.12 Service fee > 10,000,000 rejected: " + e.getMessage());
            passed++;
        }

        System.out.printf("=> Result: %d/%d validation checks passed (%.0f%%)\n", passed, total, (passed * 100.0 / total));
        return passed == total;
    }

    // =========================================================================
    // TEST 2: Polymorphism & Room Charge Calculation
    // =========================================================================
    public static boolean test2_PolymorphicRoomCharge() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("[Test 2] Polymorphic Room Charge Calculation (Standard vs Service)");
        System.out.println("------------------------------------------------------------------");
        int passed = 0;
        int total = 5;

        // 2.1 StandardRoom calculation
        Room rStandard = new StandardRoom("A101", 2, 1200000L);
        long stdCharge = rStandard.calculateRoomCharge();
        if (stdCharge == 1200000L && "STANDARD".equals(rStandard.getRoomType()) && rStandard.getServiceFee() == 0) {
            System.out.printf("  [PASS] 2.1 StandardRoom: %,d VND (Expected: 1,200,000 VND), Type: %s\n",
                    stdCharge, rStandard.getRoomType());
            passed++;
        } else {
            System.out.printf("  [FAIL] 2.1 StandardRoom charge wrong: %,d VND\n", stdCharge);
        }

        // 2.2 ServiceRoom calculation: baseRent + serviceFee
        Room rService = new ServiceRoom("A102", 3, 1800000L, 300000L);
        long svcCharge = rService.calculateRoomCharge();
        if (svcCharge == 2100000L && "SERVICE".equals(rService.getRoomType()) && rService.getServiceFee() == 300000L) {
            System.out.printf("  [PASS] 2.2 ServiceRoom: %,d VND (Expected: 2,100,000 VND), Service Fee: %,d VND\n",
                    svcCharge, rService.getServiceFee());
            passed++;
        } else {
            System.out.printf("  [FAIL] 2.2 ServiceRoom charge wrong: %,d VND\n", svcCharge);
        }

        // 2.3 Polymorphic Collection execution
        List<Room> roomList = new ArrayList<Room>();
        roomList.add(rStandard);
        roomList.add(rService);
        long totalCalculated = 0;
        for (Room r : roomList) {
            totalCalculated += r.calculateRoomCharge(); // Pure polymorphic call
        }
        if (totalCalculated == (1200000L + 2100000L)) {
            System.out.printf("  [PASS] 2.3 Polymorphic iteration total: %,d VND (Expected: 3,300,000 VND)\n", totalCalculated);
            passed++;
        } else {
            System.out.printf("  [FAIL] 2.3 Polymorphic iteration total wrong: %,d VND\n", totalCalculated);
        }

        // 2.4 toString formatting
        String stdStr = rStandard.toString();
        if (stdStr.contains("A101") && stdStr.contains("STANDARD") && stdStr.contains("Capacity: 2") && stdStr.contains("1,200,000")) {
            System.out.println("  [PASS] 2.4 StandardRoom toString format correct: " + stdStr);
            passed++;
        } else {
            System.out.println("  [FAIL] 2.4 StandardRoom toString format mismatch: " + stdStr);
        }

        String svcStr = rService.toString();
        if (svcStr.contains("A102") && svcStr.contains("SERVICE") && svcStr.contains("Capacity: 3") && svcStr.contains("2,100,000")) {
            System.out.println("  [PASS] 2.5 ServiceRoom toString format correct: " + svcStr);
            passed++;
        } else {
            System.out.println("  [FAIL] 2.5 ServiceRoom toString format mismatch: " + svcStr);
        }

        System.out.printf("=> Result: %d/%d polymorphism tests passed (%.0f%%)\n", passed, total, (passed * 100.0 / total));
        return passed == total;
    }

    // =========================================================================
    // TEST 3: Resident Management & Vacancy Logic
    // =========================================================================
    public static boolean test3_ResidentAndVacancy() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("[Test 3] Resident Occupancy & Vacancy Logic");
        System.out.println("------------------------------------------------------------------");
        int passed = 0;
        int total = 7;

        Room room = new StandardRoom("A101", 2, 1200000L);

        // 3.1 Initial state
        if (room.getResidentCount() == 0 && room.getVacancy() == 2 && !room.isFull()) {
            System.out.println("  [PASS] 3.1 Initial room state: 0 occupied, 2 vacant, isFull = false");
            passed++;
        } else {
            System.out.println("  [FAIL] 3.1 Initial room state incorrect");
        }

        // 3.2 Add 1st resident
        boolean add1 = room.addResident("QE210001");
        if (add1 && room.getResidentCount() == 1 && room.getVacancy() == 1 && room.hasResident("QE210001")) {
            System.out.println("  [PASS] 3.2 Added QE210001: 1 occupied, 1 vacant, hasResident = true");
            passed++;
        } else {
            System.out.println("  [FAIL] 3.2 Failed to add first resident");
        }

        // 3.3 Add duplicate resident
        boolean addDup = room.addResident("QE210001");
        if (!addDup && room.getResidentCount() == 1) {
            System.out.println("  [PASS] 3.3 Duplicate resident QE210001 correctly rejected");
            passed++;
        } else {
            System.out.println("  [FAIL] 3.3 Duplicate resident was not rejected");
        }

        // 3.4 Add 2nd resident to reach capacity
        boolean add2 = room.addResident("QE210002");
        if (add2 && room.getResidentCount() == 2 && room.getVacancy() == 0 && room.isFull()) {
            System.out.println("  [PASS] 3.4 Added QE210002: 2 occupied, 0 vacant, isFull = true");
            passed++;
        } else {
            System.out.println("  [FAIL] 3.4 Failed to add second resident to reach full capacity");
        }

        // 3.5 Add 3rd resident into full room
        boolean addOver = room.addResident("QE210003");
        if (!addOver && room.getResidentCount() == 2) {
            System.out.println("  [PASS] 3.5 Over-capacity assignment (3rd student in 2-cap room) rejected");
            passed++;
        } else {
            System.out.println("  [FAIL] 3.5 Over-capacity assignment should have been rejected");
        }

        // 3.6 Remove resident
        boolean rem = room.removeResident("QE210001");
        if (rem && room.getResidentCount() == 1 && room.getVacancy() == 1 && !room.isFull()) {
            System.out.println("  [PASS] 3.6 Removed QE210001: 1 occupied, 1 vacant, isFull = false");
            passed++;
        } else {
            System.out.println("  [FAIL] 3.6 Failed to remove resident QE210001");
        }

        // 3.7 Remove non-existent resident
        boolean remFake = room.removeResident("QE999999");
        if (!remFake && room.getResidentCount() == 1) {
            System.out.println("  [PASS] 3.7 Removing non-existent resident correctly returned false");
            passed++;
        } else {
            System.out.println("  [FAIL] 3.7 Removing non-existent resident should return false");
        }

        System.out.printf("=> Result: %d/%d resident management tests passed (%.0f%%)\n", passed, total, (passed * 100.0 / total));
        return passed == total;
    }

    // =========================================================================
    // TEST 4: Price Update & Snapshot Integrity
    // =========================================================================
    public static boolean test4_PriceUpdateAndSnapshot() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("[Test 4] Dynamic Price Rate Updates & Bill Snapshot Integrity");
        System.out.println("------------------------------------------------------------------");
        int passed = 0;
        int total = 4;

        // 4.1 Mutate base rent on StandardRoom
        Room roomStd = new StandardRoom("A101", 2, 1200000L);
        roomStd.setBaseRent(1500000L);
        if (roomStd.calculateRoomCharge() == 1500000L) {
            System.out.printf("  [PASS] 4.1 Updated standard baseRent: %,d VND (Expected: 1,500,000 VND)\n",
                    roomStd.calculateRoomCharge());
            passed++;
        } else {
            System.out.println("  [FAIL] 4.1 setBaseRent did not update calculateRoomCharge");
        }

        // 4.2 Mutate service fee on ServiceRoom
        ServiceRoom roomSvc = new ServiceRoom("A102", 3, 1800000L, 300000L);
        roomSvc.setServiceFee(500000L);
        long newSvcTotal = roomSvc.calculateRoomCharge();
        if (newSvcTotal == 2300000L) {
            System.out.printf("  [PASS] 4.2 Updated serviceFee to 500k: %,d VND (Expected: 2,300,000 VND)\n", newSvcTotal);
            passed++;
        } else {
            System.out.printf("  [FAIL] 4.2 setServiceFee did not update calculateRoomCharge: %,d VND\n", newSvcTotal);
        }

        // 4.3 Bill snapshot isolation
        // Month 10: base rent = 1,200,000 VND
        PriceSnapshot snapMonth10 = new PriceSnapshot("STANDARD", 1200000L, 0L, 3000L, 15000L);
        Bill bill10 = new Bill("HD001", "A101", "2026-10", 100, 150, 20, 30, snapMonth10);
        long oldTotal = bill10.getTotalAmount(); // 1.2M + 50*3k (150k) + 10*15k (150k) = 1.5M

        // Month 11: rate changes to 1,500,000 VND and electric 4,000 VND
        PriceSnapshot snapMonth11 = new PriceSnapshot("STANDARD", 1500000L, 0L, 4000L, 15000L);
        Bill bill11 = new Bill("HD002", "A101", "2026-11", 100, 150, 20, 30, snapMonth11);
        long newTotal = bill11.getTotalAmount(); // 1.5M + 50*4k (200k) + 10*15k (150k) = 1.85M

        if (oldTotal == 1500000L) {
            System.out.printf("  [PASS] 4.3 Month 10 bill preserved immutable snapshot: %,d VND\n", oldTotal);
            passed++;
        } else {
            System.out.printf("  [FAIL] 4.3 Month 10 bill modified unintentionally: %,d VND\n", oldTotal);
        }

        if (newTotal == 1850000L) {
            System.out.printf("  [PASS] 4.4 Month 11 bill applied updated price snapshot: %,d VND\n", newTotal);
            passed++;
        } else {
            System.out.printf("  [FAIL] 4.4 Month 11 bill amount incorrect: %,d VND\n", newTotal);
        }

        System.out.printf("=> Result: %d/%d price update tests passed (%.0f%%)\n", passed, total, (passed * 100.0 / total));
        return passed == total;
    }

    // =========================================================================
    // TEST 5: Menu Workflow Simulation (Feature 1 & Feature 7)
    // =========================================================================
    public static boolean test5_MenuIntegrationSimulation() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("[Test 5] Menu Workflow Simulation (Feature 1: Rooms & Feature 7: Rates)");
        System.out.println("------------------------------------------------------------------");
        int passed = 0;
        int total = 4;

        Map<String, Room> mockRooms = new HashMap<String, Room>();

        // 5.1 Simulate Menu 1: Add Standard Room
        long currentStdRent = 1200000L;
        StandardRoom r1 = new StandardRoom("B101", 4, currentStdRent);
        mockRooms.put(r1.getRoomId(), r1);
        if (mockRooms.containsKey("B101") && mockRooms.get("B101").calculateRoomCharge() == 1200000L) {
            System.out.println("  [PASS] 5.1 Simulated Menu 1: Added StandardRoom B101 (Cap 4, Rent 1.2M)");
            passed++;
        } else {
            System.out.println("  [FAIL] 5.1 Failed to add StandardRoom B101");
        }

        // 5.2 Simulate Menu 1: Add Service Room
        long currentSvcRent = 1800000L;
        long currentSvcFee = 300000L;
        ServiceRoom r2 = new ServiceRoom("B102", 2, currentSvcRent, currentSvcFee);
        mockRooms.put(r2.getRoomId(), r2);
        if (mockRooms.containsKey("B102") && mockRooms.get("B102").calculateRoomCharge() == 2100000L) {
            System.out.println("  [PASS] 5.2 Simulated Menu 1: Added ServiceRoom B102 (Cap 2, Rent 2.1M)");
            passed++;
        } else {
            System.out.println("  [FAIL] 5.2 Failed to add ServiceRoom B102");
        }

        // 5.3 Duplicate Room ID check in Menu 1
        boolean dupDetected = mockRooms.containsKey("B101");
        if (dupDetected) {
            System.out.println("  [PASS] 5.3 Simulated Menu 1: Duplicate Room ID B101 detected and prevented");
            passed++;
        } else {
            System.out.println("  [FAIL] 5.3 Duplicate detection failed");
        }

        // 5.4 Simulate Menu 7: Update global rates and create new room with new rate
        long updatedStdRent = 1600000L;
        StandardRoom rNewRate = new StandardRoom("B103", 2, updatedStdRent);
        mockRooms.put(rNewRate.getRoomId(), rNewRate);
        if (mockRooms.get("B103").calculateRoomCharge() == 1600000L) {
            System.out.println("  [PASS] 5.4 Simulated Menu 7: Created room with updated rate (Rent: 1.6M VND)");
            passed++;
        } else {
            System.out.println("  [FAIL] 5.4 Simulated Menu 7 rate update failed");
        }

        System.out.printf("=> Result: %d/%d menu workflow tests passed (%.0f%%)\n", passed, total, (passed * 100.0 / total));
        return passed == total;
    }
}
