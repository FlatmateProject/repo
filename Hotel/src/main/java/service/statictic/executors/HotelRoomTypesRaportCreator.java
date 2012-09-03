package service.statictic.executors;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import service.dictionary.MONTH;
import service.statictic.PlotPoint;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;
import dto.RoomTypesData;

public class HotelRoomTypesRaportCreator extends RaportCreator {
	
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

		List<RoomTypesData> roomTypes = staticticDao.findRoomTypes(month.id(), year);//
		templateBuilder.createHeader(month, year);
		for (RoomTypesData roomType : roomTypes) {
			int nuberOccupiedRooms = roomType.getNuberOccupiedRooms();
			float summaryGain = roomType.getSummaryGain();
			double unitGain = summaryGain / nuberOccupiedRooms;
			
			plotPoints.add(new PlotPoint(summaryGain, unitGain));
			
			templateBuilder.appendBodyBlock(roomType.getRoomTypeName(), i, summaryGain, nuberOccupiedRooms, unitGain);
			i++;
		}
		templateBuilder.createFoot(roomTypes.size());

		return new StatisticRaport(plotPoints, templateBuilder);
	}
}
