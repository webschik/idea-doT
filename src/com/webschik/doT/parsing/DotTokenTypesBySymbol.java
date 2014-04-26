package com.webschik.doT.parsing;

import com.intellij.psi.tree.IElementType;

import java.util.HashMap;

public class DotTokenTypesBySymbol {
    public static final HashMap<Character, IElementType> types = new HashMap<Character, IElementType>();
    public static final HashMap<Character, IElementType> nonClosesTypes = new HashMap<Character, IElementType>();

    static {
        types.put('!', DotTokenTypes.OPEN_ESCAPED);
        types.put('=', DotTokenTypes.OPEN_UNESCAPED);
        types.put('?', DotTokenTypes.CONDITIONAL);
        types.put('~', DotTokenTypes.ITERATION);
        types.put('#', DotTokenTypes.OPEN_PARTIAL);

        nonClosesTypes.put('!', DotTokenTypes.OPEN_ESCAPED);
        nonClosesTypes.put('=', DotTokenTypes.OPEN_UNESCAPED);
        nonClosesTypes.put('#', DotTokenTypes.OPEN_PARTIAL);
    }
}
