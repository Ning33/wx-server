package cn.hnisi.wx.core.utils;

import java.lang.annotation.*;

/**
 * 接收json数据格式的参数, 转换成对象
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface RequestBodyParam {
    public String value() default "";
}
