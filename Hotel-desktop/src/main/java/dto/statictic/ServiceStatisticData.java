package dto.statictic;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ServiceStatisticData {

    private String serviceName;

    private long summaryTime;

    private float summaryGain;

    public ServiceStatisticData() {
    }

    public ServiceStatisticData(String serviceName, long summaryTime, float summaryGain) {
        this.serviceName = serviceName;
        this.summaryTime = summaryTime;
        this.summaryGain = summaryGain;
    }

    public String getServiceName() {
        return serviceName;
    }

    public long getSummaryTime() {
        return summaryTime;
    }

    public float getSummaryGain() {
        return summaryGain;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
