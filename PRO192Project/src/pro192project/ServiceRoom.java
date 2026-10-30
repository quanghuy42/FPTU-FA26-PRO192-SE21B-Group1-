package pro192project;

/**
 * Service dormitory room with additional service fee.
 *
 * @author Thái Quang Huy
 */
public class ServiceRoom extends Room {
    private long serviceFee;

    public ServiceRoom(String roomId, int capacity, long baseRent, long serviceFee) {
        super(roomId, capacity, baseRent);
        Room.validateMoney(serviceFee, "Service fee");
        this.serviceFee = serviceFee;
    }

    @Override
    public long calculateRoomCharge() {
        return super.getBaseRent() + this.serviceFee;
    }

    @Override
    public String getRoomType() {
        return "SERVICE";
    }

    @Override
    public long getServiceFee() {
        return this.serviceFee;
    }

    public void setServiceFee(long serviceFee) {
        Room.validateMoney(serviceFee, "Service fee");
        this.serviceFee = serviceFee;
    }
}
