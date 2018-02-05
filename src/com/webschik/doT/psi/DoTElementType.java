package com.webschik.doT.psi;

import com.intellij.psi.tree.IElementType;
import com.webschik.doT.DoTLanguage;
import org.jetbrains.annotations.*;

public class DoTElementType extends IElementType {
    public DoTElementType(@NotNull @NonNls String debugName) {
        super(debugName, DoTLanguage.INSTANCE);
    }
}
