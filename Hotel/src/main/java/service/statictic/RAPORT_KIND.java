package service.statictic;

import service.statictic.executors.FinanceYearRaportCreator;
import service.statictic.executors.HotelRoomTypesRaportCreator;
import service.statictic.executors.HotelRoomsRaportCreator;
import service.statictic.executors.HotelServiceRaportCreator;
import service.statictic.executors.HotelServiceTypesRaportCreator;
import service.statictic.executors.RaportCreator;
import service.statictic.templates.FinanceMonthRaportCreator;
import service.statictic.templates.FinanceMonthRaportTemplateBuilder;
import service.statictic.templates.FinanceYearRaportTemplateBuilder;
import service.statictic.templates.HotelRoomTypesRaportTemplateBuilder;
import service.statictic.templates.HotelRoomsRaportTemplateBuilder;
import service.statictic.templates.HotelServiceRaportTemplateBuilder;
import service.statictic.templates.HotelServiceTypesRaportTemplateBuilder;
import service.statictic.templates.RaportTemplateBuilder;

public enum RAPORT_KIND {
	FINANCE_MONTH("Bilansu z miesięcy", new FinanceMonthRaportTemplateBuilder(), new FinanceMonthRaportCreator()), //
	FINANCE_YEAR("Bilansu z lat", new FinanceYearRaportTemplateBuilder(), new FinanceYearRaportCreator()), //
	HOTEL_ROOM_TYPES("Raportu z wykorzystania klas pokoi",new HotelRoomTypesRaportTemplateBuilder(), new HotelRoomTypesRaportCreator()), //
	HOTEL_ROOMS("Raportu z wykorzystania pokoi w klasie", new HotelRoomsRaportTemplateBuilder(), new HotelRoomsRaportCreator()), //
	HOTEL_SERVICE_TYPES("Raportu z wykorzystania typów usług", new HotelServiceTypesRaportTemplateBuilder(), new HotelServiceTypesRaportCreator()), //
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