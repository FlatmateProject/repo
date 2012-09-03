package service.statictic.executors;

import java.sql.SQLException;

import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;
import dao.StaticticDao;

public abstract class RaportCreator {

	protected StaticticDao staticticDao;
	
	public abstract void setup(RaportDetails raportDetails);
	
	public abstract StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) throws SQLException;

	public void injectStaticticDao(StaticticDao staticticDao) {
		this.staticticDao = staticticDao;		
	}
}
