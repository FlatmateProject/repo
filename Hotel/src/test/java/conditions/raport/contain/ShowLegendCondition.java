package conditions.raport.contain;

import org.fest.assertions.Condition;

public class ShowLegendCondition extends Condition<String> {

	private String legend = "Legenda";
	
	public static ShowLegendCondition shownLegend() {
		return new ShowLegendCondition();
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(legend);
	}

}
