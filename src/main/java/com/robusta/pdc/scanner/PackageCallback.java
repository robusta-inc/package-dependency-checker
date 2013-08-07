package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.SourceFolderPackage;

public interface PackageCallback {
    void doWithPackage(SourceFolderPackage aPackage);
}
