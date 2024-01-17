package cn.iocoder.yudao.module.cinema.controller.admin.film;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmRespVO;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmSaveReqVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.theater.TheaterDO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import cn.iocoder.yudao.module.cinema.dal.dataobject.film.FilmDO;
import cn.iocoder.yudao.module.cinema.service.film.FilmService;

@Tag(name = "后台管理 - 影片")
@RestController
@RequestMapping("/cinema/film")
@Validated
public class FilmController {

    @Resource
    private FilmService filmService;

    @PostMapping("/create")
    @Operation(summary = "创建影片")
    @PreAuthorize("@ss.hasPermission('cinema:film:create')")
    public CommonResult<Integer> createFilm(@Valid @RequestBody FilmSaveReqVO createReqVO) {
        return success(filmService.createFilm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新影片")
    @PreAuthorize("@ss.hasPermission('cinema:film:update')")
    public CommonResult<Boolean> updateFilm(@Valid @RequestBody FilmSaveReqVO updateReqVO) {
        filmService.updateFilm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除影片")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cinema:film:delete')")
    public CommonResult<Boolean> deleteFilm(@RequestParam("id") Integer id) {
        filmService.deleteFilm(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得影片")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cinema:film:get')")
    public CommonResult<FilmRespVO> getFilm(@RequestParam("id") Integer id) {
        FilmDO film = filmService.getFilm(id);
        return success(BeanUtils.toBean(film, FilmRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得影片分页")
    @PreAuthorize("@ss.hasPermission('cinema:film:page')")
    public CommonResult<PageResult<FilmRespVO>> getFilmPage(@Valid FilmPageReqVO pageReqVO) {
        PageResult<FilmDO> pageResult = filmService.getFilmPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FilmRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出影片 Excel")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@ss.hasPermission('cinema:film:export-excel')")
    public void exportFilmExcel(@Valid FilmPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FilmDO> list = filmService.getFilmPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "电影.xls", "数据", FilmRespVO.class,
                BeanUtils.toBean(list, FilmRespVO.class));
    }
}