package com.webschik.doT.psi;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;

public class DotPsiUtil {
    /**
     * Used to determine if an element is part of an "open tag" (i.e. "{{#open}}" or "{{^openInverse}}")
     * @return An ancestor of type {@link DotPsiElement} or null if none exists
     */
    public static DotPsiElement findParentOpenTagElement(PsiElement element) {
        return (DotPsiElement) PsiTreeUtil.findFirstParent(element, true, new Condition<PsiElement>() {
            @Override
            public boolean value(PsiElement element) {
                return element != null;
            }
        });
    }

    /**
     * Used to determine if an element is part of a "close tag" (i.e. "{{/closer}}")
     * @return An ancestor of type {@link DotPsiElement} or null if none exists
     */
    public static DotPsiElement findParentCloseTagElement(PsiElement element) {
        return (DotPsiElement) PsiTreeUtil.findFirstParent(element, true, new Condition<PsiElement>() {
            @Override
            public boolean value(PsiElement element) {
                return element != null;
            }
        });
    }

    /**
     * Tests to see if the given element is not the "root" statements expression of the grammar
     */
    public static boolean isNonRootStatementsElement(PsiElement element) {
        PsiElement statementsParent = PsiTreeUtil.findFirstParent(element, true, new Condition<PsiElement>() {
            @Override
            public boolean value(PsiElement element) {
                return element != null
                        && element instanceof DotStatements;
            }
        });

        // we're a non-root statements if we're of type statements, and we have a statements parent
        return element instanceof DotStatements
                && statementsParent != null;
    }
}
