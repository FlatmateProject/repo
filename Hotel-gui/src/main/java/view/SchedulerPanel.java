package view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.schedule.Schedule;

import java.util.Calendar;

@Component
public class SchedulerPanel extends TabComponent {

    @Autowired
    private Schedule schedule;

    @Autowired
    private Calendar calendar;

    @Override
    public void create() {
    }

    @Override
    public void addEvents() {

    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
