package patterns.decorator;

import java.util.ArrayList;
import java.util.List;

public abstract class AnalyzerDecorator implements Analyzer {

	private Analyzer analyzer;
	
	public AnalyzerDecorator(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public abstract void inputConditions(List<Integer> inputData, List<Integer> result);
	
	@Override
	public List<IntegerElement> process(List<Integer> inputData) {
		List<Integer> result = new ArrayList<Integer>();
		inputConditions(inputData, result);
		return analyzer.process(result);
	}
}
