package service;


import dao.ServiceDao;
import exception.DAOException;
import exception.IncorrectDataException;
import service.dictionary.TABLE;

public class DeleteService {

    private final ServiceDao serviceDao;

    public DeleteService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public void deleteData(TABLE table, String primaryKey, long id) throws IncorrectDataException {
        try {
            serviceDao.deleteData(table, primaryKey, id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IncorrectDataException();
        }
    }
}
