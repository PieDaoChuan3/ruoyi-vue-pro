package cn.iocoder.yudao.module.cinema.dal.mysql.theater;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterPageReqVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.theater.TheaterDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 电影院 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TheaterMapper extends BaseMapperX<TheaterDO> {

    default PageResult<TheaterDO> selectPage(TheaterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TheaterDO>()
                .orderByAsc(TheaterDO::getId));
    }

}