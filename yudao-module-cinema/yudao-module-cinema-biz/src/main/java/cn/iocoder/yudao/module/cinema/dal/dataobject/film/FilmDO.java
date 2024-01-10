package cn.iocoder.yudao.module.cinema.dal.dataobject.film;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 影片 DO
 *
 * @author 芋道源码
 */
@TableName("t_film")
@KeySequence("t_film_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDO extends BaseDO {

    /**
     * 影片id
     */
    @TableId
    private Integer id;
    /**
     * 影片名称
     */
    private String name;
    /**
     * 分类
     */
    private String classify;
    /**
     * 导演
     */
    private String director;
    /**
     * 男主教
     */
    private String hero;
    /**
     * 女主角
     */
    private String heroine;
    /**
     * 制作时间
     */
    private LocalDate production;

}