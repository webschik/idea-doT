package com.webschik.doT.config;

import com.intellij.lang.html.HTMLLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * Formalizes the properties which we will persist using {@link com.intellij.ide.util.PropertiesComponent}
 */
enum Property {
    AUTO_GENERATE_CLOSE_TAG {
        @NotNull
        @Override
        public String getStringName() {
            // please excuse the "disabled" in this name.  This is an artifact from an earlier approach
            //      to properties, which we keep for backwards compatibility
            return "DotDisableAutoGenerateCloseTag";
        }

        @NotNull
        @Override
        public String getDefault() {
            return ENABLED;
        }
    },

    VARNAME {
        @NotNull
        @Override
        public String getStringName() {
            return VNAME;
        }

        @NotNull
        @Override
        public String getDefault() {
            return VNAME;
        }
    },

    FORMATTER {
        @NotNull
        @Override
        public String getStringName() {
            return "DotFormatter";
        }

        @NotNull
        @Override
        public String getDefault() {
            return ENABLED;
        }
    },

    AUTO_COLLAPSE_BLOCKS {
        @NotNull
        @Override
        public String getStringName() {
            return "DotAutoCollapseBlocks";
        }

        @NotNull
        @Override
        public String getDefault() {
            return DISABLED;
        }
    },

    COMMENTER_LANGUAGE_ID {
        @NotNull
        @Override
        public String getStringName() {
            return "DotCommenterLanguageId";
        }

        @NotNull
        @Override
        public String getDefault() {
            return HTMLLanguage.INSTANCE.getID();
        }
    };

    public static final String ENABLED = "enabled";
    public static final String DISABLED = "disabled";
    private static final String VNAME = "it";

    /**
     * The String which will actually be persisted in a user's properties using
     * {@link com.intellij.ide.util.PropertiesComponent}.
     *
     * This value must be unique amongst Property entries
     *
     * IMPORTANT: these should probably never change so that we don't lose a user's preferences between releases.
     */
    @NotNull
    public abstract String getStringName();

    /**
     * The default/initial value for a user
     */
    @NotNull
    public abstract String getDefault();
}