package service.statictic.templates;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import service.dictionary.MONTH;
import service.statictic.DiagramElement;
import service.statictic.RAPORT_KIND;
import service.statictic.StatisticRaport;
import service.statictic.executors.RaportCreator;
import service.statictic.executors.RaportDetails;
import dto.MonthSumaryGainData;

public class FinanceMonthRaportCreator extends RaportCreator {

	private MONTH monthFrom;
	
	private MONTH monthTo;
	
	private int year;
	
	@Override
	public void setup(RaportDetails raportDetails) {
		monthFrom = raportDetails.getMonthFrom();
		monthTo = raportDetails.getMonthTo();
		year = raportDetails.getYear();
	}

	@Override
	public StatisticRaport createRaport(RaportTemplateBuilder templateBuilder) throws SQLException {
		int i = 0;
		List<DiagramElement> plotPoints = new LinkedList<DiagramElement>(); 
		if (monthFrom.after(monthTo)) {
			swapMonths();
		}
		List<MonthSumaryGainData> monthSumaryGains = staticticDao.findMonthSumaryGains(monthFrom.id(), monthTo.id(), year);
		templateBuilder.createHeader(monthFrom, monthTo, year);
		for (MonthSumaryGainData financeMonthRaportData : monthSumaryGains) {
			int month = financeMonthRaportData.getMonth();
			double reservationSumaryGain = financeMonthRaportData.getReservationSumaryGain();
			double serviceSumaryGain = financeMonthRaportData.getServiceSumaryGain();
			double cantorSumaryGain = financeMonthRaportData.getCantorSumaryGain();
			double sumaryGain = reservationSumaryGain + serviceSumaryGain + cantorSumaryGain;
			plotPoints.add(new DiagramElement(reservationSumaryGain, serviceSumaryGain, cantorSumaryGain, sumaryGain));
			templateBuilder.appendBodyBlock(MONTH.getMonthName(month), i, reservationSumaryGain, serviceSumaryGain, cantorSumaryGain, sumaryGain);
			i++;
		}
		templateBuilder.createFoot(monthSumaryGains.size());
		StatisticRaport raport = new StatisticRaport(RAPORT_KIND.FINANCE_MONTH, plotPoints, templateBuilder);
		return raport;
	}

	private void swapMonths() {
		MONTH tmp = monthFrom;
		monthFrom = monthTo;
		monthTo = tmp;
	}

}
