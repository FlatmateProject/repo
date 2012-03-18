package datasource;

import hibernate.ExampleEntity;
import hibernate.TranslationEntity;
import hibernate.WordEntity;
import exception.ServiceException;

public interface DictionaryDatasource {

	public WordEntity createWord() throws ServiceException;
	
	public TranslationEntity createTranslation(WordEntity word) throws ServiceException;
	
	public ExampleEntity createExample(WordEntity word) throws ServiceException;
}
