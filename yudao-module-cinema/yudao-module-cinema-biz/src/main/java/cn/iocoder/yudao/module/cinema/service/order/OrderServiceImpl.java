package cn.iocoder.yudao.module.cinema.service.order;

import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderSaveReqVO;
import cn.iocoder.yudao.module.cinema.convert.order.OrderConverter;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.statistics.StatisticsDO;
import cn.iocoder.yudao.module.cinema.dal.mysql.schedule.ScheduleMapper;
import cn.iocoder.yudao.module.cinema.dal.mysql.statistics.StatisticsMapper;
import cn.iocoder.yudao.module.cinema.enums.CUDEnum;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import cn.iocoder.yudao.module.cinema.controller.app.order.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cinema.dal.mysql.order.OrderMapper;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;

/**
 * 电影票订单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StatisticsMapper statisticsMapper;

    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(OrderSaveReqVO createReqVO) {
        OrderDO order = OrderConverter.INSTANCE.to(createReqVO);
        // 校验余票并更新场次记录,获取票价
        int price = updateQuota(order, CUDEnum.CREATE);
        // 插入
        orderMapper.insert(order);
        // 新建/更新销售统计
        if(containsStatistics(order.getScheduleId())){
            statisticsRestriction(order, CUDEnum.UPDATE);
        }else{
            statisticsRestriction(order, CUDEnum.CREATE);
        }
        // 返回
        return order.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(AppOrderSaveReqVO createReqVO) {
        OrderDO order = OrderConverter.INSTANCE.toa(createReqVO);
        // 校验余票并更新场次记录,获取票价
        int price = updateQuota(order, CUDEnum.CREATE);
        order.setPrice(price);
        order.setOrderTime(LocalDateTime.now());
        order.setTicketNo(generateTicketNum());
        // 插入
        orderMapper.insert(order);
        // 新建/更新销售统计
        if(containsStatistics(order.getScheduleId())){
            statisticsRestriction(order, CUDEnum.UPDATE);
        }else{
            statisticsRestriction(order, CUDEnum.CREATE);
        }
        // 返回
        return order.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrder(OrderSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 保留删除订单快照
        OrderDO snapshotOrder = orderMapper.selectById(updateReqVO.getId());
        // 更新
        OrderDO updateObj = BeanUtils.toBean(updateReqVO, OrderDO.class);
        orderMapper.updateById(updateObj);
        // 更新销售统计和场次记录
        int quality_new = updateObj.getQuality() - snapshotOrder.getQuality();
        int price_new = updateObj.getPrice() - snapshotOrder.getPrice();
        updateObj.setQuality(quality_new);
        updateObj.setPrice(price_new);
        updateQuota(updateObj, CUDEnum.CREATE);
        statisticsRestriction(updateObj, CUDEnum.UPDATE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 保留删除订单快照
        OrderDO snapshotOrder = orderMapper.selectById(id);
        // 删除
        orderMapper.deleteById(id);
        // 更改销售统计和场次记录
        updateQuota(snapshotOrder, CUDEnum.DELETE);
        statisticsRestriction(snapshotOrder, CUDEnum.DELETE);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }
    private String generateTicketNum(){
        // 从长度为8开始生成票号，连续撞库五次增加一位
        int len = 10000000;
        int count = 0;
        long TicketNum = (long) ((Math.random() * 9 + 1) * len);
        while(orderMapper.selectByTicketNo(TicketNum) != 0L){
            count++;
            if(count == 5){
                len *= 10;
                count = 0;
            }
            TicketNum = (long) ((Math.random() * 9 + 1) * len);
        }
        return Long.toString(TicketNum);
    }
    private int updateQuota(OrderDO order, CUDEnum operation){
        ScheduleDO schedule = scheduleMapper.selectById(order.getScheduleId());
        if(schedule.getQuota() >= order.getQuality()){
            if(operation == CUDEnum.CREATE){
                schedule.setQuota(schedule.getQuota() - order.getQuality());
            }else{
                schedule.setQuota(schedule.getQuota() + order.getQuality());
            }
            scheduleMapper.updateById(schedule);
            return schedule.getPrice();
        }else{
            throw exception(SCHEDULE_TICKET_NOT_ENOUGH);
        }
    }

    @Override
    public OrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<OrderDO> getOrderPage(AppOrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（销售统计） ====================

    private boolean containsStatistics(int scheduleId) {
        return statisticsMapper.findByScheduleId(scheduleId) > 0;
    }

    @CacheEvict(value = "statistics", key = "#order.scheduleId")
    private void statisticsRestriction(OrderDO order, CUDEnum operation){
        StatisticsDO statistic = new StatisticsDO();
        int quality = order.getQuality();
        int amount = order.getPrice() * order.getQuality();
        int scheduleId = order.getScheduleId();

        statistic.setScheduleId(scheduleId);
        switch (operation){
            case UPDATE:
                StatisticsDO statistic_prev = statisticsMapper.selectByScheduleId(scheduleId);
                statistic.setId(statistic_prev.getId());// 否则会变成新插入记录而不是更新
                statistic.setQuality(statistic_prev.getQuality() + quality);
                statistic.setAmount(statistic_prev.getAmount() + amount);
                break;
            case CREATE:
                statistic.setQuality(quality);
                statistic.setAmount(amount);
                break;
            case DELETE:
                statistic_prev = statisticsMapper.selectByScheduleId(scheduleId);
                statistic.setId(statistic_prev.getId());
                statistic.setQuality(statistic_prev.getQuality() - quality);
                statistic.setAmount(statistic_prev.getAmount() - amount);
        }
        statisticsMapper.insertOrUpdate(statistic);
    }
}