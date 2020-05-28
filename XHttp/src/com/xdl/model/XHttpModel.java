package com.xdl.model;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huboxin
 * @title: XHttpModel
 * @projectName XHttp
 * @description: 请求参数包装类
 * @date 2020/5/2619:08
 */
@Data
@Accessors(chain = true)
public class XHttpModel implements Serializable {

    private static String HTTP="http://";

    private static String HTTPS="https://";

    /**
     * 域名
     */
    private String domain;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 请求类型
     */
    private Method methodType;

    /**
     * 请求参数
     */
    private List<XHttpParam> paramList;

    /**
     * 请求头
     */
    private Map<String,String>  header=new HashMap<>(1);

    /**
     * httpRequest
     */
    private HttpRequest httpRequest;

    /**
     * httpResponse
     */
    private HttpResponse httpResponse;

    /**
     * 返回内容
     */
    private String responseBody;
    /**
     * 请求体
     */
    private String requestBody;

}
