package cn.iocoder.yudao.module.cinema.controller.app.schedule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "用户 APP - 电影场次 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AppScheduleRespVO {

    @Schema(description = "场次id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18112")
    private Integer sessionId;

    @Schema(description = "单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "3808")
    private Integer price;

    @Schema(description = "余票数量", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quota;

    @Schema(description = "放映时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime showTime;

    @Schema(description = "电影名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "神奇动物")
    private String movieName;

    @Schema(description = "剧院名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "光明影院")
    private String theaterName;

}