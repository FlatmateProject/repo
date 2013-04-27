package view;

import dao.StatisticDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.statictic.Statistic;

@Component
public class StatisticPanel extends TabComponent {

    @Autowired
    private StatisticDao statisticDao;

    @Autowired
    private Statistic statistic;

    @Override
    public void create() {

    }

    @Override
    public void addEvents() {

    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public void setStatisticDao(StatisticDao statisticDao) {
        this.statisticDao = statisticDao;
    }
}
