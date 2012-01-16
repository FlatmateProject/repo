package fasade;

import java.util.Set;

import exception.ApplicationException;

public interface DictionaryEndPoint {

	boolean addElement(String wordName, Set<String> translations, Set<String> examples) throws ApplicationException;
}
