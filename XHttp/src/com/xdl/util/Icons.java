/*
  Copyright (C), 2018-2020, ZhangYuanSheng
  FileName: Icons
  Author:   ZhangYuanSheng
  Date:     2020/5/6 10:39
  Description:
  History:
  <author>          <time>          <version>          <desc>
  作者姓名            修改时间           版本号              描述
 */
package com.xdl.util;

import cn.hutool.http.Method;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.net.URL;

/**
 * @author hbx
 * @version 1.0
 */
public class Icons {

    public static final Icon SERVICE = load("/icons/service.png");

    public static final Icon METHOD_UNDEFINED = load("/icons/method/undefined.png");

    public static final Icon METHOD_GET = load("/icons/method/GET.png");
    public static final Icon METHOD_POST = load("/icons/method/POST.png");
    public static final Icon METHOD_DELETE = load("/icons/method/DELETE.png");
    public static final Icon METHOD_PUT = load("/icons/method/PUT.png");
    public static final Icon METHOD_PATCH = load("/icons/method/PATCH.png");

    @NotNull
    public static Icon load(@NotNull String path) {
        URL resource = Icons.class.getResource(path);
        return new ImageIcon(resource);
    }

    /**
     * 获取方法对应的图标
     *
     * @param method 请求类型
     * @return icon
     */
    @NotNull
    public static Icon getMethodIcon(Method method) {
        if (method == null) {
            return Icons.METHOD_UNDEFINED;
        }
        switch (method) {
            case GET:
                return Icons.METHOD_GET;
            case POST:
                return Icons.METHOD_POST;
            case DELETE:
                return Icons.METHOD_DELETE;
            case PUT:
                return Icons.METHOD_PUT;
            case PATCH:
                return Icons.METHOD_PATCH;
            default:
                return Icons.METHOD_UNDEFINED;
        }
    }


    /**
     * 获取方法对应的图标
     *
     * @param method 请求类型
     * @return icon
     */
    @NotNull
    public static Icon getMethodIcon(String method) {
        if (method == null) {
            return Icons.METHOD_UNDEFINED;
        }
        switch (method) {
            case SpringUtils.GET_MAPPING:
                return Icons.METHOD_GET;
            case SpringUtils.POST_MAPPING:
                return Icons.METHOD_POST;
            case SpringUtils.DELETE_MAPPING:
                return Icons.METHOD_DELETE;
            case SpringUtils.PUT_MAPPING:
                return Icons.METHOD_PUT;
            case SpringUtils.PATCH_MAPPING:
                return Icons.METHOD_PATCH;
            default:
                return Icons.METHOD_UNDEFINED;
        }
    }
}
