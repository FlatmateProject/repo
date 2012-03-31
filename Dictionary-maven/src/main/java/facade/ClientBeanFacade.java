package facade;



import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import model.dictionary.WordEntity;


import service.GetRandomWordsService;
import exception.ApplicationException;
import facade.response.ResponseFactory;
import facade.response.WordResponse;

@Stateless
@WebService(name="Client", endpointInterface = "facade.ClientEndPoint", serviceName="ClientEndPoint")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class ClientBeanFacade implements ClientEndPoint {

	@Override
	public Set<WordResponse> getRandomWords(int limit)	throws ApplicationException {
		GetRandomWordsService service = FacadeUtil.getService(GetRandomWordsService.class);
		service.setLimit(limit);
		List<WordEntity> result = FacadeUtil.executeService(service);
		return ResponseFactory.createWordsResponse(result);
	}

}
