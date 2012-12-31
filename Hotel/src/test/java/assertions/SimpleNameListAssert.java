package assertions;

import dto.SimpleNameData;
import org.fest.assertions.ListAssert;

import java.util.ArrayList;
import java.util.List;

public class SimpleNameListAssert extends ListAssert {

    private final List<String> names;

    private SimpleNameListAssert(List<SimpleNameData> actual) {
        super(actual);
        names = new ArrayList<String>();
    }

    public static SimpleNameListAssert assertThat(List<SimpleNameData> actual) {
        return new SimpleNameListAssert(actual);
    }

    public SimpleNameListAssert isNotEmptyList() {
        super.isNotNull();
        super.isNotEmpty();
        return this;
    }

    public SimpleNameListAssert containRoomType(String roomTypeName) {
        addToList(roomTypeName);
        return this;
    }

    public SimpleNameListAssert containServiceType(String serviceType) {
        addToList(serviceType);
        return this;
    }

    public SimpleNameListAssert exactly() {
        super.onProperty("name").containsExactly(names.toArray());
        return this;
    }

    private void addToList(String name) {
        names.add(name);
    }
}
