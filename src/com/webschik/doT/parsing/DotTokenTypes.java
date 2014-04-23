package com.webschik.doT.parsing;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.webschik.doT.DotLanguage;

public class DotTokenTypes {

    /**
     * private constructor since this is a constants class
     */
    private DotTokenTypes() {}

    public static final IElementType OPEN = new DotElementType("OPEN", "Dot.parsing.element.expected.open");
    public static final IElementType CLOSE = new DotElementType("CLOSE", "Dot.parsing.element.expected.close");
    public static final IElementType CONTENT = new DotElementType("CONTENT", "Dot.parsing.element.expected.content");
    public static final IElementType DATA_PREFIX = new DotElementType("DATA_PREFIX", "Dot.parsing.element.expected.data_prefix");
    public static final IElementType OPEN_UNESCAPED = new DotElementType("OPEN_UNESCAPED", "Dot.parsing.element.expected.open_unescaped");
    public static final IElementType OPEN_ESCAPED = new DotElementType("OPEN_ESCAPED", "Dot.parsing.element.expected.open_escaped");
    public static final IElementType DATA = new DotElementType("DATA", "Dot.parsing.element.expected.data");
    public static final IElementType CONDITIONAL = new DotElementType("CONDITIONAL", "Dot.parsing.element.expected.conditional");
    public static final IElementType ITERATION = new DotElementType("ITERATION", "Dot.parsing.element.expected.iteration");
    public static final IElementType OPEN_PARTIAL = new DotElementType("OPEN_PARTIAL", "Dot.parsing.element.expected.open_partial");
    public static final IElementType OPEN_DEFINE = new DotElementType("OPEN_DEFINE", "Dot.parsing.element.expected.open_define");
    public static final IElementType CLOSE_DEFINE = new DotElementType("CLOSE_DEFINE", "Dot.parsing.element.expected.close_define");


    public static final IElementType BLOCK_WRAPPER = new DotCompositeElementType("BLOCK_WRAPPER"); // used to delineate blocks in the PSI tree. The formatter requires this extra structure.
    public static final IElementType OPEN_BLOCK_STACHE = new DotCompositeElementType("OPEN_BLOCK_STACHE");
    public static final IElementType OPEN_INVERSE_BLOCK_STACHE = new DotCompositeElementType("OPEN_INVERSE_BLOCK_STACHE");
    public static final IElementType CLOSE_BLOCK_STACHE = new DotCompositeElementType("CLOSE_BLOCK_STACHE");
    public static final IElementType PATH = new DotCompositeElementType("PATH");
    public static final IElementType PARAM = new DotCompositeElementType("PARAM");
    public static final IElementType PARTIAL_STACHE = new DotCompositeElementType("PARTIAL_STACHE");
    public static final IElementType SIMPLE_INVERSE = new DotCompositeElementType("SIMPLE_INVERSE");
    public static final IElementType STATEMENTS = new DotCompositeElementType("STATEMENTS");

    public static final IElementType OUTER_ELEMENT_TYPE = new DotElementType("Dot_FRAGMENT", "Dot.parsing.element.expected.outer_element_type");

    public static final IElementType WHITE_SPACE = new DotElementType("WHITE_SPACE", "Dot.parsing.element.expected.white_space");
    public static final IElementType COMMENT = new DotElementType("COMMENT", "Dot.parsing.element.expected.comment");
    public static final IElementType UNCLOSED_COMMENT = new DotElementType("UNCLOSED_COMMENT", "");

    public static final IElementType OPEN_BLOCK = new DotElementType("OPEN_BLOCK", "Dot.parsing.element.expected.open_block");
    public static final IElementType OPEN_ENDBLOCK = new DotElementType("OPEN_ENDBLOCK", "Dot.parsing.element.expected.open_end_block");
    public static final IElementType OPEN_INVERSE = new DotElementType("OPEN_INVERSE", "Dot.parsing.element.expected.open_inverse");
    public static final IElementType EQUALS = new DotElementType("EQUALS", "Dot.parsing.element.expected.equals");
    public static final IElementType ID = new DotElementType("ID", "Dot.parsing.element.expected.id");
    public static final IElementType PARTIAL_NAME = new DotElementType("PARTIAL_NAME", "Dot.parsing.element.expected.partial.name");
    public static final IElementType SEP = new DotElementType("SEP", "Dot.parsing.element.expected.separator");
    public static final IElementType ELSE = new DotElementType("ELSE", "");
    public static final IElementType BOOLEAN = new DotElementType("BOOLEAN", "Dot.parsing.element.expected.boolean");
    public static final IElementType INTEGER = new DotElementType("INTEGER", "Dot.parsing.element.expected.integer");
    public static final IElementType STRING = new DotElementType("STRING", "Dot.parsing.element.expected.string");
    public static final IElementType ESCAPE_CHAR = new DotElementType("ESCAPE_CHAR", "");
    public static final IElementType INVALID = new DotElementType("INVALID", "Dot.parsing.element.expected.invalid");

    public static final IFileElementType FILE = new IFileElementType("FILE", DotLanguage.INSTANCE);

    public static final TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(COMMENT);
    public static final TokenSet STRING_LITERALS = TokenSet.create(STRING);
}