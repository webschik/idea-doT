package com.webschik.doT.parsing;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import com.webschik.doT.parsing.DotTokenTypes;
import com.webschik.doT.config.DotConfig;

// suppress various warnings/inspections for the generated class
@SuppressWarnings ({"FieldCanBeLocal", "UnusedDeclaration", "UnusedAssignment", "AccessStaticViaInstance", "MismatchedReadAndWriteOfArray", "WeakerAccess", "SameParameterValue", "CanBeFinal", "SameReturnValue", "RedundantThrows", "ConstantConditions"})
%%

%class _DotLexer
%implements FlexLexer
%final
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

%{
    private Stack<Integer> stack = new Stack<Integer>();

    public void yypushState(int newState) {
      stack.push(yystate());
      yybegin(newState);
    }

    public void yypopState() {
      yybegin(stack.pop());
    }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]


%state mu
%state emu
%state par
%state comment
%state data

%%

<YYINITIAL> {

~"{{" {
          // backtrack over any stache characters at the end of this string
          while (yylength() > 0 && yytext().subSequence(yylength() - 1, yylength()).toString().equals("{")) {
            yypushback(1);
          }

          if (yylength() > 0 && yytext().toString().substring(yylength() - 1, yylength()).equals("\\")) {
            yypushback(1); // put the escape char back
            yypushState(emu);
          } else {
            yypushState(mu);
          }

          if (!yytext().toString().equals("")) {
              if (yytext().toString().trim().length() == 0) {
                  return DotTokenTypes.WHITE_SPACE;
              } else {
                  return DotTokenTypes.CONTENT;
              }
          }
        }

  // Check for anything that is not a string containing "{{"; that's CONTENT
  !([^]*"{{"[^]*)                         { return DotTokenTypes.CONTENT; }
}

<emu> {
    "\\" { return DotTokenTypes.ESCAPE_CHAR; }
    "{{"~"{{" { // grab everything up to the next open stache
          // backtrack over any stache characters at the end of this string
          while (yylength() > 0 && yytext().subSequence(yylength() - 1, yylength()).toString().equals("{")) {
            yypushback(1);
          }

          if (yylength() > 0 && yytext().toString().substring(yylength() - 1, yylength()).equals("\\")) {
            yypushback(1);
          } else {
            yypopState();
          }

          return DotTokenTypes.CONTENT;
    }
    "{{"!([^]*"{{"[^]*) {
        return DotTokenTypes.CONTENT;
    }
}

<mu> {

  "{{##" { yypushState(par); return DotTokenTypes.OPEN_PARTIAL; }
  "{{?" { return DotTokenTypes.OPEN_BLOCK; }
  "{{^" { return DotTokenTypes.OPEN_INVERSE; }
  "{{=" { return DotTokenTypes.OPEN_UNESCAPED; }
  "{{/*" { yypushback(3); yypopState(); yypushState(comment); }
  "{{" { return DotTokenTypes.OPEN; }
  "==" { return DotTokenTypes.EQUALS; }
  "."/["}"\t \n\x0B\f\r] { return DotTokenTypes.ID; }
  ".." { return DotTokenTypes.ID; }
  [\/.] { return DotTokenTypes.SEP; }
  [\t \n\x0B\f\r]* { return DotTokenTypes.WHITE_SPACE; }
  "}}" { yypopState(); return DotTokenTypes.CLOSE; }
  "~}}" { yypopState(); return DotTokenTypes.CLOSE; }
  "?}}" { yypopState(); return DotTokenTypes.CLOSE; }
  "#}}" { yypopState(); return DotTokenTypes.CLOSE; }
  \"([^\"\\]|\\.)*\" { return DotTokenTypes.STRING; }
  '([^'\\]|\\.)*' { return DotTokenTypes.STRING; }
  "it." { yypushState(data); return DotTokenTypes.DATA_PREFIX; }
  "??"/["}"\t \n\x0B\f\r] { return DotTokenTypes.ELSE; }
  "true"/["}"\t \n\x0B\f\r] { return DotTokenTypes.BOOLEAN; }
  "false"/["}"\t \n\x0B\f\r] { return DotTokenTypes.BOOLEAN; }
  \-?[0-9]+/[}\t \n\x0B\f\r]  { return DotTokenTypes.INTEGER; }
  [a-zA-Z0-9_$-():]+/[=}\t \n\x0B\f\r\/.] { return DotTokenTypes.ID; }
  "["[^\]]*"]" { return DotTokenTypes.ID; }
}

<par> {
    [a-zA-Z0-9_$-/]+ { yypopState(); return DotTokenTypes.PARTIAL_NAME; }
}

<comment> {
  "{{/*"~"*/}}" { yypopState(); return DotTokenTypes.COMMENT; }
  "{{/"[^"*"]~"*/}}" {
      // backtrack over any extra stache characters at the end of this string
      while (yylength() > 2 && yytext().subSequence(yylength() - 3, yylength()).toString().equals("}}")) {
        yypushback(1);
      }
      yypopState();
      return DotTokenTypes.COMMENT;
  }
  // lex unclosed comments so that we can give better errors
  "{{/*"!([^]*"*/}}"[^]*) { yypopState(); return DotTokenTypes.UNCLOSED_COMMENT; }
  "{{/"[^"*"]!([^]~"}}"[^]*) { yypopState(); return DotTokenTypes.UNCLOSED_COMMENT; }
}

<data> {
  [a-zA-Z0-9$():]+ { yypopState(); return DotTokenTypes.DATA; }
  "}}" { yypushback(2); yypopState(); } // stop looking for data id when we hit a close stache
}

{WhiteSpace}+ { return DotTokenTypes.WHITE_SPACE; }
. { return DotTokenTypes.INVALID; }