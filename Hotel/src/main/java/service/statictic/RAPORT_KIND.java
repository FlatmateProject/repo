package service.statictic;

import service.statictic.executors.FinanceYearRaportCreator;
import service.statictic.executors.HotelRoomTypesRaportCreator;
import service.statictic.executors.HotelRoomRaportCreator;
import service.statictic.executors.HotelServiceRaportCreator;
import service.statictic.executors.HotelServicesRaportCreator;
import service.statictic.executors.RaportCreator;
import service.statictic.templates.FinanceMonthRaportCreator;
import service.statictic.templates.FinanceMonthRaportTemplateBuilder;
import service.statictic.templates.FinanceYearRaportTemplateBuilder;
import service.statictic.templates.HotelRoomTypesRaportTemplateBuilder;
import service.statictic.templates.HotelRoomRaportTemplateBuilder;
import service.statictic.templates.HotelServiceRaportTemplateBuilder;
import service.statictic.templates.HotelServicesRaportTemplateBuilder;
import service.statictic.templates.RaportTemplateBuilder;

public enum RAPORT_KIND {
	FINANCE_MONTH("Bilansu z miesięcy", new FinanceMonthRaportTemplateBuilder(), new FinanceMonthRaportCreator()), //
	FINANCE_YEAR("Bilansu z lat", new FinanceYearRaportTemplateBuilder(), new FinanceYearRaportCreator()), //
	HOTEL_ROOM_TYPES("Raportu z wykorzystania klas pokoi",new HotelRoomTypesRaportTemplateBuilder(), new HotelRoomTypesRaportCreator()), //
	HOTEL_ROOM("Raportu z wykorzystania pokoi w klasie", new HotelRoomRaportTemplateBuilder(), new HotelRoomRaportCreator()), //
	HOTEL_SERVICES("Raportu z wykorzystania typów usług", new HotelServicesRaportTemplateBuilder(), new HotelServicesRaportCreator()), //
	HOTEL_SERVICE("Raportu z wybranej uslugi", new HotelServiceRaportTemplateBuilder(), new HotelServiceRaportCreator())//
	;

	private String desc;

	private RaportTemplateBuilder templateBuilder;

	private RaportCreator raportCreator;

	private RAPORT_KIND(String desc) {
		this.desc = desc;
	}

	private RAPORT_KIND(String desc, RaportTemplateBuilder template, RaportCreator creator) {
		this.desc = desc;
		this.templateBuilder = template;
		this.raportCreator = creator;
	}

	public String getDesc() {
		return desc;
	}

	public RaportTemplateBuilder getRaportTemplateBuilder() {
		return templateBuilder;
	}

	public RaportCreator getRaportCreator() {
		return raportCreator;
	}
	
	@Override
	public String toString() {
		return desc;
	}

};