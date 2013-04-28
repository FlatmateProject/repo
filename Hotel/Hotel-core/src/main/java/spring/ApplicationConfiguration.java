package spring;

import dao.*;
import dao.impl.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import service.AddService;
import service.DeleteService;
import service.UpdateService;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import service.employeeManager.EmployeeManager;
import service.guessBook.GuestBook;
import service.manager.Manager;
import service.reception.Reception;
import service.reservation.Reservation;
import service.schedule.Schedule;
import service.statictic.Statistic;
import session.DataSource;
import session.SimpleSession;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Configuration
@ImportResource("classpath:/properties-core-config.xml")
public class ApplicationConfiguration {

    @Value("${driver}")
    private String driver;

    @Value("${host}")
    private String host;

    @Value("${database}")
    private String database;

    @Value("${username}")
    private String user;

    @Value("${password}")
    private String password;

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

    public Statistic statistic(StatisticDao statisticDao) {
        Statistic statistic = new Statistic();
        statistic.setStatisticDao(statisticDao);
        return statistic;
    }

    @Bean
    public CantorDao cantorDao() {
        return new CantorDaoImpl(session());
    }

    @Bean
    public CantorMoneyExchanger cantorMoneyExchanger() {
        return cantorMoneyExchanger(cantorDao());
    }

    public CantorMoneyExchanger cantorMoneyExchanger(CantorDao cantorDao) {
        return new CantorMoneyExchanger(cantorDao);
    }

    @Bean
    public CantorTableCreator cantorTableCreator() {
        return cantorTableCreator(cantorDao());
    }

    public CantorTableCreator cantorTableCreator(CantorDao cantorDao) {
        return new CantorTableCreator(cantorDao);
    }

    @Bean
    public ManagerDao managerDao() {
        return new ManagerDaoImpl(session());
    }

    @Bean
    public Manager manager() {
        return new Manager(managerDao());
    }

    @Bean
    public GuestBookDao guestBookDao() {
        return new GuestBookDaoImpl(session());
    }

    @Bean
    public GuestBook guestBook() {
        return new GuestBook(guestBookDao());
    }


    @Bean
    public Calendar calendar() {
        return calendar;
    }

    @Bean
    public Schedule schedule() {
        Schedule schedule = new Schedule();
        return schedule;
    }

    @Bean
    public Reception reception() {
        Reception reception = new Reception();
        return reception;
    }

    @Bean
    public Reservation reservation() {
        Reservation reservation = new Reservation();
        return reservation;
    }

    @Bean
    public EmployeeManager employeeManager() {
        return new EmployeeManager();
    }
}
