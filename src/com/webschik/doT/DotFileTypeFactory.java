package com.webschik.doT;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class DotFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(DotFileType.INSTANCE, "dot");
    }
}
