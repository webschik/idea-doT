package com.webschik.doT.psi.impl;

import com.webschik.doT.DotIcons;
import com.webschik.doT.parsing.DotTokenTypes;
import com.webschik.doT.psi.DotSimpleInverse;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

public class DotSimpleInverseImpl extends DotPsiElementImpl implements DotSimpleInverse {
    public DotSimpleInverseImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }

    @Override
    public String getName() {
        ASTNode elseNode = getElseNode();
        if (elseNode != null) {
            return elseNode.getText();
        }
        return ""; // no name for "{{^}}" expressions
    }

    @Nullable
    @Override
    public Icon getIcon(@IconFlags int flags) {
        return DotIcons.OPEN_INVERSE;
    }

    /**
     * If this element was created from an "{{else}}" expression, it will have an {@link DotTokenTypes#ELSE} child.
     * Otherwise, it was created from "{{^}}"
     *
     * @return the {@link DotTokenTypes#ELSE} element if it exists, null otherwise
     */
    private ASTNode getElseNode() {
        ASTNode[] elseChildren = getNode().getChildren(TokenSet.create(DotTokenTypes.ELSE));
        if (elseChildren != null && elseChildren.length > 0) {
            return elseChildren[0];
        }
        return null;
    }
}
