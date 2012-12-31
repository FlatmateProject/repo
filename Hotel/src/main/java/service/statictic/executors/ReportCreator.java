package service.statictic.executors;

import dao.StatisticDao;
import exception.DAOException;
import service.statictic.StatisticReport;
import service.statictic.templates.ReportTemplateBuilder;

public abstract class ReportCreator {

    protected StatisticDao statisticDao;

    public abstract void setup(ReportDetails reportDetails);

    public abstract StatisticReport createReport(ReportTemplateBuilder templateBuilder) throws DAOException;

    public void injectStatisticDao(StatisticDao statisticDao) {
        this.statisticDao = statisticDao;
    }
}
