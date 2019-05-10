package cn.caoler.evaluation.utils;

import cn.caoler.evaluation.enums.CodeEnum;

/**
 * Created by caole on 2019/3/17 15:14
 */
public class EnumUtil {

    /**
     * 通过code和枚举类型获取枚举
     * @param code code
     * @param enumClass 枚举类型class
     * @param <T>  枚举类型
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each: enumClass.getEnumConstants()){//遍历枚举类型
            if(each.getCode().equals(code)){
                return each;
            }
        }
        return  null;
    }
}
