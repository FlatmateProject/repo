package service.statictic.executors;

import java.util.LinkedList;
import java.util.List;

import service.dictionary.MONTH;
import service.statictic.PlotPoint;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;
import dto.ClassRoomData;

public class HotelRoomClassRaportExecutor extends RaportExecutor {
	
	private MONTH month;
	
	private int year;

	@Override
	public void setup(RaportDetails raportDetails) {
		month = raportDetails.getMonth();
		year = raportDetails.getYear();
	}

	@Override
	public StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) {
		int i = 0;
		List<PlotPoint> plotPoints = new LinkedList<PlotPoint>(); 

		List<ClassRoomData> classRooms = staticticDao.findClassRooms(month.ordinal(), year);//
		templateBuilder.createHeader("Raportu z wykorzystania klas pokoi", month, year);
		for (ClassRoomData classRoom : classRooms) {
			int nuberOccupiedRooms = classRoom.getNuberOccupiedRooms();
			double summaryGain = classRoom.getSummaryGain();
			double unitGain = summaryGain / nuberOccupiedRooms;
			
			plotPoints.add(new PlotPoint(summaryGain, unitGain));
			
			templateBuilder.appendBodyBlock(classRoom.getDescription(), i, summaryGain, nuberOccupiedRooms, unitGain);
			i++;
		}
		templateBuilder.createFoot(classRooms.size());

		String resultText = templateBuilder.build();
		return new StatisticRaport(plotPoints, resultText);
	}
}
