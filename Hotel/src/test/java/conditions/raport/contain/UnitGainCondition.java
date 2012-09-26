package conditions.raport.contain;

import org.fest.assertions.Condition;

public class UnitGainCondition extends Condition<String> {

	private double unitGain;

	public UnitGainCondition(double unitGain) {
		this.unitGain = unitGain;
	}

	public static UnitGainCondition bodyContainUnitGain(double unitGain) {
		return new UnitGainCondition(unitGain);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Double.toString(unitGain));
	}
}
