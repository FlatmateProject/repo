package spring;

import dao.CantorDao;
import dao.StatisticDao;
import gui.CantorPanel;
import gui.Gui;
import gui.ManagerPanel;
import gui.guestBook.ClientPanel;
import gui.guestBook.GuestBookPanel;
import gui.statistic.StatisticPanel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.UpdateService;
import service.guessBook.GuestBook;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static gui.guestBook.CompanySpecification.companySpecification;
import static gui.guestBook.CustomerSpecification.customerSpecification;

@Configuration
@Import(ApplicationConfiguration.class)
public class WebApplicationConfiguration extends ApplicationConfiguration {

    private Calendar calendar = GregorianCalendar.getInstance();

    private StatisticPanel statisticPanel() {
        StatisticDao statisticDao = statisticDao();
        StatisticPanel statisticPanel = new StatisticPanel(statisticDao);
        statisticPanel.setStatistic(statistic(statisticDao));
        return statisticPanel;
    }

    private CantorPanel cantorPanel() {
        CantorDao cantorDao = cantorDao();
        CantorPanel cantorPanel = new CantorPanel(cantorDao, cantorTableCreator(cantorDao));
        cantorPanel.setCantor(cantorMoneyExchanger(cantorDao));
        return cantorPanel;
    }

    private ManagerPanel managerPanel() {
        ManagerPanel managerPanel = new ManagerPanel(manager(), calendar());
        managerPanel.setGuestBook(guestBook());
        managerPanel.setAddService(addService());
        managerPanel.setUpdateService(updateService());
        managerPanel.setDeleteService(deleteService());
        return managerPanel;
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
    public Calendar calendar() {
        return calendar;
    }

    @Bean
    public Gui gui() {
        Gui gui = new Gui();
        gui.setCantorPanel(cantorPanel());
        gui.setManagerPanel(managerPanel());
        gui.setGuessBookPanel(guestBookPanel());
        gui.setStatisticPanel(statisticPanel());
        return gui;
    }
}
