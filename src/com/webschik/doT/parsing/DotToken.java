package com.webschik.doT.parsing;

import com.intellij.psi.tree.IElementType;

public class DotToken {
    private IElementType type;
    public int tokenStart;
    public int tokenEnd;

    DotToken(IElementType type, int tokenStart, int tokenEnd) {
        this.type = type;
        this.tokenStart = tokenStart;
        this.tokenEnd = tokenEnd;
    }

    public IElementType getType() {
        return type;
    }

    public void setType(IElementType type) {
        this.type = type;
    }

    public int getTokenStart() {
        return tokenStart;
    }

    public int getTokenEnd() {
        return tokenEnd;
    }

    public void increment() {
        tokenEnd++;
    }

    public void invalidate(IElementType errorType) {
        setType(errorType);
    }

    public String toString() {
        return type + "[" + tokenStart + ":" + tokenEnd + "]";
    }
}
