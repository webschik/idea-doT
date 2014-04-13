package com.webschik.doT.psi.impl;

import com.intellij.lang.ASTNode;
import com.webschik.doT.psi.DotData;
import org.jetbrains.annotations.NotNull;

public class DotDataImpl extends DotPsiElementImpl implements DotData {
    public DotDataImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }

    @Override
    public String getName() {
        return getText();
    }
}
