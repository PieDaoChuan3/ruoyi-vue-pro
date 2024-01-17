package cn.iocoder.yudao.module.cinema.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.exception.enums.ServiceErrorCodeRange;

public interface ErrorCodeConstants {
    ErrorCode FILM_NOT_EXISTS = new ErrorCode(1010000010, "影片不存在");

    ErrorCode SCHEDULE_NOT_EXISTS = new ErrorCode(1010000020, "电影场次不存在");

    ErrorCode SCHEDULE_TICKET_NOT_ENOUGH = new ErrorCode(1010000021, "该场次余票不足");

    ErrorCode STATISTICS_NOT_EXISTS = new ErrorCode(1010000030, "电影票销售统计id不存在");

    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(1010000040, "电影票订单不存在");

    ErrorCode THEATER_NOT_EXISTS = new ErrorCode(1010000050, "电影院不存在");
}
