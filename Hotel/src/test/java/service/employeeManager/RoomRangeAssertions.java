package service.employeeManager;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class RoomRangeAssertions extends GenericAssert<RoomRangeAssertions, RoomRange> {

    private RoomRangeAssertions(Class<RoomRangeAssertions> selfType, RoomRange actual) {
        super(selfType, actual);
    }

    public static RoomRangeAssertions assertThat(RoomRange actual) {
        return new RoomRangeAssertions(RoomRangeAssertions.class, actual);
    }

    public RoomRangeAssertions isRange(long roomFromId, long roomToId) {
        Assertions.assertThat(actual.getRoomFromId()).isEqualTo(roomFromId);
        Assertions.assertThat(actual.getRoomToId()).isEqualTo(roomToId);
        return this;
    }
}
