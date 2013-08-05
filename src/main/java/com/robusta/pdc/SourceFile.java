package com.robusta.pdc;

import java.io.File;

public class SourceFile {
    private final SourceFolder sourceFolder;
    private final SourceFolderPackage sourceFolderPackage;
    private final File sourceFile;

    public SourceFile(SourceFolder sourceFolder, SourceFolderPackage sourceFolderPackage, File sourceFile) {
        this.sourceFolder = sourceFolder;
        this.sourceFolderPackage = sourceFolderPackage;
        this.sourceFile = sourceFile;
    }
}
