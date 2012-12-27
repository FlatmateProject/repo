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
@WebService(name = "ClientEndPoint")  
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL) 
public interface ClientEndPoint {

	@WebMethod(action="getRandomWords")
	@WebResult(name="result")
	Set<WordResponse> getRandomWords(
		@WebParam(name="limit") int limit)
	throws ApplicationException;	
}
