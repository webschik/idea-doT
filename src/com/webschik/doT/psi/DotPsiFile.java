package com.webschik.doT.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.impl.PsiFileEx;
import com.webschik.doT.DotLanguage;
import com.webschik.doT.file.DotFileType;
import org.jetbrains.annotations.NotNull;

public class DotPsiFile extends PsiFileBase implements PsiFileEx {

    public DotPsiFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, DotLanguage.INSTANCE);
    }

    @NotNull
    public FileType getFileType() {
        return DotFileType.INSTANCE;
    }
}
