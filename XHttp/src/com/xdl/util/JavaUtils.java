package com.xdl.util;

import com.google.common.base.Optional;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PropertyUtil;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanglin
 */
public final class JavaUtils {

    private JavaUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean isModelClazz(@Nullable PsiClass clazz) {
        return null != clazz && !clazz.isAnnotationType() && !clazz.isInterface() && !clazz.isEnum() && clazz.isValid();
    }

    @NotNull
    public static Optional<PsiField> findSettablePsiField(
            @NotNull final PsiClass clazz,
            @Nullable final String propertyName) {
        final PsiField field = PropertyUtil.findPropertyField(clazz, propertyName, false);
        return field != null ? Optional.of(field) : Optional.absent();
    }

    @NotNull
    public static PsiField[] findSettablePsiFields(final @NotNull PsiClass clazz) {
        final PsiField[] fields = clazz.getAllFields();
        final List<PsiField> settableFields = new ArrayList<>(fields.length);

        for (final PsiField f : fields) {
            final PsiModifierList modifiers = f.getModifierList();

            if (modifiers != null && (
                    modifiers.hasModifierProperty(PsiModifier.STATIC) ||
                    modifiers.hasModifierProperty(PsiModifier.FINAL))) {
                continue;
            }

            settableFields.add(f);
        }

        return settableFields.toArray(new PsiField[0]);
    }

    public static boolean isElementWithinInterface(@Nullable PsiElement element) {
        if (element instanceof PsiClass && ((PsiClass) element).isInterface()) {
            return true;
        }
        PsiClass type = PsiTreeUtil.getParentOfType(element, PsiClass.class);
        return Optional.fromNullable(type).isPresent() && type.isInterface();
    }

    @NotNull
    public static Optional<PsiClass> findClazz(@NotNull Project project, @NotNull String clazzName) {
        return Optional.fromNullable(JavaPsiFacade.getInstance(project).findClass(clazzName, GlobalSearchScope.allScope(project)));
    }

    @NotNull
    public static Optional<PsiMethod> findMethod(@NotNull Project project, @Nullable String clazzName, @Nullable String methodName) {
        if (StringUtils.isBlank(clazzName) && StringUtils.isBlank(methodName)) {
            return Optional.absent();
        }
        Optional<PsiClass> clazz = findClazz(project, clazzName);
        if (clazz.isPresent()) {
            PsiMethod[] methods = clazz.get().findMethodsByName(methodName, true);
            return ArrayUtils.isEmpty(methods) ? Optional.<PsiMethod>absent() : Optional.of(methods[0]);
        }
        return Optional.absent();
    }


}
