package dao;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
	
	public <T> List<T> transform(ResultSet dataSet, Class<T> resultClass) {
		try {
			List<T> transformedResult = new ArrayList<T>();
			if (dataSet != null) {
				while (dataSet.next()){
					T object = createObject(resultClass);
					object= fillObject(dataSet, createObject(resultClass), resultClass);
					transformedResult.add(resultClass.cast(object));
				}
			}
			return transformedResult;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	private <T> T createObject(Class<T> resultClass) throws InstantiationException, IllegalAccessException {
		return resultClass.newInstance();
	}

	private <T> T fillObject(ResultSet dataSet, T object, Class<T> resultClass) throws IllegalArgumentException, IllegalAccessException, SQLException {
		int i = 1;
		for (Field field : resultClass.getDeclaredFields()) {
			field.setAccessible(true);
			field.set(object, retriveObject(dataSet, i, field.getType()));
			i++;
			log.info(field.getName() + ": " + field.toString());
		}
		return object;
	}

	private  Object retriveObject(ResultSet dataSet, int i, Class<?> type) throws SQLException {
		if(type.equals(long.class)){
			return dataSet.getLong(i);
		}
		if(type.equals(GregorianCalendar.class)){
			Date date = dataSet.getDate(i);
			long millis = date.getTime();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(millis);
			return calendar;
		}
		return dataSet.getObject(i);
	}
}
