package spring;

import dao.*;
import dao.impl.*;
import gui.*;
import gui.guestBook.ClientPanel;
import gui.guestBook.GuestBookPanel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import service.guessBook.GuestBook;
import service.manager.Manager;
import service.statictic.Statistic;
import session.DataSource;
import session.SimpleSession;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static gui.guestBook.CompanySpecification.companySpecification;
import static gui.guestBook.CustomerSpecification.customerSpecification;

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

    private Calendar calendar = GregorianCalendar.getInstance();

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
    public ServiceDao serviceDao() {
        return new ServiceDaoImpl(session());
    }

    @Bean
    public AddService addService() {
        return new AddService(serviceDao());
    }

    @Bean
    public UpdateService updateService() {
        return new UpdateService(serviceDao());
    }

    @Bean
    public DeleteService deleteService() {
        return new DeleteService(serviceDao());
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
        return new SchedulerPanel(calendar(), schedule());
    }

    @Bean
    public Reception reception() {
        Reception reception = new Reception();
        reception.setSingleton(singleton());
        return reception;
    }

    private ReceptionPanel receptionPanel() {
        return new ReceptionPanel(reception());
    }

    @Bean
    public Reservation reservation() {
        Reservation reservation = new Reservation();
        reservation.setSingleton(singleton());
        return reservation;
    }

    private ReservationPanel reservationPanel() {
        return new ReservationPanel(reservation());
    }

    @Bean
    public ManagerDao managerDao() {
        return new ManagerDaoImpl(session());
    }

    @Bean
    public Manager manager() {
        return new Manager(managerDao());
    }

    private ManagerPanel managerPanel() {
        ManagerPanel managerPanel = new ManagerPanel(manager(), calendar());
        managerPanel.setGuestBook(guestBook());
        managerPanel.setAddService(addService());
        managerPanel.setUpdateService(updateService());
        managerPanel.setDeleteService(deleteService());
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
        GuestBook guestBook = guestBook();
        UpdateService updateService = updateService();
        ClientPanel customerPanel = customerPanel(guestBook, updateService);
        ClientPanel companyPanel = companyPanel(guestBook, updateService);
        GuestBookPanel guestBookPanel = new GuestBookPanel(customerPanel, companyPanel);
        return guestBookPanel;
    }

    private ClientPanel customerPanel(GuestBook guestBook, UpdateService updateService) {
        return new ClientPanel(guestBook, updateService, customerSpecification());
    }

    private ClientPanel companyPanel(GuestBook guestBook, UpdateService updateService) {
        return new ClientPanel(guestBook, updateService, companySpecification());
    }

    @Bean
    public EmployeeManager employeeManager() {
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.setSingleton(singleton());
        employeeManager.setCalendar(calendar());
        return employeeManager;
    }

    private EmployeeManagerPanel employeeManagerPanel() {
        EmployeeManagerPanel employeeManagerPanel = new EmployeeManagerPanel(employeeManager(), singleton(), calendar());
        return employeeManagerPanel;
    }

    @Bean
    public Calendar calendar() {
        return calendar;
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
