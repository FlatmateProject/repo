package assertions;

import dto.ColumnData;
import org.fest.assertions.ListAssert;

import java.util.ArrayList;
import java.util.List;

public class ColumnListAssert extends ListAssert {

    private final List<String> names;

    private ColumnListAssert(List<ColumnData> actual) {
        super(actual);
        names = new ArrayList<String>();
    }

    public static ColumnListAssert assertThat(List<ColumnData> actual) {
        return new ColumnListAssert(actual);
    }

    public ColumnListAssert isNotEmptyList() {
        super.isNotNull();
        super.isNotEmpty();
        return this;
    }

    public ColumnListAssert containRoomType(String roomTypeName) {
        addToList(roomTypeName);
        return this;
    }

    public ColumnListAssert containServiceType(String serviceType) {
        addToList(serviceType);
        return this;
    }

    public ColumnListAssert containColumn(String column) {
        addToList(column);
        return this;
    }

    public ColumnListAssert exactly() {
        super.onProperty("name").containsExactly(names.toArray());
        return this;
    }

    private void addToList(String name) {
        names.add(name);
    }
}
