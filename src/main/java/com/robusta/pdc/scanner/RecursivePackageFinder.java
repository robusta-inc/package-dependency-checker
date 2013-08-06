package com.robusta.pdc.scanner;

import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class RecursivePackageFinder extends AbstractDirectoryFindingPackageFinder {
    public static final FileFilter DIRECTORY_FILTER = new FileFilter() {
        @Override
        public boolean accept(File pathName) {
            return pathName.isDirectory();
        }
    };

    @Override
    protected void processFoundPackageDirectory(List<SourceFolderPackage> packages, SourceFolder sourceFolder, File packageDirectory) {
        packages.add(newSourceFolderPackage(sourceFolder, packageDirectory));
        logger.debug("Added source folder package with directory: '{}'", packageDirectory);
        for (File subPackage : packageDirectory.listFiles(DIRECTORY_FILTER)) {
            logger.debug("Recursively finding packages in sub folder: '{}'", subPackage);
            find(packages, sourceFolder, subPackage);
        }
    }
}
