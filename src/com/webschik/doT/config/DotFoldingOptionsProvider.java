package com.webschik.doT.config;

import com.intellij.application.options.editor.CodeFoldingOptionsProvider;
import com.intellij.openapi.options.BeanConfigurable;
import com.webschik.doT.DotBundle;

public class DotFoldingOptionsProvider
        extends BeanConfigurable<DotFoldingOptionsProvider.DotCodeFoldingOptionsBean> implements CodeFoldingOptionsProvider {

    @SuppressWarnings ("UnusedDeclaration") // the properties in this class are accessed using reflection by the parent
    public static class DotCodeFoldingOptionsBean {

        public boolean isAutoCollapseBlocks() {
            return DotConfig.isAutoCollapseBlocksEnabled();
        }

        public void setAutoCollapseBlocks(boolean value) {
            DotConfig.setAutoCollapseBlocks(value);
        }
    }

    public DotFoldingOptionsProvider() {
        super(new DotCodeFoldingOptionsBean());

        checkBox("autoCollapseBlocks", DotBundle.message("doT.pages.folding.auto.collapse.blocks"));
    }
}