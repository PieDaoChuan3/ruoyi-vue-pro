package cn.iocoder.yudao.module.cinema.controller.app.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "用户 APP - 电影票订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AppOrderRespVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8362")
    private Long id;

    @Schema(description = "下单时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2023-01-10 21:22:23.386000")
    private LocalDateTime orderTime;

    @Schema(description = "单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "250")
    private Integer price;

    @Schema(description = "购买票数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer quality;

    @Schema(description = "电影名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String movieName;

    @Schema(description = "放映时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime showTime;

}