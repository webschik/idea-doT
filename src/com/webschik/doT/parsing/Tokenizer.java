package com.webschik.doT.parsing;

import com.intellij.psi.tree.IElementType;

public interface Tokenizer {

    int getTokenStart(int index);

    int getTokenEnd(int index);

    IElementType getTokenType(int index);

    void resetSequence(CharSequence buffer);

}
