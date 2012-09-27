package service.statictic;

import service.dictionary.MONTH;
import service.statictic.executors.RaportCreator;
import service.statictic.executors.RaportDetails;
import service.statictic.templates.RaportTemplateBuilder;
import dao.StaticticDao;

public class Statistic {

	private StaticticDao staticticDao;

	public Statistic(StaticticDao staticticDao) {
		this.staticticDao = staticticDao;
	}
	
	public StatisticRaport finance(RAPORT_KIND raportKind, MONTH monthFrom, MONTH monthTo, int yearFrom, int yearTo) {
		return createStatistic(raportKind, new RaportDetails(monthFrom, monthTo, yearFrom, yearTo));
	}

	public StatisticRaport hotel(RAPORT_KIND raportKind, MONTH month, int year, String classRoom, String serveTypeName){
		return createStatistic(raportKind, new RaportDetails(month, year, classRoom, serveTypeName));
	}
	
	private StatisticRaport createStatistic(RAPORT_KIND raportKind, RaportDetails raportDetails) {
		try {
			RaportCreator creator = raportKind.getRaportCreator();
			RaportTemplateBuilder temlpateBuilder = raportKind.getRaportTemplateBuilder();
			creator.setup(raportDetails);
			creator.injectStaticticDao(staticticDao);
			return creator.createRaport(temlpateBuilder);
		} catch (Exception e) {
			e.printStackTrace();
			return null;	
		}
	}
}
