package spring;

import dao.*;
import dao.impl.*;
import exception.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import repository.reservation.ReservationRepository;
import service.AddService;
import service.DeleteService;
import service.UpdateService;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import service.employeeManager.EmployeeManager;
import service.manager.Manager;
import service.reception.Reception;
import service.reservation.Reservation;
import service.schedule.Schedule;
import service.statictic.Statistic;
import session.SimpleDataSource;
import session.SimpleSession;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Configuration
@ImportResource("classpath:/properties-core-config.xml")
@EnableJpaRepositories({"repository", "entity"})
@ComponentScan(basePackages = {"service.guessBook"})
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
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(host + database);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setPackagesToScan("repository", "entity");
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = entityManagerFactory(dataSource(), jpaVendorAdapter());
        return jpaTransactionManager;
    }

    @Bean
    public SimpleDataSource simpleDataSource() {
        SimpleDataSource simpleDataSource = new SimpleDataSource();
        simpleDataSource.setDriver(driver);
        simpleDataSource.setHost(host);
        simpleDataSource.setDatabase(database);
        simpleDataSource.setUser(user);
        simpleDataSource.setPassword(password);
        return simpleDataSource;
    }

    @Bean
    public SimpleSession session() {
        return new SimpleSession(simpleDataSource());
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
        Manager manager = new Manager(managerDao());
        manager.setCalendar(calendar());
        return manager;
    }

    @Bean
    public GuestBookDao guestBookDao() {
        return new GuestBookDaoImpl(session());
    }

    @Bean
    public Calendar calendar() {
        return calendar;
    }

    @Bean
    public Schedule schedule() {
        return new Schedule();
    }

    @Bean
    public Reception reception() {
        return new Reception();
    }

    @Bean
    public Reservation reservation() {
        return new Reservation();
    }

    @Bean
    public EmployeeManager employeeManager() {
        return new EmployeeManager();
    }

    public static void main(String[] args) throws DAOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        PlatformTransactionManager platformTransactionManager = context.getBean(PlatformTransactionManager.class);
        ReservationRepository reservationRepository = context.getBean(ReservationRepository.class);
        Logger.getRootLogger().info("siema: " + platformTransactionManager);
        Logger.getRootLogger().info("siema: " + reservationRepository);
        Logger.getRootLogger().info("siema: " + reservationRepository.findByPeselId(123456L));
        context.close();
    }
}
