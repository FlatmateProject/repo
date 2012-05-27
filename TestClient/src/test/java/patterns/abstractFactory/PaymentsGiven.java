package patterns.abstractFactory;

import java.util.HashMap;
import java.util.Map;

import patterns.abstractFactory.advert.AdvertPaymentsFactory;
import patterns.abstractFactory.model.AdvertEntity;
import patterns.abstractFactory.model.OBJECT_TYPE;
import patterns.abstractFactory.model.ObjectEntity;
import patterns.abstractFactory.model.PublicityEntity;
import patterns.abstractFactory.publicity.PublicityPaymentsFactory;

public class PaymentsGiven {

	private Map<String, ObjectEntity> entities;
	
	private Map<String, PaymentsFactory> factories;
	
	public PaymentsGiven(){
		entities = new HashMap<String, ObjectEntity>();
		entities.put(OBJECT_TYPE.ADVERT.name(), new AdvertEntity());
		entities.put(OBJECT_TYPE.PUBLICITY.name(), new PublicityEntity());
		
		factories = new HashMap<String, PaymentsFactory>();
		factories.put(OBJECT_TYPE.ADVERT.name(), new AdvertPaymentsFactory());
		factories.put(OBJECT_TYPE.PUBLICITY.name(), new PublicityPaymentsFactory());
	}

	public ObjectEntity getEntity(String type) {
		return entities.get(type);
	}

	public PaymentsFactory getPaymentsFactory(String type) {
		return factories.get(type);
	}
	
	
}
