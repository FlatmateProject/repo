package fasade;

import hibernate.WordEntity;

import java.util.Set;

import service.AddElementService;
import exception.ApplicationException;

public class DictionaryBeanFacade implements DictionaryEndPoint {

	@Override
	public boolean addElement(String wordName, Set<String> translations, Set<String> examples) throws ApplicationException {
		
		AddElementService service = FacadeUtil.getService(AddElementService.class);
		
		service.setWordName(wordName);
		service.setTranslations(translations);
		service.setExamples(examples);
		WordEntity result = FacadeUtil.executeService(service);
		
		return result.getWordName().equals(wordName);
	}

}
