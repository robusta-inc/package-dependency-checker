package com.robusta.pdc.domain;

public class CommandLineArguments {
    private final SourceFolders sourceFolders;
    private final PackageNames packageNames;
    private final AllowedPackages allowedPackages;

    public CommandLineArguments(SourceFolders sourceFolders, PackageNames packageNames, AllowedPackages allowedPackages) {
        this.sourceFolders = sourceFolders;
        this.packageNames = packageNames;
        this.allowedPackages = allowedPackages;
    }

    public SourceFolders sourceFolders() {
        return sourceFolders;
    }

    public PackageNames packageNames() {
        return packageNames;
    }

    public AllowedPackages allowedPackages() {
        return allowedPackages;
    }
}
