package com.webschik.doT.editor.comments;

import com.webschik.doT.DotLanguage;
import com.webschik.doT.config.DotConfig;
import com.intellij.lang.Commenter;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageCommenters;
import org.jetbrains.annotations.Nullable;

public class DotCommenter implements Commenter {

    private static final Commenter ourDotCommenter = new DotJSCommenter();

    @Nullable
    @Override
    public String getLineCommentPrefix() {
        return getCommenter().getLineCommentPrefix();
    }

    @Nullable
    @Override
    public String getBlockCommentPrefix() {
        return getCommenter().getBlockCommentPrefix();
    }

    @Nullable
    @Override
    public String getBlockCommentSuffix() {
        return getCommenter().getBlockCommentSuffix();
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return getCommenter().getCommentedBlockCommentPrefix();
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return getCommenter().getCommentedBlockCommentSuffix();
    }

    private Commenter getCommenter() {
        Language commenterLanguage = DotConfig.getCommenterLanguage();
        if (commenterLanguage == null) {
            commenterLanguage = DotLanguage.getDefaultTemplateLang().getLanguage();
        } else if (commenterLanguage.getID().equals(DotLanguage.INSTANCE.getID())) {
            return ourDotCommenter;
        }

        return LanguageCommenters.INSTANCE.forLanguage(commenterLanguage);
    }
}