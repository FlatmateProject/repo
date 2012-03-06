package facade;

import hibernate.WordEntity;


import java.util.Set;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import service.AddElementService;
import exception.ApplicationException;

@Stateless
@WebService(name="Dictionary", endpointInterface = "facade.DictionaryEndPoint", serviceName="DictionaryEndPoint")
@SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class DictionaryBeanFacade implements DictionaryEndPoint {
	
	@WebMethod()
	public boolean addElement(String wordName, Set<String> translations, Set<String> examples) throws ApplicationException {
		
		AddElementService service = FacadeUtil.getService(AddElementService.class);
		
		service.setWordName(wordName);
		service.setTranslations(translations);
		service.setExamples(examples);
		WordEntity result = FacadeUtil.executeService(service);
		
		return result.getWordName().equals(wordName);
	}

}
