package conditions.raport.contain;

import org.fest.assertions.Condition;

public class NumberOccupiedRoomsCondition extends Condition<String> {

	private int numberOccupiedRooms;

	public NumberOccupiedRoomsCondition(int numberOccupiedRooms) {
		this.numberOccupiedRooms = numberOccupiedRooms;
	}

	public static NumberOccupiedRoomsCondition bodyContainNumberOccupiedRooms(int numberOccupiedRooms) {
		return new NumberOccupiedRoomsCondition(numberOccupiedRooms);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Integer.toString(numberOccupiedRooms));
	}
}
