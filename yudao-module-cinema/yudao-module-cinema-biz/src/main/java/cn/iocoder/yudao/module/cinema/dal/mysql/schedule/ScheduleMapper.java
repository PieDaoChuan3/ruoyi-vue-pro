package cn.iocoder.yudao.module.cinema.dal.mysql.schedule;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.*;

/**
 * 电影场次 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ScheduleMapper extends BaseMapperX<ScheduleDO> {

    default PageResult<ScheduleDO> selectPage(AppSchedulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ScheduleDO>()
                .orderByAsc(ScheduleDO::getId));
    }

    default void deleteByFId(Integer fId){
        delete("f_id", String.valueOf(fId));
    }

    default void deleteByTheaterId(Integer TheaterId){
        delete("theater_id", String.valueOf(TheaterId));
    }

}