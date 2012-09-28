package conditions.raport.contain;

import org.fest.assertions.Condition;

public class DoubleCondition extends Condition<String> {

	private double value;

	public DoubleCondition(double value) {
		this.value = value;
	}

	public static DoubleCondition bodyContainSumaryGain(double sumaryGain) {
		return new DoubleCondition(sumaryGain);
	}

	public static DoubleCondition bodyContainUnitGain(double unitGain) {
		return new DoubleCondition(unitGain);
	}
	
	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Double.toString(value));
	}
}
