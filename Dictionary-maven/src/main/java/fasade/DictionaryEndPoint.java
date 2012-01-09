package fasade;

import java.util.Set;

public interface DictionaryEndPoint {

	boolean addElement(String wordName, Set<String> translations, Set<String> examples);
}
