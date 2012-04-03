package facade.response;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "WordResponse")
@XmlType(propOrder={})
public class WordResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String wordName;

	private Set<String> translations = new HashSet<String>();

	private Set<String> examples = new HashSet<String>();

	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public Set<String> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<String> translations) {
		this.translations = translations;
	}

	public Set<String> getExamples() {
		return examples;
	}

	public void setExamples(Set<String> examples) {
		this.examples = examples;
	}
}
