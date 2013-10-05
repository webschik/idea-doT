package com.webschik.doT.pages;

import com.webschik.doT.DotBundle;
import com.webschik.doT.DotHighlighter;
import com.webschik.doT.DotIcons;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Map;
import java.util.Set;

public class DotColorsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] ATTRS;

    static {
        ATTRS = new AttributesDescriptor[DotHighlighter.DISPLAY_NAMES.size()];
        Set<TextAttributesKey> textAttributesKeys = DotHighlighter.DISPLAY_NAMES.keySet();
        TextAttributesKey[] keys = textAttributesKeys.toArray(new TextAttributesKey[textAttributesKeys.size()]);
        for (int i = 0; i < keys.length; i++) {
            TextAttributesKey key = keys[i];
            String name = DotHighlighter.DISPLAY_NAMES.get(key).getFirst();
            ATTRS[i] = new AttributesDescriptor(name, key);
        }
    }

    @NotNull
    public String getDisplayName() {
        return DotBundle.message("doT.files.file.type.description");
    }

    public Icon getIcon() {
        return DotIcons.FILE_ICON;
    }

    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        return ATTRS;
    }

    @NotNull
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    public SyntaxHighlighter getHighlighter() {
        return new DotHighlighter();
    }

    @NotNull
    public String getDemoText() {
        return "{{ var a = \"This a simple string\" }}\n" +
               "{{! a }}\n"
               ;
    }

    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }
}