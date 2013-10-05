package com.webschik.doT.psi.impl;

import com.webschik.doT.psi.DotBlockWrapper;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

public class DotBlockWrapperImpl extends DotPsiElementImpl implements DotBlockWrapper {
    public DotBlockWrapperImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }
}
