package cn.iocoder.yudao.module.cinema.dal.mysql.statistics;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cinema.dal.dataobject.statistics.StatisticsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cinema.controller.app.statistics.vo.*;

/**
 * 电影票销售统计id Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StatisticsMapper extends BaseMapperX<StatisticsDO> {

    default PageResult<StatisticsDO> selectPage(AppStatisticsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StatisticsDO>()
                .orderByAsc(StatisticsDO::getId));
    }

    default Long findByScheduleId(Integer scheduleId){
        return selectCount("schedule_id", scheduleId);
    }

    default StatisticsDO selectByScheduleId(Integer scheduleId){
        return selectOne("schedule_id", scheduleId);
    }

}