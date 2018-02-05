// This is a generated file. Not intended for manual editing.
package com.webschik.doT.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.webschik.doT.psi.DoTTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.webschik.doT.psi.*;

public class DoTPropertyImpl extends ASTWrapperPsiElement implements DoTProperty {

  public DoTPropertyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DoTVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DoTVisitor) accept((DoTVisitor)visitor);
    else super.accept(visitor);
  }

}
