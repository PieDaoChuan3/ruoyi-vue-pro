package cn.iocoder.yudao.module.cinema.controller.app.schedule;

import cn.iocoder.yudao.module.cinema.convert.schedule.ScheduleConverter;
import cn.iocoder.yudao.module.cinema.service.film.FilmService;
import cn.iocoder.yudao.module.cinema.service.theater.TheaterService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.module.cinema.service.schedule.ScheduleService;

@Tag(name = "用户 APP - 电影场次")
@RestController
@RequestMapping("/cinema/app/schedule")
@Validated
public class AppScheduleController {

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private ScheduleConverter scheduleConverter;

    @Resource
    private FilmService filmService;

    @Resource
    private TheaterService theaterService;

    @GetMapping("/list")
    @Operation(summary = "获得电影场次分页")
    @PermitAll
    public CommonResult<PageResult<AppScheduleRespVO>> getSchedulePage(@Valid AppSchedulePageReqVO pageReqVO) {
        // 需要将影片id和剧院id转换成名称，只能二次封装
        PageResult<ScheduleDO> pageResult = scheduleService.getSchedulePage(pageReqVO);
        PageResult<AppScheduleRespVO> pageResp = new PageResult<>();
        pageResp.setTotal(pageResult.getTotal());

        List<ScheduleDO> listResult = pageResult.getList();
        List<AppScheduleRespVO> listResp = new ArrayList<>();

        for(ScheduleDO schedule : listResult){
            AppScheduleRespVO resp = scheduleConverter.toa(schedule);
            resp.setMovieName(filmService.getFilm(schedule.getFId()).getName());
            resp.setTheaterName(theaterService.getTheater(schedule.getTheaterId()).getName());
            listResp.add(resp);
        }
        pageResp.setList(listResp);
        return success(pageResp);
    }

}