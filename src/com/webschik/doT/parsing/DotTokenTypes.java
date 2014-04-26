package com.webschik.doT.parsing;

import com.intellij.psi.tree.IElementType;

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


    public static final IElementType OUTER_ELEMENT_TYPE = new DotElementType("Dot_FRAGMENT", "Dot.parsing.element.expected.outer_element_type");

    public static final IElementType COMMENT = new DotElementType("COMMENT", "Dot.parsing.element.expected.comment");
    public static final IElementType OPEN_BLOCK = new DotElementType("OPEN_BLOCK", "Dot.parsing.element.expected.open_block");
    public static final IElementType OPEN_ENDBLOCK = new DotElementType("OPEN_ENDBLOCK", "Dot.parsing.element.expected.open_end_block");
    public static final IElementType OPEN_INVERSE = new DotElementType("OPEN_INVERSE", "Dot.parsing.element.expected.open_inverse");
    public static final IElementType ID = new DotElementType("ID", "Dot.parsing.element.expected.id");
    public static final IElementType ELSE = new DotElementType("ELSE", "");
}