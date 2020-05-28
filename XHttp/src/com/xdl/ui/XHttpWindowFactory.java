package com.xdl.ui;

import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
/**
 * @author huboxin
 * @title: XHttpWindowFactory
 * @projectName XHttp
 * @description:
 * @date 2020/5/2614:29
 */
public class XHttpWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        //创建出NoteListWindow对象
        XHttpUi xHttpUi = new XHttpUi(project,toolWindow);
        //获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        //获取用于toolWindow显示的内容
        Content content = contentFactory.createContent(xHttpUi.getParentPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
