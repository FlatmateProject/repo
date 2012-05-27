package patterns.prototype;

import java.util.ArrayList;
import java.util.List;

public class UserFeaturesHistory {

	public List<UserFeature> getFeatures() {
		return features;
	}

	private List<UserFeature> features = new ArrayList<UserFeature>();

	public void addFeature(UserFeature feature) {
		features.add(feature);
	}
	
	public void getFeature(int i) {
		features.get(i);
	}	
	
	public int historySize(){
		return features.size();
	}
	
	public boolean contains(UserFeature feature) {
		return features.contains(feature);
	}
}
