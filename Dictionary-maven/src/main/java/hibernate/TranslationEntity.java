package hibernate;

import java.util.HashSet;
import java.util.Set;

public class TranslationEntity {

	private long id;
	private String translationName;
	private WordEntity word;

	public long getId() {
		return id;
	}

	private TranslationEntity() {
		
	}

	public String getTranslationName() {
		return translationName;
	}

	public WordEntity getWord() {
		return word;
	}

	public static final class Factory {
		public static TranslationEntity createTranslation(String translationName, WordEntity word) {
			TranslationEntity translation = new TranslationEntity();
			translation.translationName = translationName;
			translation.word = word;
			return translation;
		}

		public static Set<TranslationEntity> createTranslations(Set<String> translationNames, WordEntity word) {
			Set<TranslationEntity> translations = new HashSet<TranslationEntity>();
			for (String translationName : translationNames) {
				translations.add(createTranslation(translationName, word));
			}
			return translations;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((translationName == null) ? 0 : translationName.hashCode());
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
		TranslationEntity other = (TranslationEntity) obj;
		if (translationName == null) {
			if (other.translationName != null)
				return false;
		} else if (!translationName.equals(other.translationName))
			return false;
		return true;
	}

}
