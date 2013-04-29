package converter.impl;

import converter.TypeConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FloatConverter implements TypeConverter<Float> {

    private ResultSet resultSet;

    public FloatConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public Float convert(int index) throws SQLException {
        return resultSet.getFloat(index);
    }
}

