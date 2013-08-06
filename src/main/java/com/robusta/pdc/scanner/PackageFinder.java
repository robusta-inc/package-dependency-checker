package com.robusta.pdc.scanner;

import com.robusta.pdc.PackageNames;
import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;

import java.util.Collection;

public interface PackageFinder {
    Collection<SourceFolderPackage> find(SourceFolder sourceFolder, PackageNames packageNames);
}
