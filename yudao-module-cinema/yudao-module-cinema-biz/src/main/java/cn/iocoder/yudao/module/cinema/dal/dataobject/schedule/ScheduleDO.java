package cn.iocoder.yudao.module.cinema.dal.dataobject.schedule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电影场次 DO
 *
 * @author 芋道源码
 */
@TableName("t_schedule")
@KeySequence("t_schedule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDO extends BaseDO {

    /**
     * 场次id
     */
    @TableId
    private Integer id;
    /**
     * 单价
     */
    private Integer price;
    /**
     * 余票数量
     */
    private Integer quota;
    /**
     * 放映时间
     */
    private LocalDateTime showTime;
    /**
     * 电影id
     */
    private Integer fId;
    /**
     * 剧院id
     */
    private Integer theaterId;
    /**
     * 乐观锁
     */
    @Version
    private Integer version;

}