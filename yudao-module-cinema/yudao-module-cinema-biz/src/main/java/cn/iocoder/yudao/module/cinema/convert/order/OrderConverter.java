package cn.iocoder.yudao.module.cinema.convert.order;

import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderRespVO;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderSaveReqVO;
import cn.iocoder.yudao.module.cinema.controller.app.order.vo.AppOrderPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.app.order.vo.AppOrderRespVO;
import cn.iocoder.yudao.module.cinema.controller.app.order.vo.AppOrderSaveReqVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.order.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderConverter {
    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    OrderSaveReqVO to(AppOrderSaveReqVO saveReqVO);

    OrderPageReqVO to(AppOrderPageReqVO pageReqVO);

    OrderDO to(OrderSaveReqVO saveReqVO);

    OrderDO toa(AppOrderSaveReqVO saveReqVO);

    OrderRespVO to(OrderDO order);
    AppOrderRespVO toa(OrderDO order);
}
