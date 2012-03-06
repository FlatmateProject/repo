package facade;

import java.util.Set;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import exception.ApplicationException;

@Remote
@WebService(name = "DictionaryEndPoint")  
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL) 
public interface DictionaryEndPoint {

	@WebMethod(action="addElement")
	boolean addElement(
		@WebParam(name="wordName")String wordName,
		@WebParam(name="translations")Set<String> translations,
		@WebParam(name="examples")Set<String> examples) throws ApplicationException;
}
