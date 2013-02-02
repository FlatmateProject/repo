package gui.guestBook;

import common.tableBuilder.ArrayObtained;

import java.awt.*;

public interface Specification {

    String getTable();

    Rectangle geDataTableBounds();

    Rectangle getServiceTableBounds();

    <T extends ArrayObtained> Class<T> getClientDtoClass();

    String getPrimaryId();
}
