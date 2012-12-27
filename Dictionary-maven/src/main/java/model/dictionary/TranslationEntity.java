package model.dictionary;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
		 return new HashCodeBuilder()  
		 	.append(translationName)   
		    .toHashCode(); 
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || object instanceof TranslationEntity == false) {
			return false;
		}
		TranslationEntity obj = (TranslationEntity) object;
		return new EqualsBuilder()
			.append(translationName, obj.translationName)
			.isEquals();
	}

	@Override
	public String toString() {
		return translationName;
	}
}
