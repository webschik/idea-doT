package com.webschik.doT.structure;

import com.webschik.doT.psi.DotPsiFile;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

class DotTreeElementFile extends PsiTreeElementBase<DotPsiFile> {

    private final DotPsiFile myFile;

    public DotTreeElementFile(@NotNull DotPsiFile psiFile) {
        super(psiFile);
        this.myFile = psiFile;
    }

    @NotNull
    @Override
    public Collection<StructureViewTreeElement> getChildrenBase() {
        return DotTreeElement.getStructureViewTreeElements(myFile);
    }

    @Nullable
    @Override
    public String getPresentableText() {
        return myFile.getName();
    }
}
