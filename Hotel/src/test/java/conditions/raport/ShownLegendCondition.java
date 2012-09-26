package conditions.raport;

import org.fest.assertions.Condition;

public class ShownLegendCondition extends Condition<String> {

	private String legend = "Legenda";
	
	public static ShownLegendCondition shownLegend() {
		return new ShownLegendCondition();
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(legend);
	}

}
