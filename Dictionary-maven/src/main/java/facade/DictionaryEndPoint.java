package facade;

import exception.ApplicationException;
import facade.response.WordResponse;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Set;

@Remote
@WebService(name = "DictionaryEndPoint")  
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL) 
public interface DictionaryEndPoint {

	@WebMethod(action="addElement")
	@WebResult(name="result")
	WordResponse addWord(
		@WebParam(name="wordName") String wordName,
		@WebParam(name="translations") Set<String> translations,
		@WebParam(name="examples") Set<String> examples) 
	throws ApplicationException;
	
	@WebMethod(action="findElement")
	@WebResult(name="result")
	WordResponse findWord(
		@WebParam(name="wordName") String wordName) 
	throws ApplicationException;
	
	@WebMethod(action="addTranslations")
	@WebResult(name="result")
	WordResponse addTranslations(
		@WebParam(name="wordName") String wordName,
		@WebParam(name="translations") Set<String> translations)
	throws ApplicationException;
	
	@WebMethod(action="addExamples")
	@WebResult(name="result")
	WordResponse addExamples(
		@WebParam(name="wordName") String wordName,
		@WebParam(name="examples") Set<String> examples)
	throws ApplicationException;
	
}
