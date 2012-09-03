package service.statictic.executors;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dto.ServeData;

import service.dictionary.MONTH;
import service.statictic.PlotPoint;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;

public class HotelServiceRaportCreator extends RaportCreator {
	
	private MONTH month;
	
	private int year;
	
	private String serveTypeName;
	
	@Override
	public void setup(RaportDetails raportDetails) {
		month = raportDetails.getMonth();
		year = raportDetails.getYear();
		serveTypeName = raportDetails.getServeTypeName();
	}

	@Override
	public StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) throws SQLException {
		int i = 0;
		List<PlotPoint> plotPoints = new LinkedList<PlotPoint>();
		
		List<ServeData> serves = staticticDao.createServeRaport(month.id(), year,serveTypeName);

		templateBuilder.createHeader(serveTypeName, month, year);
		for (ServeData serve : serves) {
			String serveName = serve.getServeName();
			float sumaryGain = serve.getSumaryGain();
			int useNumber = staticticDao.countUseNumberForServeName(serveName);
			float unitGain = sumaryGain / useNumber;
			templateBuilder.appendBodyBlock(serveName, i, serve.getTime(), sumaryGain, useNumber, unitGain);
			
			i++;
		}
		templateBuilder.createFoot(serves.size());
		
		return new StatisticRaport(plotPoints, templateBuilder);
	}

}
