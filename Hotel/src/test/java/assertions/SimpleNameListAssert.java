package assertions;

import dto.SimpleNameData;
import org.fest.assertions.ListAssert;

import java.util.List;

public class SimpleNameListAssert extends ListAssert {

    private SimpleNameListAssert(List<SimpleNameData> actual) {
        super(actual);

    }

    public static SimpleNameListAssert assertThat(List<SimpleNameData> actual) {
        return new SimpleNameListAssert(actual);
    }

    public SimpleNameListAssert isNotEmptyList() {
        super.isNotNull();
        super.isNotEmpty();
        return this;

    }

    public SimpleNameListAssert containsRoomType(String roomTypeNames) {
        return contain(roomTypeNames);
    }

    private SimpleNameListAssert contain(String roomTypeNames) {
        super.onProperty("name").contains(roomTypeNames);
        return this;
    }
}
