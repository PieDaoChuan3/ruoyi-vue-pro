package cn.iocoder.yudao.module.cinema.service.theater;

import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterSaveReqVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cinema.dal.dataobject.theater.TheaterDO;
import cn.iocoder.yudao.module.cinema.dal.mysql.theater.TheaterMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link TheaterServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TheaterServiceImpl.class)
public class TheaterServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TheaterServiceImpl theaterService;

    @Resource
    private TheaterMapper theaterMapper;

    @Test
    public void testCreateTheater_success() {
        // 准备参数
        TheaterSaveReqVO createReqVO = randomPojo(TheaterSaveReqVO.class).setId(null);

        // 调用
        Integer theaterId = theaterService.createTheater(createReqVO);
        // 断言
        assertNotNull(theaterId);
        // 校验记录的属性是否正确
        TheaterDO theater = theaterMapper.selectById(theaterId);
        assertPojoEquals(createReqVO, theater, "id");
    }

    @Test
    public void testUpdateTheater_success() {
        // mock 数据
        TheaterDO dbTheater = randomPojo(TheaterDO.class);
        theaterMapper.insert(dbTheater);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TheaterSaveReqVO updateReqVO = randomPojo(TheaterSaveReqVO.class, o -> {
            o.setId(dbTheater.getId()); // 设置更新的 ID
        });

        // 调用
        theaterService.updateTheater(updateReqVO);
        // 校验是否更新正确
        TheaterDO theater = theaterMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, theater);
    }

    @Test
    public void testUpdateTheater_notExists() {
        // 准备参数
        TheaterSaveReqVO updateReqVO = randomPojo(TheaterSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> theaterService.updateTheater(updateReqVO), THEATER_NOT_EXISTS);
    }

    @Test
    public void testDeleteTheater_success() {
        // mock 数据
        TheaterDO dbTheater = randomPojo(TheaterDO.class);
        theaterMapper.insert(dbTheater);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbTheater.getId();

        // 调用
        theaterService.deleteTheater(id);
       // 校验数据不存在了
       assertNull(theaterMapper.selectById(id));
    }

    @Test
    public void testDeleteTheater_notExists() {
        // 准备参数
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> theaterService.deleteTheater(id), THEATER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTheaterPage() {
       // mock 数据
       TheaterDO dbTheater = randomPojo(TheaterDO.class, o -> { // 等会查询到
           o.setId(null);
       });
       theaterMapper.insert(dbTheater);
       // 测试 id 不匹配
       theaterMapper.insert(cloneIgnoreId(dbTheater, o -> o.setId(null)));
       // 准备参数
       TheaterPageReqVO reqVO = new TheaterPageReqVO();

       // 调用
       PageResult<TheaterDO> pageResult = theaterService.getTheaterPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTheater, pageResult.getList().get(0));
    }

}