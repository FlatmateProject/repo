package spring;

import dao.GuestBookDao;
import dao.ManagerDao;
import dao.ServiceDao;
import dao.StatisticDao;
import dao.impl.GuestBookDaoImpl;
import dao.impl.ManagerDaoImpl;
import dao.impl.ServiceDaoImpl;
import dao.impl.StatisticDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import session.SimpleDataSource;
import session.SimpleSession;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Configuration
@ImportResource("classpath:/properties-core-config.xml")
@EnableJpaRepositories({"repository", "entity"})
@ComponentScan(basePackages = {"service"})
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
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory(dataSource(), jpaVendorAdapter()).getObject());
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
    public StatisticDao statisticDao() {
        return new StatisticDaoImpl(session());
    }

    @Bean
    public ManagerDao managerDao() {
        return new ManagerDaoImpl(session());
    }

    @Bean
    public GuestBookDao guestBookDao() {
        return new GuestBookDaoImpl(session());
    }

    @Bean
    public Calendar calendar() {
        return calendar;
    }
}
