package cn.iocoder.yudao.module.cinema.controller.admin.theater.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import com.alibaba.excel.annotation.*;

@Schema(description = "用户 APP - 电影院 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TheaterRespVO {

    @Schema(description = "剧院id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("剧院id")
    private Integer id;

    @Schema(description = "座位数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "200")
    @ExcelProperty("座位数量")
    private Integer capacity;

}