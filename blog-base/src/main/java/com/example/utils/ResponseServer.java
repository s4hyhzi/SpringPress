package com.example.utils;

import com.example.entity.ServerEnum;

public class ResponseServer {
    private Integer code;
    private String message;
    private Object data;
    private Boolean success;

    private ResponseServer() {
    }

    private ResponseServer(Integer code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    private ResponseServer(Integer code, String message, Boolean success, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    /**
     * 返回默认的 成功状态 200
     *
     * @return
     */
    public static ResponseServer success() {
        return new ResponseServer(ServerEnum.SUCCESS.getCode(), ServerEnum.SUCCESS.getMessage(), ServerEnum.SUCCESS.getSuccess());
    }

    /**
     * 返回默认的带数据 成功状态 200
     *
     * @param data
     * @return
     */
    public static ResponseServer success(Object data) {
        return new ResponseServer(ServerEnum.SUCCESS.getCode(), ServerEnum.SUCCESS.getMessage(), ServerEnum.SUCCESS.getSuccess(), data);
    }

    /**
     * 其他特殊类型的成功状态，
     *
     * @param serverEnum
     * @return
     */
    public static ResponseServer success(ServerEnum serverEnum) {
        return new ResponseServer(serverEnum.getCode(), serverEnum.getMessage(), serverEnum.getSuccess());
    }

    /**
     * 带返回数据的其他特殊类型的成功状态
     *
     * @param serverEnum
     * @param data
     * @return
     */
    public static ResponseServer success(ServerEnum serverEnum, Object data) {
        return new ResponseServer(serverEnum.getCode(), serverEnum.getMessage(), serverEnum.getSuccess(), data);
    }


    //失败
    public static ResponseServer error(Integer code, String message, Boolean success) {
        return new ResponseServer(code, message, success);
    }

    /**
     * +     * @return
     */
    public static ResponseServer error() {
        return new ResponseServer(ServerEnum.ERROR.getCode(), ServerEnum.ERROR.getMessage(), ServerEnum.ERROR.getSuccess());
    }

    /**
     * 返回默认的带数据 失败状态 500
     *
     * @param data
     * @return
     */
    public static ResponseServer error(Object data) {
        return new ResponseServer(ServerEnum.ERROR.getCode(), ServerEnum.ERROR.getMessage(), ServerEnum.ERROR.getSuccess(), data);
    }

    /**
     * 其他特殊类型的失败状态，
     *
     * @param serverEnum
     * @return
     */
    public static ResponseServer error(ServerEnum serverEnum) {
        return new ResponseServer(serverEnum.getCode(), serverEnum.getMessage(), serverEnum.getSuccess());
    }

    /**
     * 带返回数据的其他特殊类型的失败状态
     *
     * @param serverEnum
     * @param data
     * @return
     */
    public static ResponseServer error(ServerEnum serverEnum, Object data) {
        return new ResponseServer(serverEnum.getCode(), serverEnum.getMessage(), serverEnum.getSuccess(), data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Boolean getSuccess(){
        return success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccess(Boolean success){
        this.success=success;
    }
}
