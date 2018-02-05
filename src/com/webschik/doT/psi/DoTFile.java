package com.webschik.doT.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.webschik.doT.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DoTFile extends PsiFileBase {
    public DoTFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, DoTLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return DoTFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "doT.js file";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
