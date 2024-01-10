package cn.iocoder.yudao.module.cinema.controller.app.statistics.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "用户 APP - 电影票销售统计id分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppStatisticsPageReqVO extends PageParam {

}