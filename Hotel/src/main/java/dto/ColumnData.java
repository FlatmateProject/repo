package dto;

import java.util.ArrayList;
import java.util.List;

public class ColumnData {

    private String name;

    private String dataType;

    private String nullable;

    private String key;

    private String defaultValue;

    private String extra;

    public ColumnData() {
    }

    public ColumnData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<ColumnData> arrayOfMe(String value, int size) {
        List<ColumnData> list = new ArrayList<ColumnData>();
        for (int i = 0; i < size; i++) {
            list.add(new ColumnData(value));
        }
        return list;
    }
}
