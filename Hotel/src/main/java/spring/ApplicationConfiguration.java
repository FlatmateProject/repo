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

    @Bean
    public StatisticDao statisticDao() {
        return new StatisticDaoImpl();
    }

    @Bean
    public Statistic statistic() {
        Statistic statistic = new Statistic();
        statistic.setStatisticDao(statisticDao());
        return statistic;
    }

    @Bean
    public StatisticPanel statisticPanel() {
        StatisticPanel statisticPanel = new StatisticPanel(statisticDao());
        statisticPanel.setStatistic(statistic());
        return statisticPanel;
    }

    @Bean
    public CantorDao cantorDao() {
        return new CantorDaoImpl();
    }

    @Bean
    public CantorMoneyExchanger cantorMoneyExchanger() {
        return new CantorMoneyExchanger(cantorDao());
    }

    @Bean
    public CantorTableCreator cantorTableCreator() {
        return new CantorTableCreator(cantorDao());
    }

    @Bean
    public CantorPanel cantorPanel() {
        CantorPanel cantorPanel = new CantorPanel(cantorDao(), cantorTableCreator());
        cantorPanel.setCantor(cantorMoneyExchanger());
        return cantorPanel;
    }

    @Bean
    public Schedule schedule() {
        return new Schedule();
    }

    @Bean
    public SchedulerPanel schedulerPanel() {
        return new SchedulerPanel(schedule());
    }

    @Bean
    public Reception reception() {
        return new Reception();
    }

    @Bean
    public ReceptionPanel receptionPanel() {
        return new ReceptionPanel(reception());
    }

    @Bean
    public Reservation reservation() {
        return new Reservation();
    }

    @Bean
    public ReservationPanel reservationPanel() {
        return new ReservationPanel(reservation());
    }

    @Bean
    public Manager manager() {
        return new Manager();
    }

    @Bean
    public ManagerPanel managerPanel() {
        ManagerPanel managerPanel = new ManagerPanel(manager());
        managerPanel.setGuestBook(guestBook());
        return managerPanel;
    }

    @Bean
    public GuestBook guestBook() {
        return new GuestBook();
    }

    @Bean
    public GuestBookPanel guestBookPanel() {
        return new GuestBookPanel(guestBook());
    }

    @Bean
    public EmployeeManager employeeManager() {
        return new EmployeeManager();
    }

    @Bean
    public EmployeeManagerPanel employeeManagerPanel() {
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
