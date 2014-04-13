package com.webschik.doT.parsing;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class DotLexer extends FlexAdapter {
    public DotLexer() {
        super(new _DotLexer((Reader) null));
    }

}