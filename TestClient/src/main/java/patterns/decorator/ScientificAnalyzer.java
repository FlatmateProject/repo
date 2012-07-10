package patterns.decorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScientificAnalyzer implements Analyzer {

	@Override
	public List<IntegerElement> process(List<Integer> inputData) {
		Collections.sort(inputData);
		List<IntegerElement> result = new ArrayList<IntegerElement>();
		for (Integer value : inputData) {
			IntegerElement element = new IntegerElement(value);
			addOrUpdate(result, element);
		}
		return result;
	}

	private void addOrUpdate(List<IntegerElement> result, IntegerElement element) {
		int i = result.indexOf(element);
		if (i == -1) {
			result.add(element);
		} else {
			element = result.get(i);
			element.increase();
			result.set(i, element);
		}
	}
}
