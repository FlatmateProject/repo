package patterns.decorator;

import java.util.ArrayList;
import java.util.List;

public abstract class AnalyzerDecorator implements Analyzer {

	private Analyzer analizer;
	
	public AnalyzerDecorator(Analyzer analizer) {
		this.analizer = analizer;
	}

	public abstract void inputConditions(List<Integer> inputData, List<Integer> result);
	
	@Override
	public List<IntegerElement> process(List<Integer> inputData) {
		List<Integer> result = new ArrayList<Integer>();
		inputConditions(inputData, result);
		return analizer.process(result);
	}
}
