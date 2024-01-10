package cn.iocoder.yudao.module.cinema.controller.app.schedule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "用户 APP - 电影场次新增/修改 Request VO")
@Data
public class AppScheduleSaveReqVO {

    @Schema(description = "场次id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18112")
    @NotNull(message = "场次id不能为空")
    private Integer id;

}