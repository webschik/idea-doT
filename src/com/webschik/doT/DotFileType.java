package com.webschik.doT;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class DotFileType extends LanguageFileType {
    public static final DotFileType INSTANCE = new DotFileType();

    private DotFileType() {
        super(DotLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Dot file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Dot template file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "dot";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return DotIcons.FILE;
    }
}
