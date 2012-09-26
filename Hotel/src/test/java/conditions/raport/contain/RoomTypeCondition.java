package conditions.raport.contain;

import org.fest.assertions.Condition;

public class RoomTypeCondition extends Condition<String> {

	private String roomTypeName;
	
	public RoomTypeCondition(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	
	public static RoomTypeCondition headerContainValidRoomType(String roomTypeName) {
		return new RoomTypeCondition(roomTypeName);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(roomTypeName);
	}

}
