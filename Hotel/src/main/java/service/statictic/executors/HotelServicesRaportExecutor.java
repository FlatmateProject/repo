package service.statictic.executors;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dto.ServeTypeData;
import service.dictionary.MONTH;
import service.statictic.PlotPoint;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;

public class HotelServicesRaportExecutor extends RaportExecutor {

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
		List<PlotPoint> plotPoints = new LinkedList<PlotPoint>();
		
		List<ServeTypeData> serveTypes = staticticDao.createServesRaport(month.id(), year);
		templateBuilder.createHeader(month, year);
		for (ServeTypeData serveType : serveTypes) {
			String typeName = serveType.getTypeName();
			float sumaryGain = serveType.getSumaryGain();
			int useNumber = staticticDao.countUseNumberForServeType(typeName);
			float unitGain = sumaryGain / useNumber;
			
			templateBuilder.appendBodyBlock(typeName, i, serveType.getTime(), sumaryGain, useNumber, unitGain);
			
			plotPoints.add(new PlotPoint(sumaryGain, sumaryGain));
			i++;
		}
		templateBuilder.createFoot(serveTypes.size());
		
		return new StatisticRaport(plotPoints, templateBuilder);
	}

}
