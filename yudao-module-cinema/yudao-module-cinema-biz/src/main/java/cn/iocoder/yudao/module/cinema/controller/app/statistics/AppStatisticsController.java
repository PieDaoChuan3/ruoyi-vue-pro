package cn.iocoder.yudao.module.cinema.controller.app.statistics;

import cn.iocoder.yudao.framework.security.core.annotations.PreAuthenticated;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.AppScheduleRespVO;
import cn.iocoder.yudao.module.cinema.convert.statistic.StatisticsConverter;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.module.cinema.service.film.FilmService;
import cn.iocoder.yudao.module.cinema.service.schedule.ScheduleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.cinema.controller.app.statistics.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.statistics.StatisticsDO;
import cn.iocoder.yudao.module.cinema.service.statistics.StatisticsService;

@Tag(name = "用户 APP - 电影票销售统计id")
@RestController
@RequestMapping("/cinema/app/statistics")
@Validated
public class AppStatisticsController {

    @Resource
    private StatisticsService statisticsService;

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private StatisticsConverter statisticsConverter;

    @Resource
    private FilmService filmService;


    @GetMapping("/page")
    @Operation(summary = "获得电影票销售统计id分页")
    @PreAuthenticated
    public CommonResult<PageResult<AppStatisticsRespVO>> getStatisticsPage(@Valid AppStatisticsPageReqVO pageReqVO) {
        PageResult<StatisticsDO> pageResult = statisticsService.getStatisticsPage(pageReqVO);
        PageResult<AppStatisticsRespVO> pageResp = new PageResult<>();
        pageResp.setTotal(pageResult.getTotal());

        List<StatisticsDO> listResult = pageResult.getList();
        List<AppStatisticsRespVO> listResp = new ArrayList<>();

        for(StatisticsDO statistic : listResult){
            AppStatisticsRespVO resp = statisticsConverter.toa(statistic);
            Integer filmId = scheduleService.getSchedule(statistic.getScheduleId()).getFId();
            resp.setMovieName(filmService.getFilm(filmId).getName());
            resp.setShowTime(scheduleService.getSchedule(statistic.getScheduleId()).getShowTime());
            listResp.add(resp);
        }
        pageResp.setList(listResp);

        return success(pageResp);
    }

}