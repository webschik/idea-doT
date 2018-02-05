package com.webschik.doT;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class DoTLexerAdapter extends FlexAdapter {
    public DoTLexerAdapter() {
        super(new DoTLexer((Reader) null));
    }
}
