package com.robusta.pdc.scanner;

import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;

import java.io.File;
import java.util.List;

public class NonRecursivePackageFinder extends AbstractDirectoryFindingPackageFinder {

    @Override
    protected void processFoundPackageDirectory(List<SourceFolderPackage> packages, SourceFolder sourceFolder, File packageDirectory) {
        packages.add(newSourceFolderPackage(sourceFolder, packageDirectory));
    }
}
