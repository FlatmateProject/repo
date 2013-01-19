package converter.impl;

import converter.TypeConverter;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class GregorianCalendarConverter implements TypeConverter<GregorianCalendar> {

    private ResultSet resultSet;

    public GregorianCalendarConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public GregorianCalendar convert(int index) throws SQLException {
        Date date = resultSet.getDate(index);
        long millis = date.getTime();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(millis);
        return calendar;
    }
}
