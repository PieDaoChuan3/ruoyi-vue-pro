package cn.iocoder.yudao.module.cinema.service.film;

import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmSaveReqVO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.module.cinema.dal.dataobject.film.FilmDO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.schedule.ScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cinema.dal.mysql.film.FilmMapper;
import cn.iocoder.yudao.module.cinema.dal.mysql.schedule.ScheduleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;

/**
 * 影片 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@CacheConfig(cacheNames = "Film")
public class FilmServiceImpl implements FilmService {

    @Resource
    private FilmMapper filmMapper;
    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createFilm(FilmSaveReqVO createReqVO) {
        // 插入
        FilmDO film = BeanUtils.toBean(createReqVO, FilmDO.class);
        filmMapper.insert(film);
        // 返回
        return film.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#updateReqVO.id")
    public void updateFilm(FilmSaveReqVO updateReqVO) {
        // 校验存在
        validateFilmExists(updateReqVO.getId());
        // 更新
        FilmDO updateObj = BeanUtils.toBean(updateReqVO, FilmDO.class);
        filmMapper.updateById(updateObj);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#id")
    public void deleteFilm(Integer id) {
        // 校验存在
        validateFilmExists(id);
        // 删除
        filmMapper.deleteById(id);

        // 删除子表
        deleteScheduleByFId(id);
    }

    private void validateFilmExists(Integer id) {
        if (filmMapper.selectById(id) == null) {
            throw exception(FILM_NOT_EXISTS);
        }
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public FilmDO getFilm(Integer id) {
        return filmMapper.selectById(id);
    }


    @Override
    @Cacheable(key = "#pageReqVO.pageNO + '_' + #pageReqVO.pageSize", unless = "#result == null")
    public PageResult<FilmDO> getFilmPage(FilmPageReqVO pageReqVO) {
        return filmMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（电影场次） ====================

    private void deleteScheduleByFId(Integer fId) {
        scheduleMapper.deleteByFId(fId);
    }

}