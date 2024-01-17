package cn.iocoder.yudao.module.cinema.convert.schedule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.SchedulePageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.schedule.vo.ScheduleRespVO;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.AppSchedulePageReqVO;
import cn.iocoder.yudao.module.cinema.controller.app.schedule.vo.AppScheduleRespVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleConverter {
    ScheduleConverter INSTANCE = Mappers.getMapper(ScheduleConverter.class);

    AppSchedulePageReqVO to(SchedulePageReqVO pageReqVO);

    @Mappings({
            @Mapping(source = "id", target = "sessionId")
    })
    ScheduleRespVO to(ScheduleDO schedule);

    @Mappings({
            @Mapping(source = "id", target = "sessionId")
    })
    AppScheduleRespVO toa(ScheduleDO schedule);
}
