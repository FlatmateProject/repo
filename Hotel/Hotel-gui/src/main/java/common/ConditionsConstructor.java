package common;

import java.util.ArrayList;
import java.util.List;

public class ConditionsConstructor {

    private String[] labels;

    private String[] data;

    public ConditionsConstructor(String[] labels, String[] data) {
        this.labels = labels;
        this.data = data;
    }

    public List<Condition> createConditions() {
        List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (!isNotClientDataEmpty(i)) {
                conditions.add(addCondition(i));
            }
        }
        return conditions;
    }

    private Condition addCondition(int i) {
        return Condition.create(labels[i], data[i]);
    }

    private boolean isNotClientDataEmpty(int i) {
        return data[i].isEmpty();
    }
}
