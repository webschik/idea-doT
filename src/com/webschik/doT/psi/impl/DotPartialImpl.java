package com.webschik.doT.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.util.PsiTreeUtil;
import com.webschik.doT.DotIcons;
import com.webschik.doT.psi.DotPartial;
import com.webschik.doT.psi.DotPartialName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DotPartialImpl extends DotPsiElementImpl implements  DotPartial {
    public DotPartialImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }

    @Override
    public String getName() {
        DotPartialName partialName = PsiTreeUtil.findChildOfType(this, DotPartialName.class);
        return partialName == null ? null : partialName.getName();
    }

    @Nullable
    @Override
    public Icon getIcon(@Iconable.IconFlags int flags) {
        return DotIcons.OPEN_PARTIAL;
    }
}
