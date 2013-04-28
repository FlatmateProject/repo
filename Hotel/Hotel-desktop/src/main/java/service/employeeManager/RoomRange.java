package service.employeeManager;

public class RoomRange {

    private long roomFromId;

    private long roomToId;

    private RoomRange(long roomFromId, long roomToId) {
        this.roomFromId = roomFromId;
        this.roomToId = roomToId;
    }

    public static RoomRange create(long roomFromId, long roomToId) {
        return new RoomRange(roomFromId, roomToId);
    }

    public long getRoomFromId() {
        return roomFromId;
    }

    public long getRoomToId() {
        return roomToId;
    }
}
