package converter;

import java.sql.SQLException;

public interface TypeConverter<T> {

    T convert(int index) throws SQLException;

}
