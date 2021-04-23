package zhouxiong.demo.demo.infrastructure.exception;

/**
 * @Package zhouxiong.demo.demo.infrastructure.exception
 * @Description:  错误信息接口
 * @author: zhouxiong
 * @create: 2021-04-23 20:59
 **/
public interface ErrorInfo {
    /**
     * 获取错误码
     *
     * @return 错误码
     */
    Integer getCode();

    /**
     * 获取错误详情
     *
     * @return 错误详情
     */
    String getDesc();
}
