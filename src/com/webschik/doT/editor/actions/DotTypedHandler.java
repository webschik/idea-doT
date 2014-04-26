package com.webschik.doT.editor.actions;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.webschik.doT.DotLanguage;
import com.webschik.doT.config.DotConfig;
import com.webschik.doT.parsing.DotTokenTypesBySymbol;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Handler for custom plugin actions on chars typed by the user.  See {@link DotEnterHandler} for custom actions
 * on Enter.
 */
public class DotTypedHandler extends TypedHandlerDelegate {
    private int openedBracesCount = 0;
    private HashMap<Character, IElementType> typesBySymbol = DotTokenTypesBySymbol.types;
    private HashMap<Character, IElementType> nonClosesTypes = DotTokenTypesBySymbol.nonClosesTypes;
    private char openBrace = '{';
    private char closeBrace = '}';

    @Override
    public Result beforeCharTyped(char c, Project project, Editor editor, PsiFile file, FileType fileType) {
        int offset = editor.getCaretModel().getOffset();

        if (offset == 0 || offset > editor.getDocument().getTextLength()) {
            return Result.CONTINUE;
        }
        String text = editor.getDocument().getText(new TextRange(offset - 1, offset));
        char previousChar = text.length() > 0 ? text.charAt(0) : 0;

        PsiDocumentManager.getInstance(project).commitAllDocuments();

        // we suppress the built-in "}" auto-complete when we see "{{"
        if (c == openBrace && previousChar == c) {
            openedBracesCount++;

            // since the "}" autocomplete is built in to IDEA, we need to hack around it a bit by
            // intercepting it before it is inserted, doing the work of inserting for the user
            // by inserting the '{' the user just typed...
            editor.getDocument().insertString(offset, Character.toString(c));
            // ... and position their caret after it as they'd expect...
            editor.getCaretModel().moveToOffset(offset + 1);

            // ... then finally telling subsequent responses to this charTyped to do nothing
            return Result.STOP;
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

        String text = editor.getDocument().getText(new TextRange(offset - 2, offset - 1));
        char previousChar = text.length() > 0 ? text.charAt(0) : 0;

        if (c == closeBrace && previousChar == c) {
            openedBracesCount--;
            autoInsertCloseTag(project, offset, editor, provider);
        }

        return Result.CONTINUE;
    }

    /**
     * When appropriate, auto-inserts doT close tags.  i.e.
     * When "{{? statements}}", {{?}} is automatically inserted
     */
    private void autoInsertCloseTag(Project project, int offset, Editor editor, FileViewProvider provider) {
        String text;
        char ch,
            pr,
            prv,
            next;
        int i, len;

        if (!DotConfig.isAutoGenerateCloseTagEnabled()) {
            return;
        }

        PsiDocumentManager.getInstance(project).commitAllDocuments();

        PsiElement element = provider.findElementAt(offset - 1, DotLanguage.class);

        if (element != null) {
            text = element.getText();
            len = text.length();

            // without last "}}" and with "{{" on begin (start from index = 2)
            for (i = offset - 3; i >= 2; i--) {
                ch = text.charAt(i);
                pr = text.charAt(i - 1);
                prv = text.charAt(i - 2);
                next = i + 1 < len ? text.charAt(i + 1) : 0;

                if (typesBySymbol.containsKey(ch)) {
                    if (
                        !nonClosesTypes.containsKey(ch) &&
                        (ch != '#' || pr != ch) &&
                        (ch != '?' || next != closeBrace)
                    ) {
                        // insert the corresponding close tag
                        editor.getDocument().insertString(offset, "{{" + ch + "}}");
                    }

                    break;
                }
            }
        }
    }
}