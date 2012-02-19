package datasource;

import java.util.Set;

public interface Generator {
	
	String randomWord();

	Set<String> randomTranslations();
	
	Set<String> randomExamples();
	
}
