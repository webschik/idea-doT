package com.webschik.doT.parsing;

import com.intellij.psi.tree.IElementType;
import com.webschik.doT.DotBundle;
import com.webschik.doT.DotLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

class DotElementType extends IElementType {
    private final String parseExpectedMessageKey;

    /**
     * @param messageKey Key to the {@link DotBundle} message to show the user when the parser
     *                             expected this token, but found something else.
     */
    public DotElementType(@NotNull @NonNls String debugName, @NotNull @NonNls String messageKey) {
        super(debugName, DotLanguage.INSTANCE);
        parseExpectedMessageKey = messageKey;
    }

    @Override
    public String toString() {
        return "[Dot] " + super.toString();
    }

    public String parseExpectedMessage() {
        return parseExpectedMessageKey;
    }
}