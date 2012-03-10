package datasource;

import hibernate.ExampleEntity;
import hibernate.TranslationEntity;
import hibernate.WordEntity;

import java.util.Set;

import service.AddWordService;
import exception.DatasourceException;

public class DictionarytDatasourceImpl extends AbstractDatasource implements DictionaryDatasource {
	
	@Override
	public WordEntity createWord() throws DatasourceException {
		AddWordService service = getService(AddWordService.class);
		
		String wordName = generator.randomWord();
		Set<String> translations = generator.randomTranslations();
		Set<String> examples = generator.randomExamples();
		
		restrictionIsNotNull(service, "Nie udało się utworzyć usługi");
		
		service.setWordName(wordName);
		service.setTranslations(translations);
		service.setExamples(examples);
		
		return execute(service);
	}

	@Override
	public TranslationEntity createTranslation(WordEntity word) throws DatasourceException {
		return TranslationEntity.Factory.createTranslation(generator.randomWord(), word); 
	}

	@Override
	public ExampleEntity createExample(WordEntity word)	throws DatasourceException {
		return ExampleEntity.Factory.createExample(generator.randomWord(), word);
	}
	
}
