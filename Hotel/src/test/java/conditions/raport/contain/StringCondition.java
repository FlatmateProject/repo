package conditions.raport.contain;

import org.fest.assertions.Condition;

public class StringCondition extends Condition<String> {

	private String value;
	
	public StringCondition(String value) {
		this.value = value;
	}
	
	public static StringCondition headerContainRoomType(String roomTypeName) {
		return new StringCondition(roomTypeName);
	}

	public static StringCondition headerContainServiceType(String serviceTypeName) {
		return new StringCondition(serviceTypeName);
	}
	
	public static StringCondition footerContainLegend() {
		String legend = "Legenda";
		return new StringCondition(legend);
	}
	
	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(value);
	}

}
