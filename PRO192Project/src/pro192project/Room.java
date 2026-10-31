package pro192project;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a dormitory room.
 *
 * @author Thái Quang Huy
 */
public abstract class Room {
    private String roomId;
    private int capacity;
    private long baseRent;
    private List<String> residentIds;

    public Room(String roomId, int capacity, long baseRent) {
        Room.validateRoomId(roomId);
        Room.validateCapacity(capacity);
        Room.validateMoney(baseRent, "Room rent");
        this.roomId = roomId;
        this.capacity = capacity;
        this.baseRent = baseRent;
        this.residentIds = new ArrayList<String>();
    }

    /**
     * Calculates the total monthly room charge.
     *
     * @return monthly room charge in VND
     */
    public abstract long calculateRoomCharge();

    /**
     * Gets the room type identifier.
     *
     * @return room type
     */
    public abstract String getRoomType();

    /**
     * Gets the monthly service fee.
     *
     * @return service fee in VND
     */
    public abstract long getServiceFee();

    public String getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getBaseRent() {
        return baseRent;
    }

    public void setBaseRent(long baseRent) {
        Room.validateMoney(baseRent, "Room rent");
        this.baseRent = baseRent;
    }

    public int getResidentCount() {
        return this.residentIds.size();
    }

    public int getVacancy() {
        return this.capacity - this.residentIds.size();
    }

    public boolean isFull() {
        return this.residentIds.size() >= this.capacity;
    }

    public boolean hasResident(String studentId) {
        return this.residentIds.contains(studentId);
    }

    public boolean addResident(String studentId) {
        if (this.isFull() || this.hasResident(studentId)) {
            return false;
        }
        this.residentIds.add(studentId);
        return true;
    }

    public boolean removeResident(String studentId) {
        return this.residentIds.remove(studentId);
    }

    public boolean addStudent(String studentId) {
        return this.addResident(studentId);
    }

    public boolean removeStudent(String studentId) {
        return this.removeResident(studentId);
    }

    public int getCurrentOccupancy() {
        return this.getResidentCount();
    }

    public int getAvailableSlots() {
        return this.getVacancy();
    }

    public List<String> getResidentIds() {
        return new ArrayList<String>(this.residentIds);
    }

    public static void validateRoomId(String roomId) {
        if (roomId == null || roomId.isEmpty()) {
            throw new IllegalArgumentException("Room ID cannot be empty.");
        }
        for (int i = 0; i < roomId.length(); i++) {
            if (Character.isWhitespace(roomId.charAt(i))) {
                throw new IllegalArgumentException("Room ID cannot contain whitespace.");
            }
        }
    }

    public static void validateCapacity(int capacity) {
        if (capacity < 1 || capacity > 10) {
            throw new IllegalArgumentException("Capacity must be between 1 and 10.");
        }
    }

    public static void validateMoney(long value, String fieldName) {
        if (value < 0 || value > 10_000_000L) {
            throw new IllegalArgumentException(fieldName + " must be between 0 and 10,000,000 VND.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s | %s | Capacity: %d | Occupied: %d | Vacant: %d | Rent/month: %,d VND",
                this.roomId, this.getRoomType(), this.capacity,
                this.getResidentCount(), this.getVacancy(), this.calculateRoomCharge());
    }
}
