package datasource;

import java.util.Set;

public interface AbstractGenerator {
	
	String randomWord();

	Set<String> randomTranslations();
	
	Set<String> randomExamples();
	
}
