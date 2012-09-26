package assertions;

import org.fest.assertions.Assertions;
import org.fest.assertions.DoubleArrayAssert;

public class DiagramBarsAssert extends DoubleArrayAssert {

	protected DiagramBarsAssert(double[] actual) {
		super(actual);
	}
	
	public static DiagramBarsAssert assertThat(double[] actual) {
		return new DiagramBarsAssert(actual);
	}
	
	public DiagramBarsAssert isSumaryGainEqualTo(double sumaryGain) {
		Assertions.assertThat(actual[0]).isEqualTo(sumaryGain);
		return this;
	}
	
	public DiagramBarsAssert isUnitGainEqualTo(double unitGain) {
		Assertions.assertThat(actual[1]).isEqualTo(unitGain);
		return this;
	}
}
