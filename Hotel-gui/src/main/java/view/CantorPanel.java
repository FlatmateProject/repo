package view;


import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import dao.CantorDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import service.cantor.ExchangeCalculation;

@Component
public class CantorPanel extends TabComponent {

    private static final Logger log = Logger.getLogger(CantorPanel.class);

    private Button addButton;

    @Autowired
    private CantorTableCreator creator;

    @Autowired
    private CantorDao cantorDao;

    @Autowired
    private CantorMoneyExchanger cantor;

    private ExchangeCalculation exchangeCalculation;

    @Override
    public void create() {
        addButton = new Button("Add");
        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(new TextField("Name"));
        vertical.addComponent(new TextField("Street address"));
        vertical.addComponent(new TextField("Postal code"));
        vertical.addComponent(addButton);
        setCompositionRoot(vertical);
    }

    @Override
    public void addEvents() {
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                log.info("############ creator: " + creator);
            }
        });
    }

    public void setCreator(CantorTableCreator creator) {
        this.creator = creator;
    }

    public void setCantorDao(CantorDao cantorDao) {
        this.cantorDao = cantorDao;
    }

    public void setCantor(CantorMoneyExchanger cantor) {
        this.cantor = cantor;
    }
}
