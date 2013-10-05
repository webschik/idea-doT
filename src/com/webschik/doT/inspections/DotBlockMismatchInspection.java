package com.webschik.doT.inspections;

import com.webschik.doT.DotBundle;
import com.webschik.doT.psi.DotPath;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class DotBlockMismatchInspection implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

    }
}