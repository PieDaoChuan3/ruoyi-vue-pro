package cn.iocoder.yudao.module.cinema.dal.dataobject.statistics;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电影票销售统计id DO
 *
 * @author 芋道源码
 */
@TableName("t_statistics")
@KeySequence("t_statistics_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDO extends BaseDO {

    /**
     * 销售统计id
     */
    @TableId
    private Integer id;
    /**
     * 总额
     */
    private Integer amount;
    /**
     * 数量
     */
    private Integer quality;
    /**
     * 场次id
     */
    private Integer scheduleId;

}