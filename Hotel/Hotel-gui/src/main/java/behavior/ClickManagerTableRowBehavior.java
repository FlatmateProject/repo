package behavior;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import view.managerPanel.ManagerPanel;

/**
 * User: piotro
 * Date: 11/6/13
 * Time: 7:44 PM
 */
public class ClickManagerTableRowBehavior implements ItemClickEvent.ItemClickListener {

    private static final Logger log = Logger.getLogger(ClickManagerTableRowBehavior.class);

    @Autowired
    private ManagerPanel managerPanel;

    @Override
    public void itemClick(ItemClickEvent event) {
        int numberOfColumn = event.getItem().getItemPropertyIds().size();
        int numberOfInputFields = managerPanel.getDataPanel().getNumberOfFields();
        if (numberOfColumn == numberOfInputFields) {
            fillInputs(event.getItem());
        }
    }

    private void fillInputs(Item selectedRow) {
        log.info("selectedRow " + selectedRow);
        for (int i = 1; i <= managerPanel.getDataPanel().getNumberOfFields(); i++) {
            Property property = selectedRow.getItemProperty(i);
            String value = String.valueOf(property.getValue());
            managerPanel.getDataPanel().updateField(i - 1, value);
        }
    }
}
