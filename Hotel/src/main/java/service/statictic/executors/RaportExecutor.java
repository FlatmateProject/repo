package service.statictic.executors;

import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;
import dao.StaticticDao;

public abstract class RaportExecutor {

	protected StaticticDao staticticDao;
	
	public abstract void setup(RaportDetails raportDetails);
	
	public abstract StatisticRaport createRaport(RaportTemplateBuilder templateBuilder);

	public void injectStaticticDao(StaticticDao staticticDao) {
		this.staticticDao = staticticDao;		
	}
}
