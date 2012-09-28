package dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ServiceData {

	private String serviceName;
	
	private long summaryTime;
	
	private float summaryGain;

	public ServiceData() {
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public void setSummaryGain(float summaryGain) {
		this.summaryGain = summaryGain;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,	ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
