package com.webschik.doT.parsing;

import com.webschik.doT.DotBundle;
import com.webschik.doT.DotLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

class DotElementType extends IElementType {
    private final String _parseExpectedMessageKey;

    /**
     * @param parseExpectedMessageKey Key to the {@link DotBundle} message to show the user when the parser
     *                             expected this token, but found something else.
     */
    public DotElementType(@NotNull @NonNls String debugName, @NotNull @NonNls String parseExpectedMessageKey) {
        super(debugName, DotLanguage.INSTANCE);
        _parseExpectedMessageKey = parseExpectedMessageKey;
    }

    @Override
    public String toString() {
        return "[Dot] " + super.toString();
    }

    public String parseExpectedMessage() {
        return _parseExpectedMessageKey;
    }
}