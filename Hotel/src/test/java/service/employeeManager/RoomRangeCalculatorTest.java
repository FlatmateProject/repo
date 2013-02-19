package service.employeeManager;

import dto.employeeManager.CleanTimeData;
import exception.RoomsNotFoundException;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static service.employeeManager.RoomRangeAssertions.assertThat;

public class RoomRangeCalculatorTest {

    @Test(expectedExceptions = RoomsNotFoundException.class)
    public void shouldFirstThrowRoomsNotFoundExceptionForRoomEmptyList() throws RoomsNotFoundException {
        // given
        List<CleanTimeData> rooms = Collections.emptyList();

        // when
        RoomRangeCalculator roomRangeCalculator = new RoomRangeCalculator(rooms);
        roomRangeCalculator.getFirstEmptyRange();

        // then
    }

    @Test
    public void shouldReturnPassedRoomRangeForEmptyRoomList() throws RoomsNotFoundException {
        // given
        long roomFromId = 1L;
        List<CleanTimeData> rooms = Collections.emptyList();
        RoomRange roomRange = mock(RoomRange.class);
        when(roomRange.getRoomFromId()).thenReturn(roomFromId);
        when(roomRange.getRoomToId()).thenReturn(roomFromId);

        // when
        RoomRangeCalculator roomRangeCalculator = new RoomRangeCalculator(rooms);
        RoomRange nextRoomRange = roomRangeCalculator.getNextRoomRange(roomRange);

        // then
        assertThat(nextRoomRange)
                .isNotNull()
                .isRange(roomFromId, roomFromId);
    }
}
