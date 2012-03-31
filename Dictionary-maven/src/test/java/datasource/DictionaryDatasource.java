package datasource;

import model.dictionary.ExampleEntity;
import model.dictionary.TranslationEntity;
import model.dictionary.WordEntity;
import exception.ServiceException;

public interface DictionaryDatasource {

	public WordEntity createWord() throws ServiceException;
	
	public TranslationEntity createTranslation(WordEntity word) throws ServiceException;
	
	public ExampleEntity createExample(WordEntity word) throws ServiceException;
}
