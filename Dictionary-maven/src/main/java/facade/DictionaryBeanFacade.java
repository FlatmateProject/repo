package facade;

import hibernate.WordEntity;

import java.util.Set;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import service.AddWordService;
import service.FindElementService;
import exception.ApplicationException;
import facade.response.ResponseFactory;
import facade.response.WordResponse;

@Stateless
@WebService(name="Dictionary", endpointInterface = "facade.DictionaryEndPoint", serviceName="DictionaryEndPoint")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class DictionaryBeanFacade implements DictionaryEndPoint {
	
	public WordResponse addWord(String wordName, Set<String> translations, Set<String> examples) throws ApplicationException {
		AddWordService service = FacadeUtil.getService(AddWordService.class);
		service.setWordName(wordName);
		service.setTranslations(translations);
		service.setExamples(examples);
		WordEntity result = FacadeUtil.executeService(service);
		return ResponseFactory.createWordItem(result);
	}
	
	public WordResponse findWord(String wordName) throws ApplicationException {
		FindElementService service = FacadeUtil.getService(FindElementService.class);
		service.setWordName(wordName);
		WordEntity result = FacadeUtil.executeService(service);
		return ResponseFactory.createWordItem(result);
	}
	

	
}
