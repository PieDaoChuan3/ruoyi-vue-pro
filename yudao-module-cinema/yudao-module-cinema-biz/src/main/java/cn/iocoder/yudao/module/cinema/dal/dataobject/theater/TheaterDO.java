package cn.iocoder.yudao.module.cinema.dal.dataobject.theater;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电影院 DO
 *
 * @author 芋道源码
 */
@TableName("t_theater")
@KeySequence("t_theater_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDO extends BaseDO {

    /**
     * 剧院id
     */
    @TableId
    private Integer id;
    /**
     * 剧院名称
     */
    private String name;
    /**
     * 剧院地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 座位数量
     */
    private Integer capacity;

}