package service.statictic;

import service.statictic.executors.FinanceYesrRaportExecutor;
import service.statictic.executors.HotelRoomClassRaportExecutor;
import service.statictic.executors.HotelRoomRaportExecutor;
import service.statictic.executors.HotelServiceRaportExecutor;
import service.statictic.executors.HotelServicesRaportExecutor;
import service.statictic.executors.RaportDetails;
import service.statictic.executors.RaportExecutor;
import service.statictic.templates.FinanceMonthRaportExecutor;
import service.statictic.templates.FinanceMonthRaportTemplateBuilder;
import service.statictic.templates.FinanceYearRaportTemplateBuilder;
import service.statictic.templates.HotelRoomClassRaportTemplateBuilder;
import service.statictic.templates.HotelRoomRaportTemplateBuilder;
import service.statictic.templates.HotelServiceRaportTemplateBuilder;
import service.statictic.templates.HotelServicesRaportTemplateBuilder;
import service.statictic.templates.RaportTemplateBuilder;
import dao.StaticticDao;

public enum RAPORT_KIND {
	FINANCE_MONTH("Bilansu z miesięcy", new FinanceMonthRaportTemplateBuilder(), new FinanceMonthRaportExecutor()), //
	FINANCE_YEAR("Bilansu z lat", new FinanceYearRaportTemplateBuilder(), new FinanceYesrRaportExecutor()), //
	HOTEL_CLASS_ROOM("Raportu z wykorzystania klas pokoi",new HotelRoomClassRaportTemplateBuilder(), new HotelRoomClassRaportExecutor()), //
	HOTEL_ROOM("Raportu z wykorzystania pokoi w klasie", new HotelRoomRaportTemplateBuilder(), new HotelRoomRaportExecutor()), //
	HOTEL_SERVICES("Raportu z wykorzystania typów usług", new HotelServicesRaportTemplateBuilder(), new HotelServicesRaportExecutor()), //
	HOTEL_SERVICE("Raportu z wybranej uslugi", new HotelServiceRaportTemplateBuilder(), new HotelServiceRaportExecutor())//
	;

	private String desc;

	private RaportTemplateBuilder templateBuilder;

	private RaportExecutor raportExecutor;

	private RAPORT_KIND(String desc) {
		this.desc = desc;
	}

	private RAPORT_KIND(String desc, RaportTemplateBuilder template, RaportExecutor executor) {
		this.desc = desc;
		this.templateBuilder = template;
		this.raportExecutor = executor;
	}

	public StatisticRaport createRaport(RaportDetails raportDetails, StaticticDao staticticDao) {
		raportExecutor.setup(raportDetails);
		raportExecutor.injectStaticticDao(staticticDao);
		return raportExecutor.createRaport(templateBuilder);
	}

	public String getDesc() {
		return desc;
	}

	public RaportTemplateBuilder getRaportTemplateBuilder() {
		return templateBuilder;
	}

	public RaportExecutor getRaportExecutor() {
		return raportExecutor;
	}
	
	@Override
	public String toString() {
		return desc;
	}

};