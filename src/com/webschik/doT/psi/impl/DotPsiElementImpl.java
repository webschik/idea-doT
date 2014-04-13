package com.webschik.doT.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProviders;
import com.webschik.doT.psi.DotPsiElement;
import org.jetbrains.annotations.NotNull;

public class DotPsiElementImpl extends ASTWrapperPsiElement implements DotPsiElement {
    public DotPsiElementImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }

    @Override
    public ItemPresentation getPresentation() {
        return ItemPresentationProviders.getItemPresentation(this);
    }
}
