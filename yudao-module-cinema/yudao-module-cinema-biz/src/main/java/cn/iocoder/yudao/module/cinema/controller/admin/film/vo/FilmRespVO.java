package cn.iocoder.yudao.module.cinema.controller.admin.film.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import com.alibaba.excel.annotation.*;

@Schema(description = "用户 APP - 影片 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FilmRespVO {

    @Schema(description = "影片id", requiredMode = Schema.RequiredMode.REQUIRED, example = "16459")
    @ExcelProperty("影片id")
    private Integer id;

    @Schema(description = "影片名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "阿凡达")
    @ExcelProperty("影片名称")
    private String name;

    @Schema(description = "分类", requiredMode = Schema.RequiredMode.REQUIRED, example = "科幻")
    @ExcelProperty("分类")
    private String classify;

}