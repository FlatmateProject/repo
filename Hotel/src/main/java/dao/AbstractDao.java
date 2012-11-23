package dao;

import exception.DAOException;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

 abstract class AbstractDao {
	
	private static final Logger log = Logger.getLogger(AbstractDao.class);
	
	Singleton session = Singleton.getInstance();

	Object uniqueResult(String query) throws DAOException {
		try {
			ResultSet resultSet = session.query(query);
			return resultSet.next() ? resultSet.getObject(1) : 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return new Object();
		}
	}

     <T> T  uniqueResult(String query, Class<T> dtoClass) throws DAOException {
         return executeQuery(query, dtoClass).get(0);
     }

     <T> List<T> executeQuery(String query, Class<T> dtoClass) throws DAOException {
		ResultSet resultSet = session.query(query);
		return transform(resultSet, dtoClass);
	}

	private <T> List<T> transform(ResultSet dataSet, Class<T> resultClass) {
		ArrayList<T> EMPTY_LIST = new ArrayList<T>();
		try {
			if (dataSet != null) {
				return createTransformedRows(dataSet, resultClass);
			}
			return EMPTY_LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return EMPTY_LIST;
		}
	}

	private <T> List<T> createTransformedRows(ResultSet dataSet, Class<T> objectClass) throws Exception {
		List<T> transformedRows = new ArrayList<T>();
		while (dataSet.next()) {
			T object = createObject(objectClass);
			fillObject(object, objectClass.getDeclaredFields(), dataSet);
			transformedRows.add(objectClass.cast(object));
		}
		return transformedRows;
	}

	private <T> T createObject(Class<T> resultClass) throws InstantiationException, IllegalAccessException {
		return resultClass.newInstance();
	}

	private <T> void fillObject(T object, Field[] fields, ResultSet dataSet) throws IllegalArgumentException, IllegalAccessException, SQLException {
		int index = 1;
		for (Field field : fields) {
			field.setAccessible(true);
			field.set(object, getObjectByIndex(dataSet, index, field.getType()));
			index++;
			log.info(field.getName() + ": " + field.toString());
		}
	}

	private  Object getObjectByIndex(ResultSet dataSet, int index, Class<?> type) throws SQLException {
		if(type.equals(long.class)){
			return dataSet.getLong(index);
		}
		if(type.equals(GregorianCalendar.class)){
			Date date = dataSet.getDate(index);
			long millis = date.getTime();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(millis);
			return calendar;
		}
		return dataSet.getObject(index);
	}
}
