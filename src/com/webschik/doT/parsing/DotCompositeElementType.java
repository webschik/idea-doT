package com.webschik.doT.parsing;

import com.webschik.doT.DotLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Distinct interface to distinguish the leaf elements we get from the lexer from the synthetic
 * composite elements we create in the parser
 */
class DotCompositeElementType extends IElementType {
    public DotCompositeElementType(@NotNull @NonNls String debugName) {
        super(debugName, DotLanguage.INSTANCE);
    }
}