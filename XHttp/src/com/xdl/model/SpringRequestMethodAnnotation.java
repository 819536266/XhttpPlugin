package com.xdl.model;

import cn.hutool.http.Method;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author ZhangYuanSheng
 */
public enum SpringRequestMethodAnnotation {

    /**
     * RequestMapping
     */
    REQUEST_MAPPING("org.springframework.web.bind.annotation.RequestMapping", null),

    /**
     * GetMapping
     */
    GET_MAPPING("org.springframework.web.bind.annotation.GetMapping", Method.GET),

    /**
     * GetMapping
     */
    GET_MAPPING_NAME("@GetMapping", Method.GET),

    /**
     * PostMapping
     */
    POST_MAPPING("org.springframework.web.bind.annotation.PostMapping", Method.POST),

    /**
     * GetMapping
     */
    POST_MAPPING_NAME("@PostMapping", Method.POST),
    /**
     * PutMapping
     */
    PUT_MAPPING("org.springframework.web.bind.annotation.PutMapping", Method.PUT),

    /**
     * GetMapping
     */
    PUT_MAPPING_NAME("@PutMapping", Method.PUT),
    /**
     * DeleteMapping
     */
    DELETE_MAPPING("org.springframework.web.bind.annotation.DeleteMapping", Method.DELETE),

    /**
     * GetMapping
     */
    DELETE_MAPPING_NAME("@DeleteMapping", Method.DELETE),

    /**
     * PatchMapping
     */
    PATCH_MAPPING("org.springframework.web.bind.annotation.PatchMapping", Method.PATCH),


    /**
     * GetMapping
     */
    PATCH_MAPPING_NAME("@PatchMapping", Method.PATCH),
    /**
     * RequestParam
     */
    REQUEST_PARAM("org.springframework.web.bind.annotation.RequestParam", null),

    /**
     * GetMapping
     */
    REQUEST_PARAM_NAME("@GetMapping",null);


    private final String qualifiedName;
    private final Method method;

    SpringRequestMethodAnnotation(String qualifiedName, Method method) {
        this.qualifiedName = qualifiedName;
        this.method = method;
    }

    @Nullable
    public static SpringRequestMethodAnnotation getByQualifiedName(String qualifiedName) {
        for (SpringRequestMethodAnnotation springRequestAnnotation : SpringRequestMethodAnnotation.values()) {
            if (springRequestAnnotation.getQualifiedName().equals(qualifiedName)) {
                return springRequestAnnotation;
            }
        }
        return null;
    }

    @Nullable
    public static SpringRequestMethodAnnotation getByShortName(String requestMapping) {
        for (SpringRequestMethodAnnotation springRequestAnnotation : SpringRequestMethodAnnotation.values()) {
            if (springRequestAnnotation.getQualifiedName().endsWith(requestMapping)) {
                return springRequestAnnotation;
            }
        }
        return null;
    }

    public Method getMethod() {
        return this.method;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    @NotNull
    public String getShortName() {
        return qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
    }
}
