package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ServeData {

	private String serveName;
	
	private long time;
	
	private float sumaryGain;

	public ServeData(String serveName, long time, float sumaryGain) {
		this.serveName = serveName;
		this.time = time;
		this.sumaryGain = sumaryGain;
	}

	public String getServeName() {
		return serveName;
	}

	public void setServeName(String serveName) {
		this.serveName = serveName;
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
