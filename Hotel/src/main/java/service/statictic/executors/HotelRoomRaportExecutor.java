package service.statictic.executors;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import service.dictionary.MONTH;
import service.statictic.PlotPoint;
import service.statictic.StatisticRaport;
import service.statictic.templates.RaportTemplateBuilder;
import dto.RoomData;

public class HotelRoomRaportExecutor extends RaportExecutor {

	private MONTH month;
	
	private int year;
	
	private String classRoom;
	
	@Override
	public void setup(RaportDetails raportDetails) {
		month = raportDetails.getMonth();
		year = raportDetails.getYear();
		classRoom = raportDetails.getClassRoom();
	}

	@Override
	public StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) throws SQLException {
		int i = 0;
		List<PlotPoint> plotPoints = new LinkedList<PlotPoint>(); 
		
		List<RoomData> rooms = staticticDao.createRoomRaport(month.id(), year, classRoom);
		templateBuilder.createHeader(classRoom, month,year);
		for (RoomData room : rooms) {
			int nuberOccupiedRooms = room.getNuberOccupiedRooms();
			float summaryGain = room.getSummaryGain();
			float unitGain = summaryGain / nuberOccupiedRooms;
			
			plotPoints.add(new PlotPoint(summaryGain, unitGain));
			
			templateBuilder.appendBodyBlock(room.getRoomId(), i, summaryGain, nuberOccupiedRooms,	unitGain);
			i++;
		}
		templateBuilder.createFoot(rooms.size());

		return new StatisticRaport(plotPoints, templateBuilder);
	}

}
