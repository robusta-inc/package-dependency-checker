package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.SourceFolder;
import com.robusta.pdc.domain.SourceFolderPackage;

import java.io.File;
import java.util.List;

class NonRecursivePackageFinder extends AbstractDirectoryFindingPackageFinder {

    @Override
    protected void processFoundPackageDirectory(List<SourceFolderPackage> packages, SourceFolder sourceFolder, File packageDirectory) {
        packages.add(newSourceFolderPackage(sourceFolder, packageDirectory));
    }
}
