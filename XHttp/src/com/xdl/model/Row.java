package com.xdl.model;

import lombok.Data;

/*
 *@program:Mark
 *
 *@description:列
 *
 *@author:胡博欣
 *
 *@create:2020-05-00-11
 */
@Data
public class Row {

    private String title;
    private String comment;

    private String fileName;

    private String fileType;

    private String content;


    public Row(String title, String comment, String fileName, String fileType, String content) {
        this.title = title;
        this.comment = comment;
        this.fileName = fileName;
        this.fileType = fileType;
        this.content = content;
    }


}
