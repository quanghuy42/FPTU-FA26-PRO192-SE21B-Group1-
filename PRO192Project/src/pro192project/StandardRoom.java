package pro192project;

public class StandardRoom extends Room {

    public StandardRoom(String roomId, int capacity, long baseRent) {
        super(roomId, capacity, baseRent);
    }

    @Override
    public long calculateRoomCharge() {
        return super.getBaseRent();
    }

    @Override
    public String getRoomType() {
        return "STANDARD";
    }

    @Override
    public long getServiceFee() {
        return 0;
    }
}
