package facade.response;

import hibernate.WordEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResponseFactory {

	public static <T> Set<String> convertToStringSet(Set<T> set) {
		Set<String> results = new HashSet<String>();
		for (T element : set) {
			results.add(element.toString());
		}
		return results;
	}

	public static WordResponse createWordResponse(WordEntity result) {
		WordResponse response = new WordResponse();
		response.setWordName(result.getWordName());
		response.setExamples(convertToStringSet(result.getExamples()));
		response.setTranslations(convertToStringSet(result.getTranslations()));
		return response;
	}

	public static Set<WordResponse> createWordsResponse(List<WordEntity> results) {
		Set<WordResponse> responses = new HashSet<WordResponse>();
		for (WordEntity result : results) {
			responses.add(createWordResponse(result));
		}
		return responses;
	}

}
