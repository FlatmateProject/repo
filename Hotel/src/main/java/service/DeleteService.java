package service;


import dao.ServiceDao;
import exception.DAOException;
import service.dictionary.TABLE;

public class DeleteService {

    private final ServiceDao serviceDao;

    public DeleteService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public Boolean deleteData(TABLE table, String labels, String data) {
        try {
            return serviceDao.deleteData(table, labels, data);
        } catch (DAOException e) {
            e.printStackTrace();
            return true;
        }
    }
}
