package cn.iocoder.yudao.module.cinema.service.statistics;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cinema.controller.app.statistics.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.statistics.StatisticsDO;
import cn.iocoder.yudao.module.cinema.dal.mysql.statistics.StatisticsMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link StatisticsServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(StatisticsServiceImpl.class)
public class StatisticsServiceImplTest extends BaseDbUnitTest {

    @Resource
    private StatisticsServiceImpl statisticsService;

    @Resource
    private StatisticsMapper statisticsMapper;



    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetStatisticsPage() {
       // mock 数据
       StatisticsDO dbStatistics = randomPojo(StatisticsDO.class, o -> { // 等会查询到
           o.setScheduleId(null);
       });
       statisticsMapper.insert(dbStatistics);
       // 测试 scheduleId 不匹配
       statisticsMapper.insert(cloneIgnoreId(dbStatistics, o -> o.setScheduleId(null)));
       // 准备参数
       AppStatisticsPageReqVO reqVO = new AppStatisticsPageReqVO();
//       reqVO.setScheduleId(null);

       // 调用
       PageResult<StatisticsDO> pageResult = statisticsService.getStatisticsPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbStatistics, pageResult.getList().get(0));
    }

}