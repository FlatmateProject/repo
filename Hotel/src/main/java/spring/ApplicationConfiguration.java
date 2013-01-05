package spring;

import dao.CantorDao;
import dao.StatisticDao;
import dao.impl.CantorDaoImpl;
import dao.impl.Singleton;
import dao.impl.StatisticDaoImpl;
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
    public Singleton session() {
        return Singleton.getInstance();
    }

    @Bean
    public StatisticDao statisticDao() {
        StatisticDaoImpl statisticDao = new StatisticDaoImpl();
        statisticDao.setSession(session());
        return statisticDao;
    }

    @Bean
    public Statistic statistic() {
        return statistic(statisticDao());
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

    @Bean
    public CantorDao cantorDao() {
        CantorDaoImpl cantorDao = new CantorDaoImpl();
        cantorDao.setSession(session());
        return cantorDao;
    }

    @Bean
    public CantorMoneyExchanger cantorMoneyExchanger() {
        return cantorMoneyExchanger(cantorDao());
    }

    private CantorMoneyExchanger cantorMoneyExchanger(CantorDao cantorDao) {
        return new CantorMoneyExchanger(cantorDao);
    }

    @Bean
    public CantorTableCreator cantorTableCreator() {
        return cantorTableCreator(cantorDao());
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

    @Bean
    public Schedule schedule() {
        Schedule schedule = new Schedule();
        schedule.setSing(session());
        return schedule;
    }

    private SchedulerPanel schedulerPanel() {
        return new SchedulerPanel(schedule());
    }

    @Bean
    public Reception reception() {
        Reception reception = new Reception();
        reception.setSing(session());
        return reception;
    }

    private ReceptionPanel receptionPanel() {
        return new ReceptionPanel(reception());
    }

    @Bean
    public Reservation reservation() {
        Reservation reservation = new Reservation();
        reservation.setSing(session());
        return reservation;
    }

    private ReservationPanel reservationPanel() {
        return new ReservationPanel(reservation());
    }

    @Bean
    public Manager manager() {
        Manager manager = new Manager();
        manager.setSing(session());
        return manager;
    }

    private ManagerPanel managerPanel() {
        ManagerPanel managerPanel = new ManagerPanel(manager());
        managerPanel.setGuestBook(guestBook());
        return managerPanel;
    }

    @Bean
    public GuestBook guestBook() {
        GuestBook guestBook = new GuestBook();
        guestBook.setSing(session());
        return guestBook;
    }

    private GuestBookPanel guestBookPanel() {
        GuestBookPanel guestBookPanel = new GuestBookPanel(guestBook());
        return guestBookPanel;
    }

    @Bean
    public EmployeeManager employeeManager() {
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.setDb(session());
        return employeeManager;
    }

    private EmployeeManagerPanel employeeManagerPanel() {
        EmployeeManagerPanel employeeManagerPanel = new EmployeeManagerPanel(employeeManager(), session());
        return employeeManagerPanel;
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
