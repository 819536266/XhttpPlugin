package com.xdl.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.Method;
import com.xdl.model.SpringRequestMethodAnnotation;
import com.xdl.model.XHttpParam;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author huboxin
 * @title: SpringMathodUtils
 * @projectName XHttp
 * @description:
 * @date 2020/5/278:55
 */
@Data
public class SpringUtils {


    public static final String POST_MAPPING = "@PostMapping";
    public static final String GET_MAPPING = "@GetMapping";
    public static final String DELETE_MAPPING = "@DeleteMapping";
    public static final String PUT_MAPPING = "@PutMapping";
    public static final String PATCH_MAPPING = "@PatchMapping";
    public static final String REQUEST_MAPPING = "@RequestMapping";

    public static final String MAPPING_PARAM_VALUE = "value";

    public static final SpringRequestMethodAnnotation[] METHOD_TYPE = {
            SpringRequestMethodAnnotation.GET_MAPPING,
            SpringRequestMethodAnnotation.POST_MAPPING,
            SpringRequestMethodAnnotation.DELETE_MAPPING,
            SpringRequestMethodAnnotation.PUT_MAPPING,
            SpringRequestMethodAnnotation.PATCH_MAPPING,
    };


    public static String getMethodText(Method method) {
        if (method == null) {
            return REQUEST_MAPPING;
        }
        switch (method) {
            case GET:
                return GET_MAPPING;
            case POST:
                return POST_MAPPING;
            case DELETE:
                return DELETE_MAPPING;
            case PUT:
                return PUT_MAPPING;
            case PATCH:
                return PATCH_MAPPING;
            default:
                return REQUEST_MAPPING;
        }
    }


    /**
     * restful风格路径
     *
     * @param url 原url
     * @param xHttpParams  xHttpParams
     * @return String
     */
    public static String restful(String url, List<XHttpParam> xHttpParams) {
        if (ObjectUtil.isEmpty(url) || CollUtil.isEmpty(xHttpParams)) {
            return "";
        }
        for (XHttpParam xHttpParam : xHttpParams) {
            if (xHttpParam.getIsCheck()) {
                url= url.replace("{" + xHttpParam.getName() + "}", xHttpParam.getValue());
            }
        }
        return url;
    }
}
