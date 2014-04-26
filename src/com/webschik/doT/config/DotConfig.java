package com.webschik.doT.config;

import com.intellij.ide.util.PropertiesComponent;

import static com.webschik.doT.config.Property.*;

public class DotConfig {

    public static boolean isAutoGenerateCloseTagEnabled() {
        return getBooleanPropertyValue(AUTO_GENERATE_CLOSE_TAG);
    }

    public static void setAutoGenerateCloseTagEnabled(boolean enabled) {
        setBooleanPropertyValue(AUTO_GENERATE_CLOSE_TAG, enabled);
    }

    public static boolean isFormattingEnabled() {
        return getBooleanPropertyValue(FORMATTER);
    }

    public static void setFormattingEnabled(boolean enabled) {
        setBooleanPropertyValue(FORMATTER, enabled);
    }

    public static boolean isAutoCollapseBlocksEnabled() {
        return getBooleanPropertyValue(AUTO_COLLAPSE_BLOCKS);
    }

    public static void setAutoCollapseBlocks(boolean enabled) {
        setBooleanPropertyValue(AUTO_COLLAPSE_BLOCKS, enabled);
    }

    private static String getStringPropertyValue(Property property) {
        return new PropertyAccessor(PropertiesComponent.getInstance())
                .getPropertyValue(property);
    }

    private static void setStringPropertyValue(Property property, String value) {
        new PropertyAccessor(PropertiesComponent.getInstance())
                .setPropertyValue(property, value);
    }

    private static boolean getBooleanPropertyValue(Property property) {
        return ENABLED.equals(getStringPropertyValue(property));
    }

    private static void setBooleanPropertyValue(Property property, boolean enabled) {
        setStringPropertyValue(property, enabled ? ENABLED : DISABLED);
    }
}