package com.webschik.doT.pages;

import com.webschik.doT.DotBundle;
import com.webschik.doT.DotLanguage;
import com.webschik.doT.config.DotConfig;
import com.intellij.ide.ui.ListCellRendererWrapper;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DotConfigurationPage implements SearchableConfigurable {
    private JPanel myWholePanel;

    @NotNull
    @Override
    public String getId() {
        return "editor.preferences.handlebarsOptions";
    }

    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return DotBundle.message("doT.pages.options.title");
    }

    // this  can probably be deleted eventually; IDEA 11 expects it to be here
    @SuppressWarnings ({"UnusedDeclaration", "SameReturnValue"})
    public Icon getIcon() {
        return null;
    }

    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public JComponent createComponent() {
        return myWholePanel;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }

    @Override
    public void reset() {

    }

    @Override
    public void disposeUIResources() {
    }
}
