package com.xdl.ui;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import com.xdl.model.DataCenter;
import com.xdl.model.Row;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *@program:Mark
 *
 *@description:创建内容
 *
 *@author:胡博欣
 *
 *@create:2020-05-23-51
 */
public class OutContent extends DialogWrapper {

    private EditorTextField title;

    private EditorTextField content;

    public OutContent() {
        super(true);
        setTitle("创建标题");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel jPanel = new JPanel();
        title = new EditorTextField("笔记标题");
        JLabel titleLabel = new JLabel("笔记标题");
        JLabel contentLabel = new JLabel("笔记内容");
        title = new EditorTextField("笔记标题");
        content = new EditorTextField("笔记内容");
        title.setPreferredSize(new Dimension(200, 30));
        content.setPreferredSize(new Dimension(200, 100));
        JPanel jPanel1 = new JPanel();
        jPanel1.add(titleLabel);
        jPanel1.add(title);
        JPanel jPanel2 = new JPanel();
        jPanel2.add(contentLabel);
        jPanel2.add(content);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(3);
        jPanel.add(jPanel1);
        jPanel.add(jPanel2);
        jPanel.setLayout(gridLayout);
        return jPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel jPanel = new JPanel();
        JButton jButton = new JButton("添加笔记");
        jButton.addActionListener(e -> {
            String title1 = title.getText();
            String comment = content.getText();
            String fileName = DataCenter.FILE_NAME;
            String substring = fileName.substring(fileName.lastIndexOf(".") + 1);
            Row java = new Row(title1, comment, fileName, substring, DataCenter.TEXT);
            List<Row> list = DataCenter.LIST;
            list.add(java);
            DataCenter.tableModel.addRow(DataCenter.convert(java));
            this.close(1);
        });
        jPanel.add(jButton);
        return jPanel;
    }



}
