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

    public void setManagerPanel(TabComponent managerPanel) {
        this.managerPanel = managerPanel;
    }

    public void setCantorPanel(TabComponent cantorPanel) {
        this.cantorPanel = cantorPanel;
    }

    public void setSchedulerPanel(TabComponent schedulerPanel) {
        this.schedulerPanel = schedulerPanel;
    }

    public void setReceptionPanel(TabComponent receptionPanel) {
        this.receptionPanel = receptionPanel;
    }

    public void setReservationPanel(TabComponent reservationPanel) {
        this.reservationPanel = reservationPanel;
    }

    public void setGuessBookPanel(TabComponent guessBookPanel) {
        this.guessBookPanel = guessBookPanel;
    }

    public void setStatisticPanel(TabComponent statisticPanel) {
        this.statisticPanel = statisticPanel;
    }

    public void setEmployeeManagerPanel(TabComponent employeeManagerPanel) {
        this.employeeManagerPanel = employeeManagerPanel;
    }
}
