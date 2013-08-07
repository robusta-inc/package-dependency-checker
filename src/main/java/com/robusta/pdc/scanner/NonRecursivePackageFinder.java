package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.SourceFolder;
import com.robusta.pdc.domain.SourceFolderPackage;

import java.io.File;
import java.util.List;

/**
 * {@link PackageFinder} implementation that finds the packages
 * in a given source folder and package path, but does not recurse
 * further into the package path subdirectories.
 */
class NonRecursivePackageFinder extends AbstractDirectoryFindingPackageFinder {

    @Override
    protected void processFoundPackageDirectory(List<SourceFolderPackage> packages, SourceFolder sourceFolder, File packageDirectory) {
        packages.add(newSourceFolderPackage(sourceFolder, packageDirectory));
    }
}
