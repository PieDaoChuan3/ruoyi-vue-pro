package cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "后台管理 - 电影场次 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ScheduleRespVO {
    @Schema(description = "场次id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18112")
    @ExcelProperty("场次id")
    private Integer sessionId;

    @Schema(description = "单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "3808")
    @ExcelProperty("单价")
    private Integer price;

    @Schema(description = "余票数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("余票数量")
    private Integer quota;

    @Schema(description = "放映时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("放映时间")
    private LocalDateTime showTime;

    @Schema(description = "电影名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "21543")
    @ExcelProperty("电影名称")
    private String movieName;

    @Schema(description = "剧院名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "19065")
    @ExcelProperty("剧院名称")
    private String theaterName;
}
