package zhouxiong.demo.demo.infrastructure.status;

import zhouxiong.demo.demo.infrastructure.exception.CheckedException;
import zhouxiong.demo.demo.infrastructure.exception.ErrorInfo;

import java.util.Arrays;

/**
 * @Package zhouxiong.demo.demo.infrastructure.status
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 21:53
 **/
public enum ErrorInfoEnum implements ErrorInfo {

    /**
     * 车辆进入停车场信息存入数据库失败
     */
    ERROR(10001,"车辆进入停车场信息存入数据库失败，请检查！")
    ;

    ErrorInfoEnum(Integer code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    private String desc;
    private Integer code;

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 通过错误码获取枚举实例
     *
     * @param code 错误码
     * @return 枚举实例
     */
    public static ErrorInfoEnum getType(Integer code) {
        return Arrays.stream(ErrorInfoEnum.values()).filter(status -> status.getCode().equals(code)).findFirst()
                .orElseThrow(() -> new CheckedException("匹配不到正确的类型信息"));
    }
}
