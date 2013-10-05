package com.webschik.doT.file;

import com.webschik.doT.DotBundle;
import com.webschik.doT.DotIcons;
import com.webschik.doT.DotLanguage;
import com.webschik.doT.DotTemplateHighlighter;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.EditorHighlighterProvider;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeEditorHighlighterProviders;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.TemplateLanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.nio.charset.Charset;

public class DotFileType extends LanguageFileType implements TemplateLanguageFileType {
    public static final LanguageFileType INSTANCE = new DotFileType();

    @NonNls
    public static final String DEFAULT_EXTENSION = "dot";

    private DotFileType() {
        super(DotLanguage.INSTANCE);

        FileTypeEditorHighlighterProviders.INSTANCE.addExplicitExtension(this, new EditorHighlighterProvider() {
            public EditorHighlighter getEditorHighlighter(@Nullable Project project,
                                                          @NotNull FileType fileType,
                                                          @Nullable VirtualFile virtualFile,
                                                          @NotNull EditorColorsScheme editorColorsScheme) {
                return new DotTemplateHighlighter(project, virtualFile, editorColorsScheme);
            }
        });
    }

    @NotNull
    public String getName() {
        return "doT";
    }

    @NotNull
    public String getDescription() {
        return DotBundle.message("doT.files.file.type.description");
    }

    @NotNull
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    public Icon getIcon() {
        return DotIcons.FILE_ICON;
    }

    public Charset extractCharsetFromFileContent(@Nullable final Project project, @Nullable final VirtualFile file, @NotNull final String content) {
        LanguageFileType associatedFileType = getAssociatedFileType(file, project);

        if (associatedFileType == null) {
            return null;
        }

        return associatedFileType.extractCharsetFromFileContent(project, file, content);
    }

    private LanguageFileType getAssociatedFileType(VirtualFile file, Project project) {
        if (project == null) {
            return null;
        }
        Language language = TemplateDataLanguageMappings.getInstance(project).getMapping(file);

        LanguageFileType associatedFileType = null;
        if (language != null) {
            associatedFileType = language.getAssociatedFileType();
        }

        if (language == null || associatedFileType == null) {
            associatedFileType = DotLanguage.getDefaultTemplateLang();
        }
        return associatedFileType;
    }
}