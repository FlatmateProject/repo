package view;

import com.vaadin.annotations.Title;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Title("Hotel")
@Component
public class HotelUI extends CustomComponent implements ComponentLifeCycle {

    @Autowired
    private CustomComponent managerPanel;

    @Autowired
    private CustomComponent cantorPanel;

    @Autowired
    private CustomComponent schedulerPanel;

    @Autowired
    private CustomComponent receptionPanel;

    @Autowired
    private CustomComponent reservationPanel;

    @Autowired
    private CustomComponent guessBookPanel;

    @Autowired
    private CustomComponent statisticPanel;

    @Autowired
    private CustomComponent employeeManagerPanel;

    public HotelUI() {
    }

    public void construct() {
        create();
        addEvents();
    }

    public void create() {
        TabSheet tabsheet = new TabSheet();
        tabsheet.addTab(managerPanel, "Manager");
        tabsheet.addTab(cantorPanel, "Cantor");
        tabsheet.addTab(schedulerPanel, "Grafik");
        tabsheet.addTab(receptionPanel, "Recepcja");
        tabsheet.addTab(reservationPanel, "Rezerwacje");
        tabsheet.addTab(guessBookPanel, "Księga gości");
        tabsheet.addTab(statisticPanel, "Statystyka");
        tabsheet.addTab(employeeManagerPanel, "Menadżer personelu");
        setCompositionRoot(tabsheet);
    }

    public void addEvents() {
    }

    public void setManagerPanel(CustomComponent managerPanel) {
        this.managerPanel = managerPanel;
    }

    public void setCantorPanel(CustomComponent cantorPanel) {
        this.cantorPanel = cantorPanel;
    }

    public void setSchedulerPanel(CustomComponent schedulerPanel) {
        this.schedulerPanel = schedulerPanel;
    }

    public void setReceptionPanel(CustomComponent receptionPanel) {
        this.receptionPanel = receptionPanel;
    }

    public void setReservationPanel(CustomComponent reservationPanel) {
        this.reservationPanel = reservationPanel;
    }

    public void setGuessBookPanel(CustomComponent guessBookPanel) {
        this.guessBookPanel = guessBookPanel;
    }

    public void setStatisticPanel(CustomComponent statisticPanel) {
        this.statisticPanel = statisticPanel;
    }

    public void setEmployeeManagerPanel(CustomComponent employeeManagerPanel) {
        this.employeeManagerPanel = employeeManagerPanel;
    }
}
