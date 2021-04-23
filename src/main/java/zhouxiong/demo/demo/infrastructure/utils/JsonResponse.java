package zhouxiong.demo.demo.infrastructure.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import zhouxiong.demo.demo.infrastructure.exception.ErrorInfo;

import java.io.Serializable;

/**
 * @Package zhouxiong.demo.demo.infrastructure.utils
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 20:57
 **/
@ApiModel("返回结果")
public class JsonResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    @ApiModelProperty("信息")
    private String msg = "success";

    @ApiModelProperty("编码，0代表成功")
    private int code = SUCCESS;

    @ApiModelProperty("实体数据")
    private T data;

    public JsonResponse() {
        super();
    }

    public JsonResponse(T data) {
        super();
        this.data = data;
    }

    public JsonResponse(ErrorInfo errorInfo) {
        super();
        this.msg = errorInfo.getDesc();
        this.code = errorInfo.getCode();
    }

    public JsonResponse(T data, ErrorInfo errorInfo) {
        super();
        this.data = data;
        this.msg = errorInfo.getDesc();
        this.code = errorInfo.getCode();
    }

    public JsonResponse(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public JsonResponse(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public JsonResponse(int code, String message, T data) {
        super();
        this.msg = message;
        this.code = code;
        this.data = data;
    }

    public static <T> JsonResponse<T> success() {
        return new JsonResponse<>();
    }

    public static <T> JsonResponse<T> success(T data) {
        return new JsonResponse<>(data);
    }

    public static <T> JsonResponse<T> success(T data, String message) {
        return new JsonResponse<>(data, message);
    }

    public static <T> JsonResponse<T> error(ErrorInfo errorInfo) {
        return new JsonResponse<>(errorInfo);
    }

    public static <T> JsonResponse<T> error(T data, ErrorInfo errorInfo) {
        return new JsonResponse<>(data, errorInfo);
    }

    public static <T> JsonResponse<T> error(int code, String message, T data) {
        return new JsonResponse<>(code, message, data);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
