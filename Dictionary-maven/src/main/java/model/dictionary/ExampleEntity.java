package model.dictionary;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ExampleEntity {

	private long id;
	private String exampleName;
	private WordEntity word;

	public String getExampleName() {
		return exampleName;
	}

	public long getId() {
		return id;
	}

	private ExampleEntity() {

	}

	public WordEntity getWord() {
		return word;
	}

	public static final class Factory {
		public static ExampleEntity createExample(String exampleName,
				WordEntity word) {
			ExampleEntity example = new ExampleEntity();
			example.exampleName = exampleName;
			example.word = word;
			return example;
		}

		public static Set<ExampleEntity> createExamples(
				Set<String> exampleNames, WordEntity word) {
			Set<ExampleEntity> examples = new HashSet<ExampleEntity>();
			for (String exampleName : exampleNames) {
				examples.add(createExample(exampleName, word));
			}
			return examples;
		}
	}

	@Override
	public int hashCode() {
		 return new HashCodeBuilder()  
		 	.append(exampleName)   
		    .toHashCode(); 
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || object instanceof ExampleEntity == false) {
			return false;
		}
		ExampleEntity obj = (ExampleEntity) object;
		return new EqualsBuilder()
			.append(exampleName, obj.exampleName)
			.isEquals();
	}

	@Override
	public String toString() {
		return exampleName;
	}
}