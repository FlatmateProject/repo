package datasource;

import hibernate.WordEntity;
import exception.DatasourceException;

public interface DictionaryDatasource {

	public WordEntity createWord() throws DatasourceException;
}
