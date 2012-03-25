package datasource;



import java.util.Set;

import model.ExampleEntity;
import model.TranslationEntity;
import model.WordEntity;

import service.AddWordService;
import exception.ServiceException;

public class DictionarytDatasourceImpl extends AbstractDatasource implements DictionaryDatasource {
	
	@Override
	public WordEntity createWord() throws ServiceException {
		AddWordService service = getService(AddWordService.class);
		
		String wordName = generator.randomWord();
		Set<String> translations = generator.randomTranslations();
		Set<String> examples = generator.randomExamples();
		service.setWordName(wordName);
		service.setTranslations(translations);
		service.setExamples(examples);
		
		return execute(service);
	}

	@Override
	public TranslationEntity createTranslation(WordEntity word) throws ServiceException {
		return TranslationEntity.Factory.createTranslation(generator.randomWord(), word); 
	}

	@Override
	public ExampleEntity createExample(WordEntity word)	throws ServiceException {
		return ExampleEntity.Factory.createExample(generator.randomWord(), word);
	}
	
}
