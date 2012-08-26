package dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public abstract class AbstractDao {

	private Singleton session = Singleton.getInstance();

	public Singleton getSession() {
		return session;
	}

	public void setSession(Singleton session) {
		this.session = session;
	}

	protected <T> List<T> transform(ResultSet dataSet, Class<T> resultClass) {
		try {
			List<T> transformedResult = Collections.emptyList();
			if (dataSet != null) {
				while (dataSet.next()){
					T object = createAndFillObject(dataSet, resultClass.newInstance(), resultClass);
					transformedResult.add(resultClass.cast(object));
				}
			}
			return transformedResult;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	private <T> T createAndFillObject(ResultSet dataSet, T object, Class<T> resultClass) throws IllegalArgumentException, IllegalAccessException, SQLException {
		int i = 1;
		for (Field field : resultClass.getDeclaredFields()) {
			field.setAccessible(true);
			field.set(object, dataSet.getObject(i));
			i++;
			System.out.println(field.getName() + ": " + field.toString());
		}
		return object;
	}
}
