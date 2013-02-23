package gui.guestBook;

import dictionary.TABLE;

import java.awt.*;

public interface Specification {

    TABLE getTable();

    Rectangle geDataTableBounds();

    Rectangle getServiceTableBounds();

    String getPrimaryId();
}
