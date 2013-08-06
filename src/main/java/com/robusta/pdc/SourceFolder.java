package com.robusta.pdc;

import java.io.File;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author sudhir.ravindramohan
 */
public class SourceFolder {
    private final File sourceFolder;

    public SourceFolder(String sourceFolderAsString) {
        this.sourceFolder = new File(sourceFolderAsString);
        checkState(sourceFolder.exists(), "The source folder: '%s' does not exist.", sourceFolderAsString);
        checkState(sourceFolder.isDirectory(), "The source folder: '%s' is not a directory", sourceFolderAsString);
        checkState(sourceFolder.canRead(), "The source folder: '%s' is not accessible", sourceFolderAsString);
    }

    @Override
    public String toString() {
        return "SourceFolder{" +
                "sourceFolder=" + sourceFolder +
                '}';
    }

    public String getAbsolutePath() {
        return sourceFolder.getAbsolutePath();
    }
}
