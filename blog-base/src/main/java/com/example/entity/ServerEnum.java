package com.example.entity;

public enum ServerEnum {
    SUCCESS(200, "操作成功",true),
    DEL_DEPT_SCUCCESS(201, "删除成功",true),
    LOGIN_ISNULL(5000, "用户名或者密码为空",false),
    PHONE_ISNULL(5007, "手机号不能为空",false),
    USERNAME_NOTEXIST(5001, "用户名输入有误。",false),
    PASSWORD_WRONG(5002, "密码输入错误，请检查",false),
    LOGIN_SUCCESS(5003, "登陆成功",true),
    LOGIN_EXPIRED(5004, "登录超时，请重新登陆",false),
    SECRET_ERROR(5005, "传入的token值有误，不能通过签名验证",false),
    TOKEN_TIMEOUT(5006, "登录失效，请重新登录",false),
    TOKEN_ISNULL(5008, "获取到的Token值为空",false),
    NO_MENU_RIGHT(6000, "没有权限访问该菜单，请联系管理员",false),
    NOT_DATA(7001, "没有要导出的数据",false),
    HTTP_URL_ISNULL(8002, "你传递的URL路径为空了",false),
    SERVER_TIMEOUT(8004, "服务连接请求超时",false),
    HTTP_ERROR(8003, "接口访问失败",false),
    SERVER_STOP(8005, "服务连接不上",false),
    SAFETY_ERROR(9000, "接口验签失败",false),
    SAFETY_BAD(9001, "接口被非法攻击",false),
    SAFETY_TIMEOUT(9002, "接口访问超时",false),
    SAFETY_INVALID(9003, "签名值无效",false),
    SAFETY_REPLAY_ATTACK(9004, "接口被重放攻击",false),
    LOGIN_PHONEORCODE_INNULL(10000, "手机号或者验证码为空了",false),
    LOGIN_CODE_ERROR(10001, "手机验证码输入有误",false),
    ALL_STOCK_NULL(20001, "商品的库存都不足了",false),
    NO_ORDER_TO_PAY(20002, "没有要支付的订单",false),
    CRATER_PAY_ERROR(20003, "生成支付二维码失败",false),
    PAY_TIMEOUT(20004, "支付超时请刷新页面",false),
    ERROR(500, "操作失败",false);

    private ServerEnum(int code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    private Integer code;

    private String message;
    private Boolean success;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess(){
        return success;
    }
}
