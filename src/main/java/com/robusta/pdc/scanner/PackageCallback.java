package com.robusta.pdc.scanner;

import com.robusta.pdc.SourceFolderPackage;

public interface PackageCallback {
    void doWithPackage(SourceFolderPackage aPackage);
}
