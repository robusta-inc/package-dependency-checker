package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.PackageNames;
import com.robusta.pdc.domain.SourceFolder;
import com.robusta.pdc.domain.SourceFolderPackage;

import java.util.Collection;

public interface PackageFinder {
    Collection<SourceFolderPackage> find(SourceFolder sourceFolder, PackageNames packageNames);
}
