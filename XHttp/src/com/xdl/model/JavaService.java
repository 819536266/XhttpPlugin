package com.xdl.model;

import com.google.common.base.Optional;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.util.CommonProcessors;
import com.intellij.util.Processor;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * @author yanglin
 */
public class JavaService {

    private Project project;

    private JavaPsiFacade javaPsiFacade;


    public JavaService(Project project) {
        this.project = project;
        this.javaPsiFacade = JavaPsiFacade.getInstance(project);
    }

    public static JavaService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, JavaService.class);
    }

    public Optional<PsiClass> getReferenceClazzOfPsiField(@NotNull PsiElement field) {
        if (!(field instanceof PsiField)) {
            return Optional.absent();
        }
        PsiType type = ((PsiField) field).getType();
        return type instanceof PsiClassReferenceType ? Optional.fromNullable(((PsiClassReferenceType) type).resolve()) : Optional.<PsiClass>absent();
    }

    public Optional<DomElement> findStatement(@Nullable PsiMethod method) {
        CommonProcessors.FindFirstProcessor<DomElement> processor = new CommonProcessors.FindFirstProcessor<DomElement>();
        process(method, processor);
        return processor.isFound() ? Optional.fromNullable(processor.getFoundValue()) : Optional.<DomElement>absent();
    }

    @SuppressWarnings("unchecked")
    public void process(@NotNull PsiClass clazz, @NotNull Processor processor) {
        String ns = clazz.getQualifiedName();
        System.out.println("类+:::::::::::"+clazz);
        System.out.println("process  :"+ns);
        processor.process(clazz.getContext());
    }


    @SuppressWarnings("unchecked")
    public void process(@NotNull PsiMethod psiMethod, @NotNull Processor processor) {
        PsiClass psiClass = psiMethod.getContainingClass();
        System.out.println("方法+:::::::::::"+psiClass);
        PsiCodeBlock body = psiMethod.getBody();
        System.out.println("body: "+body);
        PsiAnnotation[] annotations = psiMethod.getAnnotations();
        System.out.println("注解  :"+Arrays.toString(annotations));
        System.out.println("参数列表  :"+psiMethod.getParameterList().getText());
        String id = psiClass.getQualifiedName() + "." + psiMethod.getName();
        PsiElement context = psiMethod.getContext();
        processor.process(context);
        System.out.println("id:"+id);
    }

    public void process(@NotNull PsiElement target, @NotNull Processor processor) {
        if (target instanceof PsiMethod) {
            System.out.println("方法  :");
            process((PsiMethod) target, processor);
        } else if (target instanceof PsiClass) {
            System.out.println("类  :");
            process((PsiClass) target, processor);
        }
    }

    public <T> Optional<T> findWithFindFirstProcessor(@NotNull PsiElement target) {
        CommonProcessors.FindFirstProcessor<T> processor = new CommonProcessors.FindFirstProcessor<T>();
        process(target, processor);
        return Optional.fromNullable(processor.getFoundValue());
    }


}

