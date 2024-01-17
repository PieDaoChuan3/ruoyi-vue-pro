package cn.iocoder.yudao.module.cinema.controller.app.order;

import cn.iocoder.yudao.framework.idempotent.core.annotation.Idempotent;
import cn.iocoder.yudao.framework.security.core.annotations.PreAuthenticated;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderRespVO;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderSaveReqVO;
import cn.iocoder.yudao.module.cinema.convert.order.OrderConverter;
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
import java.time.LocalDateTime;
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

import cn.iocoder.yudao.module.cinema.controller.app.order.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.module.cinema.service.order.OrderService;

@Tag(name = "用户 APP - 电影票订单")
@RestController
@RequestMapping("/cinema/app/order")
@Validated
public class AppOrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private FilmService filmService;

    @Resource
    private OrderConverter orderConverter;

    @PostMapping("/create")
    @Operation(summary = "创建电影票订单")
    @PreAuthenticated
    @Idempotent
    public CommonResult<Long> createOrder(@Valid @RequestBody AppOrderSaveReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @GetMapping("/page")
    @Operation(summary = "获得当前用户电影票订单分页")
    @PreAuthenticated
    public CommonResult<PageResult<AppOrderRespVO>> getOrderPage(@Valid AppOrderPageReqVO pageReqVO) {
        OrderPageReqVO reqVo = orderConverter.to(pageReqVO);
        PageResult<OrderDO> pageResult = orderService.getOrderPage(reqVo);

        PageResult<AppOrderRespVO> pageResp = new PageResult<>();
        pageResp.setTotal(pageResult.getTotal());

        List<OrderDO> listResult = pageResult.getList();
        List<AppOrderRespVO> listResp = new ArrayList<>();

        for(OrderDO order : listResult){
            AppOrderRespVO Resp = orderConverter.toa(order);
            Integer filmId = scheduleService.getSchedule(order.getScheduleId()).getFId();
            Resp.setMovieName(filmService.getFilm(filmId).getName());
            Resp.setShowTime(scheduleService.getSchedule(order.getScheduleId()).getShowTime());
            listResp.add(Resp);
        }
        pageResp.setList(listResp);

        return success(pageResp);
    }

}