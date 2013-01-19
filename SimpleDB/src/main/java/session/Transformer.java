package session;

import converter.TypeConverter;
import converter.impl.FloatConverter;
import converter.impl.GregorianCalendarConverter;
import converter.impl.LongConverter;
import converter.impl.StringConverter;
import exception.DAOException;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class Transformer {

    private static final Logger log = Logger.getLogger(Transformer.class);

    private ResultSet resultSet;

    private final Map<Class, TypeConverter> conversionMap = new HashMap<Class, TypeConverter>();


    {
        conversionMap.put(long.class, new LongConverter(resultSet));
        conversionMap.put(float.class, new FloatConverter(resultSet));
        conversionMap.put(GregorianCalendar.class, new GregorianCalendarConverter(resultSet));
        conversionMap.put(String.class, new StringConverter(resultSet));
    }

    private Transformer(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public static Transformer on(ResultSet resultSet) {
        return new Transformer(resultSet);
    }

    public <T> T transformTo(Class<T> dtoClass) throws DAOException {
        try {
            List<T> oneElementList = transformToListOf(dtoClass);
            return oneElementList.get(0);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public <T> List<T> transformToListOf(Class<T> resultClass) throws DAOException {
            ArrayList<T> EMPTY_LIST = new ArrayList<T>();
            try {
                if (isNotEmptyResult()) {
                    return createTransformedRows(resultClass);
                }
                return EMPTY_LIST;
            } catch (Exception e) {
                throw new DAOException(e);
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
}