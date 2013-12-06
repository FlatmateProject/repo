package common;

public class Condition {

    private final String property;

    private final String value;

    private Condition(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public static Condition create(String property, String value) {
        return new Condition(property, value);
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }
}
