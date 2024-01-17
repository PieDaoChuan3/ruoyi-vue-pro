package cn.iocoder.yudao.module.cinema.enums;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CUDEnum {


    /**
     * 创建标记
     */
    CREATE(1),
    /**
     * 更新标记
     */
    UPDATE(0),
    /**
     * 删除标记
     */
    DELETE(-1);

    private final Integer status;

}