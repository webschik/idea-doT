package com.webschik.doT.parsing;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.webschik.doT.psi.DotPsiFile;
import com.webschik.doT.psi.impl.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DotParseDefinition implements ParserDefinition {
    @Nullable
    public Lexer createLexer(Project project) {
        return null;
    }

    @Nullable
    public PsiParser createParser(Project project) {
        return null;
    }

    public IFileElementType getFileNodeType() {
        return DotTokenTypes.FILE;
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return DotTokenTypes.WHITESPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return DotTokenTypes.COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return DotTokenTypes.STRING_LITERALS;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        if (node.getElementType() == DotTokenTypes.BLOCK_WRAPPER) {
            return new DotBlockWrapperImpl(node);
        }

        if (node.getElementType() == DotTokenTypes.PATH) {
            return new DotPathImpl(node);
        }

        if (node.getElementType() == DotTokenTypes.DATA) {
            return new DotDataImpl(node);
        }

        if (node.getElementType() == DotTokenTypes.PARAM) {
            return new DotParamImpl(node);
        }

        if (node.getElementType() == DotTokenTypes.PARTIAL_STACHE) {
            return new DotPartialImpl(node);
        }

        if (node.getElementType() == DotTokenTypes.PARTIAL_NAME) {
            return new DotPartialNameImpl(node);
        }

        if (node.getElementType() == DotTokenTypes.SIMPLE_INVERSE) {
            return new DotSimpleInverseImpl(node);
        }

        if (node.getElementType() == DotTokenTypes.STATEMENTS) {
            return new DotStatementsImpl(node);
        }

        if (node.getElementType() == DotTokenTypes.COMMENT) {
            return new DotCommentImpl(node);
        }

        return new DotPsiElementImpl(node);
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new DotPsiFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}