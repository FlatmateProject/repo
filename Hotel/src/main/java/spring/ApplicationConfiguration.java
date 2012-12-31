package spring;

import dao.CantorDao;
import dao.CantorDaoImpl;
import dao.StatisticDao;
import dao.StatisticDaoImpl;
import gui.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import service.statictic.Statistic;

@Configuration
public class ApplicationConfiguration {

    private StatisticDao statisticDao() {
        return new StatisticDaoImpl();
    }

    private Statistic statistic(StatisticDao statisticDao) {
        Statistic statistic = new Statistic();
        statistic.setStatisticDao(statisticDao);
        return statistic;
    }

    private StatisticPanel statisticPanel() {
        StatisticDao statisticDao = statisticDao();
        StatisticPanel statisticPanel = new StatisticPanel(statisticDao);
        statisticPanel.setStatistic(statistic(statisticDao));
        return statisticPanel;
    }

    private CantorDao cantorDao() {
        return new CantorDaoImpl();
    }

    private CantorMoneyExchanger cantorMoneyExchanger(CantorDao cantorDao) {
        return new CantorMoneyExchanger(cantorDao);
    }

    private CantorTableCreator cantorTableCreator(CantorDao cantorDao) {
        return new CantorTableCreator(cantorDao);
    }

    private CantorPanel cantorPanel() {
        CantorDao cantorDao = cantorDao();
        CantorPanel cantorPanel = new CantorPanel(cantorDao, cantorTableCreator(cantorDao));
        cantorPanel.setCantor(cantorMoneyExchanger(cantorDao));
        return cantorPanel;
    }

    private Schedule schedule() {
        return new Schedule();
    }

    private SchedulerPanel schedulerPanel() {
        return new SchedulerPanel(schedule());
    }

    private Reception reception() {
        return new Reception();
    }

    private ReceptionPanel receptionPanel() {
        return new ReceptionPanel(reception());
    }

    private Reservation reservation() {
        return new Reservation();
    }

    private ReservationPanel reservationPanel() {
        return new ReservationPanel(reservation());
    }

    private Manager manager() {
        return new Manager();
    }

    private ManagerPanel managerPanel() {
        ManagerPanel managerPanel = new ManagerPanel(manager());
        managerPanel.setGuestBook(guestBook());
        return managerPanel;
    }

    private GuestBook guestBook() {
        return new GuestBook();
    }

    private GuestBookPanel guestBookPanel() {
        return new GuestBookPanel(guestBook());
    }

    private EmployeeManager employeeManager() {
        return new EmployeeManager();
    }

    private EmployeeManagerPanel employeeManagerPanel() {
        return new EmployeeManagerPanel(employeeManager());
    }

    @Bean
    public Gui gui() {
        Gui gui = new Gui();
        gui.setCantorPanel(cantorPanel());
        gui.setSchedulerPanel(schedulerPanel());
        gui.setReceptionPanel(receptionPanel());
        gui.setReservationPanel(reservationPanel());
        gui.setManagerPanel(managerPanel());
        gui.setGuessBookPanel(guestBookPanel());
        gui.setStatisticPanel(statisticPanel());
        gui.setEmployeeManagerPanel(employeeManagerPanel());
        gui.construct();
        return gui;
    }
}
