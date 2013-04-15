package view;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

@Title("Hotel")
public class HotelUI extends UI implements ComponentLifeCycle {

    private CustomComponent managerPanel = new ManagerPanel();

    private CustomComponent cantorPanel = new CantorPanel();

    private CustomComponent schedulerPanel = new SchedulerPanel();

    private CustomComponent receptionPanel = new ReceptionPanel();

    private CustomComponent reservationPanel = new ReservationPanel();

    private CustomComponent guessBookPanel = new GuessBookPanel();

    private CustomComponent statisticPanel = new StatisticPanel();

    private CustomComponent employeeManagerPanel = new EmployeeManagerPanel();


    protected void init(VaadinRequest request) {
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
        setContent(tabsheet);
    }

    public void addEvents() {
    }
}
