package service.statictic.executors;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dto.ServiceTypeData;
import service.dictionary.MONTH;
import service.statictic.DiagramElement;
import service.statictic.RAPORT_KIND;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;

public class HotelServiceTypesRaportCreator extends RaportCreator {

	private MONTH month;
	
	private int year;
	
	@Override
	public void setup(RaportDetails raportDetails) {
		month = raportDetails.getMonth();
		year = raportDetails.getYear();
	}

	@Override
	public StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) throws SQLException {
		int i = 0;
		List<DiagramElement> plotPoints = new LinkedList<DiagramElement>();
		
		List<ServiceTypeData> serviceTypes = staticticDao.findServiceTypes(month.id(), year);
		templateBuilder.createHeader(month, year);
		for (ServiceTypeData serviceType : serviceTypes) {
			String typeName = serviceType.getTypeName();
			float sumaryGain = serviceType.getSummaryGain();
			int useNumber = staticticDao.countUseNumberForServiceType(typeName);
			float unitGain = sumaryGain / useNumber;
			
			templateBuilder.appendBodyBlock(typeName, i, serviceType.getSummaryTime(), sumaryGain, useNumber, unitGain);
			
			plotPoints.add(new DiagramElement(sumaryGain, unitGain));
			i++;
		}
		templateBuilder.createFoot(serviceTypes.size());
		
		return new StatisticRaport(RAPORT_KIND.HOTEL_SERVICE_TYPES, plotPoints, templateBuilder);
	}

}
