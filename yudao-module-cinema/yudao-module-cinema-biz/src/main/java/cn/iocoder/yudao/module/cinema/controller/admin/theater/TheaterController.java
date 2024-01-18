package cn.iocoder.yudao.module.cinema.controller.admin.theater;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmRespVO;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterRespVO;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterSaveReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterPageReqVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.film.FilmDO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.servlet.http.HttpServletResponse;
import javax.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import cn.iocoder.yudao.module.cinema.dal.dataobject.theater.TheaterDO;
import cn.iocoder.yudao.module.cinema.service.theater.TheaterService;

import java.io.IOException;
import java.util.List;

@Tag(name = "后台管理 - 电影院")
@RestController
@RequestMapping("/cinema/theater")
@Validated
public class TheaterController {

    @Resource
    private TheaterService theaterService;

    @PostMapping("/create")
    @Operation(summary = "创建电影院")
    @PreAuthorize("@ss.hasPermission('cinema:theater:create')")
    public CommonResult<Integer> createTheater(@Valid @RequestBody TheaterSaveReqVO createReqVO) {
        return success(theaterService.createTheater(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新电影院")
    @PreAuthorize("@ss.hasPermission('cinema:theater:update')")
    public CommonResult<Boolean> updateTheater(@Valid @RequestBody TheaterSaveReqVO updateReqVO) {
        theaterService.updateTheater(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除电影院")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cinema:theater:delete')")
    public CommonResult<Boolean> deleteTheater(@RequestParam("id") Integer id) {
        theaterService.deleteTheater(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得电影院")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cinema:theater:query')")
    public CommonResult<TheaterRespVO> getTheater(@RequestParam("id") Integer id) {
        TheaterDO theater = theaterService.getTheater(id);
        return success(BeanUtils.toBean(theater, TheaterRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得电影院分页")
    @PreAuthorize("@ss.hasPermission('cinema:theater:query')")
    public CommonResult<PageResult<TheaterRespVO>> getTheaterPage(@Valid TheaterPageReqVO pageReqVO) {
        PageResult<TheaterDO> pageResult = theaterService.getTheaterPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TheaterRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出影院 Excel")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@ss.hasPermission('cinema:theater:export')")
    public void exportFilmExcel(@Valid TheaterPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TheaterDO> list = theaterService.getTheaterPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "电影院.xls", "数据", TheaterRespVO.class,
                BeanUtils.toBean(list, TheaterRespVO.class));
    }
}