package patterns.decorator;

import java.util.List;

public interface Analyzer {

	List<IntegerElement> process(List<Integer> inputData);
}
