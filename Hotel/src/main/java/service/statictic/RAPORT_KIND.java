package service.statictic;

import dao.StaticticDao;
import service.statictic.executors.HotelRoomClassRaportExecutor;
import service.statictic.executors.RaportDetails;
import service.statictic.executors.RaportExecutor;
import service.statictic.templates.HotelRoomClassRaportTemplateBuilder;
import service.statictic.templates.RaportTemplateBuilder;

public enum RAPORT_KIND {
	FINANCE_MONTH("Bilansu z miesięcy"), //
	FINANCE_YEAR("Bilansu z lat"), //
	HOTEL_CLASS_ROOM("Raportu z wykorzystania klas pokoi",new HotelRoomClassRaportTemplateBuilder(), new HotelRoomClassRaportExecutor()), //
	HOTEL_ROOM("Raportu z wykorzystania pokoi w klasie"), //
	HOTEL_SERVICES("Raportu z wykorzystania typów usług"), //
	HOTEL_SERVICE("Raportu z wybranej uslugi")//
	;

	private String desc;

	private RaportTemplateBuilder templateBuilder;

	private RaportExecutor executor;

	private RAPORT_KIND(String desc) {
		this.desc = desc;
	}

	private RAPORT_KIND(String desc, RaportTemplateBuilder template, RaportExecutor executor) {
		this.desc = desc;
		this.templateBuilder = template;
		this.executor = executor;
	}

	public StatisticRaport createRaport(RaportDetails raportDetails, StaticticDao staticticDao) {
		executor.setup(raportDetails);
		executor.injectStaticticDao(staticticDao);
		return executor.createRaport(templateBuilder);
	}

	public String getDesc() {
		return desc;
	}

	public RaportTemplateBuilder getRaportTemplateBuilder() {
		return templateBuilder;
	}

	@Override
	public String toString() {
		return desc;
	}
};