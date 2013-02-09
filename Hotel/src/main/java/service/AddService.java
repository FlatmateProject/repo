package service;


import dao.ServiceDao;
import exception.DAOException;
import exception.IncorrectDataException;
import service.dictionary.TABLE;

public class AddService {

    private final ServiceDao serviceDao;

    public AddService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public void insertData(TABLE table, String labels[], String data[]) throws IncorrectDataException {
        try {
            serviceDao.insertData(table, labels, data);
        } catch (DAOException e) {
            throw new IncorrectDataException(e);
        }
    }
}
