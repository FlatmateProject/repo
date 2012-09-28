package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ServiceTypeData {

	private String typeName;
	
	private long summaryTime;
	
	private float summaryGain;

	public String getTypeName() {
		return typeName;
	}

	public void setType(String typeName) {
		this.typeName = typeName;
	}

	public long getSummaryTime() {
		return summaryTime;
	}

	public void setSummaryTime(long summaryTime) {
		this.summaryTime = summaryTime;
	}

	public float getSummaryGain() {
		return summaryGain;
	}

	public void setSummaryGain(float sumaryGain) {
		this.summaryGain = sumaryGain;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,	ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
