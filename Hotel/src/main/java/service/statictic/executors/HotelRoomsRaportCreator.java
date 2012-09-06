package service.statictic.executors;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import service.dictionary.MONTH;
import service.statictic.DiagramElement;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;
import dto.RoomData;

public class HotelRoomsRaportCreator extends RaportCreator {

	private MONTH month;
	
	private int year;
	
	private String roomType;
	
	@Override
	public void setup(RaportDetails raportDetails) {
		month = raportDetails.getMonth();
		year = raportDetails.getYear();
		roomType = raportDetails.getRoomType();
	}

	@Override
	public StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) throws SQLException {
		int i = 0;
		List<DiagramElement> plotPoints = new LinkedList<DiagramElement>(); 
		
		List<RoomData> rooms = staticticDao.findRoomsByType(month.id(), year, roomType);
		templateBuilder.createHeader(roomType, month,year);
		for (RoomData room : rooms) {
			int nuberOccupiedRooms = room.getNuberOccupiedRooms();
			float summaryGain = room.getSummaryGain();
			float unitGain = summaryGain / nuberOccupiedRooms;
			
			plotPoints.add(new DiagramElement(summaryGain, unitGain));
			
			templateBuilder.appendBodyBlock(room.getRoomId(), i, summaryGain, nuberOccupiedRooms,	unitGain);
			i++;
		}
		templateBuilder.createFoot(rooms.size());

		return new StatisticRaport(plotPoints, templateBuilder);
	}

}
