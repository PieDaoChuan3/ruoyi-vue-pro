package cn.iocoder.yudao.module.cinema.service.statistics;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.cinema.controller.app.statistics.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.statistics.StatisticsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cinema.dal.mysql.statistics.StatisticsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;

/**
 * 电影票销售统计id Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public PageResult<StatisticsDO> getStatisticsPage(AppStatisticsPageReqVO pageReqVO) {
        return statisticsMapper.selectPage(pageReqVO);
    }

}