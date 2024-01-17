package cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "后台管理 - 电影场次新增/修改 Request VO")
@Data
public class ScheduleSaveReqVO {
    @Schema(description = "场次id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18112")
    @NotNull(message = "场次id不能为空")
    private Integer id;

    @Schema(description = "单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "250")
    @NotNull(message = "单价不能为空")
    private Integer price;

    @Schema(description = "余票数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "200")
    @Min(value = 0, message = "余票不能为负数")
    private Integer quota;

    @Schema(description = "放映时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2023-02-13 09:00:00.000000")
    @NotNull(message = "放映时间不能为空")
    private LocalDateTime showTime;

    @Schema(description = "影片id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "影片id不能为空")
    private Integer fId;

    @Schema(description = "剧院id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotNull(message = "剧院id")
    private Integer theaterId;
}
