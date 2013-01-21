package spring;

import dao.CantorDao;
import dao.GuestBookDao;
import dao.StatisticDao;
import dao.impl.CantorDaoImpl;
import dao.impl.GuestBookDaoImpl;
import dao.impl.Singleton;
import dao.impl.StatisticDaoImpl;
import gui.*;
import gui.guestBook.GuestBookPanel;
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

    //    @Value("#{driver}")
    private String driver = "com.mysql.jdbc.Driver";

    //    @Value("#{host}")
    private String host = "jdbc:mysql://localhost:3306/";

    //    @Value("#{database}")
    private String database = "hotel";

    //    @Value("#{username}")
    private String user = "hotel";

    //    @Value("#{password}")
    private String password = "hotel_dupe";

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriver(driver);
        dataSource.setHost(host);
        dataSource.setDatabase(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
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
        return new StatisticDaoImpl(session());
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
        return new CantorDaoImpl(session());
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
        return new GuestBook(guestBookDao());
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
//        gui.setCantorPanel(cantorPanel());
//        gui.setSchedulerPanel(schedulerPanel());
//        gui.setReceptionPanel(receptionPanel());
//        gui.setReservationPanel(reservationPanel());
//        gui.setManagerPanel(managerPanel());
        gui.setGuessBookPanel(guestBookPanel());
//        gui.setStatisticPanel(statisticPanel());
//        gui.setEmployeeManagerPanel(employeeManagerPanel());
        return gui;
    }
}
