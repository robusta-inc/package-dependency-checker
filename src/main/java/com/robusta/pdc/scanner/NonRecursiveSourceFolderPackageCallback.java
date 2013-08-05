package com.robusta.pdc.scanner;

import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;
import com.robusta.pdc.scanner.SourceFolderPackageCallback;

import java.io.File;
import java.util.List;

public class NonRecursiveSourceFolderPackageCallback implements SourceFolderPackageCallback {
    private final List<SourceFolderPackage> sourceFolderPackages;

    public NonRecursiveSourceFolderPackageCallback(List<SourceFolderPackage> sourceFolderPackages) {
        this.sourceFolderPackages = sourceFolderPackages;
    }

    @Override
    public void doWithSourceFolderPackage(SourceFolder sourceFolder, File packageDirectory) {
        sourceFolderPackages.add(new SourceFolderPackage(sourceFolder, packageDirectory));
    }
}
