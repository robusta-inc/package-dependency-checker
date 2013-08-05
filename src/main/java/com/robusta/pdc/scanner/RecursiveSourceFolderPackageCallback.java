package com.robusta.pdc.scanner;

import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class RecursiveSourceFolderPackageCallback implements SourceFolderPackageCallback {
    public static final FileFilter DIRECTORY_FILTER = new FileFilter() {
        @Override
        public boolean accept(File pathName) {
            return pathName.isDirectory();
        }
    };
    private final List<SourceFolderPackage> sourceFolderPackages;

    public RecursiveSourceFolderPackageCallback(List<SourceFolderPackage> sourceFolderPackages) {
        this.sourceFolderPackages = sourceFolderPackages;
    }

    @Override
    public void doWithSourceFolderPackage(SourceFolder sourceFolder, File packageDirectory) {
        sourceFolderPackages.add(new SourceFolderPackage(sourceFolder, packageDirectory));
        for (File subPackage : packageDirectory.listFiles(DIRECTORY_FILTER)) {
            doWithSourceFolderPackage(sourceFolder, subPackage);
        }
    }
}
