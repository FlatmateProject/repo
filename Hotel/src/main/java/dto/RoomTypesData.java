package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RoomTypesData {

	private String roomTypeName;

	private int nuberOccupiedRooms;

	private float summaryGain;

	public RoomTypesData() {

	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public int getNuberOccupiedRooms() {
		return nuberOccupiedRooms;
	}

	public void setNuberoccupiedRooms(int nuberOccupiedRooms) {
		this.nuberOccupiedRooms = nuberOccupiedRooms;
	}

	public float getSummaryGain() {
		return summaryGain;
	}

	public void setGain(float gain) {
		this.summaryGain = gain;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,	ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
