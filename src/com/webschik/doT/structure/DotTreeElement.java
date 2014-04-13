package com.webschik.doT.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.psi.PsiElement;
import com.intellij.util.ReflectionCache;
import com.webschik.doT.psi.DotPsiElement;
import com.webschik.doT.psi.DotStatements;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class DotTreeElement extends PsiTreeElementBase<DotPsiElement> {

    private final DotPsiElement myElement;

    private DotTreeElement(DotPsiElement psiElement) {
        super(psiElement);
        myElement = psiElement;
    }

    @NotNull
    @Override
    public Collection<StructureViewTreeElement> getChildrenBase() {
        return getStructureViewTreeElements(myElement);
    }

    static List<StructureViewTreeElement> getStructureViewTreeElements(PsiElement psiElement) {
        List<StructureViewTreeElement> children = new ArrayList<StructureViewTreeElement>();
        for (PsiElement childElement : psiElement.getChildren()) {
            if (!(childElement instanceof DotPsiElement)) {
                continue;
            }

            if (childElement instanceof DotStatements) {
                // DotStatments elements transparently wrap other elements, so we don't add
                // this element to the tree, but we add its children
                children.addAll(new DotTreeElement((DotPsiElement) childElement).getChildrenBase());
            }

            for (Class suitableClass : DotStructureViewModel.ourSuitableClasses) {
                if (ReflectionCache.isAssignable(suitableClass, childElement.getClass())) {
                    children.add(new DotTreeElement((DotPsiElement) childElement));
                    break;
                }
            }
        }
        return children;
    }

    @Nullable
    @Override
    public String getPresentableText() {
        return myElement.getName();
    }

    @Override
    public Icon getIcon(boolean open) {
        return myElement.getIcon(0);
    }
}
