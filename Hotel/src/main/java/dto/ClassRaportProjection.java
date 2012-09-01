package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ClassRaportProjection {

	private String description;

	private int nuberOccupiedRooms;

	private float summaryGain;

	public ClassRaportProjection() {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
