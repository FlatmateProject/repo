package patterns.decorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class DataProcessingUnitTest {

	@Test
	public void shouldReturnNull() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
		
		// when
		List<IntegerElement> outputData = unit.analyze(null);
		
		// then
		assertNull(outputData);
	}
	
	@Test
	public void shouldReturnEmptyList() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
		List<Integer> inputData = new ArrayList<Integer>();

		// when
		List<IntegerElement> outputData = unit.analyze(inputData);

		// then
		assertNotNull(outputData);
		assertEquals(0, outputData.size());
	}
	
	@Test
	public void shouldReturnOneElementList() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
		List<Integer> inputData = Arrays.asList(1);
		
		// when
		List<IntegerElement> outputData = unit.analyze(inputData);
		
		// then
		assertNotNull(outputData);
		assertEquals(1, outputData.size());
		for (IntegerElement element : outputData) {
			assertEquals(1, element.getValue());
		}
	}
	
	@Test
	public void shouldReturnListWitoutRepetition() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
		List<Integer> inputData = Arrays.asList(1, 1);
		
		// when
		List<IntegerElement> outputData = unit.analyze(inputData);
		
		// then
		assertNotNull(outputData);
		assertEquals(1, outputData.size());
		for (IntegerElement element : outputData) {
			assertEquals(1, element.getValue());
			assertEquals(2, element.getQuantity());
		}
	}
	
	@Test
	public void shouldReturnSortedListWitoutRepetition() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
		List<Integer> inputData = Arrays.asList(2, 1, 1);
		
		// when
		List<IntegerElement> outputData = unit.analyze(inputData);
		
		// then
		assertNotNull(outputData);
		assertEquals(2, outputData.size());
		IntegerElement[] elements = outputData.toArray(new IntegerElement[outputData.size()]);
		assertElement(1 ,2, elements[0]);
		assertElement(2, 1, elements[1]);

	}

	@Test
	public void shouldReturnOnlyValueWithAccuraccy95Percent() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new TechnicalAnalyzer());
		List<Integer> inputData = Arrays.asList(20, 10, 1);
		
		// when
		List<IntegerElement> outputData = unit.analyze(inputData);
		
		// then
		assertNotNull(outputData);
		assertEquals(3, outputData.size());
		IntegerElement[] elements = outputData.toArray(new IntegerElement[outputData.size()]);
		assertElement(1 ,1, elements[0]);
		assertElement(10, 1, elements[1]);
		assertElement(20, 1, elements[2]);
	}
	
	@Test
	public void shouldReturnOnlyValueWithAccuraccy95PercentAndBiggerThanZero() {
		// given
		TechnicalAnalyzer analyzer = new TechnicalAnalyzer();
		DataProcessingUnit unit = new DataProcessingUnit(new AnalyzerDecorator(analyzer){

			@Override
			public void inputConditions(List<Integer> inputData, List<Integer> result) {
				for (Integer number : inputData) {
					if(number > 0){
						result.add(number);
					}
				}	
			}});
		List<Integer> inputData = Arrays.asList(20, 10, 1, -1);

		// when
		List<IntegerElement> outputData = unit.analyze(inputData);

		// then
		assertNotNull(outputData);
		assertEquals(3, outputData.size());
		IntegerElement[] elements = outputData.toArray(new IntegerElement[outputData.size()]);
		assertElement(1, 1, elements[0]);
		assertElement(10, 1, elements[1]);
		assertElement(20, 1, elements[2]);
	}
	
	
	private static void assertElement(int expectedValue, int expectedQuantity, IntegerElement actual) {
		assertEquals(expectedValue, actual.getValue());
		assertEquals(expectedQuantity, actual.getQuantity());
	}
}
