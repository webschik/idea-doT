package com.webschik.doT.psi;

import com.intellij.psi.PsiElement;

/**
 * Base for all Handlebars elements
 */
public interface DotPsiElement extends PsiElement {
    String getName();
}
