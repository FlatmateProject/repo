package hibernate;

import java.util.HashSet;
import java.util.Set;

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
		public static ExampleEntity createExample(String exampleName, WordEntity word) {
			ExampleEntity example = new ExampleEntity();
			example.exampleName = exampleName;
			example.word = word;
			return example;
		}

		public static Set<ExampleEntity> createExamples(Set<String> exampleNames, WordEntity word) {
			Set<ExampleEntity> examples = new HashSet<ExampleEntity>();
			for (String exampleName : exampleNames) {
				examples.add(createExample(exampleName, word));
			}
			return examples;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exampleName == null) ? 0 : exampleName.hashCode());
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
		ExampleEntity other = (ExampleEntity) obj;
		if (exampleName == null) {
			if (other.exampleName != null)
				return false;
		} else if (!exampleName.equals(other.exampleName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return exampleName;
	}
}