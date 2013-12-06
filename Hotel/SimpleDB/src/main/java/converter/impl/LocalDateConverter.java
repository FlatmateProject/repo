package converter.impl;

import converter.TypeConverter;
import org.joda.time.LocalDate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalDateConverter implements TypeConverter<LocalDate> {

    private final ResultSet resultSet;

    public LocalDateConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public LocalDate convert(int index) throws SQLException {
        Date date = resultSet.getDate(index);
        return new LocalDate(date);
    }
}
