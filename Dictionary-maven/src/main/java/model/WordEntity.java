package model;

import java.util.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
		 return new HashCodeBuilder()  
		 	.append(wordName)   
		    .toHashCode(); 
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || object instanceof WordEntity == false) {
			return false;
		}
		WordEntity obj = (WordEntity) object;
		return new EqualsBuilder()
			.append(wordName, obj.wordName)
			.isEquals();
	}


	public Set<TranslationEntity> addTranslations(Set<String> translationNames) {
		translations.addAll(TranslationEntity.Factory.createTranslations(translationNames, this));
		return translations;
	}

	public Set<ExampleEntity> addExamples(Set<String> exampleNames) {
		examples.addAll(ExampleEntity.Factory.createExamples(exampleNames, this));
		return examples;
	}
}
