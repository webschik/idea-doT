package com.webschik.doT;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.tree.IElementType;
import com.webschik.doT.parsing.DotLexer;
import com.webschik.doT.parsing.DotTokenTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* @author max
*/
public class DotHighlighter extends SyntaxHighlighterBase {
    private static final Map<IElementType, TextAttributesKey> keys1;
    private static final Map<IElementType, TextAttributesKey> keys2;

    @Nullable
    public Lexer getHighlightingLexer() {
        return new DotLexer();
    }

    private static final TextAttributesKey IDENTIFIERS = TextAttributesKey.createTextAttributesKey(
            "DOT.IDENTIFIERS",
            SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
    );

    private static final TextAttributesKey COMMENTS = TextAttributesKey.createTextAttributesKey(
            "DOT.COMMENTS",
            SyntaxHighlighterColors.DOC_COMMENT.getDefaultAttributes()
    );

    private static final TextAttributesKey OPERATORS = TextAttributesKey.createTextAttributesKey(
            "DOT.OPERATORS",
            SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
    );

    private static final TextAttributesKey VALUES = TextAttributesKey.createTextAttributesKey(
            "DOT.VALUES",
            SyntaxHighlighterColors.NUMBER.getDefaultAttributes()
    );

    private static final TextAttributesKey STRINGS = TextAttributesKey.createTextAttributesKey(
            "DOT.STRINGS",
            SyntaxHighlighterColors.STRING.getDefaultAttributes()
    );

    private static final TextAttributesKey DATA_PREFIX = TextAttributesKey.createTextAttributesKey(
            "DOT.DATA_PREFIX",
            SyntaxHighlighterColors.NUMBER.getDefaultAttributes()
    );

    private static final TextAttributesKey DATA = TextAttributesKey.createTextAttributesKey(
            "DOT.DATA",
            SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
    );

    @SuppressWarnings ("UseJBColor") // TODO port to JBColor when we stop supporting IDEA 11
    private static final TextAttributesKey ESCAPE = TextAttributesKey.createTextAttributesKey(
            "DOT.ESCAPE",
            new TextAttributes(Color.BLUE, null, null, null, 0)
    );

    static {
        keys1 = new HashMap<IElementType, TextAttributesKey>();
        keys2 = new HashMap<IElementType, TextAttributesKey>();

        keys1.put(DotTokenTypes.ID, IDENTIFIERS);
        keys1.put(DotTokenTypes.PARTIAL_NAME, IDENTIFIERS);
        keys1.put(DotTokenTypes.COMMENT, COMMENTS);
        keys1.put(DotTokenTypes.UNCLOSED_COMMENT, COMMENTS);
        keys1.put(DotTokenTypes.EQUALS, OPERATORS);
        keys1.put(DotTokenTypes.SEP, OPERATORS);
        keys1.put(DotTokenTypes.INTEGER, VALUES);
        keys1.put(DotTokenTypes.ELSE, IDENTIFIERS);
        keys1.put(DotTokenTypes.BOOLEAN, VALUES);
        keys1.put(DotTokenTypes.STRING, STRINGS);
        keys1.put(DotTokenTypes.DATA_PREFIX, DATA_PREFIX);
        keys1.put(DotTokenTypes.DATA, DATA);
        keys1.put(DotTokenTypes.ESCAPE_CHAR, ESCAPE);

    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(keys1.get(tokenType), keys2.get(tokenType));
    }

    public static final Map<TextAttributesKey, Pair<String, HighlightSeverity>> DISPLAY_NAMES
            = new LinkedHashMap<TextAttributesKey, Pair<String, HighlightSeverity>>();
    static {
        DISPLAY_NAMES.put(IDENTIFIERS, new Pair<String, HighlightSeverity>(DotBundle.message("doT.page.colors.descriptor.identifiers.key"),null));
        DISPLAY_NAMES.put(COMMENTS, new Pair<String, HighlightSeverity>(DotBundle.message("doT.page.colors.descriptor.comments.key"),null));
        DISPLAY_NAMES.put(OPERATORS, new Pair<String, HighlightSeverity>(DotBundle.message("doT.page.colors.descriptor.operators.key"),null));
        DISPLAY_NAMES.put(VALUES, new Pair<String, HighlightSeverity>(DotBundle.message("doT.page.colors.descriptor.values.key"),null));
        DISPLAY_NAMES.put(STRINGS, new Pair<String, HighlightSeverity>(DotBundle.message("doT.page.colors.descriptor.strings.key"),null));
        DISPLAY_NAMES.put(DATA_PREFIX, new Pair<String, HighlightSeverity>(DotBundle.message("doT.page.colors.descriptor.data.prefix.key"),null));
        DISPLAY_NAMES.put(DATA, new Pair<String, HighlightSeverity>(DotBundle.message("doT.page.colors.descriptor.data.key"),null));
        DISPLAY_NAMES.put(ESCAPE, new Pair<String, HighlightSeverity>(DotBundle.message("doT.page.colors.descriptor.escape.key"),null));
    }
}