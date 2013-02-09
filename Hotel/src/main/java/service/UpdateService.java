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
            String conditions = createConditions(labels, data);
            serviceDao.updateData(table, conditions);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IncorrectDataException();
        }
    }

    private String createConditions(String[] labels, String[] data) {
        int count = 0;
        String conditions = "";
        for (int i = 1; i < data.length; i++) {
            if (!data[i].isEmpty()) {
                if (count > 0) {
                    conditions += ", ";
                }
                conditions += labels[i] + " = \"" + data[i] + "\"";
                count++;
            }
        }
        conditions += " where " + labels[0] + " = \"" + data[0] + "\"";
        return conditions;
    }
}
