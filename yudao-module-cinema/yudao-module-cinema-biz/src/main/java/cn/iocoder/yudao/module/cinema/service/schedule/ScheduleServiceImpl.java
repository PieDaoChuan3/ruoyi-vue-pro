package cn.iocoder.yudao.module.cinema.service.schedule;

import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.ScheduleSaveReqVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cinema.dal.mysql.schedule.ScheduleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;

/**
 * 电影场次 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createSchedule(ScheduleSaveReqVO createReqVO) {
        // 插入
        ScheduleDO schedule = BeanUtils.toBean(createReqVO, ScheduleDO.class);
        scheduleMapper.insert(schedule);
        // 返回
        return schedule.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSchedule(ScheduleSaveReqVO updateReqVO) {
        // 校验存在
        validateScheduleExists(updateReqVO.getId());
        // 更新
        ScheduleDO updateObj = BeanUtils.toBean(updateReqVO, ScheduleDO.class);
        scheduleMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSchedule(Integer id) {
        // 校验存在
        validateScheduleExists(id);
        // 删除
        scheduleMapper.deleteById(id);
    }

    private void validateScheduleExists(Integer id) {
        if (scheduleMapper.selectById(id) == null) {
            throw exception(SCHEDULE_NOT_EXISTS);
        }
    }

    @Override
    public ScheduleDO getSchedule(Integer id) {
        return scheduleMapper.selectById(id);
    }

    @Override
    public PageResult<ScheduleDO> getSchedulePage(AppSchedulePageReqVO pageReqVO) {
        return scheduleMapper.selectPage(pageReqVO);
    }

}