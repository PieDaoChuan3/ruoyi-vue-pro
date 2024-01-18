package cn.iocoder.yudao.module.cinema.controller.admin.film.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "后台管理 - 影片分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FilmPageReqVO extends PageParam {

    @Schema(description = "影片id", example = "16459")
    private Integer id;

    @Schema(description = "影片名称", example = "阿凡达")
    private String name;

    @Schema(description = "分类", example = "科幻")
    private String classify;

}