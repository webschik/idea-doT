package com.webschik.doT.psi.impl;

import com.webschik.doT.psi.DotPath;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class DotPathImpl extends DotPsiElementImpl implements DotPath {
    public DotPathImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }

    @Override
    public String getName() {
        return getText();
    }
}
