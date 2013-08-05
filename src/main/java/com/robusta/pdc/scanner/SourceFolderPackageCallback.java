package com.robusta.pdc.scanner;

import com.robusta.pdc.SourceFolder;

import java.io.File;

public interface SourceFolderPackageCallback {
    void doWithSourceFolderPackage(SourceFolder sourceFolder, File packageDirectory);
}
