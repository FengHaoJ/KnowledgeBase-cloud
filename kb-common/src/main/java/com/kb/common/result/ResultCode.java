package com.kb.common.result;

public class ResultCode {
    // 成功响应
    public static final int SUCCESS = 200; // 请求成功

    // 客户端错误
    public static final int BAD_REQUEST = 400; // 请求无效
    public static final int UNAUTHORIZED = 401; // 未授权
    public static final int FORBIDDEN = 403; // 禁止访问
    public static final int NOT_FOUND = 404; // 资源未找到

    // 服务器错误
    public static final int INTERNAL_SERVER_ERROR = 500; // 服务器内部错误
    public static final int SERVICE_UNAVAILABLE = 503; // 服务不可用

    // 自定义业务错误
    public static final int VALIDATION_ERROR = 1000; // 业务逻辑校验错误
    public static final int DATA_PROCESSING_ERROR = 1001; // 数据处理错误
    public static final int OPERATION_FAILED = 1002; // 操作失败
}

