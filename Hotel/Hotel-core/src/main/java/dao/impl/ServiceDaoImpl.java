package dao.impl;

import common.ArrayObtained;
import common.Condition;
import dao.ServiceDao;
import dictionary.TABLE;
import exception.DAOException;
import session.SimpleSession;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {

    private final SimpleSession session;

    private final EntityManager entityManager;

    public ServiceDaoImpl(SimpleSession session, EntityManager entityManager) {
        this.session = session;
        this.entityManager = entityManager;
    }

    @Override
    public void updateData(TABLE table, String conditions) throws DAOException {
        String query = "update hotel." + table + " set " + conditions;
        session.update(query);
    }

    @Override
    public boolean insertData(TABLE table, String[] labels, String[] data) throws DAOException {
        String query = "insert into hotel." + table + "(";
        int length = data.length;
        for (int i = 0; i < length; i++) {
            if (i == 0 && data[i].isEmpty()) {
                return false;
            } else if (!data[i].isEmpty()) {
                if (i != 0)
                    query += ", ";
                query += labels[i];
            }
        }
        query += ") values(";
        for (int i = 0; i < length; i++) {
            if (i == 0 && data[i].isEmpty()) {
                return false;
            } else if (!data[i].isEmpty()) {
                if (i != 0)
                    query += ", ";
                query += "\"" + data[i] + "\"";
            }
        }
        query += ");";
        session.update(query);
        return true;
    }

    @Override
    public void deleteData(TABLE table, String primaryKey, long id) throws DAOException {
        String query = "delete from hotel." + table + " where " + primaryKey + " = " + id;
        session.update(query);
    }

    @Override
    public List<? extends ArrayObtained> getDataFromTable(TABLE table) throws DAOException {
        return getDataFromTable(table, Collections.<Condition>emptyList());
    }

    @Override
    public List<? extends ArrayObtained> getDataFromTable(TABLE table, List<Condition> conditions) throws DAOException {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        for (Condition condition : conditions) {
//            queryBuilder.equal(condition.getProperty(), condition.getValue());
        }
        CriteriaQuery criteriaQuery = queryBuilder.createQuery();
        Root from = criteriaQuery.from(table.getTableDtoClass());
        return null;
    }

    @Override
    public int getCountForTable(String tableName) throws DAOException {
        String query = "select count(*) from " + tableName;
        Long count = (Long) session.simpleResult(query);
        return count != null ? count.intValue() : 0;
    }
}
