package view.managerPanel;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import dto.ColumnData;

import java.util.Arrays;
import java.util.List;

import static validation.BusinessValidation.*;
import static validation.UIValidation.isNotNumber;

public class DataPanel extends CustomComponent {

    private TextField dataFields[] = new TextField[0];

    private DataPanel() {
    }

    private DataPanel(List<ColumnData> columns) {
        super();
        dataFields = new TextField[columns.size()];
        VerticalLayout vertical = new VerticalLayout();
        vertical.setMargin(true);
        int i = 0;
        for (ColumnData columnData : columns) {
            dataFields[i] = new TextField(columnData.getName());
            vertical.addComponent(dataFields[i]);
            i++;
        }
        setCompositionRoot(vertical);
    }

    public static DataPanel empty() {
        return new DataPanel();
    }

    public static DataPanel create(List<ColumnData> columns) {
        return new DataPanel(columns);
    }

    public boolean areValidFields() {
        for (int i = 0; i < dataFields.length; i++) {
            String fieldText = dataFields[i].getValue();
            if (fieldText.isEmpty() && isNotValidField(dataFields[i].getCaption(), fieldText)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotValidField(String label, String field) {
        List<String> fieldsShouldBeNumbers = Arrays.asList(
                "CENA_SP", "CENA_KU", "ILOSC", "WARTOSC", "PODATEK", "IL_OSOB",
                "ID_STANOWISKA", "TELEFON", "NIP", "NR_LOKALU", "REGON", "CENA",
                "PODSTAWA", "PREMIA", "ID_POKOJU", "ID_KLASY", "ID_REZ", "ID_USLUGI"
        );
        List<String> fieldsShouldBePesel = Arrays.asList("IDK_PESEL", "IDP_PESEL");
        List<String> fieldsShouldBeData = Arrays.asList("DATA_Z", "DATA_W", "DATA");
        if (fieldsShouldBePesel.contains(label) && !isPesel(field)) {
            Notification.show("Wrong PESEL!");
            return true;
        }
        if (label.equals("IDF_KRS") && !isKRS(field)) {
            Notification.show("Wrong KRS!");
            return true;
        }
        if (fieldsShouldBeData.contains(label) && isNotDate(field)) {
            Notification.show("Wrong date!");
            return true;
        }
        if (fieldsShouldBeNumbers.contains(label) && isNotNumber(field)) {
            Notification.show("Wrong number!");
            return true;
        }
        return false;
    }

    public void cleanFields() {
        for (TextField field : dataFields) {
            field.setValue("");
        }
    }

    public String[] getData() {
        int length = dataFields.length;
        String data[] = new String[length];
        for (int i = 0; i < length; i++) {
            data[i] = dataFields[i].getValue();
        }
        return data;
    }

    public long getIdValue() {
        try {
            return Long.parseLong(dataFields[0].getValue());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    public String getPrimaryKey() {
        return dataFields[0].getCaption();
    }

    public String[] getLabels() {
        int length = dataFields.length;
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            array[i] = dataFields[i].getCaption();
        }
        return array;
    }

    public int getNumberOfFields() {
        return dataFields.length;

    }

    public void updateFields(String[] values) {
        int length = dataFields.length;
        String data[] = new String[length];
        for (int i = 0; i < length; i++) {
            dataFields[i].setValue(values[i]);
        }
    }
}
