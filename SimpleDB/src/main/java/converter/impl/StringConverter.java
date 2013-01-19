package converter.impl;

import converter.TypeConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringConverter implements TypeConverter<String> {

    private ResultSet resultSet;

    public StringConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public String convert(int index) throws SQLException {
        return resultSet.getString(index);
    }
}
