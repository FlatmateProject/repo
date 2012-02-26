package facade;

import java.util.Set;

import javax.ejb.Remote;
import javax.jws.WebService;

import exception.ApplicationException;

@Remote
@WebService()
public interface DictionaryEndPoint {

	boolean addElement(String wordName, Set<String> translations, Set<String> examples) throws ApplicationException;
}
