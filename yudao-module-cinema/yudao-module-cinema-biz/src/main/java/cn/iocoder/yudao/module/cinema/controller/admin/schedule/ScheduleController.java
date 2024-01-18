package cn.iocoder.yudao.module.cinema.controller.admin.schedule;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.SchedulePageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.ScheduleRespVO;
import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.ScheduleSaveReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterRespVO;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.AppSchedulePageReqVO;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.AppScheduleRespVO;
import cn.iocoder.yudao.module.cinema.convert.schedule.ScheduleConverter;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.theater.TheaterDO;
import cn.iocoder.yudao.module.cinema.service.film.FilmService;
import cn.iocoder.yudao.module.cinema.service.schedule.ScheduleService;
import cn.iocoder.yudao.module.cinema.service.theater.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "后台管理 - 电影场次")
@RestController
@RequestMapping("/cinema/schedule")
@Validated
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private FilmService filmService;

    @Resource
    private TheaterService theaterService;

    @PostMapping("/create")
    @Operation(summary = "创建电影场次")
    @PreAuthorize("@ss.hasPermission('cinema:schedule:create')")
    public CommonResult<Integer> createTheater(@Valid @RequestBody ScheduleSaveReqVO createReqVO) {
        return success(scheduleService.createSchedule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新电影场次")
    @PreAuthorize("@ss.hasPermission('cinema:schedule:update')")
    public CommonResult<Boolean> updateTheater(@Valid @RequestBody ScheduleSaveReqVO updateReqVO) {
        scheduleService.updateSchedule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除电影场次")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cinema:schedule:delete')")
    public CommonResult<Boolean> deleteTheater(@RequestParam("id") Integer id) {
        scheduleService.deleteSchedule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得电影场次")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cinema:schedule:query')")
    public CommonResult<ScheduleRespVO> getTheater(@RequestParam("id") Integer id) {
        ScheduleDO schedule = scheduleService.getSchedule(id);
        ScheduleRespVO scheduleResp = ScheduleConverter.INSTANCE.to(schedule);
        scheduleResp.setMovieName(filmService.getFilm(schedule.getFId()).getName());
        scheduleResp.setTheaterName(theaterService.getTheater(schedule.getTheaterId()).getName());
        return success(scheduleResp);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出电影场次 Excel")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@ss.hasPermission('cinema:schedule:export')")
    public void exportFilmExcel(@Valid SchedulePageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        AppSchedulePageReqVO pageReqVO1 = ScheduleConverter.INSTANCE.to(pageReqVO);
        pageReqVO1.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ScheduleDO> list = scheduleService.getSchedulePage(pageReqVO1).getList();

        List<ScheduleRespVO> listResp = new ArrayList<>();

        for(ScheduleDO schedule : list){
            ScheduleRespVO resp = ScheduleConverter.INSTANCE.to(schedule);
            resp.setMovieName(filmService.getFilm(schedule.getFId()).getName());
            resp.setTheaterName(theaterService.getTheater(schedule.getTheaterId()).getName());
            listResp.add(resp);
        }

        // 导出 Excel
        ExcelUtils.write(response, "电影场次.xls", "数据", ScheduleRespVO.class,
                listResp);
    }
}