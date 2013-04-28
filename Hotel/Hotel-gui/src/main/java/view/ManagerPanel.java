package view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.AddService;
import service.DeleteService;
import service.UpdateService;
import service.guessBook.GuestBook;
import service.manager.Manager;

import java.util.Calendar;

@Component
public class ManagerPanel extends TabComponent {

    @Autowired
    private Calendar calendar;

    @Autowired
    private Manager manager;

    @Autowired
    private GuestBook guestBook;

    @Autowired
    private AddService addService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @Override
    public void create() {
    }

    @Override
    public void addEvents() {
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setGuestBook(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    public void setAddService(AddService addService) {
        this.addService = addService;
    }

    public void setUpdateService(UpdateService updateService) {
        this.updateService = updateService;
    }

    public void setDeleteService(DeleteService deleteService) {
        this.deleteService = deleteService;
    }
}
