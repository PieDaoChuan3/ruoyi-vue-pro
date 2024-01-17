package cn.iocoder.yudao.module.cinema.controller.app.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(description = "用户 APP - 电影票订单新增/修改基础 Request VO")
@Data
public class AppOrderSaveReqVO {

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "用户id不能为空")
    private Integer customerId;

    @Schema(description = "购买票数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Min(value = 1, message = "至少购买一张票")
    private Integer quality;

    @Schema(description = "场次id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "场次id不能为空")
    private Integer scheduleId;

}