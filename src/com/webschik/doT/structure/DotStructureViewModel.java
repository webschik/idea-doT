package com.webschik.doT.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.webschik.doT.psi.DotBlockWrapper;
import com.webschik.doT.psi.DotPsiFile;
import org.jetbrains.annotations.NotNull;

class DotStructureViewModel extends TextEditorBasedStructureViewModel {

    private final DotPsiFile myFile;
    // classes which we construct structure view nodes for
    static final Class [] ourSuitableClasses = new Class[] { DotBlockWrapper.class};

    public DotStructureViewModel(@NotNull DotPsiFile psiFile) {
        super(psiFile);
        this.myFile = psiFile;
    }

    @NotNull
    @Override
    protected Class[] getSuitableClasses() {
        return ourSuitableClasses;
    }

    @NotNull
    @Override
    public StructureViewTreeElement getRoot() {
        return new DotTreeElementFile(myFile);
    }
}
