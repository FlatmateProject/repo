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

	private Map<OBJECT_TYPE, ObjectEntity> entities;
	
	private Map<OBJECT_TYPE, PaymentsFactory> factories;
	
	public PaymentsGiven(){
		entities = new HashMap<OBJECT_TYPE, ObjectEntity>();
		entities.put(OBJECT_TYPE.ADVERT, new AdvertEntity());
		entities.put(OBJECT_TYPE.PUBLICITY, new PublicityEntity());
		
		factories = new HashMap<OBJECT_TYPE, PaymentsFactory>();
		factories.put(OBJECT_TYPE.ADVERT, new AdvertPaymentsFactory());
		factories.put(OBJECT_TYPE.PUBLICITY, new PublicityPaymentsFactory());
	}

	public ObjectEntity getEntity(String type) {
		OBJECT_TYPE objectType = OBJECT_TYPE.valueOf(type);
		return entities.get(objectType);
	}

	public PaymentsFactory getPaymentsFactory(String type) {
		OBJECT_TYPE objectType = OBJECT_TYPE.valueOf(type);
		return factories.get(objectType);
	}
	
	
}
