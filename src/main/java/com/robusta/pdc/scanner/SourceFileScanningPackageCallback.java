package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.SourceFile;
import com.robusta.pdc.domain.SourceFolderPackage;

public class SourceFileScanningPackageCallback implements PackageCallback {
    private final SourceFileScanner sourceFileScanner;

    public SourceFileScanningPackageCallback(SourceFileScanner sourceFileScanner) {
        this.sourceFileScanner = sourceFileScanner;
    }

    @Override
    public void doWithPackage(SourceFolderPackage aPackage) {
        Iterable<SourceFile> sourceFiles = aPackage.javaSourceFiles();
        for (SourceFile sourceFile : sourceFiles) {
            sourceFileScanner.scan(sourceFile);
        }
    }
}
