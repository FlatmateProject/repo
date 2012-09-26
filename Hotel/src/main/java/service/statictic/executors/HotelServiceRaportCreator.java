package service.statictic.executors;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dto.ServiceData;

import service.dictionary.MONTH;
import service.statictic.DiagramElement;
import service.statictic.RAPORT_KIND;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;

public class HotelServiceRaportCreator extends RaportCreator {
	
	private MONTH month;
	
	private int year;
	
	private String serviceTypeName;
	
	@Override
	public void setup(RaportDetails raportDetails) {
		month = raportDetails.getMonth();
		year = raportDetails.getYear();
		serviceTypeName = raportDetails.getServiceTypeName();
	}

	@Override
	public StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) throws SQLException {
		int i = 0;
		List<DiagramElement> plotPoints = new LinkedList<DiagramElement>();
		
		List<ServiceData> services = staticticDao.findServiceByType(month.id(), year,serviceTypeName);

		templateBuilder.createHeader(serviceTypeName, month, year);
		for (ServiceData service : services) {
			String serveName = service.getServiceName();
			float sumaryGain = service.getSummaryGain();
			int useNumber = staticticDao.countUseNumberForServiceName(serveName);
			float unitGain = sumaryGain / useNumber;
			
			templateBuilder.appendBodyBlock(serveName, i, service.getTime(), sumaryGain, useNumber, unitGain);
			
			plotPoints.add(new DiagramElement(sumaryGain, unitGain));
			i++;
		}
		templateBuilder.createFoot(services.size());
		
		return new StatisticRaport(RAPORT_KIND.HOTEL_SERVICE, plotPoints, templateBuilder);
	}

}
