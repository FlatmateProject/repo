package service;

import dao.ServiceDao;
import dictionary.TABLE;
import exception.DAOException;
import exception.IncorrectDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddService {

    @Autowired
    private ServiceDao serviceDao;

    public void insertData(TABLE table, String labels[], String data[]) throws IncorrectDataException {
        try {
            serviceDao.insertData(table, labels, data);
        } catch (DAOException e) {
            throw new IncorrectDataException(e);
        }
    }
}
