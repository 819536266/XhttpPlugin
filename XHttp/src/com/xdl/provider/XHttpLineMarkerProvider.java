package com.xdl.provider;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.Method;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor;
import com.intellij.execution.ExecutionBundle;
import com.intellij.execution.lineMarker.RunLineMarkerContributor.Info;
import com.intellij.icons.AllIcons;
import com.intellij.lang.jvm.annotation.JvmAnnotationAttribute;
import com.intellij.lang.jvm.annotation.JvmAnnotationConstantValue;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Separator;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.editor.markup.MarkupEditorFilter;
import com.intellij.openapi.editor.markup.MarkupEditorFilterFactory;
import com.intellij.psi.*;
import com.intellij.util.Function;
import com.xdl.action.XHttpAction;
import com.xdl.model.SpringRequestMethodAnnotation;
import com.xdl.util.Icons;
import com.xdl.util.SpringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 15290
 */
public class XHttpLineMarkerProvider extends LineMarkerProviderDescriptor {


    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (!(element instanceof PsiMethod)) return null;
        PsiMethod psiMethod = (PsiMethod) element;
        // System.out.println("类内容  :"+psiMethod.getContext().getText()); //类内容
        // System.out.println("注释内容  :"+psiMethod.getDocComment().getText()); //注释内容
//         System.out.println("方法全部内容  :"+psiMethod.getOriginalElement().getText()); //方法全部内容
        //System.out.println("内容  :"+psiMethod.getParameterList().getText());  (@PathVariable(value = "materialId") Integer materialId, @RequestBody List<Competing> competingList)
        // System.out.println("类内容  :"+psiMethod.getBody().getText()); //方法内容 不带方法名
        List<XHttpAction> list = CollUtil.newArrayList();
        for (SpringRequestMethodAnnotation methodType : SpringUtils.METHOD_TYPE) {
            PsiAnnotation annotation = psiMethod.getAnnotation(methodType.getQualifiedName());
            if(ObjectUtil.isNotEmpty(annotation)){
                XHttpAction xHttpAction = new XHttpAction(psiMethod, methodType, SpringUtils.getMethodText(methodType.getMethod()),"发个请求吧!", Icons.getMethodIcon(methodType.getMethod()));
                list.add(xHttpAction);
            }
        }
        if(CollUtil.isEmpty(list)){
            return null;
        }
        DefaultActionGroup actionGroup = new DefaultActionGroup();
        actionGroup.addAll(list);
        // System.out.println("+++"+getMapping.getText());  +++@PostMapping("resetCompeting/{materialId}")
        // System.out.println("+++----"+getMapping.getQualifiedName());  +++----org.springframework.web.bind.annotation.PostMapping
        // parameterList.getText(); System.out.println("+++"+text);  +++("resetCompeting/{materialId}")
        // System.out.println(value.getText()); //"resetCompeting/{materialId}"
//            JvmAnnotationConstantValue attributeValue = (JvmAnnotationConstantValue) attribute.getAttributeValue();
            //System.out.println("----"+attribute.getAttributeName()+"--"+attributeValue.getConstantValue());  // ----value--resetCompeting/{materialId}
        return new RunLineMarkerInfo(psiMethod.getIdentifyingElement(), AllIcons.RunConfigurations.TestState.Run, t->"发送请求", actionGroup);
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<PsiElement> list, @NotNull Collection<LineMarkerInfo> collection) {

    }

    static class RunLineMarkerInfo extends LineMarkerInfo<PsiElement> {
        private final DefaultActionGroup myActionGroup;

        RunLineMarkerInfo(PsiElement element, Icon icon, Function<PsiElement, String> tooltipProvider,
                          DefaultActionGroup actionGroup) {
            super(element, element.getTextRange(), icon, 0,tooltipProvider, null, GutterIconRenderer.Alignment.CENTER);
            myActionGroup = actionGroup;
        }

        @Override
        public GutterIconRenderer createGutterRenderer() {
            return new LineMarkerGutterIconRenderer<PsiElement>(this) {
                @Override
                public AnAction getClickAction() {
                    return null;
                }

                @Override
                public boolean isNavigateAction() {
                    return true;
                }

                @Override
                public ActionGroup getPopupMenuActions() {
                    return myActionGroup;
                }
            };
        }

        @NotNull
        @Override
        public MarkupEditorFilter getEditorFilter() {
            return MarkupEditorFilterFactory.createIsNotDiffFilter();
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "";
    }

}
