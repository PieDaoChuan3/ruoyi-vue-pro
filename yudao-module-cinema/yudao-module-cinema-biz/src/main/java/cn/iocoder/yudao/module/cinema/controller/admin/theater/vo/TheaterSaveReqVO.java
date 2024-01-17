package cn.iocoder.yudao.module.cinema.controller.admin.theater.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "用户 APP - 电影院新增/修改 Request VO")
@Data
public class TheaterSaveReqVO {
    @Schema(description = "剧院id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("剧院id")
    @NotNull(message = "剧院id不能为空")
    private Integer id;

    @Schema(description = "剧院名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "光明影院")
    @ExcelProperty("剧院名称")
    @NotNull(message = "剧院名称不能为空")
    private String name;

    @Schema(description = "剧院地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "合肥长江路211号")
    @ExcelProperty("剧院地址")
    @NotNull(message = "剧院地址不能为空")
    private String address;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("联系电话")
    @NotNull(message = "联系电话不能为空")
    private String phone;

    @Schema(description = "座位数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("座位数量")
    @Min(value = 1, message = "座位数量不能小于1")
    private Integer capacity;
}