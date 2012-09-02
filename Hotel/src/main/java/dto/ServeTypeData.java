package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ServeTypeData {

	private String typeName;
	
	private long time;
	
	private float sumaryGain;

	public ServeTypeData(String typeName, long time, float sumaryGain) {
		this.typeName = typeName;
		this.time = time;
		this.sumaryGain = sumaryGain;
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

	public float getSumaryGain() {
		return sumaryGain;
	}

	public void setSumaryGain(float sumaryGain) {
		this.sumaryGain = sumaryGain;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,	ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
