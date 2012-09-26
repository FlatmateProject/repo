package conditions.raport.contain;

import org.fest.assertions.Condition;

public class ServiceTypeCondition extends Condition<String> {

	private String serviceTypeName;
	
	public ServiceTypeCondition(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	
	public static ServiceTypeCondition headerContainValidServiceType(String serviceTypeName) {
		return new ServiceTypeCondition(serviceTypeName);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(serviceTypeName);
	}

}
