package patterns.decorator;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class IntegerElementAssertions extends GenericAssert<IntegerElementAssertions, IntegerElement>{

    private IntegerElementAssertions(Class<IntegerElementAssertions> selfType, IntegerElement actual) {
        super(selfType, actual);
    }

    public static IntegerElementAssertions assertThat(IntegerElement actual){
        return new IntegerElementAssertions(IntegerElementAssertions.class, actual);
    }

    public IntegerElementAssertions isValue(int value) {
        Assertions.assertThat(actual.getValue()).isEqualTo(value);
        return this;
    }

    public IntegerElementAssertions isQuantity(int quantity) {
        Assertions.assertThat(actual.getQuantity()).isEqualTo(quantity);
        return this;
    }

}
