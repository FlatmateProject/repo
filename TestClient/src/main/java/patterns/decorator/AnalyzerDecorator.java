package patterns.decorator;

import java.util.ArrayList;
import java.util.List;

public abstract class AnalyzerDecorator extends TechnicalAnalyzer {

	private TechnicalAnalyzer analizer = new TechnicalAnalyzer();
	
	public abstract void inputConditions(List<Integer> inputData, List<Integer> result);
	
	@Override
	public List<IntegerElement> process(List<Integer> inputData) {
		List<Integer> result = new ArrayList<Integer>();
		inputConditions(inputData, result);
		return analizer.process(result);
	}
}
