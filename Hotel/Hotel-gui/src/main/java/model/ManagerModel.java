package model;

import dictionary.TABLE;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: piotro
 * Date: 11/6/13
 * Time: 7:29 PM
 */
@Component
@Scope("singleton")
public class ManagerModel {

    private TABLE currentlySelectedTable = TABLE.Customer;

    public TABLE getCurrentlySelectedTable() {
        return currentlySelectedTable;
    }

    public void setCurrentlySelectedTable(TABLE currentlySelectedTable) {
        this.currentlySelectedTable = currentlySelectedTable;
    }
}
