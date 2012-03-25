package datasource;

import model.ExampleEntity;
import model.TranslationEntity;
import model.WordEntity;
import exception.ServiceException;

public interface DictionaryDatasource {

	public WordEntity createWord() throws ServiceException;
	
	public TranslationEntity createTranslation(WordEntity word) throws ServiceException;
	
	public ExampleEntity createExample(WordEntity word) throws ServiceException;
}
