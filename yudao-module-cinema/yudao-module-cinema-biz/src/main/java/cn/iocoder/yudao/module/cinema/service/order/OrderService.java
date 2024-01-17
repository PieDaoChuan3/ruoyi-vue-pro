package cn.iocoder.yudao.module.cinema.service.order;

import javax.validation.*;

import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderSaveReqVO;
import cn.iocoder.yudao.module.cinema.controller.app.order.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 电影票订单 Service 接口
 *
 * @author 芋道源码
 */
public interface OrderService {

    /**
     * 创建电影票订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid OrderSaveReqVO createReqVO);

    Long createOrder(@Valid AppOrderSaveReqVO createReqVO);

    /**
     * 更新电影票订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid OrderSaveReqVO updateReqVO);

    /**
     * 删除电影票订单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得电影票订单
     *
     * @param id 编号
     * @return 电影票订单
     */
    OrderDO getOrder(Long id);

    /**
     * 获得电影票订单分页
     *
     * @param pageReqVO 分页查询
     * @return 电影票订单分页
     */
    PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO);

    PageResult<OrderDO> getOrderPage(AppOrderPageReqVO pageReqVO);

}