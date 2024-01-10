package cn.iocoder.yudao.module.cinema.dal.mysql.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderPageReqVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cinema.controller.app.order.vo.*;

/**
 * 电影票订单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderMapper extends BaseMapperX<OrderDO> {

    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getCustomerId, reqVO.getCustomerId())
                .orderByAsc(OrderDO::getId));
    }

    default PageResult<OrderDO> selectPage(AppOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getCustomerId, reqVO.getCustomerId())
                .orderByAsc(OrderDO::getId));
    }

    default Long selectByTicketNo(long TicketNo){
        return selectCount("ticket_no", TicketNo);
    }

}