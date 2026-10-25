
package com.mycompany.dev.luan;


public class Student {

    private String studentId;
    private String fullName;
    private String currentRoomId;

    public Student(String studentId, String fullName) {
        if (studentId == null || studentId.trim().isEmpty()
                || studentId.matches(".*\\s+.*")) {
            throw new IllegalArgumentException("Invalid student ID");
        }

        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        this.studentId = studentId;
        this.fullName = fullName;
        this.currentRoomId = null;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCurrentRoomId() {
        return currentRoomId;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.fullName = fullName;
    }

    public boolean isAssigned() {
        return currentRoomId != null
                && !currentRoomId.equals("NONE");
    }

    public void assignToRoom(String roomId) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid room ID");
        }

        this.currentRoomId = roomId;
    }

    public void checkOut() {
        this.currentRoomId = null;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId
                + ", Name: " + fullName
                + ", Room: " + (isAssigned()
                ? currentRoomId : "NONE");
    }
}

