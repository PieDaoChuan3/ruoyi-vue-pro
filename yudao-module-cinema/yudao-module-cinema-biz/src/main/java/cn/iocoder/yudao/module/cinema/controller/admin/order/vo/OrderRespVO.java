package cn.iocoder.yudao.module.cinema.controller.admin.order.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "后台管理 - 电影票订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrderRespVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8362")
    @ExcelProperty("订单id")
    private Long id;

    @Schema(description = "下单时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2023-01-10 21:22:23.386000")
    @ExcelProperty("下单时间")
    private LocalDateTime orderTime;

    @Schema(description = "单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "250")
    @ExcelProperty("单价")
    private Integer price;

    @Schema(description = "购买票数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("购买票数")
    private Integer quality;

    @Schema(description = "电影票id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12345678")
    @ExcelProperty("电影票id")
    private String ticketNo;

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("用户id")
    private Integer customerId;

    @Schema(description = "电影名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("电影名称")
    private String movieName;

    @Schema(description = "放映时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("放映时间")
    private LocalDateTime showTime;

}