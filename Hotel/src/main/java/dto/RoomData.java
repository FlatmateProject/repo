package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RoomData {

	private long roomId;

	private int nuberOccupiedRooms;

	private float summaryGain;

	public RoomData() {

	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
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