package dto;

import java.util.ArrayList;
import java.util.List;

public class SimpleNameData {

    private String name;

    private String dataType;

    private String nullable;

    private String key;

    private String defaultValue;

    private String extra;

    public SimpleNameData() {
    }

    public SimpleNameData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<SimpleNameData> arrayOfMe(String value, int size) {
        List<SimpleNameData> list = new ArrayList<SimpleNameData>();
        for (int i = 0; i < size; i++) {
            list.add(new SimpleNameData(value));
        }
        return list;
    }
}
