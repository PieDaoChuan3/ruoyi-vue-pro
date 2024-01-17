package cn.iocoder.yudao.module.cinema.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Schema(description = "后台管理 - 订单修改 Request VO")
@Data
public class OrderSaveReqVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "下单时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2023-01-10 21:22:23.386000")
    @NotNull(message = "更新的下单时间不能为空")
    private LocalDateTime orderTime;

    @Schema(description = "单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "250")
    @NotNull(message = "更新的单价不能为空")
    private Integer price;

    @Schema(description = "购买票数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "更新的购买票数不能为空")
    private Integer quality;

    @Schema(description = "电影票id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12345678")
    @NotNull(message = "更新的电影票id不能为空")
    private String ticketNo;

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "更新的用户id不能为空")
    private Integer customerId;

    @Schema(description = "场次id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "更新的场次id不能为空")
    private Integer scheduleId;
}
