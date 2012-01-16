package datasource;

import hibernate.TranslationEntity;
import hibernate.WordEntity;
import exception.DatasourceException;

public interface DictionaryDatasource {

	public WordEntity createWord() throws DatasourceException;
	
	public TranslationEntity createTranslation(WordEntity word) throws DatasourceException;
}
