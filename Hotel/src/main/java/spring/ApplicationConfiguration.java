package spring;

import dao.CantorDao;
import dao.GuestBookDao;
import dao.StatisticDao;
import dao.impl.CantorDaoImpl;
import dao.impl.GuestBookDaoImpl;
import dao.impl.Singleton;
import dao.impl.StatisticDaoImpl;
import gui.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import service.statictic.Statistic;
import session.DataSource;
import session.SimpleSession;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setHost("jdbc:mysql://localhost:3306/");
        dataSource.setDatabase("hotel");
        dataSource.setUser("hotel");
        dataSource.setPassword("hotel_dupe");
        return dataSource;
    }

    @Bean
    public SimpleSession session() {
        return new SimpleSession(dataSource());
    }

    @Bean
    public Singleton singleton() {
        return Singleton.getInstance();
    }

    @Bean
    public StatisticDao statisticDao() {
        StatisticDaoImpl statisticDao = new StatisticDaoImpl(session());
        statisticDao.setSession(singleton());
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
        CantorDaoImpl cantorDao = new CantorDaoImpl(session());
        cantorDao.setSession(singleton());
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
        schedule.setSing(singleton());
        return schedule;
    }

    private SchedulerPanel schedulerPanel() {
        return new SchedulerPanel(schedule());
    }

    @Bean
    public Reception reception() {
        Reception reception = new Reception();
        reception.setSing(singleton());
        return reception;
    }

    private ReceptionPanel receptionPanel() {
        return new ReceptionPanel(reception());
    }

    @Bean
    public Reservation reservation() {
        Reservation reservation = new Reservation();
        reservation.setSing(singleton());
        return reservation;
    }

    private ReservationPanel reservationPanel() {
        return new ReservationPanel(reservation());
    }

    @Bean
    public Manager manager() {
        Manager manager = new Manager();
        manager.setSing(singleton());
        return manager;
    }

    private ManagerPanel managerPanel() {
        ManagerPanel managerPanel = new ManagerPanel(manager());
        managerPanel.setGuestBook(guestBook());
        return managerPanel;
    }

    @Bean
    public GuestBookDao guestBookDao() {
        return new GuestBookDaoImpl(session());
    }

    @Bean
    public GuestBook guestBook() {
        GuestBook guestBook = new GuestBook(guestBookDao());
        guestBook.setSing(singleton());
        return guestBook;
    }

    private GuestBookPanel guestBookPanel() {
        GuestBookPanel guestBookPanel = new GuestBookPanel(guestBook());
        return guestBookPanel;
    }

    @Bean
    public EmployeeManager employeeManager() {
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.setDb(singleton());
        return employeeManager;
    }

    private EmployeeManagerPanel employeeManagerPanel() {
        EmployeeManagerPanel employeeManagerPanel = new EmployeeManagerPanel(employeeManager(), singleton());
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
        return gui;
    }
}
