package com.webschik.doT;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class DoTFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(com.webschik.doT.DoTFileType.INSTANCE, "dot");
    }
}
