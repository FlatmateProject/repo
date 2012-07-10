package patterns.decorator;

import java.util.List;

public class DataProcessingUnit {

	private Analyzer analyzer = new ScientificAnalyzer();

	public DataProcessingUnit(Analyzer analyzer) {
		if(analyzer != null) {
			this.analyzer = analyzer;
		}
	}

	public List<IntegerElement> analyze(List<Integer> inputData) {
		if(inputData == null){
			return null;
		}
		return analyzer.process(inputData);
	}

}
