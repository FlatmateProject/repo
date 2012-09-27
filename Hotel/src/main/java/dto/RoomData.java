package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RoomData {

	private long roomId;

	private int occupationNumber;

	private float summaryGain;

	public RoomData() {

	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public int getOccupationNumber() {
		return occupationNumber;
	}

	public void setOccupationNumber(int occupationNumber) {
		this.occupationNumber = occupationNumber;
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
