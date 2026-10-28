
package com.mycompany.pro192project;


import java.util.ArrayList;
import java.util.Map;

public class StudentManager {

    private ArrayList<Student> students;
    private Map<String, Room> rooms;

    public StudentManager(Map<String, Room> rooms) {
        this.students = new ArrayList<Student>();
        this.rooms = rooms;
    }

    // Add student
    public boolean addStudent(String id, String name) {
        if (findStudent(id) != null) {
            return false;
        }

        try {
            students.add(new Student(id, name));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Find student by ID
    public Student findStudent(String id) {
        if (id == null) {
            return null;
        }

        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    // Assign student to room
    public boolean assignStudentToRoom(
            String studentId, String roomId) {

        Student student = findStudent(studentId);
        Room room = rooms.get(roomId);

        if (student == null || room == null) {
            return false;
        }

        if (student.isAssigned() || room.isFull()) {
            return false;
        }

        if (room.addStudent(studentId)) {
            student.assignToRoom(roomId);
            return true;
        }

        return false;
    }

    // Check out student
    public boolean checkOutStudent(String studentId) {

        Student student = findStudent(studentId);

        if (student == null || !student.isAssigned()) {
            return false;
        }

        Room room = rooms.get(student.getCurrentRoomId());

        if (room == null) {
            return false;
        }

        if (room.removeStudent(studentId)) {
            student.checkOut();
            return true;
        }

        return false;
    }

    // Display room capacity
    public void displayRoomCapacity() {

        for (Room room : rooms.values()) {
            System.out.println("--------------------");
            System.out.println("Room: " + room.getRoomId());
            System.out.println("Capacity: " + room.getCapacity());
            System.out.println("Occupancy: "
                    + room.getCurrentOccupancy());
            System.out.println("Available slots: "
                    + room.getAvailableSlots());
        }
    }

    // Display all students
    public void displayStudents() {

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<Student>(students);
    }
}
