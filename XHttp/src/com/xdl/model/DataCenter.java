package com.xdl.model;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

/*
 *@program:Mark
 *
 *@description:
 *
 *@author:胡博欣
 *
 *@create:2020-05-00-19
 */
public class DataCenter {

  public static String  TEXT;

    public static String FILE_NAME;

    public static List<Row> LIST = new LinkedList<>();

    public  static String[] HEAD={"标题","内容","文件名","代码段"};

    public static DefaultTableModel tableModel=new DefaultTableModel(null,HEAD);


    /**
     * 清除列表
     */
    public static void clear(){
        LIST.clear();
        tableModel.setDataVector(null, HEAD);
    }


    public static  String[] convert(Row row){
        String [] s=new String[4];
        s[0]=row.getTitle();
        s[1]=row.getComment();
        s[2]=row.getFileName();
        s[3]=row.getContent();
        return s;
    }
}
