package behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import common.TableContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.CantorTableCreator;
import view.CantorPanel;
import view.PopUpComponent;
import view.common.TableUIBuilder;

import static validation.BusinessValidation.isKRS;
import static validation.BusinessValidation.isPesel;

@Component
public class ClickSearchClientButtonBehavior implements Button.ClickListener {

    @Autowired
    private CantorTableCreator creator;

    @Autowired
    private PopUpComponent popUp;

    private CantorPanel cantorPanel;

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (isCustomerIdEmpty()) {
            popUp.showError("Nie podano numeru PESEL/KRS");
            return;
        }
        String customerId = cantorPanel.getClientId();
        if (isPesel(customerId)) {
            cantorPanel.refreshCustomerTable(createCustomerTable());
        } else if (isKRS(customerId)) {
            cantorPanel.refreshCompanyTable(createCompanyTable());
        } else {
            popUp.showError("Nieprawidłowy PESEL/KRS");
        }
    }

    private boolean isCustomerIdEmpty() {
        return cantorPanel.getClientId().isEmpty();
    }

    private Table createCustomerTable() {
        long pesel = Long.parseLong(cantorPanel.getClientId());
        TableContent customerTableContent = creator.createCustomerTable(pesel);
        return TableUIBuilder.table()
                .withTitle("Klienci indywidualni")
                .withContent(customerTableContent)
                .withSelection()
                .build();
    }

    private Table createCompanyTable() {
        long krs = Long.parseLong(cantorPanel.getClientId());
        TableContent companyTableContent = creator.createCompanyTable(krs);
        return TableUIBuilder.table()
                .withTitle("Firmy")
                .withContent(companyTableContent)
                .withSelection()
                .build();
    }

    public void setCantorPanel(CantorPanel cantorPanel) {
        this.cantorPanel = cantorPanel;
    }

    public void setCreator(CantorTableCreator creator) {
        this.creator = creator;
    }

    public void setPopUp(PopUpComponent popUp) {
        this.popUp = popUp;
    }
}