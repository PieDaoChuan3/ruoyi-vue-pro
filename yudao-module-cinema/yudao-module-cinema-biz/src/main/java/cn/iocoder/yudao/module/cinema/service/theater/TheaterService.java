package cn.iocoder.yudao.module.cinema.service.theater;

import java.util.*;
import javax.validation.*;

import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.theater.vo.TheaterSaveReqVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.theater.TheaterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 电影院 Service 接口
 *
 * @author 芋道源码
 */
public interface TheaterService {

    /**
     * 创建电影院
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createTheater(@Valid TheaterSaveReqVO createReqVO);

    /**
     * 更新电影院
     *
     * @param updateReqVO 更新信息
     */
    void updateTheater(@Valid TheaterSaveReqVO updateReqVO);

    /**
     * 删除电影院
     *
     * @param id 编号
     */
    void deleteTheater(Integer id);

    /**
     * 获得电影院
     *
     * @param id 编号
     * @return 电影院
     */
    TheaterDO getTheater(Integer id);

    /**
     * 获得电影院分页
     *
     * @param pageReqVO 分页查询
     * @return 电影院分页
     */
    PageResult<TheaterDO> getTheaterPage(TheaterPageReqVO pageReqVO);

}