package cn.iocoder.yudao.module.cinema.controller.app.statistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.*;
import com.alibaba.excel.annotation.*;

@Schema(description = "用户 APP - 电影票销售统计id Response VO")
@Data
@ExcelIgnoreUnannotated
public class AppStatisticsRespVO {

    @Schema(description = "销售统计id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13575")
    private Integer id;

    @Schema(description = "总额", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer amount;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quality;

    @Schema(description = "电影名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String movieName;

    @Schema(description = "放映时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime showTime;

}