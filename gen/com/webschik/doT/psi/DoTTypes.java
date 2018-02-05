// This is a generated file. Not intended for manual editing.
package com.webschik.doT.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.webschik.doT.psi.impl.*;

public interface DoTTypes {

  IElementType PROPERTY = new DoTElementType("PROPERTY");

  IElementType COMMENT = new DoTTokenType("COMMENT");
  IElementType CRLF = new DoTTokenType("CRLF");
  IElementType KEY = new DoTTokenType("KEY");
  IElementType SEPARATOR = new DoTTokenType("SEPARATOR");
  IElementType VALUE = new DoTTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == PROPERTY) {
        return new DoTPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
