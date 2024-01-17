package cn.iocoder.yudao.module.cinema.controller.admin.order;

import cn.hutool.db.sql.Order;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.framework.security.core.annotations.PreAuthenticated;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.SchedulePageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.ScheduleRespVO;
import cn.iocoder.yudao.module.cinema.controller.app.order.vo.AppOrderRespVO;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.AppSchedulePageReqVO;
import cn.iocoder.yudao.module.cinema.controller.app.statistics.vo.AppStatisticsRespVO;
import cn.iocoder.yudao.module.cinema.convert.order.OrderConverter;
import cn.iocoder.yudao.module.cinema.convert.schedule.ScheduleConverter;
import cn.iocoder.yudao.module.cinema.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.statistics.StatisticsDO;
import cn.iocoder.yudao.module.cinema.service.film.FilmService;
import cn.iocoder.yudao.module.cinema.service.order.OrderService;
import cn.iocoder.yudao.module.cinema.service.schedule.ScheduleService;
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

@Tag(name = "后台管理 - 订单")
@RestController
@RequestMapping("/cinema/order")
@Validated
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private OrderConverter orderConverter;

    @Resource
    private FilmService filmService;

    @PostMapping("/create")
    @Operation(summary = "创建订单")
    @PreAuthorize("@ss.hasPermission('cinema:order:create')")
    public CommonResult<Long> createFilm(@Valid @RequestBody OrderSaveReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单")
    @PreAuthorize("@ss.hasPermission('cinema:order:update')")
    public CommonResult<Boolean> updateFilm(@Valid @RequestBody OrderSaveReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cinema:film:delete')")
    public CommonResult<Boolean> deleteFilm(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cinema:film:get')")
    public CommonResult<OrderRespVO> getFilm(@RequestParam("id") Long id) {
        OrderDO order = orderService.getOrder(id);
        OrderRespVO Resp = orderConverter.to(order);
        Integer filmId = scheduleService.getSchedule(order.getScheduleId()).getFId();
        Resp.setMovieName(filmService.getFilm(filmId).getName());
        Resp.setShowTime(scheduleService.getSchedule(order.getScheduleId()).getShowTime());
        return success(Resp);
    }

    @GetMapping("/pages")
    @Operation(summary = "获得电影票订单分页")
    @PreAuthorize("@ss.hasPermission('cinema:film:page')")
    public CommonResult<PageResult<OrderRespVO>> getOrderPage(@Valid OrderPageReqVO pageReqVO) {
        PageResult<OrderDO> pageResult = orderService.getOrderPage(pageReqVO);
        PageResult<OrderRespVO> pageResp = new PageResult<>();
        pageResp.setTotal(pageResult.getTotal());

        List<OrderDO> listResult = pageResult.getList();
        List<OrderRespVO> listResp = new ArrayList<>();

        for(OrderDO order : listResult){
            OrderRespVO Resp = orderConverter.to(order);
            Integer filmId = scheduleService.getSchedule(order.getScheduleId()).getFId();
            Resp.setMovieName(filmService.getFilm(filmId).getName());
            Resp.setShowTime(scheduleService.getSchedule(order.getScheduleId()).getShowTime());
            listResp.add(Resp);
        }
        pageResp.setList(listResp);

        return success(pageResp);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出电影场次 Excel")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@ss.hasPermission('cinema:schedule:export-excel')")
    public void exportFilmExcel(@Valid OrderPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderDO> list = orderService.getOrderPage(pageReqVO).getList();

        List<OrderRespVO> listResp = new ArrayList<>();

        for(OrderDO order : list){
            OrderRespVO Resp = orderConverter.to(order);
            Integer filmId = scheduleService.getSchedule(order.getScheduleId()).getFId();
            Resp.setMovieName(filmService.getFilm(filmId).getName());
            Resp.setShowTime(scheduleService.getSchedule(order.getScheduleId()).getShowTime());
            listResp.add(Resp);
        }

        // 导出 Excel
        ExcelUtils.write(response, "电影场次.xls", "数据", OrderRespVO.class,
                listResp);
    }
}