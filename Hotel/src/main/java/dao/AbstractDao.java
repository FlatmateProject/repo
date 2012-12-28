package dao;

import exception.DAOException;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

abstract class AbstractDao {

    private static final Logger log = Logger.getLogger(AbstractDao.class);

    private Singleton session = Singleton.getInstance();

    private ResultSet resultSet;

    private final Map<Class, TypeConverter> conversionMap = new HashMap<Class, TypeConverter>();

    {
        conversionMap.put(long.class, new TypeConverter() {
            @Override
            public Object convert(int index) throws SQLException {
                return resultSet.getLong(index);
            }
        });

        conversionMap.put(float.class, new TypeConverter() {
            @Override
            public Object convert(int index) throws SQLException {
                return resultSet.getFloat(index);
            }
        });

        conversionMap.put(GregorianCalendar.class, new TypeConverter() {
            @Override
            public Object convert(int index) throws SQLException {
                Date date = resultSet.getDate(index);
                long millis = date.getTime();
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTimeInMillis(millis);
                return calendar;
            }
        });

        conversionMap.put(String.class, new TypeConverter() {
            @Override
            public Object convert(int index) throws SQLException {
                return resultSet.getString(index);
            }
        });
    }

    public Singleton getSession() {
        return session;
    }

    Object simpleResult(String query) throws DAOException {
        try {
            resultSet = session.query(query);
            return resultSet.next() ? resultSet.getObject(1) : 0;
        } catch (Exception e) {
            throw  new DAOException(e.getMessage());
        }
    }

    <T> T uniqueResult(String query, Class<T> dtoClass) throws DAOException {
        try {
            List<T> oneElementList = executeQuery(query, dtoClass);
            return oneElementList.get(0);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    <T> List<T> executeQuery(String query, Class<T> dtoClass) throws DAOException {
        resultSet = session.query(query);
        return transform(dtoClass);
    }

    private <T> List<T> transform(Class<T> resultClass) throws DAOException {
        ArrayList<T> EMPTY_LIST = new ArrayList<T>();
        try {
            if (isNotEmptyResult()) {
                return createTransformedRows(resultClass);
            }
            return EMPTY_LIST;
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }

    private boolean isNotEmptyResult() {
        return resultSet != null;
    }

    private <T> List<T> createTransformedRows(Class<T> objectClass) throws Exception {
        List<T> transformedRows = new ArrayList<T>();
        while (resultSet.next()) {
            T object = createObject(objectClass);
            fillObject(object, objectClass.getDeclaredFields());
            transformedRows.add(objectClass.cast(object));
        }
        return transformedRows;
    }

    private <T> T createObject(Class<T> resultClass) throws InstantiationException, IllegalAccessException {
        return resultClass.newInstance();
    }

    private <T> void fillObject(T object, Field[] fields) throws IllegalArgumentException, IllegalAccessException, SQLException {
        int index = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(object, getObjectByIndex(index, field.getType()));
            index++;
            log.info(field.getName() + ": " + field.toString());
        }
    }

    private Object getObjectByIndex(int index, Class<?> type) throws SQLException {
        TypeConverter typeConverter = getConverterForType(type);
        return typeConverter.convert(index);
    }

    private TypeConverter getConverterForType(Class<?> type) {
        TypeConverter typeConverter = conversionMap.get(type);
        if (typeConverter == null) {
            throw new RuntimeException("Nie ma implementcaji konwertera dla typu: " + type + " dodaj ja");
        }
        return typeConverter;
    }


    private interface TypeConverter<T> {
        T convert(int index) throws SQLException;
    }
}
