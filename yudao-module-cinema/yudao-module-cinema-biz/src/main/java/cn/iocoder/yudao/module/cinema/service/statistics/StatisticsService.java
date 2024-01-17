package cn.iocoder.yudao.module.cinema.service.statistics;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cinema.controller.app.statistics.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.statistics.StatisticsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 电影票销售统计id Service 接口
 *
 * @author 芋道源码
 */
public interface StatisticsService {

    PageResult<StatisticsDO> getStatisticsPage(AppStatisticsPageReqVO pageReqVO);

}