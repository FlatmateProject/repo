package patterns.decorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TechnicalAnalyzer implements Analyzer {

	@Override
	public List<IntegerElement> process(List<Integer> inputData) {
		List<IntegerElement> result = createDiagram(inputData);
		double standard = calculateStandart(result);
		removeOutRange(result, standard);
		return result;
	}

	private void removeOutRange(List<IntegerElement> result, double standard) {
		removeFromBegin(result, standard);
		removeFromEnd(result, standard);
	}
	
	private void removeFromBegin(List<IntegerElement> result, double standard) {
		for (int i = 0; i < result.size(); i++) {
			if(isLessThan2Standard(result.get(i), standard)){
				result.remove(i);
			}else{
				return;
			}
		}
	}
	
	private boolean isLessThan2Standard(IntegerElement element, double standard) {
		return element.getValue() < standard;
	}
	
	private void removeFromEnd(List<IntegerElement> result, double standard) {
		for (int i = result.size() - 1; i >= 0 ; i--) {
			if(isBiggerThan2Standard(result.get(i), standard)){
				result.remove(i);
			}else{
				return;
			}
		}
		
	}

	private boolean isBiggerThan2Standard(IntegerElement element, double standard) {
		return element.getValue() > standard;
	}

	private double calculateStandart(List<IntegerElement> result) {
		double avarage = calculateAvarage(result);
		double midleSquer = calaulateMidleSquer(result, avarage);
		return Math.sqrt(midleSquer);
	}

	private double calaulateMidleSquer(List<IntegerElement> result,	double avarage) {
		double sum = 0.0;
		for (IntegerElement element : result) {
			sum += Math.pow((element.calculateRatio() - avarage), 2);
		}
		return sum;
	}

	private double calculateAvarage(List<IntegerElement> result) {
		double sum = 0.0;
		for (IntegerElement element : result) {
			sum += element.calculateRatio();
		}
		int size = result.size();
		if(size > 0){
			return sum / size;
		}
		return 0;
	}

	private List<IntegerElement> createDiagram(List<Integer> inputData) {
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
