package cn.iocoder.yudao.module.cinema.service.theater;

import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterSaveReqVO;
import cn.iocoder.yudao.module.cinema.dal.mysql.schedule.ScheduleMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.theater.TheaterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cinema.dal.mysql.theater.TheaterMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;

/**
 * 电影院 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@CacheConfig(cacheNames = "theater")
public class TheaterServiceImpl implements TheaterService {

    @Resource
    private TheaterMapper theaterMapper;

    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    public Integer createTheater(TheaterSaveReqVO createReqVO) {
        // 插入
        TheaterDO theater = BeanUtils.toBean(createReqVO, TheaterDO.class);
        theaterMapper.insert(theater);
        // 返回
        return theater.getId();
    }

    @Override
    @CacheEvict(key = "#updateReqVO.id")
    public void updateTheater(TheaterSaveReqVO updateReqVO) {
        // 校验存在
        validateTheaterExists(updateReqVO.getId());
        // 更新
        TheaterDO updateObj = BeanUtils.toBean(updateReqVO, TheaterDO.class);
        theaterMapper.updateById(updateObj);
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteTheater(Integer id) {
        // 校验存在
        validateTheaterExists(id);
        // 删除
        theaterMapper.deleteById(id);

        // 删除子表
        deleteScheduleByTheaterId(id);
    }

    private void validateTheaterExists(Integer id) {
        if (theaterMapper.selectById(id) == null) {
            throw exception(THEATER_NOT_EXISTS);
        }
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public TheaterDO getTheater(Integer id) {
        return theaterMapper.selectById(id);
    }

    @Override
    @Cacheable(key = "#pageReqVO.cacheKey()", unless = "#result == null")
    public PageResult<TheaterDO> getTheaterPage(TheaterPageReqVO pageReqVO) {
        return theaterMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（电影场次） ====================

    private void deleteScheduleByTheaterId(Integer TheaterId) {
        scheduleMapper.deleteByTheaterId(TheaterId);
    }
}