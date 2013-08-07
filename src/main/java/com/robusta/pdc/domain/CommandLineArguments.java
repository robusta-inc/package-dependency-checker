package com.robusta.pdc.domain;

public class CommandLineArguments {
    private final SourceFolders sourceFolders;
    private final PackageNames packageNames;
    private final AllowedPackages allowedPackages;
    private final boolean verbose;

    public CommandLineArguments(SourceFolders sourceFolders, PackageNames packageNames, AllowedPackages allowedPackages, boolean verbose) {
        this.sourceFolders = sourceFolders;
        this.packageNames = packageNames;
        this.allowedPackages = allowedPackages;
        this.verbose = verbose;
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

    public boolean isVerbose() {
        return verbose;
    }
}
