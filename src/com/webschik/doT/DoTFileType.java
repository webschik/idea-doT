package com.webschik.doT;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class DoTFileType extends LanguageFileType {
    public static final DoTFileType INSTANCE = new DoTFileType();

    private DoTFileType() {
        super(DoTLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "doT.js";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "doT.js template file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "dot";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return DoTIcons.FILE;
    }
}
