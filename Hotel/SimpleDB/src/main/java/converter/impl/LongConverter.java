package converter.impl;

import converter.TypeConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LongConverter implements TypeConverter<Long> {

    private final ResultSet resultSet;

    public LongConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public Long convert(int index) throws SQLException {
        return resultSet.getLong(index);
    }
}
