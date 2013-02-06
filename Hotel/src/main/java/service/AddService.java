package service;


import dao.ServiceDao;
import exception.DAOException;
import service.dictionary.TABLE;

public class AddService {

    private final ServiceDao serviceDao;

    public AddService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public Boolean insertData(TABLE table, String labels[], String data[]) {
        try {
            return serviceDao.insertData(table, labels, data);
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
