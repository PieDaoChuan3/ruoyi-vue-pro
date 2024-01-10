package cn.iocoder.yudao.module.cinema.dal.dataobject.order;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电影票订单 DO
 *
 * @author 芋道源码
 */
@TableName("t_order")
@KeySequence("t_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDO extends BaseDO {

    /**
     * 订单id
     */
    @TableId
    private Long id;
    /**
     * 下单时间
     */
    private LocalDateTime orderTime;
    /**
     * 单价
     */
    private Integer price;
    /**
     * 购买票数
     */
    private Integer quality;
    /**
     * 电影票id
     */
    private String ticketNo;
    /**
     * 用户id
     */
    private Integer customerId;
    /**
     * 场次id
     */
    private Integer scheduleId;

}