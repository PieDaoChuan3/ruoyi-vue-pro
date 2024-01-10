package cn.iocoder.yudao.module.cinema.controller.admin.film.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;

@Schema(description = "用户 APP - 影片新增/修改 Request VO")
@Data
public class FilmSaveReqVO {

    @Schema(description = "影片id", requiredMode = Schema.RequiredMode.REQUIRED, example = "16459")
    private Integer id;

    @Schema(description = "影片名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "阿凡达")
    @NotEmpty(message = "影片名称不能为空")
    private String name;

    @Schema(description = "分类", requiredMode = Schema.RequiredMode.REQUIRED, example = "科幻")
    @NotEmpty(message = "分类不能为空")
    private String classify;

    @Schema(description = "导演", requiredMode = Schema.RequiredMode.REQUIRED, example = "科幻")
    @NotEmpty(message = "导演不能为空")
    private String director;

    @Schema(description = "男主角", requiredMode = Schema.RequiredMode.REQUIRED, example = "名字")
    @NotEmpty(message = "男主角不能为空")
    private String hero;

    @Schema(description = "女主角", requiredMode = Schema.RequiredMode.REQUIRED, example = "名字")
    @NotEmpty(message = "女主角不能为空")
    private String heroine;

    @Schema(description = "制作时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2000-01-01")
    @NotEmpty(message = "制作时间不能为空")
    @Past
    private LocalDate production;

}