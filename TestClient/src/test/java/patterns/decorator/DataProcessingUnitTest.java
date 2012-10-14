package patterns.decorator;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static patterns.decorator.IntegerElementListAssertions.assertThat;
public class DataProcessingUnitTest {

	@Test
	public void shouldReturnNull() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
		List<Integer> inputData = null;

		// when
		List<IntegerElement> outputData = unit.analyze(inputData);

		// then
		assertThat(outputData).isNull();
	}

	@Test
	public void shouldReturnEmptyList() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
		List<Integer> inputData = new ArrayList<Integer>();
        int expectedSize = 0;

		// when
		List<IntegerElement> outputData = unit.analyze(inputData);

		// then
        assertThat(outputData).isNotNull().hasSize(expectedSize);
	}

	@Test
	public void shouldReturnOneElementList() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
        IntegerElement expectedElement = new IntegerElement(1);
        List<Integer> inputData = Arrays.asList(expectedElement.getValue());
        int expectedSize = 1;

		// when
		List<IntegerElement> outputData = unit.analyze(inputData);
		
		// then
        assertThat(outputData).isNotNull().hasSize(expectedSize).containsOnly(expectedElement);
	}
	
	@Test
	public void shouldReturnListWithoutRepetition() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
        IntegerElement expectedElement = new IntegerElement(1);
        int value = expectedElement.getValue();
        int quantity = 2;
        List<Integer> inputData = Arrays.asList(value, value);
        int expectedSize = 1;

        // when
		List<IntegerElement> outputData = unit.analyze(inputData);

		// then
        assertThat(outputData).isNotNull().hasSize(expectedSize).containsOnly(expectedElement);
        assertThat(outputData).hasFirstElement().isValue(value).isQuantity(quantity);
	}
	
	@Test
	public void shouldReturnSortedListWithoutRepetition() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new ScientificAnalyzer());
        IntegerElement expectedElement1 = new IntegerElement(1);
        IntegerElement expectedElement2 = new IntegerElement(2);
        List<Integer> inputData = Arrays.asList(expectedElement2.getValue(), expectedElement1.getValue(), expectedElement1.getValue());
        int expectedSize = 2;

        // when
		List<IntegerElement> outputData = unit.analyze(inputData);
		
		// then
        assertThat(outputData).isNotNull().hasSize(expectedSize).containsExactly(expectedElement1, expectedElement2);
        assertThat(outputData).hasElement(0).isValue(1).isQuantity(2);
        assertThat(outputData).hasElement(1).isValue(2).isQuantity(1);

	}

	@Test
	public void shouldReturnOnlyValueWithAccuracy95Percent() {
		// given
		DataProcessingUnit unit = new DataProcessingUnit(new TechnicalAnalyzer());
        IntegerElement expectedElement1 = new IntegerElement(1);
        IntegerElement expectedElement10 = new IntegerElement(10);
        IntegerElement expectedElement20 = new IntegerElement(20);
		List<Integer> inputData = Arrays.asList(expectedElement20.getValue(), expectedElement10.getValue(), expectedElement1.getValue());
        int expectedSize = 3;

        // when
		List<IntegerElement> outputData = unit.analyze(inputData);
		
		// then
        assertThat(outputData).isNotNull().hasSize(expectedSize).containsExactly(expectedElement1, expectedElement10, expectedElement20);
        assertThat(outputData).hasElement(0).isValue(1).isQuantity(1);
        assertThat(outputData).hasElement(1).isValue(10).isQuantity(1);
        assertThat(outputData).hasElement(2).isValue(20).isQuantity(1);
	}

    @Test
    public void shouldReturnOnlyValueWithAccuracy95PercentAndBiggerThanZero() {
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
        IntegerElement expectedElement1 = new IntegerElement(1);
        IntegerElement expectedElement10 = new IntegerElement(10);
        IntegerElement expectedElement20 = new IntegerElement(20);
        int invalidValue = -1;
        List<Integer> inputData = Arrays.asList(expectedElement20.getValue(), expectedElement10.getValue(), expectedElement1.getValue(), invalidValue);
        int expectedSize = 3;

        // when
        List<IntegerElement> outputData = unit.analyze(inputData);

        // then
        assertThat(outputData).isNotNull().hasSize(expectedSize).containsExactly(expectedElement1, expectedElement10, expectedElement20);
        assertThat(outputData).hasElement(0).isValue(1).isQuantity(1);
        assertThat(outputData).hasElement(1).isValue(10).isQuantity(1);
        assertThat(outputData).hasElement(2).isValue(20).isQuantity(1);
    }
}
