package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ServiceTypeData {

	private String typeName;
	
	private long time;
	
	private float summaryGain;

	public ServiceTypeData(String typeName, long time, float sumaryGain) {
		this.typeName = typeName;
		this.time = time;
		this.summaryGain = sumaryGain;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setType(String typeName) {
		this.typeName = typeName;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
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
