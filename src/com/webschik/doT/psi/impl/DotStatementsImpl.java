package com.webschik.doT.psi.impl;

import com.webschik.doT.psi.DotStatements;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class DotStatementsImpl extends DotPsiElementImpl implements DotStatements {
    public DotStatementsImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }
}
