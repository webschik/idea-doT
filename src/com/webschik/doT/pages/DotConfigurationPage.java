package com.webschik.doT.pages;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.webschik.doT.DotBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

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
