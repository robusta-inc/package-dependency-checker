package com.robusta.pdc;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class SourceFolderPackage {
    public static final FilenameFilter JAVA_SOURCE_FILE_NAME_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".java");
        }
    };
    private final SourceFolder sourceFolder;
    private final File packageDirectory;

    public SourceFolderPackage(SourceFolder sourceFolder, File packageDirectory) {
        this.sourceFolder = sourceFolder;
        this.packageDirectory = packageDirectory;
    }

    public Iterable<SourceFile> javaSourceFiles() {
        List<SourceFile> sourceFiles = newArrayList();
        File[] files = packageDirectory.listFiles(JAVA_SOURCE_FILE_NAME_FILTER);
        for (File sourceFile : files) {
            sourceFiles.add(new SourceFile(sourceFolder, this, sourceFile));
        }
        return sourceFiles;
    }

    @Override
    public String toString() {
        return "SourceFolderPackage{" +
                "sourceFolder=" + sourceFolder +
                ", packageDirectory=" + packageDirectory +
                '}';
    }
}
