package facade;



import java.util.Set;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import model.dictionary.WordEntity;


import service.AddExamplesToWordService;
import service.AddTranslationsToWordService;
import service.AddWordService;
import service.FindWordService;
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
		return ResponseFactory.createWordResponse(result);
	}
	
	public WordResponse findWord(String wordName) throws ApplicationException {
		FindWordService service = FacadeUtil.getService(FindWordService.class);
		service.setWordName(wordName);
		WordEntity result = FacadeUtil.executeService(service);
		return ResponseFactory.createWordResponse(result);
	}

	@Override
	public WordResponse addTranslations(String wordName, Set<String> translations) throws ApplicationException {
		AddTranslationsToWordService service = FacadeUtil.getService(AddTranslationsToWordService.class);
		service.setWordName(wordName);
		service.setTranslations(translations);
		WordEntity result = FacadeUtil.executeService(service);
		return ResponseFactory.createWordResponse(result);
	}

	@Override
	public WordResponse addExamples(String wordName, Set<String> examples) throws ApplicationException {
		AddExamplesToWordService service = FacadeUtil.getService(AddExamplesToWordService.class);
		service.setWordName(wordName);
		service.setExamples(examples);
		WordEntity result = FacadeUtil.executeService(service);
		return ResponseFactory.createWordResponse(result);
	}
	

	
}
