package com.webschik.doT.psi.impl;

import com.intellij.lang.ASTNode;
import com.webschik.doT.psi.DotBlockWrapper;
import org.jetbrains.annotations.NotNull;

public class DotBlockWrapperImpl extends DotPsiElementImpl implements DotBlockWrapper {
    public DotBlockWrapperImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }
}
