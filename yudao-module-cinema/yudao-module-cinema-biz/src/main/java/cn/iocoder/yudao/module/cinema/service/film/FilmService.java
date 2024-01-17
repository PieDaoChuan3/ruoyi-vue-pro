package cn.iocoder.yudao.module.cinema.service.film;

import java.util.*;
import javax.validation.*;

import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmSaveReqVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.film.FilmDO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 影片 Service 接口
 *
 * @author 芋道源码
 */
public interface FilmService {

    /**
     * 创建影片
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createFilm(@Valid FilmSaveReqVO createReqVO);

    /**
     * 更新影片
     *
     * @param updateReqVO 更新信息
     */
    void updateFilm(@Valid FilmSaveReqVO updateReqVO);

    /**
     * 删除影片
     *
     * @param id 编号
     */
    void deleteFilm(Integer id);

    /**
     * 获得影片
     *
     * @param id 编号
     * @return 影片
     */
    FilmDO getFilm(Integer id);

    /**
     * 获得影片分页
     *
     * @param pageReqVO 分页查询
     * @return 影片分页
     */
    PageResult<FilmDO> getFilmPage(FilmPageReqVO pageReqVO);

}