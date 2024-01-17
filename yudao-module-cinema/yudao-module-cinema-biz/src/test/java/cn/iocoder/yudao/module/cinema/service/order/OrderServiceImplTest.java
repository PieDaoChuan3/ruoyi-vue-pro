package cn.iocoder.yudao.module.cinema.service.order;

import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.order.vo.OrderSaveReqVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cinema.controller.app.order.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.module.cinema.dal.mysql.order.OrderMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;

import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link OrderServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(OrderServiceImpl.class)
public class OrderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private OrderServiceImpl orderService;

    @Resource
    private OrderMapper orderMapper;


    @Test
    public void testUpdateOrder_success() {
        // mock 数据
        OrderDO dbOrder = randomPojo(OrderDO.class);
        orderMapper.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OrderSaveReqVO updateReqVO = randomPojo(OrderSaveReqVO.class, o -> {
            o.setId(dbOrder.getId()); // 设置更新的 ID
        });

        // 调用
        orderService.updateOrder(updateReqVO);
        // 校验是否更新正确
        OrderDO order = orderMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, order);
    }

    @Test
    public void testUpdateOrder_notExists() {
        // 准备参数
        OrderSaveReqVO updateReqVO = randomPojo(OrderSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderService.updateOrder(updateReqVO), ORDER_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrder_success() {
        // mock 数据
        OrderDO dbOrder = randomPojo(OrderDO.class);
        orderMapper.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOrder.getId();

        // 调用
        orderService.deleteOrder(id);
       // 校验数据不存在了
       assertNull(orderMapper.selectById(id));
    }

    @Test
    public void testDeleteOrder_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> orderService.deleteOrder(id), ORDER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderPage() {
       // mock 数据
       OrderDO dbOrder = randomPojo(OrderDO.class, o -> { // 等会查询到
           o.setCustomerId(null);
       });
       orderMapper.insert(dbOrder);
       // 测试 customerId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCustomerId(null)));
       // 准备参数
       OrderPageReqVO reqVO = new OrderPageReqVO();
       reqVO.setCustomerId(null);

       // 调用
       PageResult<OrderDO> pageResult = orderService.getOrderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrder, pageResult.getList().get(0));
    }

}