package com.webschik.doT.psi.impl;

import com.webschik.doT.psi.DotParam;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class DotParamImpl extends DotPsiElementImpl implements DotParam {
    public DotParamImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }
}
