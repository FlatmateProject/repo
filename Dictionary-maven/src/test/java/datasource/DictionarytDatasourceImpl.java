package datasource;

import hibernate.TranslationEntity;
import hibernate.WordEntity;
import java.util.Set;

import service.AddElementService;
import exception.DatasourceException;

public class DictionarytDatasourceImpl extends AbstractDatasource implements DictionaryDatasource {
	
	@Override
	public WordEntity createWord() throws DatasourceException {
		
		String wordName = generator.randomWord();
		Set<String> translations = generator.randomTranslations();
		Set<String> examples = generator.randomExamples();
		
		AddElementService service = getService(AddElementService.class);
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
	
	
}
