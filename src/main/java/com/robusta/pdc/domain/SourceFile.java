package com.robusta.pdc.domain;

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

    @Override
    public String toString() {
        return "SourceFile{" +
                "sourceFolderPackage=" + sourceFolderPackage +
                ", sourceFile=" + sourceFile +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SourceFile that = (SourceFile) o;

        if (sourceFile != null ? !sourceFile.equals(that.sourceFile) : that.sourceFile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sourceFile != null ? sourceFile.hashCode() : 0;
    }

    public File file() {
        return sourceFile;
    }

    public String javaFileNameInDotNotation() {
        String filePath = sourceFile.getAbsolutePath();
        String sourceFolderPath = sourceFolder.getAbsolutePath();
        return filePath
                .replace(sourceFolderPath, "")
                .replace(File.separatorChar, '.')
                .replaceFirst("^\\.", "");
    }
}
