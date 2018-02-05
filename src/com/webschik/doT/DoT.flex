package com.webschik.doT;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.webschik.doT.psi.DoTTypes;
import com.intellij.psi.TokenType;

%%

%class DoTLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

WHITE_SPACE=[\ \n\t\f]
KEY_CHARACTER=[^\ \n\t\f\\] | "\\ "

%state WAITING_VALUE

%%

<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return DoTTypes.KEY; }

<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }