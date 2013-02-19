package service.employeeManager;

import dto.employeeManager.CleanTimeData;
import exception.RoomsNotFoundException;

import java.util.List;

public class RoomRangeCalculator {

    private List<CleanTimeData> cleanTimes;

    private final int numberOfRooms;

    private boolean nextRoom;

    private int currentRoomId;

    public RoomRangeCalculator(List<CleanTimeData> cleanTimes) {
        this.cleanTimes = cleanTimes;
        this.nextRoom = cleanTimes.size() > 0;
        this.currentRoomId = 0;
        this.numberOfRooms = cleanTimes.size();
    }

    public boolean isNextRoom() {
        return nextRoom;
    }

    public RoomRange getNextRoomRange(RoomRange previousRange) {
        double time = 0;
        long roomToId = previousRange.getRoomToId();
        long roomFromId = roomToId;
        while ((nextRoom = currentRoomId < numberOfRooms)) {
            time += cleanTimes.get(currentRoomId).getCleanTime();
            if (time > 8) {
                currentRoomId--;
                break;
            }
            roomToId = cleanTimes.get(currentRoomId).getRoomId();
            currentRoomId++;
        }
        return RoomRange.create(roomFromId, roomToId);
    }

    public RoomRange getFirstEmptyRange() throws RoomsNotFoundException {
        try {
            long roomFromId = cleanTimes.get(0).getRoomId();
            return RoomRange.create(roomFromId, roomFromId);
        } catch (Exception e) {
            throw new RoomsNotFoundException(e);
        }
    }

}
