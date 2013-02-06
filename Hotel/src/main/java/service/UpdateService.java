package service;


import dao.ServiceDao;
import exception.DAOException;
import exception.IncorrectDataException;
import service.dictionary.TABLE;

public class UpdateService {

    private final ServiceDao serviceDao;

    public UpdateService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public void updateClientData(TABLE table, String labels[], String data[]) throws IncorrectDataException {
        try {
            serviceDao.updateClientData(table, labels, data);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IncorrectDataException();
        }
    }
}
