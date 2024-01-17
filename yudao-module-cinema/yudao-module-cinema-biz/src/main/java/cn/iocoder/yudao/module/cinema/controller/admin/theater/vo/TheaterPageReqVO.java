package cn.iocoder.yudao.module.cinema.controller.admin.theater.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import javax.validation.constraints.NotNull;

@Schema(description = "用户 APP - 电影院分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TheaterPageReqVO extends PageParam {


}