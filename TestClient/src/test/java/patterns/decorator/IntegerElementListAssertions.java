package patterns.decorator;

import org.fest.assertions.ListAssert;

import java.util.List;

public class IntegerElementListAssertions extends ListAssert {

    private IntegerElementListAssertions(List<IntegerElement> actual) {
        super(actual);
    }

    public static IntegerElementListAssertions assertThat(List<IntegerElement> actual) {
        return new IntegerElementListAssertions(actual);
    }

    public IntegerElementAssertions hasFirstElement() {
        return hasElement(0);
    }

    public IntegerElementAssertions hasElement(int i) {
        return IntegerElementAssertions.assertThat((IntegerElement) actual.get(i));
    }
}
