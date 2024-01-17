package cn.iocoder.yudao.module.cinema.convert.statistic;

import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.AppScheduleRespVO;
import cn.iocoder.yudao.module.cinema.controller.app.statistics.vo.AppStatisticsRespVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.statistics.StatisticsDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticsConverter {
    AppStatisticsRespVO toa(StatisticsDO statistic);
}
