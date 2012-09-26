package service.statictic.executors;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import service.dictionary.MONTH;
import service.statictic.DiagramElement;
import service.statictic.RAPORT_KIND;
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
		List<DiagramElement> plotPoints = new LinkedList<DiagramElement>(); 

		List<RoomTypesData> roomTypes = staticticDao.findRoomTypes(month.id(), year);//
		templateBuilder.createHeader(month, year);
		for (RoomTypesData roomType : roomTypes) {
			int nuberOccupiedRooms = roomType.getNuberOccupiedRooms();
			float summaryGain = roomType.getSummaryGain();
			double unitGain = summaryGain / nuberOccupiedRooms;
			
			plotPoints.add(new DiagramElement(summaryGain, unitGain));
			
			templateBuilder.appendBodyBlock(roomType.getRoomTypeName(), i, summaryGain, nuberOccupiedRooms, unitGain);
			i++;
		}
		templateBuilder.createFoot(roomTypes.size());

		return new StatisticRaport(RAPORT_KIND.HOTEL_ROOM_TYPES, plotPoints, templateBuilder);
	}
}
