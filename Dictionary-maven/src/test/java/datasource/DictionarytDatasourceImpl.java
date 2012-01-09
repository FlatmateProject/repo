package datasource;

import java.util.Set;
import service.AddElementService;
import hibernate.WordEntity;
import org.hibernate.Session;

import exception.DatasourceException;

public class DictionarytDatasourceImpl extends AbstractDatasource implements DictionaryDatasource {
	
	public DictionarytDatasourceImpl(Session session) {
		super(session);
	}
	
	@Override
	public WordEntity createWord() throws DatasourceException {
		
		String wordName;
		Set<String> translations = generator.randomTranslations();
		Set<String> examples = generator.randomExamples();
		
		AddElementService service = getService(AddElementService.class);
		
		wordName = generator.randomWord();

		service.setWord(wordName);
		service.setTranslations(translations);
		service.setExamples(examples);
		
		return execute(service);
	}
}
