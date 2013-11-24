package service;

import dao.ServiceDao;
import dictionary.TABLE;
import exception.DAOException;
import exception.IncorrectDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteService {

    @Autowired
    private ServiceDao serviceDao;

    public void deleteData(TABLE table, String primaryKey, long id) throws IncorrectDataException {
        try {
            serviceDao.deleteData(table, primaryKey, id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IncorrectDataException();
        }
    }
}
