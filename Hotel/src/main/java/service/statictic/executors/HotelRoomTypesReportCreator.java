package service.statictic.executors;

import dictionary.MONTH;
import dto.statictic.RoomTypeStatisticData;
import exception.DAOException;
import service.statictic.DiagramElement;
import service.statictic.REPORT_KIND;
import service.statictic.StatisticReport;
import service.statictic.templates.ReportTemplateBuilder;

import java.util.LinkedList;
import java.util.List;

public class HotelRoomTypesReportCreator extends ReportCreator {

    private MONTH month;

    private int year;

    @Override
    public void setup(ReportDetails reportDetails) {
        month = reportDetails.getMonth();
        year = reportDetails.getYear();
    }

    @Override
    public StatisticReport createReport(ReportTemplateBuilder templateBuilder) throws DAOException {
        int i = 0;
        List<DiagramElement> diagramElements = new LinkedList<DiagramElement>();
        List<RoomTypeStatisticData> roomTypes = statisticDao.findRoomTypesStatistics(month.numberCountedFromOne(), year);//
        templateBuilder.createHeader(month, year);
        for (RoomTypeStatisticData roomType : roomTypes) {
            int numberOccupiedRooms = roomType.getNumberOccupiedRooms();
            float summaryGain = roomType.getSummaryGain();
            double unitGain = summaryGain / numberOccupiedRooms;
            diagramElements.add(new DiagramElement(summaryGain, unitGain));
            templateBuilder.appendBodyBlock(roomType.getRoomTypeName(), i, summaryGain, numberOccupiedRooms, unitGain);
            i++;
        }
        templateBuilder.createFoot(roomTypes.size());
        return new StatisticReport(REPORT_KIND.HOTEL_ROOM_TYPES, diagramElements, templateBuilder);
    }
}
