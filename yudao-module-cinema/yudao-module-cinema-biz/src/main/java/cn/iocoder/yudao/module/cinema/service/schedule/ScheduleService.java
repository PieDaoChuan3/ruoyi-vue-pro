package cn.iocoder.yudao.module.cinema.service.schedule;

import java.util.*;
import javax.validation.*;

import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.ScheduleSaveReqVO;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.*;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 电影场次 Service 接口
 *
 * @author 芋道源码
 */
public interface ScheduleService {

    /**
     * 创建电影场次
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createSchedule(@Valid ScheduleSaveReqVO createReqVO);

    /**
     * 更新电影场次
     *
     * @param updateReqVO 更新信息
     */
    void updateSchedule(@Valid ScheduleSaveReqVO updateReqVO);

    /**
     * 删除电影场次
     *
     * @param id 编号
     */
    void deleteSchedule(Integer id);

    /**
     * 获得电影场次
     *
     * @param id 编号
     * @return 电影场次
     */
    ScheduleDO getSchedule(Integer id);

    /**
     * 获得电影场次分页
     *
     * @param pageReqVO 分页查询
     * @return 电影场次分页
     */
    PageResult<ScheduleDO> getSchedulePage(AppSchedulePageReqVO pageReqVO);

}