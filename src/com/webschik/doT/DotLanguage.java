package com.webschik.doT;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.psi.templateLanguages.TemplateLanguage;

public class DotLanguage extends Language implements TemplateLanguage {
    public static final DotLanguage INSTANCE = new DotLanguage();

    // ideally this would be public static, but the static inits in the tests get cranky when we do that
    @SuppressWarnings ("SameReturnValue")
    public static LanguageFileType getDefaultTemplateLang() {
        return StdFileTypes.HTML;
    }

    private DotLanguage() {
        super("Dot", "text/x-dot-template");
    }
}
