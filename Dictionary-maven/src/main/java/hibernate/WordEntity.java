package hibernate;

import java.util.*;

public class WordEntity {

	private long id;

	private String wordName;

	private Set<TranslationEntity> translations = new HashSet<TranslationEntity>();

	private Set<ExampleEntity> examples = new HashSet<ExampleEntity>();

	public long getId() {
		return id;
	}

	private WordEntity() {
		
	}

	public String getWordName() {
		return wordName;
	}

	public Set<TranslationEntity> getTranslations() {
		return translations;
	}

	public Set<ExampleEntity> getExamples() {
		return examples;
	}

	public static final class Factory {
		public static WordEntity createWord(String wordName, Set<String> translations, Set<String> examples) {
			WordEntity word = new WordEntity();
			word.wordName = wordName;
			word.translations = TranslationEntity.Factory.createTranslations(translations, word);
			word.examples = ExampleEntity.Factory.createExamples(examples, word);
			return word;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((wordName == null) ? 0 : wordName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordEntity other = (WordEntity) obj;
		if (wordName == null) {
			if (other.wordName != null)
				return false;
		} else if (!wordName.equals(other.wordName))
			return false;
		return true;
	}

}
