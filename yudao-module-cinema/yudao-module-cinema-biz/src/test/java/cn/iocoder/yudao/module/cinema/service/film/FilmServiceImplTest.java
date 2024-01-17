package cn.iocoder.yudao.module.cinema.service.film;

import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmPageReqVO;
import cn.iocoder.yudao.module.cinema.controller.admin.film.vo.FilmSaveReqVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cinema.dal.dataobject.film.FilmDO;
import cn.iocoder.yudao.module.cinema.dal.mysql.film.FilmMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;

import static cn.iocoder.yudao.module.cinema.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link FilmServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(FilmServiceImpl.class)
public class FilmServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FilmServiceImpl filmService;

    @Resource
    private FilmMapper filmMapper;

    @Test
    public void testCreateFilm_success() {
        // 准备参数
        FilmSaveReqVO createReqVO = randomPojo(FilmSaveReqVO.class).setId(null);

        // 调用
        Integer filmId = filmService.createFilm(createReqVO);
        // 断言
        assertNotNull(filmId);
        // 校验记录的属性是否正确
        FilmDO film = filmMapper.selectById(filmId);
        assertPojoEquals(createReqVO, film, "id");
    }

    @Test
    public void testUpdateFilm_success() {
        // mock 数据
        FilmDO dbFilm = randomPojo(FilmDO.class);
        filmMapper.insert(dbFilm);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FilmSaveReqVO updateReqVO = randomPojo(FilmSaveReqVO.class, o -> {
            o.setId(dbFilm.getId()); // 设置更新的 ID
        });

        // 调用
        filmService.updateFilm(updateReqVO);
        // 校验是否更新正确
        FilmDO film = filmMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, film);
    }

    @Test
    public void testUpdateFilm_notExists() {
        // 准备参数
        FilmSaveReqVO updateReqVO = randomPojo(FilmSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> filmService.updateFilm(updateReqVO), FILM_NOT_EXISTS);
    }

    @Test
    public void testDeleteFilm_success() {
        // mock 数据
        FilmDO dbFilm = randomPojo(FilmDO.class);
        filmMapper.insert(dbFilm);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbFilm.getId();

        // 调用
        filmService.deleteFilm(id);
       // 校验数据不存在了
       assertNull(filmMapper.selectById(id));
    }

    @Test
    public void testDeleteFilm_notExists() {
        // 准备参数
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> filmService.deleteFilm(id), FILM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFilmPage() {
       // mock 数据
       FilmDO dbFilm = randomPojo(FilmDO.class, o -> { // 等会查询到
           o.setId(null);
           o.setName(null);
           o.setClassify(null);
       });
       filmMapper.insert(dbFilm);
       // 测试 id 不匹配
       filmMapper.insert(cloneIgnoreId(dbFilm, o -> o.setId(null)));
       // 测试 name 不匹配
       filmMapper.insert(cloneIgnoreId(dbFilm, o -> o.setName(null)));
       // 测试 classify 不匹配
       filmMapper.insert(cloneIgnoreId(dbFilm, o -> o.setClassify(null)));
       // 准备参数
       FilmPageReqVO reqVO = new FilmPageReqVO();
//       reqVO.setId(null);
//       reqVO.setName(null);
//       reqVO.setClassify(null);

       // 调用
       PageResult<FilmDO> pageResult = filmService.getFilmPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFilm, pageResult.getList().get(0));
    }

}