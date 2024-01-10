package cn.iocoder.yudao.module.cinema.dal.mysql.film;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmPageReqVO;
import cn.iocoder.yudao.module.cinema.dal.dataobject.film.FilmDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 影片 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FilmMapper extends BaseMapperX<FilmDO> {

    default PageResult<FilmDO> selectPage(FilmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FilmDO>()
//                .eqIfPresent(FilmDO::getId, reqVO.getId())
//                .likeIfPresent(FilmDO::getName, reqVO.getName())
//                .eqIfPresent(FilmDO::getClassify, reqVO.getClassify())
                .orderByAsc(FilmDO::getId));
    }

}