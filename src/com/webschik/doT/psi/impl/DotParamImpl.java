package com.webschik.doT.psi.impl;

import com.intellij.lang.ASTNode;
import com.webschik.doT.psi.DotParam;
import org.jetbrains.annotations.NotNull;

public class DotParamImpl extends DotPsiElementImpl implements DotParam {
    public DotParamImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }
}
