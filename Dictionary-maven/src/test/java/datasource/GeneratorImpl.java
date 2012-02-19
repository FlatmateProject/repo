package datasource;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeneratorImpl implements Generator {

	private Random rand = new Random();
	
	public String randomWord() {
		return randomString(rand.nextInt(15) + 1);
	}

	public Set<String> randomTranslations() {
		return randomSet(rand.nextInt(5) + 1, 5);
	}
	
	public Set<String> randomExamples() {
		return randomSet(rand.nextInt(6) + 1, 15);
	}
	
	private String randomString(int length, int minLength) {

		char[] values = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9' };

		String out = "";
		int idx = 0;
		length += minLength;
		for (int i = 0; i < length; i++) {
			idx = rand.nextInt(values.length);
			out += values[idx];
		}
		return out;
	}
	
	private String randomString(int n) {
		return randomString(n, 5);
	}
		
	private Set<String> randomSet(int n, int minLength) {
		Set<String> set = new HashSet<String>();
		for(int i = 0; i < n; i++){
			set.add(randomString(rand.nextInt(5), minLength));
		}
		return set;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}
	
}
