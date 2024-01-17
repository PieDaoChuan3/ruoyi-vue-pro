package cn.iocoder.yudao.module.cinema.controller.admin.order.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "后台管理 - 电影票订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderPageReqVO extends PageParam {
    @Schema(description = "用户id", example = "1")
    private Integer customerId;
}
