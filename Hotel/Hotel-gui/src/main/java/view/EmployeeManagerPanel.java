package view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.employeeManager.EmployeeManager;

import java.util.Calendar;

@Component
public class EmployeeManagerPanel extends TabComponent {

    @Autowired
    private EmployeeManager employeeManager;

    @Autowired
    private Calendar calendar;

    @Override
    public void create() {

    }

    @Override
    public void addEvents() {

    }
}
