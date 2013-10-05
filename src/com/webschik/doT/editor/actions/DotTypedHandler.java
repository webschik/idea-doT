package com.webschik.doT.editor.actions;

import com.webschik.doT.DotLanguage;
import com.webschik.doT.config.DotConfig;
import com.webschik.doT.file.DotFileViewProvider;
import com.webschik.doT.psi.DotPath;
import com.webschik.doT.psi.DotPsiElement;
import com.webschik.doT.psi.DotPsiUtil;
import com.webschik.doT.psi.DotSimpleInverse;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Handler for custom plugin actions on chars typed by the user.  See {@link DotEnterHandler} for custom actions
 * on Enter.
 */
public class DotTypedHandler extends TypedHandlerDelegate {
    @Override
    public Result beforeCharTyped(char c, Project project, Editor editor, PsiFile file, FileType fileType) {
        int offset = editor.getCaretModel().getOffset();

        if (offset == 0 || offset > editor.getDocument().getTextLength()) {
            return Result.CONTINUE;
        }

        String previousChar = editor.getDocument().getText(new TextRange(offset - 1, offset));

        if (file.getViewProvider() instanceof DotFileViewProvider) {
            PsiDocumentManager.getInstance(project).commitAllDocuments();

            // we suppress the built-in "}" auto-complete when we see "{{"
            if (c == '{' && previousChar.equals("{")) {
                // since the "}" autocomplete is built in to IDEA, we need to hack around it a bit by
                // intercepting it before it is inserted, doing the work of inserting for the user
                // by inserting the '{' the user just typed...
                editor.getDocument().insertString(offset, Character.toString(c));
                // ... and position their caret after it as they'd expect...
                editor.getCaretModel().moveToOffset(offset + 1);

                // ... then finally telling subsequent responses to this charTyped to do nothing
                return Result.STOP;
            }
        }

        return Result.CONTINUE;
    }

    @Override
    public Result charTyped(char c, Project project, Editor editor, @NotNull PsiFile file) {
        int offset = editor.getCaretModel().getOffset();
        FileViewProvider provider = file.getViewProvider();

        if (offset < 2 || offset > editor.getDocument().getTextLength()) {
            return Result.CONTINUE;
        }

        String previousChar = editor.getDocument().getText(new TextRange(offset - 2, offset - 1));

        if (file.getViewProvider() instanceof DotFileViewProvider) {
            // if we're looking at a close stache, we may have some business too attend to
            if (c == '}' && previousChar.equals("}")) {
                autoInsertCloseTag(project, offset, editor, provider);
            }
        }

        return Result.CONTINUE;
    }

    /**
     * When appropriate, auto-inserts Handlebars close tags.  i.e.  When "{{#tagId}}" or "{{^tagId}} is typed,
     *      {{/tagId}} is automatically inserted
     */
    private void autoInsertCloseTag(Project project, int offset, Editor editor, FileViewProvider provider) {
        if (!DotConfig.isAutoGenerateCloseTagEnabled()) {
            return;
        }

        PsiDocumentManager.getInstance(project).commitAllDocuments();

        PsiElement elementAtCaret = provider.findElementAt(offset - 1, DotLanguage.class);

        PsiElement openTag = DotPsiUtil.findParentOpenTagElement(elementAtCaret);

        if (openTag != null && openTag.getChildren().length > 1) {
            // we've got an open block type stache... find its "name" (its first path element)
            DotPsiElement pathElem = (DotPsiElement) openTag.getChildren()[1];

            if (pathElem != null && pathElem instanceof DotPath) {
                // insert the corresponding close tag
                editor.getDocument().insertString(offset, "{{/" + pathElem.getText() + "}}");
            }
        }
    }
}