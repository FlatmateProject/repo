package dao;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class AbstractDao {
	
	private static final Logger log = Logger.getLogger(AbstractDao.class);
	
	private Singleton session = Singleton.getInstance();

	public Singleton getSession() {
		return session;
	}

	public void setSession(Singleton session) {
		this.session = session;
	}

	protected Object uniqueResult(String query) {
		try {
			ResultSet resultSet = getSession().query(query);
			return resultSet.next() ? resultSet.getObject(1) : 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return new Object();
		}
	}
	
	protected <T> List<T> exacuteQuery(String query, Class<T> dtoClass) {
		ResultSet resultSet = getSession().query(query);
		return transform(resultSet, dtoClass);
	}
	
	private <T> List<T> transform(ResultSet dataSet, Class<T> resultClass) {
		ArrayList<T> EMPTY_LIST = new ArrayList<T>();
		try {
			if (dataSet != null) {
				return createTransformedResult(dataSet, resultClass);
			}
			return EMPTY_LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return EMPTY_LIST;
		}
	}

	private <T> List<T> createTransformedResult(ResultSet dataSet,	Class<T> objectClass) throws Exception {
		List<T> transformedResult = new ArrayList<T>();
		while (dataSet.next()) {
			T object = createObject(objectClass);
			fillObject(object, objectClass.getDeclaredFields(), dataSet);
			transformedResult.add(objectClass.cast(object));
		}
		return transformedResult;
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
