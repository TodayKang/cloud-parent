package com.cloud.lang.util;

import com.cloud.api.base.BaseResponse;
import com.cloud.lang.constant.Constant;
import org.apache.commons.lang3.BooleanUtils;

public class ResponseUtil {

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>(Boolean.TRUE, Constant.SUCCESS_CODE, Constant.SUCCESS_MESSAGE, null);
    }

    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<T>(Boolean.TRUE, Constant.SUCCESS_CODE, message, null);
    }

    public static <T> BaseResponse<T> success(T attributes) {
        return new BaseResponse<T>(Boolean.TRUE, Constant.SUCCESS_CODE, Constant.SUCCESS_MESSAGE, attributes);
    }

    public static <T> BaseResponse<T> success(String code, String message) {
        return new BaseResponse<T>(Boolean.TRUE, code, message, null);
    }

    public static <T> BaseResponse<T> success(String message, T attributes) {
        return new BaseResponse<T>(Boolean.TRUE, Constant.SUCCESS_CODE, message, attributes);
    }

    public static <T> BaseResponse<T> success(String code, String message, T attributes) {
        return new BaseResponse<T>(Boolean.TRUE, code, message, attributes);
    }

    public static <T> BaseResponse<T> error() {
        return new BaseResponse<T>(Boolean.FALSE, Constant.ERROR_CODE, Constant.ERROR_MESSAGE, null);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<T>(Boolean.FALSE, Constant.ERROR_CODE, message, null);
    }

    public static <T> BaseResponse<T> error(T attributes) {
        return new BaseResponse<T>(Boolean.FALSE, Constant.ERROR_CODE, Constant.ERROR_MESSAGE, attributes);
    }

    public static <T> BaseResponse<T> error(String code, String message) {
        return new BaseResponse<T>(Boolean.FALSE, code, message, null);
    }

    public static <T> BaseResponse<T> error(String message, T attributes) {
        return new BaseResponse<T>(Boolean.FALSE, Constant.ERROR_CODE, message, attributes);
    }

    public static <T> BaseResponse<T> error(String code, String message, T attributes) {
        return new BaseResponse<T>(Boolean.FALSE, code, message, attributes);
    }

    public static BaseResponse<Boolean> resp(Boolean status) {
        return new BaseResponse<Boolean>(BooleanUtils.toBoolean(status), //
                BooleanUtils.isTrue(status) ? Constant.SUCCESS_CODE : Constant.ERROR_CODE, //
                BooleanUtils.isTrue(status) ? Constant.SUCCESS_MESSAGE : Constant.ERROR_MESSAGE, status);
    }

    public static BaseResponse<Boolean> resp(String message, Boolean status) {
        return new BaseResponse<Boolean>(BooleanUtils.toBoolean(status), //
                BooleanUtils.isTrue(status) ? Constant.SUCCESS_CODE : Constant.ERROR_CODE, //
                message, status);
    }

    public static BaseResponse<Boolean> resp(String code, String message, Boolean status) {
        return new BaseResponse<Boolean>(BooleanUtils.toBoolean(status), //
                code, message, status);
    }

}
