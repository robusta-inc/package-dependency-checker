package com.robusta.pdc.scanner;

import com.robusta.pdc.SourceFile;
import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;

public class PackageScannerImpl implements PackageScanner {
    private final
    @Override
    public void scan(SourceFolderPackage aPackage) {
        for (SourceFile sourceFile : aPackage.javaSourceFiles()) {

        }
    }
}
