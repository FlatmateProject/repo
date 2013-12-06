package view;

import com.vaadin.annotations.Title;
import com.vaadin.ui.TabSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Title("Hotel")
@Component
public class HotelUI extends TabComponent {

    @Autowired
    private TabComponent managerPanel;

    @Autowired
    private TabComponent cantorPanel;

    @Autowired
    private TabComponent schedulerPanel;

    @Autowired
    private TabComponent receptionPanel;

    @Autowired
    private TabComponent reservationPanel;

    @Autowired
    private TabComponent guessBookPanel;

    @Autowired
    private TabComponent statisticPanel;

    @Autowired
    private TabComponent employeeManagerPanel;

    @Override
    public void create() {
        TabSheet tabsheet = new TabSheet();
        tabsheet.addTab(cantorPanel.construct(), "Cantor");
        tabsheet.addTab(managerPanel.construct(), "Manager");
        tabsheet.addTab(schedulerPanel.construct(), "Grafik");
        tabsheet.addTab(receptionPanel.construct(), "Recepcja");
        tabsheet.addTab(reservationPanel.construct(), "Rezerwacje");
        tabsheet.addTab(guessBookPanel.construct(), "Księga gości");
        tabsheet.addTab(statisticPanel.construct(), "Statystyka");
        tabsheet.addTab(employeeManagerPanel.construct(), "Menadżer personelu");
        setCompositionRoot(tabsheet);
    }

    @Override
    public void addEvents() {
    }
}
